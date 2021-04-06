package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.Property;
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
import lk.sliit.itpmProject.business.custom.AddStudentBO;
import lk.sliit.itpmProject.dto.AddStudentDTO;
import lk.sliit.itpmProject.util.StudentTM;

import javax.persistence.criteria.CriteriaBuilder;

public class ManageStudentGroupsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtGroupId;

    @FXML
    private TextField txtSubGroupId;

    @FXML
    private Spinner<Integer> spinGroupNo;

    @FXML
    private Spinner<Integer> spinSubGroupNo;

    @FXML
    private JFXComboBox<String> cmbProgramme;

    @FXML
    private Spinner<Integer> spinSemester;

    @FXML
    private Spinner<Integer> spinYear;

    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.AddStudent);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("year"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("semester"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("programme"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("groupNo"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("subGroupNo"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblStudent.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("subGroupId"));


        ObservableList list1 = cmbProgramme.getItems();
        list1.add("IT");
        list1.add("CSSE");
        list1.add("CSE");
        list1.add("IM");

        try{
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
            ObservableList<StudentTM> studentTMS = tblStudent.getItems();
            for (AddStudentDTO addStudentDTO:addStudentDTOList
                 ) {
                studentTMS.add(new StudentTM(
                   addStudentDTO.getId(),
                   addStudentDTO.getYear(),
                   addStudentDTO.getSemester(),
                   addStudentDTO.getProgramme(),
                   addStudentDTO.getGroupNo(),
                   addStudentDTO.getSubGroupNo(),
                   addStudentDTO.getGroupId(),
                   addStudentDTO.getSubGroupId()
                ));
            }
        }catch(Exception e){

        }

        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnDelete.setDisable(true);
                    return;
                }

                btnDelete.setDisable(false);

                txtGroupId.setText(selectedItem.getGroupId());
                txtSubGroupId.setText(selectedItem.getSubGroupId());
                int spinSem = selectedItem.getSemester();
                int spinGroup = selectedItem.getGroupNo();
                int spinSubGroup = selectedItem.getSubGroupNo();
                int spinYe = selectedItem.getYear();

                SpinnerValueFactory<Integer> valueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, spinSem);
                spinSemester.setValueFactory(valueFactory);
                SpinnerValueFactory<Integer> valueFactory1 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, spinGroup);
                spinGroupNo.setValueFactory(valueFactory1);
                SpinnerValueFactory<Integer> valueFactory2 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, spinSubGroup);
                spinSubGroupNo.setValueFactory(valueFactory2);
                SpinnerValueFactory<Integer> valueFactory3 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, spinYe);
                spinYear.setValueFactory(valueFactory3);

                cmbProgramme.setValue(selectedItem.getProgramme());
            }
        });
    }

    @FXML
    void btnOnAction_Clear(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        cmbProgramme.setValue(null);
        txtGroupId.setText(null);
        txtSubGroupId.setText(null);

        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0);
        this.spinGroupNo.setValueFactory(spinnerValueFactory2);
        this.spinSubGroupNo.setValueFactory(spinnerValueFactory1);
        this.spinSemester.setValueFactory(spinnerValueFactory3);
        this.spinYear.setValueFactory(spinnerValueFactory4);

        spinGroupNo.setEditable(false);
        spinSubGroupNo.setEditable(false);
        spinSemester.setEditable(false);
        spinYear.setEditable(false);
    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            try {
                addStudentBO.deleteItem(selectedItem.getId());
                tblStudent.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE,null,e);
            }
        }
    }

    @FXML
    void btnOnAction_Update(ActionEvent event)  {
        int year = spinYear.getValue();
        int semester =spinSemester.getValue();
        String programme = cmbProgramme.getValue();
        int subGroupNo = spinSubGroupNo.getValue();
        int groupNo = spinGroupNo.getValue();

        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        try {
            addStudentBO.updateStudent(new AddStudentDTO(
                    selectedItem.getId(),
                    year,
                    semester,
                    programme,
                    groupNo,
                    subGroupNo,
                    txtGroupId.getText(),
                    txtSubGroupId.getText()
                    ));
            selectedItem.setGroupId(txtGroupId.getText());
            selectedItem.setGroupNo(groupNo);
            selectedItem.setSubGroupNo(subGroupNo);
            selectedItem.setSubGroupId(txtSubGroupId.getText());
            selectedItem.setYear((year));
            selectedItem.setProgramme(programme);
            selectedItem.setSemester(semester);
            tblStudent.refresh();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }
}
