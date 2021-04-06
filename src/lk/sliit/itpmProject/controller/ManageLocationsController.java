package lk.sliit.itpmProject.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.util.LocationTM;

public class ManageLocationsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LocationTM> tblLocations;

    @FXML
    private RadioButton LHallRadio;

    @FXML
    private RadioButton LabHallRadio;

    @FXML
    private TextField txtBuildingName;

    @FXML
    private TextField txtRoomName;

    @FXML
    private TextField txtcapacity;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    void btnOnAction_Clear(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {

    }

    private final AddLocationsBO addLocationsBO= BOFactory.getInstance().getBO(BOTypes.AddLocations);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblLocations.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLocations.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        tblLocations.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomName"));
        tblLocations.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("LectureHall"));
        tblLocations.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Laboratory"));
        tblLocations.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("capacity"));

        try{
            List<AddLocationsDTO> addLocationsDTOList = addLocationsBO.findAllLocations();
            ObservableList<LocationTM> locationTMS = tblLocations.getItems();
            for (AddLocationsDTO addlocationsDTO: addLocationsDTOList
                 ) {
                locationTMS.add(new LocationTM(
                   addlocationsDTO.getId(),
                   addlocationsDTO.getBuildingName(),
                   addlocationsDTO.getRoomName(),
                   addlocationsDTO.isLectureHall(),
                   addlocationsDTO.isLaboratory(),
                   addlocationsDTO.getCapacity()

                ));
            }
        }catch(Exception e) {

        }
        }
    }


