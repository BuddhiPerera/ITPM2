package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
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

        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);

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

                spinGroupNo.setValueFactory(spinnerValueFactory1);
            }
        });
    }

    @FXML
    void btnOnAction_Clear(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Update(ActionEvent event)  {
       // int year = spinYear.getValue();
      int semester =3; //  spinSemester.getValue();
        String programme = cmbProgramme.getValue();
     //   int subGroupNo = spinSubGroupNo.getValue();


        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        try {
            addStudentBO.updateStudent(new AddStudentDTO(
                    selectedItem.getId(),
                    3,
                    2,
                    programme,
                    spinGroupNo.getValue(),
                    45,
                    txtGroupId.getText(),
                    txtSubGroupId.getText()
                    ));
            selectedItem.setGroupId(String.valueOf(spinGroupNo.getValue()));
            selectedItem.setProgramme(programme);
            selectedItem.setSemester(semester);
            tblStudent.refresh();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE,null,e);
        }
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

    }
}
