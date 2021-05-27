package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.AddLecturerBO;
import lk.sliit.itpmproject.dto.AddLecturerDTO;
import lk.sliit.itpmproject.dto.DaysDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddLecturerController implements Initializable {

     @FXML
     JFXCheckBox thursdayCB;
    @FXML
     JFXCheckBox wednesdayCB;
    @FXML
    JFXCheckBox tuesdayCB;
    @FXML
    JFXCheckBox mondayCB;
    @FXML
    JFXCheckBox sundayCB;
    @FXML
    JFXCheckBox saturdayCB;
    @FXML
    JFXCheckBox fridayCB;
    @FXML
    Button btnAddSubject;
    @FXML
     Button btnManage;
    @FXML
    Button btnsession;
    @FXML
    Button btnGenerate;
    @FXML
     AnchorPane root;
    @FXML
    private AnchorPane root1;

    @FXML
    private Button btnLecSave;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView iconStudent;

    @FXML
    private ImageView iconLocation;

    @FXML
    private ImageView iconTimeTable;

    @FXML
    private JFXComboBox<String> buildingCombo;

    @FXML
    private JFXComboBox<String> centerCombo;

    @FXML
    private JFXComboBox<String> departmentCombo;

    @FXML
    private JFXComboBox<String> facultyCombo;

    @FXML
    private JFXComboBox<String> levelCombo;

    @FXML
    private JFXTextField rankTxt;

    @FXML
    private JFXTextField empIdTxt;

    @FXML
    private JFXTextField nameTxt;

    private final AddLecturerBO addLecturerBO = BOFactory.getInstance().getBO(BOTypes.ADD_LECTURER);
    String professor= "Professor";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list1;
        ObservableList<String> list2;
        ObservableList<String> list3;
        ObservableList<String> list4;
        ObservableList<String> list5;
        mondayCB.setSelected(true);
        thursdayCB.setSelected(true);
        wednesdayCB.setSelected(true);
        tuesdayCB.setSelected(true);
        fridayCB.setSelected(true);
        rankTxt.setDisable(true);
        centerCombo.setValue("Malabe");
        list1 = centerCombo.getItems();
        list1.add("Malabe");
        list1.add("Metro");
        list1.add("Matara");
        list1.add("Kandy");
        list1.add("Kurunagala");
        list1.add("Jaffna");

        levelCombo.setValue(professor);
        list2 = levelCombo.getItems();
        list2.add(professor);
        list2.add("Assistant Professor");
        list2.add("Senior Lecturer(HG)");
        list2.add("Senior Lecturer");
        list2.add("Lecturer");
        list2.add("Assistant Lecturer");

        list3 = facultyCombo.getItems();
        list3.add("Computing Faculty");
        list3.add("Business Faculty");
        list3.add("Engineering Faculty");
        list3.add("Architecture Faculty");
        list3.add("Faculty of Humanities and Science");

        list4 = departmentCombo.getItems();
        list4.add("IT");
        list4.add("SE");
        list4.add("ISE");
        list4.add("DS");
        list4.add("CS");
        list4.add("IM");
        list4.add("CSNE");
        list4.add("Civil");
        list4.add("Electronic Engineering");
        list4.add("Mechanical Engineering");
        list4.add("QS");

        list5 = buildingCombo.getItems();
        list5.add("Block A");
        list5.add("Block B");
        list5.add("Block E");
        list5.add("Block F");
    }

    @FXML
    void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent rootx = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    rootx = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    rootx = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    rootx = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                default:
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
                    rootx = fxmlLoader.load();
            }

            if (rootx != null) {
                Scene subScene = new Scene(rootx);
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
    void adlGenrateRankOnAction(ActionEvent event) {
        String tempLevel = "";
        String lecId = empIdTxt.getText();
        String level = levelCombo.getValue();

        if (level.equals( professor)) {
            tempLevel = ( "1");
        } else if (level .equals( "Assistant Professor")) {
            tempLevel  = ( "2");
        } else if (level .equals( "Senior Lecturer(HG)") ){
            tempLevel =( "3");
        } else if (level .equals("Senior Lecturer")) {
            tempLevel =( "4");
        } else if (level.equals("Lecturer")) {
            tempLevel =( "5");
        } else if (level.equals( "Assistant Lecturer")) {
            tempLevel = "6";
        }

        rankTxt.setText(tempLevel + "." + lecId);
    }

    @FXML
    public void btnSaveOnAction(ActionEvent event) throws SQLException {
        String empId = empIdTxt.getText();
        int maxCode = 0;

        boolean val = Pattern.matches("\\d{6}", empId);

        if (val) {
            int exists = addLecturerBO.checkExists(empId);
            if (exists == 1) {
                new Alert(Alert.AlertType.ERROR, "Employee Id already exists").show();
            } else {
                try {
                    int lastItemCode = addLecturerBO.getLastItemCode();
                    if (lastItemCode == 0) {
                        maxCode = 1;
                    } else {
                        maxCode = lastItemCode + 1;
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                }


                String lName = nameTxt.getText();
                String department = departmentCombo.getValue();
                String faculty =facultyCombo.getValue();
                String center = centerCombo.getValue();
                String buildingNo =  buildingCombo.getValue();
                String level = levelCombo.getValue();
                String rank = rankTxt.getText();


                boolean saturday =saturdayCB.isSelected();
                boolean sundayC=sundayCB.isSelected();
                boolean mondayC=mondayCB.isSelected();
                boolean thursdayC=thursdayCB.isSelected();
                boolean wednesdayC=wednesdayCB.isSelected();
                boolean tuesdayC=tuesdayCB.isSelected();
                boolean fridayC=fridayCB.isSelected();

                DaysDTO daysDTO = new DaysDTO(
                        empId,
                        saturday,
                        sundayC,
                        mondayC,
                        tuesdayC,
                        wednesdayC,
                        thursdayC,
                        fridayC

                );

//                //////////////////////////////////////////////////////////////////
                AddLecturerDTO addLecturerDTO = new AddLecturerDTO(
                        maxCode,
                        empId,
                        lName,
                        department,
                        faculty,
                        center,
                        buildingNo,
                        level,
                        rank
                );

                try {
                   boolean val1 = addLecturerBO.saveLecturerDays(daysDTO);
                   if (val1){
                       addLecturerBO.saveLecturer(addLecturerDTO);
                       new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
                   }

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Enter 6 Digit Number to the Employee Id").show();
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        empIdTxt.setText("");
        nameTxt.setText("");
        rankTxt.setText("");
        departmentCombo.setValue(null);
        facultyCombo.setValue(null);
        centerCombo.setValue(null);
        buildingCombo.setValue(null);
        levelCombo.setValue(null);
    }

    @FXML
    public void btnOnActionAddSubject(ActionEvent actionEvent) throws IOException {
        Parent rootx = null;

        rootx = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddSubject.fxml"));

        if (rootx != null) {
            Scene subScene = new Scene(rootx);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void btnOnActionManage() throws IOException {

        Parent rootx = null;

        rootx = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageLecturers.fxml"));

        if (rootx != null) {
            Scene subScene = new Scene(rootx);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void btnOnActionSession() throws IOException {

        Parent rootx = null;

        rootx = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/Managesessions.fxml"));

        if (rootx != null) {
            Scene subScene = new Scene(rootx);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void onActionMonday(ActionEvent event) {
        setWeekDAY();
    }

    @FXML
    void onActionSaturday(ActionEvent event) {
        setWeekEnd();
    }

    @FXML
    void onActionSunday(ActionEvent event) {
        setWeekEnd();
    }

    public void setWeekDAY() {
        saturdayCB.setSelected(false);
        sundayCB.setSelected(false);
    }

    public void setWeekEnd() {
        mondayCB.setSelected(false);
        thursdayCB.setSelected(false);
        wednesdayCB.setSelected(false);
        tuesdayCB.setSelected(false);
        fridayCB.setSelected(false);
    }

    @FXML
    void onActionfridayCB(ActionEvent event) {
        setWeekDAY();
    }

    @FXML
    void onActionthursdayCB(ActionEvent actionEvent) {
        setWeekDAY();
    }



}
