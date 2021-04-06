package lk.sliit.itpmProject.controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddTagBO;
import lk.sliit.itpmProject.dto.AddTagDTO;
import lk.sliit.itpmProject.util.StudentTM;
import lk.sliit.itpmProject.util.TagsTM;

public class ManageTagController implements Initializable {

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

    private final AddTagBO addTagBO = BOFactory.getInstance().getBO(BOTypes.AddTag);

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
}
