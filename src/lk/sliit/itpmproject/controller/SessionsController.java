package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;

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
import lk.sliit.itpmproject.business.custom.*;
import lk.sliit.itpmproject.dto.*;
import lk.sliit.itpmproject.util.SessionTM;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SessionsController implements Initializable {

    @FXML
    public JFXTextField naTimeLectureTxt;
    @FXML
    public JFXComboBox<String> naTimeLectureCombo;
    @FXML
    public JFXButton btnSubmitTeacher;
    @FXML
    public JFXButton btnViewTeacher;
    @FXML
    public JFXComboBox<String> naTimeLectureSessionIdTxt;
    @FXML
    JFXComboBox<String> naTimeLectureSubGroupTxt;
    @FXML
     JFXComboBox<String> naTimeLectureGroup;
    @FXML
     JFXButton btnClearTeacher;
    @FXML
     AnchorPane root1;

    private final AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.ADD_LOCATIONS);
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.ADD_SESSION);
    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.ADD_STUDENT);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.ADD_LECTURER);
    @FXML
    TableView<SessionTM> tblConsecutive;
    @FXML
    TableView<SessionTM> tblNonOverLapping;
    @FXML
    TableView<SessionTM> tblParallel;
    @FXML
    JFXTextField naTimeLectureTxt1;
    @FXML
    JFXTextField naSGroupTxt;
    @FXML
    JFXComboBox<String> naTimeSROom;
    @FXML
    JFXTextField timeTxtRoom;
    @FXML
    JFXButton btnOnActionRoom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
String check = "checkBox";
String lecture1 ="lectureOne";
String lecture2= "lectureTwo";
String subject= "subjectCode";
String subjectName ="subjectName";
String groupId = "groupId";
String tagName ="tagName";
        tblConsecutive.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(check));
        tblConsecutive.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblConsecutive.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>(lecture1));
        tblConsecutive.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>(lecture2));
        tblConsecutive.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(subject));
        tblConsecutive.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>(subjectName));
        tblConsecutive.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>(groupId));
        tblConsecutive.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>(tagName));

        tblNonOverLapping.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(check));
        tblNonOverLapping.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNonOverLapping.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>(lecture1));
        tblNonOverLapping.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>(lecture2));
        tblNonOverLapping.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(subject));
        tblNonOverLapping.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>(subjectName));
        tblNonOverLapping.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>(groupId));
        tblNonOverLapping.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>(tagName));

        tblParallel.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(check));
        tblParallel.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblParallel.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>(lecture1));
        tblParallel.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>(lecture2));
        tblParallel.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(subject));
        tblParallel.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>(subjectName));
        tblParallel.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>(groupId));
        tblParallel.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>(tagName));

        try {
            List<LoadSessionDataDTO> loadSessionDataDTOList = sessionManageBO.loadSessionTable();
            ObservableList<SessionTM> sessionTMS = tblConsecutive.getItems();
            ObservableList<SessionTM> tblNonOverLap = tblNonOverLapping.getItems();
            ObservableList<SessionTM> tblPar = tblParallel.getItems();
            for (LoadSessionDataDTO loadSessionDataDTO : loadSessionDataDTOList) {
                sessionTMS.add(new SessionTM(
                        new CheckBox(),
                        loadSessionDataDTO.getId(),
                        loadSessionDataDTO.getLectureOne(),
                        loadSessionDataDTO.getLectureTwo(),
                        loadSessionDataDTO.getSubjectCode(),
                        loadSessionDataDTO.getSubjectName(),
                        loadSessionDataDTO.getGroupId(),
                        loadSessionDataDTO.getTagName()
                ));
            }
            for (LoadSessionDataDTO loadSessionDataDTO : loadSessionDataDTOList) {
                tblNonOverLap.add(new SessionTM(
                        new CheckBox(),
                        loadSessionDataDTO.getId(),
                        loadSessionDataDTO.getLectureOne(),
                        loadSessionDataDTO.getLectureTwo(),
                        loadSessionDataDTO.getSubjectCode(),
                        loadSessionDataDTO.getSubjectName(),
                        loadSessionDataDTO.getGroupId(),
                        loadSessionDataDTO.getTagName()
                ));
            }
            for (LoadSessionDataDTO loadSessionDataDTO : loadSessionDataDTOList) {
                tblPar.add(new SessionTM(
                        new CheckBox(),
                        loadSessionDataDTO.getId(),
                        loadSessionDataDTO.getLectureOne(),
                        loadSessionDataDTO.getLectureTwo(),
                        loadSessionDataDTO.getSubjectCode(),
                        loadSessionDataDTO.getSubjectName(),
                        loadSessionDataDTO.getGroupId(),
                        loadSessionDataDTO.getTagName()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something wrong").show();
        }
        try {
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();

            ObservableList<String> lecturerTMS = naTimeLectureCombo.getItems();

            for (AddLecturerDTO addLecturerDtO : addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "gone wrong").show();

        }
        try {
            List<AddLocationsDTO> addLocationsDTOList = addLocationsBO.findAllLocations();

            ObservableList<String> lecturerTMS = naTimeSROom.getItems();

            for (AddLocationsDTO addLecturerDtO : addLocationsDTOList) {
                lecturerTMS.add(addLecturerDtO.getRoomName());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, " wrong").show();

        }
        ObservableList<String> studentTMS;
        ObservableList<String> studentTMS3;
        try {
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
             studentTMS = naTimeLectureGroup.getItems();
             studentTMS3 = naTimeLectureSubGroupTxt.getItems();
            for (AddStudentDTO addStudentDTO : addStudentDTOList) {
                studentTMS.add(addStudentDTO.getGroupId());
                studentTMS3.add(addStudentDTO.getSubGroupNo() + "-" + addStudentDTO.getSubGroupId());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrg").show();

        }
        ObservableList<String> studentTMS2;
        try {
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadSessionTable();
            studentTMS2 = naTimeLectureSessionIdTxt.getItems();
            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {

                studentTMS2.add(Integer.valueOf(addSessionDTO.getId()) + "-" + addSessionDTO.getLectureOne() + " " +
                        addSessionDTO.getSubjectCode() +
                        " " + addSessionDTO.getSubjectName() + " " + addSessionDTO.getGroupId());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "").show();

        }
    }

    public void btnAddSessionOnAction()  {

        try {
            List<LoadSessionDataDTO> dtos = new ArrayList<>();
            ObservableList<SessionTM> sessionTMS = tblConsecutive.getItems();
            for (SessionTM customEntity : sessionTMS) {
                if (customEntity.getCheckBox().isSelected()) {
                    dtos.add(new LoadSessionDataDTO(
                            customEntity.getId(),
                            customEntity.getLectureOne(),
                            customEntity.getLectureTwo(),
                            customEntity.getSubjectCode(),
                            customEntity.getSubjectName(),
                            customEntity.getGroupId(),
                            customEntity.getTagName()
                    ));
                }
            }
            sessionManageBO.saveNATimeAlocations(dtos);
            new Alert(Alert.AlertType.INFORMATION, "Data Added").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                default:
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root1.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }



    public void btnClearTeacherOnAction( ) {
        naTimeLectureSubGroupTxt.setValue("");
        naTimeLectureCombo.setValue("");
        naTimeLectureGroup.setValue("");
        naTimeLectureTxt.setText("");
    }

    public void btnSubmitTeacherOnAction( ) {
        String lectureComboValue = naTimeLectureCombo.getValue();
        if (lectureComboValue == null || lectureComboValue.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Select a Lecturer").show();
            return;
        }
        if (naTimeLectureTxt.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Add  Time").show();
            return;
        }
        int maxCode = 0;
        try {
            int lastItemCode = sessionManageBO.getLastNotAvbLectures();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
        String naTimeLectureTxtText = naTimeLectureTxt.getText();
        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                lectureComboValue,
                naTimeLectureTxtText
        );
        try {
            sessionManageBO.saveNASessionLec(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error In Save").show();
        }
    }

    public void viewTeacherOnAction() throws IOException {
        Parent root;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageNotAvlilableTimes.fxml")));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root1.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void btnAddSessionParaOnAction() {
        try {
            List<LoadSessionDataDTO> dtos = new ArrayList<>();
            ObservableList<SessionTM> sessionTMS = tblParallel.getItems();
            for (SessionTM customEntity : sessionTMS) {
                if (customEntity.getCheckBox().isSelected()) {
                    dtos.add(new LoadSessionDataDTO(
                            customEntity.getId(),
                            customEntity.getLectureOne(),
                            customEntity.getLectureTwo(),
                            customEntity.getSubjectCode(),
                            customEntity.getSubjectName(),
                            customEntity.getGroupId(),
                            customEntity.getTagName()
                    ));
                }
            }
            sessionManageBO.savetblParallel(dtos);
            new Alert(Alert.AlertType.INFORMATION, "Data Added To the Table").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "went wrong").show();
        }
    }

    public void btnAddSessionOnNAOverLapAction( ) {
        try {
            List<LoadSessionDataDTO> dtos = new ArrayList<>();
            ObservableList<SessionTM> sessionTMS = tblNonOverLapping.getItems();
            for (SessionTM customEntity : sessionTMS) {
                if (customEntity.getCheckBox().isSelected()) {
                    dtos.add(new LoadSessionDataDTO(
                            customEntity.getId(),
                            customEntity.getLectureOne(),
                            customEntity.getLectureTwo(),
                            customEntity.getSubjectCode(),
                            customEntity.getSubjectName(),
                            customEntity.getGroupId(),
                            customEntity.getTagName()
                    ));
                }
            }
            sessionManageBO.savetblNonOverLapping(dtos);
            new Alert(Alert.AlertType.INFORMATION, "Data Added").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, " went wrong").show();
        }
    }

    public void submitGroupNa( ) {
        String lectureComboValue = naTimeLectureGroup.getValue();
        if (lectureComboValue == null || lectureComboValue.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Select a Group").show();
            return;
        }
        if (naTimeLectureTxt1.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Add Time").show();
            return;
        }
        int maxCode = 0;
        try {
            int lastItemCode = sessionManageBO.getLastNotAvbGroups();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, " went wrong").show();
        }

        String lectureComboValu = naTimeLectureGroup.getValue();
        String naTimeLectureTxtText = naTimeLectureTxt1.getText();

        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                lectureComboValu,
                naTimeLectureTxtText
        );
        try {

            sessionManageBO.saveNASessionGroup(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "Group Added Successfully").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Group Added Error").show();

        }
    }

    public void submitSGroupOnAction() {

        String string = String.valueOf(naTimeLectureSubGroupTxt.getValue());
        String[] parts = string.split("-");
        String part1 = parts[0];


        if (part1.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Select a Group").show();
            return;
        }
        if (naSGroupTxt.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Add a Time").show();
            return;
        }

        int maxCode = 0;
        try {
            int lastItemCode = sessionManageBO.getLastNotAvbSubGroups();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Error").show();
        }

        String naTimeLectureTxtText = naSGroupTxt.getText();

        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                part1,
                naTimeLectureTxtText
        );
        try {

            sessionManageBO.saveNASessionSubGroup(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "User Added Error").show();
        }
    }

    public void btnOnActionRoomOnAction() {
        String lectureComboValu = String.valueOf(naTimeSROom.getValue());
        String naTimeLectureTxtText = timeTxtRoom.getText();

        if (lectureComboValu.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            return;
        }
        if (naTimeLectureTxtText.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Add a Time").show();
            return;
        }
        int maxCode = 0;
        try {
            int lastItemCode = sessionManageBO.getLastNARoom();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "went wrong").show();
        }


        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                lectureComboValu,
                naTimeLectureTxtText
        );
        try {
            sessionManageBO.saveNASessionRoom(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "Room Added Successfully").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Error In Adding").show();

        }
    }



    public void btnOnActionViewParallel() throws IOException {
        Parent root;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/Managesessions.fxml")));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root1.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }


    public void viewGroupOnAction( ) throws IOException {
        Parent root;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageNotAvbTimeGroup.fxml")));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root1.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void clearGroupOnAction( ) {
        naTimeLectureTxt1.setText("");
    }

    public void btnSGroupOnAction( ) throws IOException {
        Parent root;
        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageNATimeSubGroup.fxml")));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root1.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void sGroupClear( ) {
        naSGroupTxt.setText("");
    }

    public void viewOnActionRoomS( ) throws IOException {
        Parent root;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageNotAvilableTimeRoom.fxml")));

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root1.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void btnClarRoom( ) {timeTxtRoom.setText("");
    }
}
