package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    void btnOnAction_Clear(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {

    }

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

            try{
                List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturers();
                ObservableList<LecturerTM> lecturerTMS = tblLecturer.getItems();
                for (AddLecturerDTO addLecturerDtO: addLecturerDTOList
                     ) {
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


                    ) );
                    
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

                }
            });
    }
}
