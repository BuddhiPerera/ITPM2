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
import lk.sliit.itpmProject.business.custom.AddLecturerBO;
import lk.sliit.itpmProject.dto.AddLecturerDTO;
import lk.sliit.itpmProject.util.LecturerTM;

public class ManageLecturersController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LecturerTM> tblLecturer;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtLname;

    @FXML
    private TextField txtLemid;

    @FXML
    private TextField txtRank;

    @FXML
    private JFXComboBox<String> cmbFaculty;

    @FXML
    private JFXComboBox<String> cmbDepartment;

    @FXML
    private JFXComboBox<String> cmbCenter;

    @FXML
    private JFXComboBox<String> cmbBuilding;

    @FXML
    private JFXComboBox<String> cmbLevel;

    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.AddLecturer);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblLecturer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLecturer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("empId"));
        tblLecturer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lName"));
        tblLecturer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("department"));
        tblLecturer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("faculty"));
        tblLecturer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("center"));
        tblLecturer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("buildingNo"));
        tblLecturer.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("level"));
        tblLecturer.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("rank"));

        ObservableList list1 = cmbCenter.getItems();
        list1.add("Malabe");
        list1.add("Metro");
        list1.add("Matara");
        list1.add("Kandy");
        list1.add("Kurunagala");
        list1.add("Jaffna");

        ObservableList list2 = cmbLevel.getItems();
        list2.add("Professor");
        list2.add("Assistant Professor");
        list2.add("Senior Lecturer(HG)");
        list2.add("Senior Lecturer");
        list2.add("Lecturer");
        list2.add("Assistant Lecturer");

        ObservableList List3 = cmbFaculty.getItems();
        List3.add("Computing Faculty");
        List3.add("Bussiness Faculty");
        List3.add("Engineering Faculty");
        List3.add("Architecture Faculty");
        List3.add("Faculty of Humanities and Science");

        ObservableList List4 = cmbDepartment.getItems();
        List4.add("IT");
        List4.add("SE");
        List4.add("ISE");
        List4.add("DS");
        List4.add("CS");
        List4.add("IM");
        List4.add("CSNE");
        List4.add("Civil");
        List4.add("Electronic Engineering");
        List4.add("Mechanical Engineering");
        List4.add("QS");

        ObservableList List5 = cmbBuilding.getItems();
        List5.add("Block A");
        List5.add("Block B");
        List5.add("Block E");
        List5.add("Block F");

        try{
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturers();
            ObservableList<LecturerTM> lecturerTMS = tblLecturer.getItems();
            for (AddLecturerDTO addLecturerDtO: addLecturerDTOList) {
                lecturerTMS.add(new LecturerTM(
                        addLecturerDtO.getId(),
                        addLecturerDtO.getEmpId(),
                        addLecturerDtO.getlName(),
                        addLecturerDtO.getDepartment(),
                        addLecturerDtO.getFaculty(),
                        addLecturerDtO.getCenter(),
                        addLecturerDtO.getBuildingNo(),
                        addLecturerDtO.getLevel(),
                        addLecturerDtO.getRank()
                ));
            }
        }catch (Exception e){

            }
        tblLecturer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LecturerTM>() {
            @Override
            public void changed(ObservableValue<? extends LecturerTM> observable, LecturerTM oldValue, LecturerTM newValue) {
                LecturerTM selectedItem = tblLecturer.getSelectionModel().getSelectedItem();
                if(selectedItem == null){
                    btnDelete.setDisable(true);
                    return;
                }

                btnDelete.setDisable(false);

                cmbBuilding.setValue(selectedItem.getBuildingNo());
                cmbFaculty.setValue(selectedItem.getFaculty());
                cmbCenter.setValue(selectedItem.getCenter());
                cmbLevel.setValue(selectedItem.getLevel());
                cmbDepartment.setValue(selectedItem.getDepartment());
                txtLemid.setText(selectedItem.getEmpId());
                txtLname.setText(selectedItem.getlName());
                txtRank.setText(selectedItem.getRank());
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
            LecturerTM selectedItem = tblLecturer.getSelectionModel().getSelectedItem();
            try {
                addLecturerBO.deleteItem(selectedItem.getId());
                tblLecturer.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE,null,e);
            }
        }
    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {
        String building = cmbBuilding.getValue();
        String faculty = cmbFaculty.getValue();
        String center = cmbCenter.getValue();
        String level = cmbLevel.getValue();
        String department = cmbDepartment.getValue();
        String lName = txtLname.getText();
        String emId = txtLemid.getText();
        String rank = txtRank.getText();

        LecturerTM selectedItem = tblLecturer.getSelectionModel().getSelectedItem();
        try{
            addLecturerBO.updateLecturer(new AddLecturerDTO(
                    selectedItem.getId(),
                    emId,
                    lName,
                    department,
                    faculty,
                    center,
                    building,
                    level,
                    rank
            ));
            
            selectedItem.setEmpId(txtLemid.getText());
            selectedItem.setlName(txtLname.getText());
            selectedItem.setRank(txtRank.getText());
            selectedItem.setBuildingNo(building);
            selectedItem.setFaculty(faculty);
            selectedItem.setCenter(center);
            selectedItem.setLevel(level);
            selectedItem.setDepartment(department);

            tblLecturer.refresh();

            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();

        }catch (Exception e){
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }
}
