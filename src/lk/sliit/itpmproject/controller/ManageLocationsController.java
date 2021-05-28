package lk.sliit.itpmproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.AddLocationsBO;
import lk.sliit.itpmproject.dto.AddLocationsDTO;
import lk.sliit.itpmproject.util.LocationTM;

public class ManageLocationsController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LocationTM> tblLocations;

    @FXML
    private RadioButton lHallRadio;

    @FXML
    private RadioButton labHallRadio;

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

    private final AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.ADD_LOCATIONS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblLocations.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLocations.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        tblLocations.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomName"));
        tblLocations.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lectureHall"));
        tblLocations.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("laboratory"));
        tblLocations.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Capacity"));

        try {
            List<AddLocationsDTO> addLocationsDTOList = addLocationsBO.findAllLocations();
            ObservableList<LocationTM> locationTMS = tblLocations.getItems();
            for (AddLocationsDTO addlocationsDTO : addLocationsDTOList
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblLocations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LocationTM selectedItem = tblLocations.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                btnDelete.setDisable(true);
                return;
            }

            btnDelete.setDisable(false);

            txtcapacity.setText(selectedItem.getCapacity());
            txtBuildingName.setText(selectedItem.getBuildingName());
            txtRoomName.setText(selectedItem.getRoomName());
            labHallRadio.selectedProperty().getValue();

            if (selectedItem.isLectureHall()) {
                lHallRadio.setSelected(true);
            } else {
                labHallRadio.setSelected(true);
            }


        });
    }

    @FXML
    void btnOnActionClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        txtBuildingName.setText("");
        txtcapacity.setText("");
        txtRoomName.setText("");
        labHallRadio.selectedProperty().setValue(false);
        lHallRadio.selectedProperty().setValue(false);
    }

    @FXML
    void btnOnActionDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        boolean bol = buttonType.isPresent();
        if (bol) {
            ButtonType bool = buttonType.get();
            if (bool == ButtonType.YES) {
                LocationTM selectedItem = tblLocations.getSelectionModel().getSelectedItem();
                try {
                    addLocationsBO.deleteItem(selectedItem.getId());
                    tblLocations.getItems().remove(selectedItem);
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                    Logger.getLogger("").log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String building = txtBuildingName.getText();
        String room = txtRoomName.getText();
        String capacity = txtcapacity.getText();

        boolean lab = false;
        boolean lHall = false;
        boolean a = labHallRadio.selectedProperty().getValue();
        if (a) {
            lab = true;
        } else {
            lHall = true;
        }

        LocationTM selectedItem = tblLocations.getSelectionModel().getSelectedItem();
        try {
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
            selectedItem.setLaboratory(labHallRadio.selectedProperty().getValue());
            selectedItem.setLectureHall(lHallRadio.selectedProperty().getValue());

            tblLocations.refresh();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root1 = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                default:
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
                    root1 = fxmlLoader.load();
                    break;
            }

            if (root1 != null) {
                Scene subScene = new Scene(root1);
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


}


