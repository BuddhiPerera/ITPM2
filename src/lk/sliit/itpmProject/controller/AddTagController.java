package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddTagBO;
import lk.sliit.itpmProject.dto.AddTagDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddTagController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddTag;

    @FXML
    private TextField txtAddTagCode;

    @FXML
    private Button btnAddTagSave;

    @FXML
    private Button btnAddTagClear;

    @FXML
    private JFXComboBox<String> cmbAddTag;

    private final AddTagBO addTagBO = BOFactory.getInstance().getBO(BOTypes.AddTag);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmbAddTag.setValue("Lecture");
        ObservableList list = cmbAddTag.getItems();
        list.add("Lecture");
        list.add("Tutorial");
        list.add("Practical");

    }

    @FXML
    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmProject/view/AddWorkingDaysAndHours.fxml"));
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

    @FXML
    public void playMouseEnterAnimation(MouseEvent event) {

    }

    @FXML
    public void playMouseExitAnimatio(MouseEvent event) {

    }

    public void btnSaveTag_onAction(ActionEvent actionEvent) {
        String tagCode1 = (txtAddTagCode.getText());
int tagCode;
        boolean val = Pattern.matches("\\d+", tagCode1);
        if(val){
            tagCode = Integer.parseInt(tagCode1);

        int maxCode = 0;
        try {
            int lastItemCode = addTagBO.getLastItemCode();
            if (lastItemCode == 0) {
                maxCode = 1;
            } else {
                maxCode = lastItemCode + 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
        }

        String tagName = txtAddTag.getText();

        String addTag = cmbAddTag.getValue();

        AddTagDTO addTagDTO = new AddTagDTO(
                maxCode,
                tagName,
                tagCode,
                addTag
        );
        try{
            addTagBO.saveTag(addTagDTO);
            new Alert(Alert.AlertType.INFORMATION, "Tag Added Successfully").show();
        }catch (Exception e){
            System.out.println(e);
        }
    }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Tag Code").show();
        }
    }

    public void btnOnAction_Manage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/ManageTag.fxml"));

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

    public void btnOnAction_Clear(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        txtAddTag.setText(null);
        txtAddTagCode.setText(null);
        cmbAddTag.setValue(null);
    }
}
