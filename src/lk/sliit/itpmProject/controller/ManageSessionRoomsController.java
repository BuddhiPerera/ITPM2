package lk.sliit.itpmProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.dto.AddSessionDTO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;

public class ManageSessionRoomsController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea txtSelectedSession;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnClear;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconLecture;

    @FXML
    private ImageView iconStudent;

    @FXML
    private ImageView iconTimeTable;

    @FXML
    private ImageView iconLocation;

    @FXML
    private JFXComboBox<String> cmbSelectSession;

    @FXML
    private JFXComboBox<String> cmbSelectRoom;

    private final AddLocationsBO addLocationBO = BOFactory.getInstance().getBO(BOTypes.AddLocations);
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       txtSelectedSession.setEditable(false);
        try{
            List<AddLocationsDTO> addLocationsDTOList = addLocationBO.findAllLocations();
            ObservableList<String> observableListRooms = cmbSelectRoom.getItems();
            for(AddLocationsDTO addLocationsDTO: addLocationsDTOList){
                observableListRooms.add(addLocationsDTO.getRoomName());
            }
        }catch(Exception e){

        }

        try {
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadSessionTable();
            ObservableList studentTMS2 = cmbSelectSession.getItems();
            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {

                studentTMS2.add(Integer.valueOf(addSessionDTO.getId())+"-"+addSessionDTO.getLectureOne()+" "+
                        addSessionDTO.getSubjectCode()+
                        " "+addSessionDTO.getSubjectName()+" "+addSessionDTO.getGroupId());
            }
        } catch (Exception e) {

        }

    }

    public void btnOnAction_Submit(ActionEvent actionEvent)  {

       String val1 = txtSelectedSession.getText();
       String val2 = cmbSelectRoom.getValue();
        String[] parts = val1.split("-");
        String part1 = parts[0];


        try {
            sessionManageBO.saveRoom(part1, val2);
            txtSelectedSession.clear();
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }

    }

    public void btnOnAction_Clear(ActionEvent actionEvent) {

    }

    public void selectSessionOnAction(ActionEvent actionEvent) {
        String cmb = cmbSelectSession.getValue();
        txtSelectedSession.clear();
        txtSelectedSession.setText(cmb);
    }
}
