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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import lk.sliit.itpmproject.business.custom.AddTagBO;
import lk.sliit.itpmproject.dto.AddTagDTO;
import lk.sliit.itpmproject.util.TagsTM;

public class ManageTagController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TagsTM> tblTags;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtTagName;

    @FXML
    private TextField txtTagCode;

    @FXML
    private JFXComboBox<String> cmbRelatedTag;

    private final AddTagBO addTagBO = BOFactory.getInstance().getBO(BOTypes.ADD_TAG);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblTags.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblTags.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("tagName"));
        tblTags.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tagCode"));
        tblTags.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("relatedTag"));

        ObservableList list = cmbRelatedTag.getItems();
        list.add("Lecture");
        list.add("Tutorial");
        list.add("Practical");

        try{
            List<AddTagDTO> addTagDTOList = addTagBO.findAllTags();
            ObservableList<TagsTM> tagsTMS = tblTags.getItems();
            for (AddTagDTO addTagDTO: addTagDTOList
                 ) {
                tagsTMS.add(new TagsTM(
                        addTagDTO.getId(),
                        addTagDTO.getTagName(),
                        addTagDTO.getTagCode(),
                        addTagDTO.getRelatedTag()
                ));
            }
        }catch (Exception e){

        }

        tblTags.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TagsTM>() {
            @Override
            public void changed(ObservableValue<? extends TagsTM> observable, TagsTM oldValue, TagsTM newValue) {
                TagsTM selectedItem = tblTags.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnDelete.setDisable(true);
                    return;
                }

                btnDelete.setDisable(false);

                txtTagName.setText(selectedItem.getTagName());
                txtTagCode.setText(String.valueOf(selectedItem.getTagCode()));
                cmbRelatedTag.setValue(selectedItem.getRelatedTag());
            }
        });
    }

    @FXML
    void btnOnAction_Clear(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to clear?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        txtTagCode.setText(null);
        txtTagName.setText(null);
        cmbRelatedTag.setValue(null);
    }

    @FXML
    void btnOnAction_Delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            TagsTM selectedItem = tblTags.getSelectionModel().getSelectedItem();
            try {
                addTagBO.deleteItem(selectedItem.getId());
                tblTags.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE,null,e);
            }
        }
    }

    @FXML
    void btnOnAction_Update(ActionEvent event) {
        String tagName = txtTagName.getText();
        int tagCode = Integer.parseInt(txtTagCode.getText());
        String relatedTag = cmbRelatedTag.getValue();

        TagsTM selectedItem = tblTags.getSelectionModel().getSelectedItem();
        try{
            addTagBO.updateTags(new AddTagDTO(
                    selectedItem.getId(),
                   tagName,
                   tagCode,
                   relatedTag
            ));

            selectedItem.setTagName(txtTagName.getText());
            selectedItem.setTagCode(Integer.parseInt(txtTagCode.getText()));
            selectedItem.setRelatedTag(relatedTag);

            tblTags.refresh();

            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }catch(Exception e){
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
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/MainForm.fxml"));
                    break;
                case "iconStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddStudent.fxml"));
                    break;
                case "iconLocation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddRBLocation.fxml"));
                    break;
                case "iconLecture":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/AddLecturer.fxml"));
                    break;
                case "iconTimeTable":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/sliit/itpmproject/view/AddWorkingDaysAndHours.fxml"));
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
