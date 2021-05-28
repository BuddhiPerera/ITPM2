package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.sliit.itpmproject.business.custom.AddStudentBO;
import lk.sliit.itpmproject.dto.AddStudentDTO;
import lk.sliit.itpmproject.util.StudentTM;


public class ManageStudentGroupsController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtGroupId;

    @FXML
    private TextField txtSubGroupId;

    @FXML
    private Spinner<Integer> spinGroupNo;

    @FXML
    private Spinner<Integer> spinSubGroupNo;

    @FXML
    private JFXComboBox<String> cmbProgramme;

    @FXML
    private Spinner<Integer> spinSemester;

    @FXML
    private Spinner<Integer> spinYear;

    private final AddStudentBO addStudentBO = BOFactory.getInstance().getBO(BOTypes.ADD_STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("year"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("semester"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("programme"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("groupNo"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("subGroupNo"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tblStudent.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("subGroupId"));

        ObservableList<String> list1;
        list1 = cmbProgramme.getItems();
        list1.add("IT");
        list1.add("CSSE");
        list1.add("CSE");
        list1.add("IM");

        try {
            List<AddStudentDTO> addStudentDTOList = addStudentBO.findAllStudent();
            ObservableList<StudentTM> studentTMS = tblStudent.getItems();
            for (AddStudentDTO addStudentDTO : addStudentDTOList
            ) {
                studentTMS.add(new StudentTM(
                        addStudentDTO.getId(),
                        addStudentDTO.getYear(),
                        addStudentDTO.getSemester(),
                        addStudentDTO.getProgramme(),
                        addStudentDTO.getGroupNo(),
                        addStudentDTO.getSubGroupNo(),
                        addStudentDTO.getGroupId(),
                        addStudentDTO.getSubGroupId()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                btnDelete.setDisable(true);
                return;
            }

            btnDelete.setDisable(false);

            txtGroupId.setText(selectedItem.getGroupId());
            txtSubGroupId.setText(selectedItem.getSubGroupId());
            int spinSem = selectedItem.getSemester();
            int spinGroup = selectedItem.getGroupNo();
            int spinSubGroup = selectedItem.getSubGroupNo();
            int spinYe = selectedItem.getYear();

            SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, spinSem);
            spinSemester.setValueFactory(valueFactory);
            SpinnerValueFactory<Integer> valueFactory1 =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, spinGroup);
            spinGroupNo.setValueFactory(valueFactory1);
            SpinnerValueFactory<Integer> valueFactory2 =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, spinSubGroup);
            spinSubGroupNo.setValueFactory(valueFactory2);
            SpinnerValueFactory<Integer> valueFactory3 =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, spinYe);
            spinYear.setValueFactory(valueFactory3);

            cmbProgramme.setValue(selectedItem.getProgramme());
        });
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        cmbProgramme.setValue(null);
        txtGroupId.setText(null);
        txtSubGroupId.setText(null);

        SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0);
        this.spinGroupNo.setValueFactory(spinnerValueFactory2);
        this.spinSubGroupNo.setValueFactory(spinnerValueFactory1);
        this.spinSemester.setValueFactory(spinnerValueFactory3);
        this.spinYear.setValueFactory(spinnerValueFactory4);

        spinGroupNo.setEditable(false);
        spinSubGroupNo.setEditable(false);
        spinSemester.setEditable(false);
        spinYear.setEditable(false);
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        boolean val = buttonType.isPresent();
        if (val) {
            ButtonType a = buttonType.get();
            if (a == ButtonType.YES) {
                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
                try {
                    addStudentBO.deleteItem(selectedItem.getId());
                    tblStudent.getItems().remove(selectedItem);
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                    Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        int year = spinYear.getValue();
        int semester = spinSemester.getValue();
        String programme = cmbProgramme.getValue();
        int subGroupNo = spinSubGroupNo.getValue();
        int groupNo = spinGroupNo.getValue();

        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        try {
            addStudentBO.updateStudent(new AddStudentDTO(
                    selectedItem.getId(),
                    year,
                    semester,
                    programme,
                    groupNo,
                    subGroupNo,
                    txtGroupId.getText(),
                    txtSubGroupId.getText()
            ));
            selectedItem.setGroupId(txtGroupId.getText());
            selectedItem.setGroupNo(groupNo);
            selectedItem.setSubGroupNo(subGroupNo);
            selectedItem.setSubGroupId(txtSubGroupId.getText());
            selectedItem.setYear((year));
            selectedItem.setProgramme(programme);
            selectedItem.setSemester(semester);
            tblStudent.refresh();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root1 = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconLecture":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                case "iconStudent":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
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


}
