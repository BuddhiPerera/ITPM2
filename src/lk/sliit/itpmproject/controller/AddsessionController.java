package lk.sliit.itpmproject.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.*;
import lk.sliit.itpmproject.demo_data.AddLecturer;
import lk.sliit.itpmproject.demo_data.AddSessionDemo;
import lk.sliit.itpmproject.dto.*;

public class AddsessionController implements Initializable {

    @FXML
    TextField cmbSelectedLecture;
    @FXML
    JFXComboBox<String> cmbSelectTag;

    @FXML
    JFXComboBox<String> cmbSelectLecture;
    @FXML
    JFXComboBox<String> cmbSelectGroup;
    @FXML
    JFXComboBox<String> cmbSelectSubject;
    @FXML
    TextField cmbNoofStudent;
    @FXML
    TextField cmbSelectDurationHrs;
    @FXML
    TextField tctSession;
    @FXML
    Button btnSu;

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
    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.ADD_STUDENT);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.ADD_LECTURER);
    private final AddSubjectBO addSubjectBO = BOFactory.getInstance().getBO(BOTypes.ADD_SUBJECT);
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.ADD_SESSION);

    String value1 = "";
    String value2 = "";

    void reset() {
        btnSu.setDisable(true);
        cmbSelectSubject.setDisable(true);
        cmbSelectedLecture.setDisable(true);
        cmbSelectTag.setDisable(true);
        cmbSelectGroup.setDisable(true);
        cmbNoofStudent.setDisable(true);
        cmbSelectDurationHrs.setDisable(true);
        tctSession.setDisable(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list4;
        reset();
        try {
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();
            ObservableList<String> lecturerTMS = cmbSelectLecture.getItems();
            for (AddLecturerDTO addLecturerDtO : addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Error Loading").show();

        }

        try {
            List<AddSubjectDTO> addSubjectDTOList = addSubjectBO.findAllSubjects();
            ObservableList<String> subject = cmbSelectSubject.getItems();
            for (AddSubjectDTO addSubjectDTO : addSubjectDTOList) {
                subject.add(addSubjectDTO.getSubCode() + "-" + addSubjectDTO.getSubName());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Error").show();

        }


        list4 = cmbSelectTag.getItems();
        list4.add("Lecture");
        list4.add("Tutorial");
        list4.add("Lab");

        cmbSelectedLecture.setText(AddSessionDemo.getSelectedLecturer());
        cmbNoofStudent.setText(String.valueOf(AddSessionDemo.getStudent()));
        cmbSelectDurationHrs.setText(String.valueOf(AddSessionDemo.getDurationHrs()));
        cmbSelectLecture.setValue(AddSessionDemo.getSelectLecturer());
        cmbSelectTag.setValue(AddSessionDemo.getSelectTag());
        cmbSelectSubject.setValue(AddSessionDemo.getSubjectId());
        cmbSelectGroup.setValue(AddSessionDemo.getSelectGroup());

    }

    public void btnOnActionSubmit() throws SQLException {

        int lastItemCode = sessionManageBO.getLastItemCode();
        if (lastItemCode != 0) {
            try {
                sessionManageBO.findAllSessions(String.valueOf(AddSessionDemo.getId()));

            } catch (NullPointerException d) {
                int maxId = lastItemCode;
                if (AddSessionDemo.getId() == (maxId)) {
                    AddSessionDemo.setId(maxId);
                } else {
                    maxId++;
                    AddSessionDemo.setId((maxId));
                }
            }

        } else {
            AddSessionDemo.setId(1);
        }


        if (Pattern.matches("\\d+", cmbSelectDurationHrs.getText())) {

            int noOfStudent = Integer.parseInt(cmbNoofStudent.getText());
            String selectTag = cmbSelectTag.getValue();
            String selectGroup = cmbSelectGroup.getValue();
            String selectSubject = cmbSelectSubject.getValue();
            String[] parts = selectSubject.split("-");
            String part1 = parts[0];
            selectSubject = part1;
            int selectDurationHrs = Integer.parseInt(cmbSelectDurationHrs.getText());


            String lec1 = cmbSelectedLecture.getText();
            String lec2 = "";
            String[] yo = lec1.split("-");

            for (int i = 0; i < yo.length; i++) {
                if (i == 0) {
                    lec1 = yo[i];
                }
                if (i == 1) {
                    lec2 = yo[i];
                }
            }

            AddSessionDTO addSessionDTO2 = new AddSessionDTO(
                    AddSessionDemo.getId(),
                    lec1,
                    selectTag,
                    lec2,
                    selectGroup,
                    noOfStudent,
                    selectSubject,
                    selectDurationHrs,
                    ""
            );

            try {
                sessionManageBO.findAllSessions(String.valueOf(AddSessionDemo.getId()));
                check(addSessionDTO2);
                tctSession.setText("");
                reset();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.INFORMATION, "Error").show();
            }

            cmbNoofStudent.setText("");
            cmbSelectDurationHrs.setText("");
            cmbSelectTag.setDisable(true);
            cmbNoofStudent.setDisable(true);
            cmbSelectGroup.setValue("");
            cmbSelectSubject.setValue("");
            cmbSelectedLecture.setText("");
            value1 = "";
            value2 = "";
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Enter Number to the Student").show();
        }
        AddSessionDemo.setId(0);
    }

    void check(AddSessionDTO addSessionDTO2) throws SQLException {
        try {

            sessionManageBO.updateSession(addSessionDTO2);
            new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
        } catch (SQLException s) {
            sessionManageBO.saveSession(addSessionDTO2);
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();

        }
    }

    public void selectLectureMouseClick() {

        if (value1.equals("")) {
            String lectureTxt = cmbSelectLecture.getValue();
            cmbSelectedLecture.setText(lectureTxt);
            value1 = lectureTxt;
            cmbSelectSubject.setDisable(false);
            tctSession.setText(lectureTxt);
        } else if ((!value1.equals("")) && (value2.equals(""))) {
            String lectureTxt = cmbSelectLecture.getValue();
            cmbSelectedLecture.setText(value1 + " " + lectureTxt);
            value2 = lectureTxt;
            tctSession.setText(value1 + " , " + lectureTxt);
            cmbSelectSubject.setDisable(false);
        } else if (!value2.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Max 2").show();
        }

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
                case "iconLecture":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
                    root1 = fxmlLoader.load();
                    break;
                default:
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
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


    public void btnOnActionClear() {
        cmbSelectedLecture.setText("");
        tctSession.setText("");
        value1 = "";
        value2 = "";
        reset();
    }


    public void cmbSelectSubjectOnAction() {

        cmbSelectTag.setDisable(false);
        tctSession.setText("");
        AddLecturer.setValue(cmbSelectedLecture.getText() + "-" + cmbSelectSubject.getValue());
        tctSession.setText(AddLecturer.getValue());
    }

    public void cmbSelectTagOnAction() {

        cmbSelectGroup.setDisable(false);
        cmbSelectGroup.getItems().clear();

        if (cmbSelectTag.getValue().equals("Lab")) {
            try {
                List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
                ObservableList<String> studentTMS = cmbSelectGroup.getItems();
                for (AddStudentDTO addStudentDTO : addStudentDTOList) {
                    studentTMS.add(addStudentDTO.getSubGroupId());
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error find group").show();
            }
        } else {
            try {
                List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
                ObservableList<String> studentTMS = cmbSelectGroup.getItems();
                for (AddStudentDTO addStudentDTO : addStudentDTOList) {
                    studentTMS.add(addStudentDTO.getGroupId());
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error find Student ").show();
            }
        }

        tctSession.setText("");
        AddLecturer.setValue1(AddLecturer.getValue() + " " + cmbSelectTag.getValue());
        tctSession.setText(AddLecturer.getValue1());
    }

    public void cmbSelectGroupONAction() {
        cmbNoofStudent.setDisable(false);
        tctSession.setText("");
        AddLecturer.setValue2(AddLecturer.getValue1() + " " + cmbSelectGroup.getValue());
        tctSession.setText(AddLecturer.getValue2());
    }

    public void cmbNoofStudentOnAction() {
        cmbSelectDurationHrs.setDisable(false);
        tctSession.setText("");
        AddLecturer.setValue3(AddLecturer.getValue2() + " " + cmbNoofStudent.getText());
        tctSession.setText(AddLecturer.getValue3());
    }


    public void durationRelese() {
        tctSession.setText("");
        AddLecturer.setValue4(AddLecturer.getValue3() + " " + cmbSelectDurationHrs.getText());
        tctSession.setText(AddLecturer.getValue4());
        btnSu.setDisable(false);
    }

    public void durationAction() {
        tctSession.setText("");
        AddLecturer.setValue(AddLecturer.getValue() + " " + cmbSelectDurationHrs.getText());
        tctSession.setText(AddLecturer.getValue());
        btnSu.setDisable(false);
    }
}
