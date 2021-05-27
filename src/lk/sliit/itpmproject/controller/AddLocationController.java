package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.AddLocationsBO;
import lk.sliit.itpmproject.dto.AddLocationsDTO;

import java.io.IOException;
import java.sql.SQLException;

public class AddLocationController {
    @FXML
    JFXButton btnRefresh;
    @FXML
    Button btnStatistics;
    @FXML
    Button btnManageSessionRooms;
    @FXML
    Button btnManage;

    @FXML
    private AnchorPane root;

    @FXML
    RadioButton lHallRadio;

    @FXML
    RadioButton labHallRadio;

    @FXML
    private TextField buildingNameTxt;

    @FXML
    private TextField roomNameTxt;

    @FXML
    private TextField capacityTxt;

    @FXML
    private Button btnClear;

    AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.ADD_LOCATIONS);


    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root1 = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
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


    public void btnSaveOnAction() throws SQLException {
        int maxCode = 0;
        try {
            int lastItemCode = addLocationsBO.getLastLocationId();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
        String buildingName = buildingNameTxt.getText();
        String roomName = roomNameTxt.getText();
        Boolean lectureHall = lHallRadio.selectedProperty().getValue();
        Boolean laboratory = labHallRadio.selectedProperty().getValue();
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

    @FXML
    void btnOnActionStatistics(ActionEvent event) throws IOException {

        Parent root1 = null;

        root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/VisualizingStatistic.fxml"));

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

    public void btnOnActionManage() throws IOException {

        Parent root1 = null;

        root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageLocations.fxml"));

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

    public void btnOnActionClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        buildingNameTxt.setText("");
        capacityTxt.setText("");
        roomNameTxt.setText("");
        lHallRadio.selectedProperty().setValue(false);
        labHallRadio.selectedProperty().setValue(false);
    }

    public void btnOnActionManageSessionRooms() throws IOException {
        Parent root1 = null;

        root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageSessionRooms.fxml"));

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




    public void lHallRadioaction() {
        labHallRadio.setSelected(false);
    }

    public void labHallRadioaction() {
        lHallRadio.setSelected(false);
    }


}
