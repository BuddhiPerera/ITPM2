package lk.sliit.itpmproject.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
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
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.*;
import lk.sliit.itpmproject.dto.*;
import lk.sliit.itpmproject.util.TimeTableTM;

public class GenerateTimeTablesController implements Initializable {

    @FXML
    public TableView<TimeTableTM> tblLecture;

    @FXML
    public JFXButton btnLecGenerate;

    @FXML
    public JFXComboBox<String> comboLec;

    @FXML
    public Tab tblStd;

    @FXML
    public JFXComboBox<String> comboGroup;

    @FXML
    public JFXButton studentGenerate;

    @FXML
    public TableView<TimeTableTM> tbleLocation;

    @FXML
    public JFXComboBox<String> comboLocation;

    @FXML
    public JFXButton gentLocation;

    @FXML
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
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.ADD_SESSION);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.ADD_LECTURER);
    private final AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.ADD_LOCATIONS);
    private final TimeTableBO timeTableBO = BOFactory.getInstance().getBO(BOTypes.TIME_TABLE);
    private final AddSubjectBO addSubjectBO = BOFactory.getInstance().getBO(BOTypes.ADD_SUBJECT);
    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.ADD_STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String timeSlot = "timeSlot";
        String monday = "monday";
        String tuesday = "tuesday";
        String wednesday = "wednesday";
        String thursday = "thursday";
        String friday = "friday";
        String saturday = "saturday";
        String sunday = "sunday";

        tblLecture.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(timeSlot));
        tblLecture.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>(monday));
        tblLecture.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>(tuesday));
        tblLecture.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>(wednesday));
        tblLecture.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(thursday));
        tblLecture.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>(friday));
        tblLecture.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>(saturday));
        tblLecture.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>(sunday));


        tbleStdu.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(timeSlot));
        tbleStdu.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>(monday));
        tbleStdu.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>(tuesday));
        tbleStdu.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>(wednesday));
        tbleStdu.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(thursday));
        tbleStdu.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>(friday));
        tbleStdu.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>(saturday));
        tbleStdu.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>(sunday));

        tbleLocation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(timeSlot));
        tbleLocation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>(monday));
        tbleLocation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>(tuesday));
        tbleLocation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>(wednesday));
        tbleLocation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(thursday));
        tbleLocation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>(friday));
        tbleLocation.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>(saturday));
        tbleLocation.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>(sunday));

        try {
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();
            ObservableList<String> lecturerTMS = comboLec.getItems();
            for (AddLecturerDTO addLecturerDtO : addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObservableList<String> selectGroupW1;
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
            selectGroupW1 = comboGroup.getItems();

            for (AddStudentDTO addSessionDTO : addStudentDTOList) {
                selectGroupW1.add((addSessionDTO.getGroupId()));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            List<AddLocationsDTO> addLocationsDTOList = addLocationsBO.findAllLocations();
            ObservableList<String> locationTMS = comboLocation.getItems();
            for (AddLocationsDTO addlocationsDTO : addLocationsDTOList) {

                locationTMS.add(addlocationsDTO.getId() +
                        ". " + addlocationsDTO.getBuildingName() +
                        " - " + addlocationsDTO.getRoomName());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnLecGenerateOnAction() throws SQLException {
        tblLecture.getItems().clear();
        if (comboLec.getValue() != null) {


            String s = comboLec.getValue();
            TimeTableDTO timeTableDTOS = null;
            try {
                timeTableDTOS = timeTableBO.loadTimeTable(s);

            } catch (Exception e) {
                e.printStackTrace();
            }

                //Find Sessions available for Lecture
                List<AddSessionDTO> loadSessionDataDTOList = sessionManageBO.loadSessionLec(timeTableDTOS.getTimeSlot());
                //Avb Sessions Count
                int sessions = loadSessionDataDTOList.size();


                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < 60; i++) {

                    while (set.size() < sessions) {
                        int x = random();
                        boolean getMonday = timeTableDTOS.getMonday();
                        if (getMonday && (x == 0 || x == 7 || x == 14 || x == 21 || x == 28
                                || x == 35 || x == 42 || x == 49 || x == 56)) {
                                set.add(x);
                                break;

                        }
                        if (timeTableDTOS.getTuesday()&& (x == 1 || x == 8 || x == 15 || x == 22 || x == 29 ||
                                x == 36 || x == 43 || x == 50 || x == 57)) {
                                set.add(x);
                                break;
                        }
                        if (timeTableDTOS.getWednesday() && (x == 2 || x == 9 || x == 16 || x == 23 || x == 30 ||
                                x == 37 || x == 44 || x == 51 || x == 58) ) {
                                set.add(x);
                                break;
                        }
                        if (timeTableDTOS.getThursday() && (x == 3 || x == 10 || x == 17 || x == 24 || x == 31 ||
                                x == 38 || x == 45 || x == 52 || x == 59)) {
                                set.add(x);
                                break;

                        }
                        if (timeTableDTOS.getFriday()&& (x == 4 || x == 11 || x == 18 || x == 25 || x == 32 ||
                                x == 39 || x == 46 || x == 53 || x == 60)) {
                            set.add(x);
                                break;

                        }
                        if (timeTableDTOS.getSaturday() && (x == 5 || x == 12 || x == 19 || x == 26 || x == 33 ||
                                x == 40 || x == 47 || x == 54 || x == 61)) {
                                set.add(x);
                                break;

                        }
                        if (timeTableDTOS.getSunday()&&  (x == 6 || x == 13 || x == 20 || x == 27 || x == 34 ||
                                x == 41 || x == 48 || x == 55 || x == 62)) {
                                set.add(x);
                                break;
                        }
                    }
                }
                int j1 = 0;
                int x = 0;

                String[][] na4me = new String[9][7];
                for (int i = 0; i < 9; i++) {
                    for (int k = 0; k < 7; k++) {
                        if (set.contains(x)) {
                            String name = addSubjectBO.findSubjectName(loadSessionDataDTOList.get(j1).getSelectSubject());

                            na4me[i][k] = loadSessionDataDTOList.get(j1).getSelectLecture() + ",\n"
                                    + name + "(" + loadSessionDataDTOList.get(j1).getSelectSubject() + "),\n"
                                    + loadSessionDataDTOList.get(j1).getSelectTag() + ",\n" + loadSessionDataDTOList.get(j1).getSelectGroup()
                                    + "," + loadSessionDataDTOList.get(j1).getNoOfStudent() + "(" + loadSessionDataDTOList.get(j1).getDurationHrs() + ")";

                            j1++;
                        } else {
                            na4me[i][k] = "---";
                        }
                        x++;
                    }
                }
                List<TimeTableDTO2> load = new ArrayList<>();
                ObservableList<TimeTableTM> timeTableTMS = tblLecture.getItems();
                for (int i = 0; i < 9; i++) {
                    TimeTableDTO2 load2 = new TimeTableDTO2();
                    int y = 0;
                    for (int j11 = 0; j11 < 1; j11++) {
                        load2.setTimeSlot("");
                        load2.setMonday(na4me[i][y++]);
                        load2.setTuesday(na4me[i][y++]);
                        load2.setWednesday(na4me[i][y++]);
                        load2.setThursday(na4me[i][y++]);
                        load2.setFriday(na4me[i][y++]);
                        load2.setSaturday(na4me[i][y++]);
                        load2.setSunday(na4me[i][y]);
                    }
                    load.add(load2);
                }

                double timer = 8.30;
                for (TimeTableDTO2 timeTableDTO : load) {
                    timeTableTMS.add(new TimeTableTM(
                            timer + "0 - " + ++timer + "0",
                            timeTableDTO.getMonday(),
                            timeTableDTO.getTuesday(),
                            timeTableDTO.getWednesday(),
                            timeTableDTO.getThursday(),
                            timeTableDTO.getFriday(),
                            timeTableDTO.getSaturday(),
                            timeTableDTO.getSunday()

                    ));
                }

        } else {
            new Alert(Alert.AlertType.ERROR, "Select Value").show();
        }
    }

    public void studentGenerateOnAction() throws SQLException {
        tbleStdu.getItems().clear();
        if (comboGroup.getValue() != null) {


            String s = comboGroup.getValue();

                //Find Sessions available for Lecture
                List<AddSessionDTO> loadSessionDataDTOList = sessionManageBO.loadSessionStd(s);
                //Avb Sessions Count
                int sessions = loadSessionDataDTOList.size();

                Set<Integer> set = new HashSet<>();


                for (int i = 0; i < 60; i++) {

                    while (set.size() < sessions) {

                        int x =random();

                        set.add(x);
                    }
                }
                int j1 = 0;
                int x = 0;

                String[][] na4me = new String[9][7];
                for (int i = 0; i < 9; i++) {
                    for (int k = 0; k < 7; k++) {
                        if (set.contains(x)) {
                            String name = addSubjectBO.findSubjectName(loadSessionDataDTOList.get(j1).getSelectSubject());

                            na4me[i][k] = loadSessionDataDTOList.get(j1).getSelectLecture() + ",\n"
                                    + name + "(" + loadSessionDataDTOList.get(j1).getSelectSubject() + "),\n"
                                    + loadSessionDataDTOList.get(j1).getSelectTag() + ",\n" + loadSessionDataDTOList.get(j1).getSelectGroup()
                                    + "," + loadSessionDataDTOList.get(j1).getNoOfStudent() + "(" + loadSessionDataDTOList.get(j1).getDurationHrs() + ")";

                            j1++;
                        } else {
                            na4me[i][k] = "---";
                        }
                        x++;
                    }
                }

                List<TimeTableDTO2> load22 = new ArrayList<>();
                ObservableList<TimeTableTM> timeTableTMS2 = tbleStdu.getItems();
                for (int i = 0; i < 9; i++) {
                    TimeTableDTO2 load2 = new TimeTableDTO2();
                    int y = 0;
                    for (int j11 = 0; j11 < 1; j11++) {
                        load2.setTimeSlot("");
                        load2.setMonday(na4me[i][y++]);
                        load2.setTuesday(na4me[i][y++]);
                        load2.setWednesday(na4me[i][y++]);
                        load2.setThursday(na4me[i][y++]);
                        load2.setFriday(na4me[i][y++]);
                        load2.setSaturday(na4me[i][y++]);
                        load2.setSunday(na4me[i][y]);
                    }
                    load22.add(load2);
                }

                double timer = 8.30;
                for (TimeTableDTO2 timeTableDTO : load22) {
                    timeTableTMS2.add(new TimeTableTM(
                            timer + "0 - " + ++timer + "0",
                            timeTableDTO.getMonday(),
                            timeTableDTO.getTuesday(),
                            timeTableDTO.getWednesday(),
                            timeTableDTO.getThursday(),
                            timeTableDTO.getFriday(),
                            timeTableDTO.getSaturday(),
                            timeTableDTO.getSunday()

                    ));
                }


        } else {
            new Alert(Alert.AlertType.ERROR, "Select Value ").show();
        }
    }


    public void gentLocationOnAction() throws SQLException {
        tbleLocation.getItems().clear();
        if (comboLocation.getValue() != null) {


            String s =comboLocation.getValue();

                //Find Sessions available for Lecture
                List<AddSessionDTO> loadSessionDataDTOList = sessionManageBO.loadSessionLoc(s);
                //Avb Sessions Count
                int sessions = loadSessionDataDTOList.size();

                Set<Integer> set = new HashSet<>();

                for (int i = 0; i < 60; i++) {

                    while (set.size() < sessions) {


                        int x = random();

                        set.add(x);
                    }
                }
                int j1 = 0;
                int x = 0;

                String[][] na4me = new String[9][7];
                for (int i = 0; i < 9; i++) {
                    for (int k = 0; k < 7; k++) {
                        if (set.contains(x)) {

                            String name = addSubjectBO.findSubjectName(loadSessionDataDTOList.get(j1).getSelectSubject());

                            na4me[i][k] = loadSessionDataDTOList.get(j1).getSelectLecture() + ",\n"
                                    + name + "(" + loadSessionDataDTOList.get(j1).getSelectSubject() + "),\n"
                                    + loadSessionDataDTOList.get(j1).getSelectTag() + ",\n" + loadSessionDataDTOList.get(j1).getSelectGroup()
                                    + "," + loadSessionDataDTOList.get(j1).getNoOfStudent() + "(" + loadSessionDataDTOList.get(j1).getDurationHrs() + ")";

                            j1++;
                        } else {
                            na4me[i][k] = "---";
                        }
                        x++;
                    }
                }

                List<TimeTableDTO2> load22 = new ArrayList<>();
                ObservableList<TimeTableTM> timeTableTMS2 = tbleLocation.getItems();
                for (int i = 0; i < 9; i++) {
                    TimeTableDTO2 load2 = new TimeTableDTO2();
                    int y = 0;
                    for (int j11 = 0; j11 < 1; j11++) {
                        load2.setTimeSlot("");
                        load2.setMonday(na4me[i][y++]);
                        load2.setTuesday(na4me[i][y++]);
                        load2.setWednesday(na4me[i][y++]);
                        load2.setThursday(na4me[i][y++]);
                        load2.setFriday(na4me[i][y++]);
                        load2.setSaturday(na4me[i][y++]);
                        load2.setSunday(na4me[i][y]);
                    }
                    load22.add(load2);
                }

                double timer = 8.30;
                for (TimeTableDTO2 timeTableDTO : load22) {
                    timeTableTMS2.add(new TimeTableTM(
                            timer + "0 - " + ++timer + "0",
                            timeTableDTO.getMonday(),
                            timeTableDTO.getTuesday(),
                            timeTableDTO.getWednesday(),
                            timeTableDTO.getThursday(),
                            timeTableDTO.getFriday(),
                            timeTableDTO.getSaturday(),
                            timeTableDTO.getSunday()

                    ));
                }



        } else {
            new Alert(Alert.AlertType.ERROR, "Select Value").show();
        }
    }
    Random r = new Random();
    int random(){
        int x ;
        x = r.nextInt(60);
        return x;
    }

    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

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