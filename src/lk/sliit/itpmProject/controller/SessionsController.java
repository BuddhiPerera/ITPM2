package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.util.Callback;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.*;
import lk.sliit.itpmProject.demoData.AddSessionDemo;
import lk.sliit.itpmProject.dto.*;
import lk.sliit.itpmProject.util.SessionTM;
import lk.sliit.itpmProject.util.StudentTM;
import org.drools.template.parser.BooleanCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SessionsController implements Initializable {

    @FXML
    public JFXTextField NaTimeLectureTxt;
    @FXML
    public JFXComboBox<String> NaTimeLectureCombo;
    @FXML
    public JFXButton btnSubmitTeacher;
    @FXML
    public JFXButton btnViewTeacher;
    @FXML
    public JFXComboBox<String> NaTimeLectureSessionIdTxt;
    @FXML
    public JFXComboBox NaTimeLectureSubGroupTxt;
    @FXML
    public JFXComboBox<String> NaTimeLectureGroup;
    @FXML
    public JFXButton btnClearTeacher;
    public AnchorPane root1;

    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);
    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.AddStudent);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.AddLecturer);
    public TableView<SessionTM> tblConsecutive;
    public TableView<SessionTM> tblNonOverLapping;
    public TableView<SessionTM> tblParallel;
    public JFXTextField NaTimeLectureTxt1;
    public JFXTextField NaSGroupTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblConsecutive.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tblConsecutive.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblConsecutive.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lectureOne"));
        tblConsecutive.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lectureTwo"));
        tblConsecutive.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        tblConsecutive.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblConsecutive.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblConsecutive.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("tagName"));

        tblNonOverLapping.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tblNonOverLapping.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNonOverLapping.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lectureOne"));
        tblNonOverLapping.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lectureTwo"));
        tblNonOverLapping.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        tblNonOverLapping.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblNonOverLapping.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblNonOverLapping.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("tagName"));

        tblParallel.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tblParallel.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblParallel.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lectureOne"));
        tblParallel.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lectureTwo"));
        tblParallel.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        tblParallel.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblParallel.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblParallel.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("tagName"));

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

        }
        try {
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();

            ObservableList<String> lecturerTMS = NaTimeLectureCombo.getItems();
            NaTimeLectureCombo.setValue("Select Name");
            for (AddLecturerDTO addLecturerDtO : addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        } catch (Exception e) {
        }

        try {
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
            ObservableList studentTMS = NaTimeLectureGroup.getItems();
            ObservableList studentTMS2 = NaTimeLectureSubGroupTxt.getItems();
            for (AddStudentDTO addStudentDTO : addStudentDTOList) {
                studentTMS.add(addStudentDTO.getGroupId());
                studentTMS2.add(addStudentDTO.getSubGroupNo());
            }
        } catch (Exception e) {

        }

        try {
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadSessionTable();
            ObservableList studentTMS2 = NaTimeLectureSessionIdTxt.getItems();
            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {

                studentTMS2.add(Integer.valueOf(addSessionDTO.getId()) + "-" + addSessionDTO.getLectureOne() + " " +
                        addSessionDTO.getSubjectCode() +
                        " " + addSessionDTO.getSubjectName() + " " + addSessionDTO.getGroupId());
            }
        } catch (Exception e) {

        }
    }

    public void btnAddSessionOnAction(ActionEvent actionEvent) throws Exception {

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
                    root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AddWorkingDaysAndHours.fxml"));
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

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
    }

    public void playMouseExitAnimatio(MouseEvent mouseEvent) {
    }

    public void btnClearTeacherOnAction(ActionEvent actionEvent) {
        NaTimeLectureSubGroupTxt.setValue("");
        NaTimeLectureCombo.setValue("");
        NaTimeLectureGroup.setValue("");
        NaTimeLectureSessionIdTxt.setValue("");
        NaTimeLectureTxt.setText("");
    }

    public void btnSubmitTeacherOnAction(ActionEvent actionEvent) {

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


        String lectureComboValue = NaTimeLectureCombo.getValue();
        String naTimeLectureTxtText = NaTimeLectureTxt.getText();


        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                lectureComboValue,
                naTimeLectureTxtText
        );
        try {

            sessionManageBO.saveNASessionLec(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewTeacherOnAction(ActionEvent actionEvent) throws IOException {
        Parent root;

        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("../view/ManageNotAvlilableTimes.fxml")));

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

    public void btnAddSessionParaOnAction(ActionEvent actionEvent) {
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
            new Alert(Alert.AlertType.INFORMATION, "Data Added").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
    }

    public void btnAddSessionOnNAOverLapAction(ActionEvent actionEvent) {
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
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
    }

    public void submitGroupNa(ActionEvent event) {
        int maxCode = 0;
        try {
            int lastItemCode = sessionManageBO.getLastNotAvbGroups();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }

        String lectureComboValu = NaTimeLectureGroup.getValue();
        String naTimeLectureTxtText = NaTimeLectureTxt1.getText();

        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                lectureComboValu,
                naTimeLectureTxtText
        );
        try {

            sessionManageBO.saveNASessionGroup(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();

        } catch (Exception e) {

        }
    }

    public void submitSGroupOnAction(ActionEvent actionEvent) {
        int maxCode = 0;
        try {
            int lastItemCode = sessionManageBO.getLastNotAvbSubGroups();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
        int s = (int) NaTimeLectureSubGroupTxt.getValue();
        String lectureComboValu = String.valueOf(s);
        String naTimeLectureTxtText = NaSGroupTxt.getText();

        AddSessionNALectureDTO addSessionNALectureDTO = new AddSessionNALectureDTO(
                maxCode,
                lectureComboValu,
                naTimeLectureTxtText
        );
        try {

            sessionManageBO.saveNASessionSubGroup(addSessionNALectureDTO);
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();

        } catch (Exception e) {

        }
    }
}
