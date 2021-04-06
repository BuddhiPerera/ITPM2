package lk.sliit.itpmProject.controller;

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
import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.util.LocationTM;
import lk.sliit.itpmProject.util.StudentTM;

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

    private final AddLocationsBO addLocationsBO= BOFactory.getInstance().getBO(BOTypes.AddLocations);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblLocations.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLocations.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        tblLocations.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomName"));
        tblLocations.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lectureHall"));
        tblLocations.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("laboratory"));
        tblLocations.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Capacity"));

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

        tblLocations.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LocationTM>() {
            @Override
            public void changed(ObservableValue<? extends LocationTM> observable, LocationTM oldValue, LocationTM newValue) {
                LocationTM selectedItem = tblLocations.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnDelete.setDisable(true);
                    return;
                }

                btnDelete.setDisable(false);

                txtcapacity.setText(selectedItem.getCapacity());
                txtBuildingName.setText(selectedItem.getBuildingName());
                txtRoomName.setText(selectedItem.getRoomName());
                LabHallRadio.selectedProperty().getValue();
                LHallRadio.selectedProperty().getValue();

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
            LocationTM selectedItem = tblLocations.getSelectionModel().getSelectedItem();
            try {
                addLocationsBO.deleteItem(selectedItem.getId());
                tblLocations.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE,null,e);
            }
        }
    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {
        String building = txtBuildingName.getText();
        String room = txtRoomName.getText();
        String capacity = txtcapacity.getText();

        boolean lab = false, lHall = false;

        if(LabHallRadio.selectedProperty().getValue()){
            lab = true;
        }
        else{
            lHall = true;
        }

        LocationTM selectedItem = tblLocations.getSelectionModel().getSelectedItem();
        try{
            addLocationsBO.updateLocation(new AddLocationsDTO(
                   selectedItem.getId(),
                   building,
                   room,
                   lHall,
                    lab,
                    capacity

            ));

            selectedItem.setBuildingName(txtBuildingName.getText());
            selectedItem.setCapacity(txtcapacity.getText());
            selectedItem.setRoomName(txtRoomName.getText());
            selectedItem.setLaboratory(LabHallRadio.selectedProperty().getValue());
            selectedItem.setLectureHall(LHallRadio.selectedProperty().getValue());

            tblLocations.refresh();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }catch(Exception e){
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }
    }


