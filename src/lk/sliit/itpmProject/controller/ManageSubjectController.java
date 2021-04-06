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
        tblSubject.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("semester 1"));
        tblSubject.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("semester 2"));
        tblSubject.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("NoOFLectureHrs"));
        tblSubject.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("NoOfTutHrs"));
        tblSubject.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("NoOFlabHrs"));
        tblSubject.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("SubName"));
        tblSubject.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("NoOfEvlHrs"));
        tblSubject.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("SubCode"));

        try{
            List<AddSubjectDTO> addSubjectDTOList = addSubjectBO.findAllSubjects();
            ObservableList<SubjectTM> subjectTMS = tblSubject.getItems();
            for (AddSubjectDTO addSubjectDTO:addSubjectDTOList
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
        }catch(Exception e){

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

                int spinYear = selectedItem.getOffredYear();
                int spinLecHours = selectedItem.getNoOfLecHrs();
                int spinTuteHours = selectedItem.getNoOfTutHrs();
                int spinLabHours = selectedItem.getNoOflabHrs();
                int spinEvHours = selectedItem.getNoOfEvlHrs();


                SpinnerValueFactory<Integer> valueFactory3 =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, spinYear);
                spinOfferedYear.setValueFactory(valueFactory3);
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
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE,null,e);
            }
        }
    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {

    }
}

