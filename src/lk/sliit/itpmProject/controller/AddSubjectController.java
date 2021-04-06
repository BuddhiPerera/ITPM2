package lk.sliit.itpmProject.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddSubjectBO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;

public class AddSubjectController implements Initializable {

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
        alert.showAndWait();
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
            i++;
        }
        else{
            semesterTwo = true;
            i++;
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
}
