package lk.sliit.itpmProject.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dto.ManageNotAvbTimeDTO;
import lk.sliit.itpmProject.util.ManageNotAvbTimeTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageNotAvailableTimesControllerRoom implements Initializable {
    public TableView<ManageNotAvbTimeTM> tblNotAvbTimes;
    public AnchorPane root1;
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);

    public void playMouseExitAnimatio(MouseEvent mouseEvent) {
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
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
                sessionManageBO.deleteItemNaLecRoom(selectedItem.getId());
                tblNotAvbTimes.getItems().remove(selectedItem);
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong").show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }

    public void btnBackw(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        Parent root = null;

        root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/itpmProject/view/Sessions.fxml"));

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

    public void btnRefresh(ActionEvent actionEvent) {
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblNotAvbTimes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNotAvbTimes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblNotAvbTimes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sessionId"));

        try {
            List<ManageNotAvbTimeDTO> manageNotAvbTimeDTOS = sessionManageBO.findAllDataRom();
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
}
