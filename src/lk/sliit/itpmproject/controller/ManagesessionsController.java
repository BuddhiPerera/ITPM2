package lk.sliit.itpmproject.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.SessionManageBO;

import lk.sliit.itpmproject.demo_data.AddSessionDemo;
import lk.sliit.itpmproject.dto.AddSessionDTO;
import lk.sliit.itpmproject.dto.LoadSessionDataDTO;
import lk.sliit.itpmproject.util.SessionTM;

public class ManagesessionsController implements Initializable {
    @FXML
     TableView<SessionTM> tblManageSessions;
    @FXML
     TextField txtManageSessions;
    @FXML
    ChoiceBox<String> manageSessionsOnAction;

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

    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.ADD_SESSION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblManageSessions.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblManageSessions.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("lectureOne"));
        tblManageSessions.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lectureTwo"));
        tblManageSessions.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        tblManageSessions.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblManageSessions.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblManageSessions.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("tagName"));
        AddSessionDemo.setId( 0);
        AddSessionDemo.setSelectLecturer("");
        AddSessionDemo.setSelectTag("");
        AddSessionDemo.setSelectedLecturer("");
        AddSessionDemo.setSelectGroup("");
        AddSessionDemo.setStudent(0);
        AddSessionDemo.setSubjectId("");
        AddSessionDemo.setDurationHrs(0);

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
           e.printStackTrace();
        }
        ObservableList<String> list1;
        list1 = manageSessionsOnAction.getItems();
        list1.add("Lecture 1");
        list1.add("Lecture 2");
        list1.add("Subject Code");
        list1.add("Subject Name");
        list1.add("Group");

    }
    public void btnRefreshOnAction( ) {
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
          e.printStackTrace();
        }
    }
    public void btnSearchOnAction( )  {
        String category ="";
        category =  manageSessionsOnAction.getValue();
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
                new Alert(Alert.AlertType.ERROR, "Something Something Went Wrong").show();
            }
        }
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root1 = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                default:
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
                    root1 = fxmlLoader.load();
                    break;
            }

            if (root1 != null) {
                Scene subScene = new Scene(root1);
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



    public void btnOnActionAddSession( ) throws IOException {

        Parent root1 = null;

        root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/Addsession.fxml"));

        if (root1 != null) {
            Scene subScene = new Scene(root1);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }


    public void clickedUpdate(MouseEvent mouseEvent) throws SQLException, IOException {
        Parent root1 = null;
        SessionTM selectedOrder = tblManageSessions.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            AddSessionDTO addSessionDTO = sessionManageBO.findAllSessions(selectedOrder.getId());
            AddSessionDemo.setId( addSessionDTO.getId());
            AddSessionDemo.setSelectLecturer(addSessionDTO.getSelectLecture());
            AddSessionDemo.setSelectTag(addSessionDTO.getSelectTag());
            AddSessionDemo.setSelectedLecturer(addSessionDTO.getSelectedLecturer());
            AddSessionDemo.setSelectGroup(addSessionDTO.getSelectGroup());
            AddSessionDemo.setStudent(addSessionDTO.getNoOfStudent());
            AddSessionDemo.setSubjectId(addSessionDTO.getSelectSubject());
            AddSessionDemo.setDurationHrs( addSessionDTO.getDurationHrs());


            if (mouseEvent.getClickCount() == 1) {

                root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/Addsession.fxml"));
                Scene subScene = new Scene(root1);
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



    public void btnOnActionDelete( ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        boolean dd = buttonType.isPresent();
        if(dd){
        ButtonType cc =buttonType.get();
        if(cc== ButtonType.YES){
            SessionTM selectedItem = tblManageSessions.getSelectionModel().getSelectedItem();
            try{
                sessionManageBO.deleteItem(selectedItem.getId());
                tblManageSessions.getItems().remove(selectedItem);
            }catch(Exception e){
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }}
}

