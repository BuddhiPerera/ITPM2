package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import lk.sliit.itpmproject.business.custom.AddWorkingDaysAndHoursBO;
import lk.sliit.itpmproject.dto.AddWorkingDaysAndHoursDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddWorkingDaysAndHoursController implements Initializable {
    @FXML
    Button btnSessions;

    @FXML
    ImageView iconTimeTable;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClear;

    @FXML
    private Spinner<Integer> noOfWorkSpinner;

    @FXML
    private JFXCheckBox thursdayCB;

    @FXML
    private JFXCheckBox wednesdayCB;

    @FXML
    private JFXCheckBox tuesdayCB;

    @FXML
    private JFXCheckBox mondayCB;

    @FXML
    private JFXCheckBox sundayCB;

    @FXML
    private JFXCheckBox saturdayCB;

    @FXML
    private JFXCheckBox fridayCB;

    @FXML
    private AnchorPane root1;
    @FXML
    private Spinner<Integer> hoursSpinner;

    @FXML
    private Spinner<Integer> minutesSpinner;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconLecture;

    @FXML
    private ImageView iconStudent;

    @FXML
    private ImageView iconLocation;

    private final AddWorkingDaysAndHoursBO workingDaysAndHoursBO = BOFactory.getInstance().getBO(BOTypes.ADD_WORKING_DAYS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 5);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 8);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 30);
        this.noOfWorkSpinner.setValueFactory(spinnerValueFactory1);
        this.hoursSpinner.setValueFactory(spinnerValueFactory2);
        this.minutesSpinner.setValueFactory(spinnerValueFactory3);
        noOfWorkSpinner.setEditable(false);
        hoursSpinner.setEditable(false);
        minutesSpinner.setEditable(false);

        try {
            int i = 0;
            List<AddWorkingDaysAndHoursDTO> workingDaysAndHoursDTOS = workingDaysAndHoursBO.findAllWorkingDays();
            for (AddWorkingDaysAndHoursDTO c : workingDaysAndHoursDTOS) {
                i++;
            }
            if (i == 0) {
                btnSave.setText("Save");
            } else {
                btnSave.setText("Update");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                default:
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
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


    public void btnClearOnAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> buttonType = alert.showAndWait();
        boolean checkButton = buttonType.isPresent();
        if(checkButton) {
            ButtonType buttonType1 = buttonType.get();

            if (buttonType1 == ButtonType.YES) {
                mondayCB.setSelected(false);
                tuesdayCB.setSelected(false);
                wednesdayCB.setSelected(false);
                thursdayCB.setSelected(false);
                fridayCB.setSelected(false);
                saturdayCB.setSelected(false);
                sundayCB.setSelected(false);
                SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 5);
                SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 8);
                SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 30);
                this.noOfWorkSpinner.setValueFactory(spinnerValueFactory1);
                this.hoursSpinner.setValueFactory(spinnerValueFactory2);
                this.minutesSpinner.setValueFactory(spinnerValueFactory3);

            }
        }
    }


    public void btnSaveOnAction() throws SQLException {
        int i = 0;
        boolean sunday = false;
        boolean monday = false;
        boolean tuesday = false;
        boolean wednesday = false;
        boolean thursday = false;
        boolean friday = false;
        boolean saturday = false;

        boolean checkMonday = mondayCB.selectedProperty().getValue();
        if (checkMonday) {
            monday = true;
            i++;
        }
        boolean checkTuesday = tuesdayCB.selectedProperty().getValue();
        if (checkTuesday) {
            tuesday = true;
            i++;
        }
        boolean checkWednesday = wednesdayCB.selectedProperty().getValue();
        if (checkWednesday) {
            wednesday = true;
            i++;
        }
        boolean checkThursday = thursdayCB.selectedProperty().getValue();
        if (checkThursday) {
            thursday = true;
            i++;
        }
        boolean checkFriday = fridayCB.selectedProperty().getValue();
        if (checkFriday) {
            friday = true;
            i++;
        }
        boolean checkSaturday = saturdayCB.selectedProperty().getValue();
        if (checkSaturday) {
            saturday = true;
            i++;
        }
        boolean checkSunday = sundayCB.selectedProperty().getValue();
        if (checkSunday) {
            sunday = true;
            i++;
        }

        int noOfWorkingDays = this.noOfWorkSpinner.getValue();
        int hours = this.hoursSpinner.getValue();
        int minutes = this.minutesSpinner.getValue();

        if (i == 0) new Alert(Alert.AlertType.ERROR, "Please Select at Least One Day").show();
        else if (i != noOfWorkingDays)
            new Alert(Alert.AlertType.ERROR, "Selected Days Count Is Not Match With No Of Days").show();
        else {

            if (btnSave.getText().equals("Save")) {
                AddWorkingDaysAndHoursDTO andHoursDTO = new AddWorkingDaysAndHoursDTO(
                        1,
                        noOfWorkingDays,
                        sunday,
                        monday,
                        tuesday,
                        wednesday,
                        thursday,
                        friday,
                        saturday,
                        hours,
                        minutes
                );
                try {
                    workingDaysAndHoursBO.saveWorkingDaysAndHours(andHoursDTO);
                    new Alert(Alert.AlertType.INFORMATION, "WorkingDays Added Successfully").show();
                    btnSave.setText("Update");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                workingDaysAndHoursBO.updateWorkingDaysAndHours(new AddWorkingDaysAndHoursDTO(
                        1,
                        noOfWorkingDays,
                        sunday,
                        monday,
                        tuesday,
                        wednesday,
                        thursday,
                        friday,
                        saturday,
                        hours,
                        minutes));
                new Alert(Alert.AlertType.INFORMATION, "WorkingDays Updated Successfully").show();
            }
        }

        mondayCB.setSelected(false);
        tuesdayCB.setSelected(false);
        wednesdayCB.setSelected(false);
        thursdayCB.setSelected(false);
        fridayCB.setSelected(false);
        saturdayCB.setSelected(false);
        sundayCB.setSelected(false);
        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 5);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 8);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 30);
        this.noOfWorkSpinner.setValueFactory(spinnerValueFactory1);
        this.hoursSpinner.setValueFactory(spinnerValueFactory2);
        this.minutesSpinner.setValueFactory(spinnerValueFactory3);
    }

    public void btnSessionOnAction() throws IOException {
        Parent root = null;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/Sessions.fxml")));

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

    public void onActionthursdayCB() {
        setWeekDAY();
    }

    public void onActionWednesdayCB() {
        setWeekDAY();
    }

    public void onActionTuesdayCB() {
        setWeekDAY();
    }

    public void onActionMonday() {
        setWeekDAY();
    }

    public void onActionSunday() {
        setWeekEnd();
    }

    public void onActionSaturday() {
        setWeekEnd();

    }
    public void setWeekDAY() {
        saturdayCB.setSelected(false);
        sundayCB.setSelected(false);
    }
    public void setWeekEnd() {
        mondayCB.setSelected(false);
        thursdayCB.setSelected(false);
        wednesdayCB.setSelected(false);
        tuesdayCB.setSelected(false);
        fridayCB.setSelected(false);
    }

    public void onActionfridayCB() {
        setWeekDAY();
    }

    public void timeTableOnActon() throws IOException {
        Parent root = null;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/GenerateTimeTables.fxml")));

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