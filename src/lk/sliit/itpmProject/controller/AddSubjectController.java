package lk.sliit.itpmProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddSubjectBO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;

public class AddSubjectController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox chkSemester1;

    @FXML
    private CheckBox chkSemester2;

    @FXML
    private TextField txtSubName;

    @FXML
    private TextField txtSubCode;

    @FXML
    private Spinner<Integer> spinNoOfHours;

    @FXML
    private Spinner<Integer> spinNoOfTuteHours;

    @FXML
    private Spinner<Integer> spinNoOfLabHours;

    @FXML
    private Spinner<Integer> spinNoOfEvHours;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private Spinner<Integer> spinOfferedYear;

    private final AddSubjectBO addSubjectBO = BOFactory.getInstance().getBO(BOTypes.AddSubject);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
        SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
        SpinnerValueFactory<Integer> spinnerValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
        this.spinOfferedYear.setValueFactory(spinnerValueFactory1);
        this.spinNoOfHours.setValueFactory(spinnerValueFactory2);
        this.spinNoOfTuteHours.setValueFactory(spinnerValueFactory3);
        this.spinNoOfLabHours.setValueFactory(spinnerValueFactory4);
        this.spinNoOfEvHours.setValueFactory(spinnerValueFactory5);

        spinOfferedYear.setEditable(false);
        spinNoOfHours.setEditable(false);
        spinNoOfTuteHours.setEditable(false);
        spinNoOfLabHours.setEditable(false);
        spinNoOfEvHours.setEditable(false);

    }

    @FXML
    void btnOnAction_Clear(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            txtSubName.clear();
            txtSubCode.clear();
            chkSemester1.setSelected(false);
            SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
            this.spinOfferedYear.setValueFactory(spinnerValueFactory1);
            SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
            this.spinNoOfHours.setValueFactory(spinnerValueFactory2);
            SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
            this.spinNoOfTuteHours.setValueFactory(spinnerValueFactory3);
            SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
            this.spinNoOfLabHours.setValueFactory(spinnerValueFactory4);
            SpinnerValueFactory<Integer> spinnerValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
            this.spinNoOfEvHours.setValueFactory(spinnerValueFactory5);



        }
    }

    @FXML
    void btnOnAction_Save(ActionEvent event) {
        int maxCode = 0;
        try {
            int lastItemCode = addSubjectBO.getLastItemCode();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
        }

        int i = 0;
        boolean semesterOne = false, semesterTwo = false;

        if(chkSemester1.selectedProperty().getValue()){
            semesterOne = true;

        }
        if(chkSemester2.selectedProperty().getValue()){
            semesterTwo = true;

        }

        int offeredYear = spinOfferedYear.getValue();
        int noOfLecHours = spinNoOfHours.getValue();
        int noOfTuteHours = spinNoOfTuteHours.getValue();
        int noOfLabHours = spinNoOfLabHours.getValue();
        String subName = txtSubName.getText();
        int noOfEvalHours = spinNoOfEvHours.getValue();
        String subCode = txtSubCode.getText();

        AddSubjectDTO addSubjectDTO = new AddSubjectDTO(
                maxCode,
                offeredYear,
                semesterOne,
                semesterTwo,
                noOfLecHours,
                noOfTuteHours,
                noOfLabHours,
                subName,
                noOfEvalHours,
                subCode
        );
        try {

            addSubjectBO.saveSubject(addSubjectDTO);
            new Alert(Alert.AlertType.INFORMATION, "Subject Added Successfully").show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnOnAction_Manage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/ManageSubject.fxml"));

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

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmProject/view/AddWorkingDaysAndHours.fxml"));
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

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
    }

    public void playMouseExitAnimatio(MouseEvent mouseEvent) {

    }
}
