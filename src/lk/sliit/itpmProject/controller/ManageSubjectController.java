package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.sliit.itpmProject.util.SubjectTM;

public class ManageSubjectController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SubjectTM> tblSubject;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private CheckBox chSem1;

    @FXML
    private CheckBox chSem2;

    @FXML
    private TextField txtSubjectName;

    @FXML
    private TextField txtSubCode;

    @FXML
    private TextField txtNumOfLH;

    @FXML
    private TextField txtNoOfTH;

    @FXML
    private TextField txtNoOfLabH;

    @FXML
    private TextField txtOfEH;

    @FXML
    private JFXComboBox<?> cmbOffredyear;

    @FXML
    void btnOnAction_Clear(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {

    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

