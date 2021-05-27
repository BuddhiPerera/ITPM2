package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.AddStudentBO;
import lk.sliit.itpmproject.dto.AddStudentDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    public Button btnAddSave;
    public Button btnGoAddTag;

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnGenerateId;

    @FXML
    private Spinner<Integer> subGroupNumberSpinner;

    @FXML
    private Spinner<Integer> groupNumberSpinner;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconLecture;

    @FXML
    private ImageView iconTimeTable;

    @FXML
    private ImageView iconMap;

    @FXML
    private JFXTextField academicYearTxt;

    @FXML
    private JFXComboBox<String> programmeCombo;

    @FXML
    private JFXTextField subGroupIdTxt;

    @FXML
    private JFXTextField groupIdTxt;

    @FXML
    private Spinner<Integer> semesterSpinner;

    @FXML
    private Spinner<Integer> spinStdYear;


    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.ADD_STUDENT);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 1);
        SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        this.subGroupNumberSpinner.setValueFactory(spinnerValueFactory2);
        this.groupNumberSpinner.setValueFactory(spinnerValueFactory1);
        this.semesterSpinner.setValueFactory(spinnerValueFactory3);
        this.spinStdYear.setValueFactory(spinnerValueFactory4);

        subGroupNumberSpinner.setEditable(false);
        groupNumberSpinner.setEditable(false);
        semesterSpinner.setEditable(false);
        spinStdYear.setEditable(false);

        programmeCombo.setValue("IT");
        ObservableList list1 = programmeCombo.getItems();
        list1.add("IT");
        list1.add("CSSE");
        list1.add("CSE");
        list1.add("IM");
        btnAddSave.setDisable(true);
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
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
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
    void btnGenerateId_OnAction(ActionEvent event) {
        int year = spinStdYear.getValue();
        int semester = semesterSpinner.getValue();
        String programme = programmeCombo.getValue();
        int groupNo = groupNumberSpinner.getValue();
        int subGroupNo = subGroupNumberSpinner.getValue();

        groupIdTxt.setText("Y" + year + "." +  "S" + semester + "." + programme + "." + groupNo);
        subGroupIdTxt.setText("Y" + year + "." + "S" + semester + "." + programme + "." + groupNo + "." + subGroupNo);
        btnAddSave.setDisable(false);
    }

    @FXML
    void btnSave_onAction(ActionEvent event) {
        int maxCode = 0;
        try {
            int lastItemCode = addStudentBO.getLastItemCode();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
        }

        int year = spinStdYear.getValue();
        int semester = semesterSpinner.getValue();
        String programme = programmeCombo.getValue();
        int groupNo = groupNumberSpinner.getValue();
        int subGroupNo = subGroupNumberSpinner.getValue();
        String groupId = groupIdTxt.getText();
        String subGroupId = subGroupIdTxt.getText();

        AddStudentDTO addStudentDTO = new AddStudentDTO(
                maxCode,
                year,
                semester,
                programme,
                groupNo,
                subGroupNo,
                groupId,
                subGroupId
        );
        try {

            addStudentBO.saveStudent(addStudentDTO);
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void btnClear_onAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        programmeCombo.setValue("IT");
        groupIdTxt.setText(null);
        subGroupIdTxt.setText(null);

        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 1);
        SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        this.subGroupNumberSpinner.setValueFactory(spinnerValueFactory2);
        this.groupNumberSpinner.setValueFactory(spinnerValueFactory1);
        this.semesterSpinner.setValueFactory(spinnerValueFactory3);
        this.spinStdYear.setValueFactory(spinnerValueFactory4);

        subGroupNumberSpinner.setEditable(false);
        groupNumberSpinner.setEditable(false);
        semesterSpinner.setEditable(false);
        spinStdYear.setEditable(false);
    }

    @FXML
    public void btnAddTag_onAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddTag.fxml"));

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

    public void btnOnAction_Manage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageStudentGroups.fxml"));

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