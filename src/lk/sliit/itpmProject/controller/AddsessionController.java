package lk.sliit.itpmProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.*;
import lk.sliit.itpmProject.demoData.AddSessionDemo;
import lk.sliit.itpmProject.dto.*;
import lk.sliit.itpmProject.util.LecturerTM;

public class AddsessionController implements Initializable {

    public TextField cmb_selected_lecture;
    public ChoiceBox<String> cmb_select_tag;
    public JFXComboBox<String> cmb_select_lecture;
    public ChoiceBox<String> cmb_select_group;
    public ChoiceBox<String> cmb_select_subject;
    public TextField cmb_No_of_Student;
    public TextField cmb_select_duration_hrs;

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
    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.AddStudent);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.AddLecturer);
    private final AddTagBO addTagBO = BOFactory.getInstance().getBO(BOTypes.AddTag);
    private final AddSubjectBO addSubjectBO = BOFactory.getInstance().getBO(BOTypes.AddSubject);
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);

    String value1 = "";
    String value2 = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();
            ObservableList<String> lecturerTMS = cmb_select_lecture.getItems();
            for (AddLecturerDTO addLecturerDtO : addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        } catch (Exception e) {
        }

        try {
            List<AddSubjectDTO> addSubjectDTOList = addSubjectBO.findAllSubjects();
            ObservableList<String> subject = cmb_select_subject.getItems();
            for (AddSubjectDTO addSubjectDTO : addSubjectDTOList) {
                subject.add(addSubjectDTO.getSubCode());
            }
        } catch (Exception e) {

        }

        try {
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
            ObservableList<String> studentTMS = cmb_select_group.getItems();
            for (AddStudentDTO addStudentDTO : addStudentDTOList) {
                studentTMS.add(addStudentDTO.getSubGroupId());
            }
        } catch (Exception e) {

        }

        try {
            List<AddTagDTO> addTagDTOList = addTagBO.findAllTags();
            ObservableList<String> tag = cmb_select_tag.getItems();
            for (AddTagDTO addTagDTO : addTagDTOList) {
                tag.add(addTagDTO.getTagName());
            }
        } catch (Exception e) {

        }
        cmb_selected_lecture.setText(AddSessionDemo.selectedLecturer);
        cmb_No_of_Student.setText(String.valueOf(AddSessionDemo.student));
        cmb_select_duration_hrs.setText(String.valueOf(AddSessionDemo.durationHrs));
        cmb_select_lecture.setValue(AddSessionDemo.selectLecturer);
        cmb_select_tag.setValue(AddSessionDemo.selectTag);
        cmb_select_subject.setValue(AddSessionDemo.subjectId);
        cmb_select_group.setValue(AddSessionDemo.selectGroup);

    }

    public void btn_onaction_submit(ActionEvent event) throws Exception {
        AddSessionDTO addSessionDTO;
        int lastItemCode = sessionManageBO.getLastItemCode();
        if(lastItemCode != 0) {

            int menuDTO1 = 0;
            try {
                addSessionDTO = sessionManageBO.findAllSessions(String.valueOf(AddSessionDemo.id));
                menuDTO1 = addSessionDTO.getId();

            } catch (NullPointerException d) {
                int maxId = (lastItemCode);
                if (AddSessionDemo.id == (maxId)) {
                    AddSessionDemo.id = ((maxId));
                } else {
                    maxId++;
                    AddSessionDemo.id= ((maxId));
                }
            }

        } else  {
            AddSessionDemo.id =1;
        }



        if (Pattern.matches("\\d+", cmb_select_duration_hrs.getText())) {
            if (Pattern.matches("\\d+", cmb_No_of_Student.getText())) {

                }
                int No_of_Student = Integer.parseInt(cmb_No_of_Student.getText());
                String select_tag = cmb_select_tag.getValue();
                String select_group = cmb_select_group.getValue();
                String select_Subject = cmb_select_subject.getValue();
                int select_duration_hrs = Integer.parseInt(cmb_select_duration_hrs.getText());


                String lec1 = cmb_selected_lecture.getText();
                System.out.println(lec1);

                String lec2 = "";
                String yo[] = lec1.split(" ");
                int count = 0;

                for (int i = 0; i < yo.length; i++) {
                    if (i == 0) {
                        lec1 = yo[i];
                    }
                    if (i == 1) {
                        lec2 = yo[i];
                    }
                }

                AddSessionDTO addSessionDTO2 = new AddSessionDTO(
                        AddSessionDemo.id,
                        lec1,
                        select_tag,
                        lec2,
                        select_group,
                        No_of_Student,
                        select_Subject,
                        select_duration_hrs
                );

                try {
                    try {
                        addSessionDTO = (sessionManageBO.findAllSessions(String.valueOf(AddSessionDemo.id)));
                        System.out.println("4444444444444444444444");
                        sessionManageBO.updateSession(addSessionDTO2);
                        new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    }catch (Exception s){
                        sessionManageBO.saveSession(addSessionDTO2);
                        new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                cmb_No_of_Student.setText("");
                cmb_select_duration_hrs.setText("");
                cmb_select_tag.setValue(null);
                cmb_select_group.setValue(null);
                cmb_select_subject.setValue(null);
                cmb_selected_lecture.setText("");
                value1 = "";
                value2 = "";
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Enter Number to the Student").show();
            }
        AddSessionDemo.id = 0;
        }


    public void selectLectureMouseClick(ActionEvent actionEvent) {

        if (value1.equals("")) {
            String lectureTxt = cmb_select_lecture.getValue();
            cmb_selected_lecture.setText(lectureTxt);
            value1 = lectureTxt;

        } else if ((!value1.equals("")) && (value2.equals(""))) {
            String lectureTxt = cmb_select_lecture.getValue();
            cmb_selected_lecture.setText(value1 + " " + lectureTxt);
            value2 = lectureTxt;
        } else if (!value2.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Max 2").show();
        }

    }

    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddStudent.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AddWorkingDaysAndHours.fxml"));
                    root = fxmlLoader.load();
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AddRBLocation.fxml"));
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

    @FXML
    void initialize() {

    }

    public void btn_NEXT_ONACTION(ActionEvent event) {

    }

    public void btn_onaction_clear(ActionEvent event) {
    }


    public void btn_onaction_back(ActionEvent event) {
    }


}
