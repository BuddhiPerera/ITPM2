package lk.sliit.itpmProject.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.StudentStaticsBO;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizingStatisticController extends Application implements Initializable {

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
    public void start(Stage stage) throws Exception {
        stage.setTitle("JavaFX Chart Demo");
        StackPane pane = new StackPane();
        pane.getChildren().add(createBarChart());
        stage.setScene(new Scene(pane, 400, 200));
        stage.show();
    }

    public ObservableList<XYChart.Series<String, Double>> getDummyChartData() {
        ObservableList<XYChart.Series<String, Double>> data =
                FXCollections.observableArrayList();
        XYChart.Series<String, Double> as = new XYChart.Series<>();
        XYChart.Series<String, Double> bs = new XYChart.Series<>();
        XYChart.Series<String, Double> cs = new XYChart.Series<>();
        XYChart.Series<String, Double> ds = new XYChart.Series<>();
        XYChart.Series<String, Double> es = new XYChart.Series<>();
        XYChart.Series<String, Double> fs = new XYChart.Series<>();
        as.setName("A-Series");
        bs.setName("B-Series");
        cs.setName("C-Series");
        ds.setName("D-Series");
        es.setName("E-Series");
        fs.setName("F-Series");

        Random r = new Random();

        for (int i = 1900; i < 2017; i += 10) {

            as.getData().add(new XYChart.Data<>
                    (Integer.toString(i), r.nextDouble()));
            bs.getData().add(new XYChart.Data<>
                    (Integer.toString(i), r.nextDouble()));
            cs.getData().add(new XYChart.Data<>
                    (Integer.toString(i), r.nextDouble()));
            ds.getData().add(new XYChart.Data<>
                    (Integer.toString(i), r.nextDouble()));
            es.getData().add(new XYChart.Data<>
                    (Integer.toString(i), r.nextDouble()));
            fs.getData().add(new XYChart.Data<>
                    (Integer.toString(i), r.nextDouble()));
        }
        data.addAll(as, bs, cs, ds, es, fs);
        return data;
    }

    public XYChart<CategoryAxis, NumberAxis>
    createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart bc = new BarChart<>(xAxis, yAxis);
        bc.setData(getDummyChartData());
        bc.setTitle("Bar Chart on Random Number");
        return bc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



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
}
