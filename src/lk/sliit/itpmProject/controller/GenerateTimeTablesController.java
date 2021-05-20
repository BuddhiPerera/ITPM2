package lk.sliit.itpmProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddLecturerBO;
import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dto.AddLecturerDTO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;
import lk.sliit.itpmProject.util.LocationTM;
import lk.sliit.itpmProject.util.TimeTableTM;

public class GenerateTimeTablesController implements Initializable {

    public TableView<TimeTableTM> tblLecture;
    public JFXButton btnLecGenerate;
    public JFXComboBox comboLec;
    public Tab tblStd;
    public JFXComboBox comboGroup;
    public JFXButton studentGenerate;
    public TableView<TimeTableTM> tbleLocation;
    public JFXComboBox comboLocation;
    public JFXButton GentLocation;
    public TableView<TimeTableTM> tbleStdu;
    @FXML
    private AnchorPane root;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconLecture;

    @FXML
    private ImageView iconStudent;

    @FXML
    private ImageView iconTimeTable;

    @FXML
    private ImageView iconLocation;
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.AddLecturer);
    private final AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.AddLocations);

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        tbleStdu.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        tbleStdu.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("monday"));
        tbleStdu.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        tbleStdu.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        tbleStdu.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("thursday"));
        tbleStdu.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("friday"));
        tbleStdu.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("saturday"));
        tbleStdu.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("sunday"));


        tbleLocation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        tbleLocation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("monday"));
        tbleLocation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        tbleLocation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        tbleLocation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("thursday"));
        tbleLocation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("friday"));
        tbleLocation.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("saturday"));
        tbleLocation.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("sunday"));

        tblLecture.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        tblLecture.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("monday"));
        tblLecture.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        tblLecture.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        tblLecture.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("thursday"));
        tblLecture.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("friday"));
        tblLecture.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("saturday"));
        tblLecture.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("sunday"));

        try {
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();
            ObservableList<String> lecturerTMS = comboLec.getItems();
            for (AddLecturerDTO addLecturerDtO : addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        } catch (Exception e) {
        }

        try {
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadSessionTable();
            ObservableList selectGroupW1 = comboGroup.getItems();

            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {
                selectGroupW1.add((addSessionDTO.getGroupId()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            List<AddLocationsDTO> addLocationsDTOList = addLocationsBO.findAllLocations();
            ObservableList locationTMS = comboLocation.getItems();
            for (AddLocationsDTO addlocationsDTO : addLocationsDTOList) {

                locationTMS.add(addlocationsDTO.getId() +
                        ". " + addlocationsDTO.getBuildingName() +
                        " - " + addlocationsDTO.getRoomName());

            }
        } catch (Exception e) {

        }
    }


    public void btnLecGenerateOnAction(ActionEvent actionEvent) {
        if(comboLec.getValue()!= null){



        }else {
            new Alert(Alert.AlertType.ERROR,"Select Value").show();
        }
    }

    public void studentGenerateOnAction(ActionEvent actionEvent) {
    }


    public void GentLocationOnAction(ActionEvent actionEvent) {
    }

    public void comboLecOnAction(ActionEvent actionEvent) {
    }

    public void comboGroupOnaction(ActionEvent actionEvent) {
    }


    public void comboLocationOnAction(ActionEvent actionEvent) {
    }


    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmProject/view/AddWorkingDaysAndHours.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
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

    @FXML
    void playMouseEnterAnimation(MouseEvent event) {

    }

    @FXML
    void playMouseExitAnimatio(MouseEvent event) {

    }


}
