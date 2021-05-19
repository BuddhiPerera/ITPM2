package lk.sliit.itpmProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.SessionManageBO;

import lk.sliit.itpmProject.demoData.AddSessionDemo;
import lk.sliit.itpmProject.dto.AddSessionDTO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;
import lk.sliit.itpmProject.util.LecturerTM;
import lk.sliit.itpmProject.util.SessionTM;

public class ManagesessionsController implements Initializable {

    public TableView<SessionTM> tblManageSessions;
    public TextField txtManageSessions;
    public ChoiceBox manageSessionsOnAction;

    @FXML
    private AnchorPane root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconLecture;

    @FXML
    private ImageView iconStudent;

    @FXML
    private ImageView iconTimeTable;

    @FXML
    private ImageView iconLocatioj;

    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblManageSessions.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblManageSessions.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("lectureOne"));
        tblManageSessions.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lectureTwo"));
        tblManageSessions.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        tblManageSessions.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblManageSessions.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblManageSessions.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("tagName"));
        AddSessionDemo.id = 0;
        AddSessionDemo.selectLecturer = "";
        AddSessionDemo.selectTag = "";
        AddSessionDemo.selectedLecturer = "";
        AddSessionDemo.selectGroup = "";
        AddSessionDemo.student = 0;
        AddSessionDemo.subjectId = "";
        AddSessionDemo.durationHrs = 0;

        try {
            List<LoadSessionDataDTO> loadSessionDataDTOList = sessionManageBO.loadSessionTable();

            ObservableList<SessionTM> sessionTMS = tblManageSessions.getItems();
            for (LoadSessionDataDTO loadSessionDataDTO : loadSessionDataDTOList) {
                sessionTMS.add(new SessionTM(
                        loadSessionDataDTO.getId(),
                        loadSessionDataDTO.getLectureOne(),
                        loadSessionDataDTO.getLectureTwo(),
                        loadSessionDataDTO.getSubjectCode(),
                        loadSessionDataDTO.getSubjectName(),
                        loadSessionDataDTO.getGroupId(),
                        loadSessionDataDTO.getTagName()
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ObservableList list1 = manageSessionsOnAction.getItems();
        list1.add("Lecture 1");
        list1.add("Lecture 2");
        list1.add("Subject Code");
        list1.add("Subject Name");
        list1.add("Group");

    }
    public void btnRefreshOnAction(ActionEvent actionEvent) {
        tblManageSessions.getItems().clear();
        try {
            List<LoadSessionDataDTO> loadSessionDataDTOList = sessionManageBO.loadSessionTable();

            ObservableList<SessionTM> sessionTMS = tblManageSessions.getItems();
            for (LoadSessionDataDTO loadSessionDataDTO : loadSessionDataDTOList) {
                sessionTMS.add(new SessionTM(
                        loadSessionDataDTO.getId(),
                        loadSessionDataDTO.getLectureOne(),
                        loadSessionDataDTO.getLectureTwo(),
                        loadSessionDataDTO.getSubjectCode(),
                        loadSessionDataDTO.getSubjectName(),
                        loadSessionDataDTO.getGroupId(),
                        loadSessionDataDTO.getTagName()
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void btnSearchOnAction(ActionEvent mouseEvent) throws Exception {
        String category ="";
        category = (String) manageSessionsOnAction.getValue();
        String val =   txtManageSessions.getText();
        int i =-1;
        if(category == null){
            i= -1;

        }
        else if(category.equals("Lecture 1")){
            i=0;
        }else if (category.equals("Lecture 2")){
            i=1;
        }else if (category.equals("Subject Code")){
            i=2;
        }else if (category.equals("Subject Name")){
            i=3;
        }else if (category.equals("Group")){
            i=4;
        }
        if(val.equals("") || i == -1){

            new Alert(Alert.AlertType.ERROR, "Please Add Search Data").show();
        }else {

            tblManageSessions.getItems().clear();
            try {
                List<LoadSessionDataDTO> loadSessionDataDTOList2 = sessionManageBO.loadSessionTableSearch(i, val);
                ObservableList<SessionTM> sessionTMS = tblManageSessions.getItems();
                for (LoadSessionDataDTO loadSessionDataDTO : loadSessionDataDTOList2) {
                    sessionTMS.add(new SessionTM(
                            loadSessionDataDTO.getId(),
                            loadSessionDataDTO.getLectureOne(),
                            loadSessionDataDTO.getLectureTwo(),
                            loadSessionDataDTO.getSubjectCode(),
                            loadSessionDataDTO.getSubjectName(),
                            loadSessionDataDTO.getGroupId(),
                            loadSessionDataDTO.getTagName()

                    ));
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Something Went Wrong").show();
            }
        }
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AddWorkingDaysAndHours.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    @FXML
    void playMouseEnterAnimation(MouseEvent event) {

    }

    @FXML
    void playMouseExitAnimatio(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }

    public void btnOnAction_AddSession(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("../view/Addsession.fxml"));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void tblOrders_OnMouseClicked(MouseEvent mouseEvent) throws IOException {

    }

    public void clickedUpdate(MouseEvent mouseEvent) throws Exception {
        Parent root = null;
        SessionTM selectedOrder = tblManageSessions.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            AddSessionDTO addSessionDTO = sessionManageBO.findAllSessions(selectedOrder.getId());
            AddSessionDemo.id = addSessionDTO.getId();
            AddSessionDemo.selectLecturer = addSessionDTO.getSelectLecture();
            AddSessionDemo.selectTag = addSessionDTO.getSelectTag();
            AddSessionDemo.selectedLecturer = addSessionDTO.getSelectedLecturer();
            AddSessionDemo.selectGroup = addSessionDTO.getSelectGroup();
            AddSessionDemo.student = addSessionDTO.getNoOfStudent();
            AddSessionDemo.subjectId = addSessionDTO.getSelectSubject();
            AddSessionDemo.durationHrs = addSessionDTO.getDurationHrs();


            if (mouseEvent.getClickCount() == 1) {

                root = FXMLLoader.load(this.getClass().getResource("../view/Addsession.fxml"));
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
                primaryStage.show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Select a Row").show();
        }
    }

    public void sessionUpdate(ActionEvent actionEvent) {
    }


    public void btnOnAction_Delete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get() == ButtonType.YES){
            SessionTM selectedItem = tblManageSessions.getSelectionModel().getSelectedItem();
            try{
                sessionManageBO.deleteItem(selectedItem.getId());
                tblManageSessions.getItems().remove(selectedItem);
            }catch(Exception e){
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }
}

