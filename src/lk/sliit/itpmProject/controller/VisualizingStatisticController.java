package lk.sliit.itpmProject.controller;

import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.StudentStaticsBO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizingStatisticController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private BarChart<Integer, Integer> barChartX;

    @FXML
    private Label lblRLect;

    @FXML
    private Label lblRStudent;

    @FXML
    private Label lblRegiSubject;

    @FXML
    private Label lblRegiRooms;

    @FXML
    private Label lblLLecturer;

    @FXML
    private Label lblLSubject;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    @FXML
    private Label lblLGroup;

    private final StudentStaticsBO staticsBO = BOFactory.getInstance().getBO(BOTypes.StudentStatics);



    @Override
    public void initialize(URL location, ResourceBundle resources) {

XYChart.Series set1 = new XYChart.Series<>();
XYChart.Series set2 = new XYChart.Series<>();
set1.setName("LecturerRooms");
set2.setName("Laboratories");

        int labCount = 0;
        try {
            labCount = staticsBO.findLabCount();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(labCount);


        int labLectureCount = 0;
        try {
            labLectureCount = staticsBO.findLecturerHallCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(labLectureCount);


        set2.getData().add(new XYChart.Data("LecturerRooms ",labLectureCount));
set1.getData().add(new XYChart.Data("Laboratories ",labCount));
barChartX.getData().addAll(set1);
barChartX.getData().addAll(set2);


        try {
           int registeredLecturers = staticsBO.findLectureCount();

            lblRLect.setText(String.valueOf(registeredLecturers));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        try {
            int studentCount = staticsBO.findStudentCount();

            lblRStudent.setText(String.valueOf(studentCount));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        try {
            int subjectCount = staticsBO.findSubjhectCount();

            lblRegiSubject.setText(String.valueOf(subjectCount));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        try {
            int regisRoom = staticsBO.findRegisteredRoomCount();
            lblRegiRooms.setText(String.valueOf(regisRoom));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        try {
            String latestLecturer = staticsBO.findLatestLecturer();
            lblLLecturer.setText((latestLecturer));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        try {
            String latestGroup = staticsBO.findLatestGroup();
            lblLGroup.setText((latestGroup));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        try {
            String lastSubject = staticsBO.findLatestSubject();
            lblLSubject.setText((lastSubject));

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
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

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
    }

    public void playMouseExitAnimatio(MouseEvent mouseEvent) {
    }
}
