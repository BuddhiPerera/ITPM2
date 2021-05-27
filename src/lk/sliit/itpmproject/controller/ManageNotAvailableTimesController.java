package lk.sliit.itpmproject.controller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.SessionManageBO;
import lk.sliit.itpmproject.dto.ManageNotAvbTimeDTO;
import lk.sliit.itpmproject.util.ManageNotAvbTimeTM;

public class ManageNotAvailableTimesController implements Initializable {


    public TableView<ManageNotAvbTimeTM> tblNotAvbTimes;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root1;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconLecture;

    @FXML
    private ImageView iconStudent;

    @FXML
    private ImageView iconTImeTable;

    @FXML
    private ImageView iconLocation;
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.ADD_SESSION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblNotAvbTimes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNotAvbTimes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblNotAvbTimes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sessionId"));

        try {
            List<ManageNotAvbTimeDTO> manageNotAvbTimeDTOS = sessionManageBO.findAllData();
            ObservableList<ManageNotAvbTimeTM> sessionTMS = tblNotAvbTimes.getItems();
            for (ManageNotAvbTimeDTO manageNotAvbTimeDTO : manageNotAvbTimeDTOS) {
                sessionTMS.add(new ManageNotAvbTimeTM(
                        manageNotAvbTimeDTO.getId(),
                        manageNotAvbTimeDTO.getDuration(),
                        manageNotAvbTimeDTO.getLecture(),
                        manageNotAvbTimeDTO.getGroupId(),
                        manageNotAvbTimeDTO.getSubGroupId(),
                        manageNotAvbTimeDTO.getSessionId()
                ));
            }
        } catch (Exception e) {

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

    @FXML
    void playMouseEnterAnimation(MouseEvent event) {

    }

    @FXML
    void playMouseExitAnimatio(MouseEvent event) {

    }


    public void btnRefresh(ActionEvent actionEvent) {
    }

    public void btnBackw(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmproject/view/Sessions.fxml"));

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

    public void btnDelete(ActionEvent actionEvent) {
        ManageNotAvbTimeTM selectedItem = tblNotAvbTimes.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            new Alert(Alert.AlertType.INFORMATION,"Select a  Row").show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            try {
                sessionManageBO.deleteItemNaLec(selectedItem.getId());
                tblNotAvbTimes.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }
}
