package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AddLocationController {

    @FXML
    private AnchorPane root1;

    @FXML
    private RadioButton LHallRadio;

    @FXML
    private RadioButton LabHallRadio;

    @FXML
    private TextField buildingNameTxt;

    @FXML
    private TextField roomNameTxt;

    @FXML
    private TextField capacityTxt;

    @FXML
    private Button btnClear;
    AddLocationsBO addLocationsBO= BOFactory.getInstance().getBO(BOTypes.AddLocations);


    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddStudent.fxml"));
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
                Stage primaryStage = (Stage) this.root1.getScene().getWindow();

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

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        int maxCode = 0;
        try{
            int lastItemCode = addLocationsBO.getLastLocationId();
            if(lastItemCode == 0){
                maxCode = 1;
            }
            else{
                maxCode = lastItemCode + 1;
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
        String buildingName = buildingNameTxt.getText();
        String roomName = roomNameTxt.getText();
        Boolean lectureHall = LHallRadio.selectedProperty().getValue();
        Boolean laboratory = LabHallRadio.selectedProperty().getValue();
        String capacity = capacityTxt.getText();

        AddLocationsDTO addLocationsDTO = new AddLocationsDTO(
                maxCode,
                buildingName,
                roomName,
                lectureHall,
                laboratory,
                capacity
                );
        addLocationsBO.saveLocation(addLocationsDTO);
        new Alert(Alert.AlertType.INFORMATION, "Save Successfully").show();
    }


    public void btnOnAction_Manage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("../view/ManageLocations.fxml"));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root1.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }

    }
}
