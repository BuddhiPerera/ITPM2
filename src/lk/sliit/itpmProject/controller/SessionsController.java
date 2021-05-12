package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddLecturerBO;
import lk.sliit.itpmProject.business.custom.AddStudentBO;
import lk.sliit.itpmProject.dto.AddLecturerDTO;
import lk.sliit.itpmProject.dto.AddStudentDTO;
import lk.sliit.itpmProject.util.StudentTM;

import java.io.IOException;
import java.net.URL;
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
    public JFXComboBox NaTimeLectureSessionIdTxt;
    @FXML
    public JFXComboBox NaTimeLectureSubGroupTxt;
    @FXML
    public JFXComboBox NaTimeLectureGroup;
    @FXML
    public JFXButton btnClearTeacher;
    public AnchorPane root1;

    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.AddStudent);
    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.AddLecturer);
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

    public void btnSubmitTeacherOnAction(ActionEvent actionEvent) {
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


    public void btnClearTeacherOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            List<AddLecturerDTO> addLecturerDTOList = addLecturerBO.findAllLecturersName();

            ObservableList<String> lecturerTMS = NaTimeLectureCombo.getItems();
            NaTimeLectureCombo.setValue("Select Name");
            for (AddLecturerDTO addLecturerDtO: addLecturerDTOList) {
                lecturerTMS.add(addLecturerDtO.getlName());
            }
        }catch (Exception e){
        }

        try{
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
            ObservableList<String> studentTMS = NaTimeLectureGroup.getItems();
            ObservableList<Integer> studentTMS2 = NaTimeLectureSubGroupTxt.getItems();
            for (AddStudentDTO addStudentDTO:addStudentDTOList) {
                studentTMS.add(addStudentDTO.getGroupId());
                studentTMS2.add(addStudentDTO.getSubGroupNo());
            }
        }catch(Exception e){

        }
    }
}
