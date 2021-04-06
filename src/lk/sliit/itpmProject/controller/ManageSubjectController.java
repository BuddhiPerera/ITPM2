package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddSubjectBO;
import lk.sliit.itpmProject.dto.AddStudentDTO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;
import lk.sliit.itpmProject.util.StudentTM;
import lk.sliit.itpmProject.util.SubjectTM;

public class ManageSubjectController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SubjectTM> tblSubject;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private CheckBox chSem1;

    @FXML
    private CheckBox chSem2;

    @FXML
    private TextField txtSubjectName;

    @FXML
    private TextField txtSubCode;

    @FXML
    private Spinner<Integer> spinOfferedYear;

    @FXML
    private Spinner<Integer> spinLecHours;

    @FXML
    private Spinner<Integer> spinTuteHours;

    @FXML
    private Spinner<Integer> spinLabHours;

    @FXML
    private Spinner<Integer> spinEvaHours;

    private final AddSubjectBO addSubjectBO = BOFactory.getInstance().getBO(BOTypes.AddSubject);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblSubject.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSubject.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("offeredYear"));
        tblSubject.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("semester1"));
        tblSubject.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("semester2"));
        tblSubject.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("noOfLecHrs"));
        tblSubject.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("noOfTutHrs"));
        tblSubject.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("noOfLabHrs"));
        tblSubject.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("subName"));
        tblSubject.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("noOfEvlHrs"));
        tblSubject.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("subCode"));

        try {
            List<AddSubjectDTO> addSubjectDTOList = addSubjectBO.findAllSubjects();
            ObservableList<SubjectTM> subjectTMS = tblSubject.getItems();
            for (AddSubjectDTO addSubjectDTO : addSubjectDTOList
            ) {
                subjectTMS.add(new SubjectTM(
                        addSubjectDTO.getId(),
                        addSubjectDTO.getOffredYear(),
                        addSubjectDTO.isSemester1(),
                        addSubjectDTO.isSemester2(),
                        addSubjectDTO.getNoOfLecHrs(),
                        addSubjectDTO.getNoOfTutHrs(),
                        addSubjectDTO.getNoOflabHrs(),
                        addSubjectDTO.getSubName(),
                        addSubjectDTO.getNoOfEvlHrs(),
                        addSubjectDTO.getSubCode()
                ));
            }
        } catch (Exception e) {

        }

        tblSubject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubjectTM>() {
            @Override
            public void changed(ObservableValue<? extends SubjectTM> observable, SubjectTM oldValue, SubjectTM newValue) {
                SubjectTM selectedItem = tblSubject.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnDelete.setDisable(true);
                    return;
                }

                btnDelete.setDisable(false);

                int spinYear = selectedItem.getOfferedYear();
                int spinLHours = selectedItem.getNoOfLecHrs();
                int spinTHours = selectedItem.getNoOfTutHrs();
                int spinLbHours = selectedItem.getNoOfLabHrs();
                int spinEvalHours = selectedItem.getNoOfEvlHrs();
                txtSubjectName.setText(selectedItem.getSubName());
                txtSubCode.setText(selectedItem.getSubCode());

                if (selectedItem.isSemester1()) {
                    chSem1.setSelected(true);
                }else {
                    chSem1.setSelected(false);
                }
                if (selectedItem.isSemester2()) {
                    chSem2.setSelected(true);
                }else {
                    chSem2.setSelected(false);
                }

                SpinnerValueFactory<Integer> valueFactory1 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, spinYear);
                spinOfferedYear.setValueFactory(valueFactory1);

                SpinnerValueFactory<Integer> valueFactory2 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, spinLHours);
                spinLecHours.setValueFactory(valueFactory2);

                SpinnerValueFactory<Integer> valueFactory3 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, spinTHours);
                spinTuteHours.setValueFactory(valueFactory3);

                SpinnerValueFactory<Integer> valueFactory4 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, spinLbHours);
                spinLabHours.setValueFactory(valueFactory4);

                SpinnerValueFactory<Integer> valueFactory5 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, spinEvalHours);
                spinEvaHours.setValueFactory(valueFactory5);
            }
        });
    }

    @FXML
    void btnOnAction_Clear(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            SubjectTM selectedItem = tblSubject.getSelectionModel().getSelectedItem();
            try {
                addSubjectBO.deleteItem(selectedItem.getId());
                tblSubject.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {
        int spinYear = spinOfferedYear.getValue();
        int spinLHours = spinLecHours.getValue();
        int spinTHours = spinTuteHours.getValue();
        int spinLbHours = spinLabHours.getValue();
        int spinEvalHours = spinEvaHours.getValue();

        boolean sem1 = chSem1.isSelected();
        boolean sem2 = chSem2.isSelected();

        SubjectTM selectedItem = tblSubject.getSelectionModel().getSelectedItem();
        try {
            addSubjectBO.updateSubject(new AddSubjectDTO(
                    selectedItem.getId(),
                    spinYear,
                    sem1,
                    sem2,
                    spinLHours,
                    spinTHours,
                    spinLbHours,
                    txtSubjectName.getText(),
                    spinEvalHours,
                    txtSubCode.getText()
            ));

            selectedItem.setNoOfEvlHrs(spinEvalHours);
            selectedItem.setNoOfLabHrs(spinLbHours);
            selectedItem.setNoOfLecHrs(spinLHours);
            selectedItem.setNoOfTutHrs(spinTHours);
            selectedItem.setOfferedYear(spinYear);
            selectedItem.setSemester1(sem1);
            selectedItem.setSemester2(sem2);
            selectedItem.setSubCode(txtSubCode.getText());
            selectedItem.setSubName(txtSubjectName.getText());
            tblSubject.refresh();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }
}

