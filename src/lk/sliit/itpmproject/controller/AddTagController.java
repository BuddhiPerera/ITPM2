package lk.sliit.itpmproject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
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
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.AddTagBO;
import lk.sliit.itpmproject.dto.AddTagDTO;

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

    private final AddTagBO addTagBO = BOFactory.getInstance().getBO(BOTypes.ADD_TAG);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list;
        cmbAddTag.setValue("Lecture");
        list = cmbAddTag.getItems();
        list.add("Lecture");
        list.add("Tutorial");
        list.add("Practical");

    }

    @FXML
    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root1 = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "iconHome":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                default:
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
                    root1 = fxmlLoader.load();
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

    public void btnSaveTagOnAction() {
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
                e.printStackTrace();
            }

        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Tag Code").show();
        }

    }

    public void btnOnActionManage() throws IOException {
        Parent root1 = null;

        root1 = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/ManageTag.fxml"));

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

    public void btnOnActionClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        txtAddTag.setText(null);
        txtAddTagCode.setText(null);
        cmbAddTag.setValue(null);
    }
}