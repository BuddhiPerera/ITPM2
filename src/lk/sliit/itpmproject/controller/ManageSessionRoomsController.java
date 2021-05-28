package lk.sliit.itpmproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sliit.itpmproject.business.BOFactory;
import lk.sliit.itpmproject.business.BOTypes;
import lk.sliit.itpmproject.business.custom.AddLocationsBO;
import lk.sliit.itpmproject.business.custom.SessionManageBO;
import lk.sliit.itpmproject.dto.AddLocationsDTO;
import lk.sliit.itpmproject.dto.LoadSessionDataDTO;

public class ManageSessionRoomsController implements Initializable {
    @FXML
    TextArea selectedSessionTxt;
    @FXML
    JFXComboBox<String> selectSessionW;
    @FXML
    JFXComboBox<String> selectTAgW;
    @FXML
    JFXComboBox<String> selectTAgWRoom;
    @FXML
    JFXComboBox<String> selectLectureW;
    @FXML
    JFXComboBox<String> selectSessionWRoom;
    @FXML
    JFXComboBox<String> selectSubjectWRoom;
    @FXML
    JFXComboBox<String> selectSubjectW;
    @FXML
    JFXComboBox<String> selectGroupWRoom;
    @FXML
    JFXComboBox<String> selectGroupW;
    @FXML
    JFXComboBox<String> selectLectureWRoom;
    @FXML
    JFXComboBox<String> selectCSession;
    @FXML
    JFXComboBox<String> scelectCSeasonRoom;
    @FXML
    private AnchorPane root;


    private final AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.ADD_LOCATIONS);
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.ADD_SESSION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ObservableList<String> studentTMS2;
            ObservableList<String> selectTAgW2;
            ObservableList<String> selectLecture2;
            ObservableList<String> selectGroupW1;
            ObservableList<String> selectSubjectWq;
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadSessionTable();
            studentTMS2 = selectSessionW.getItems();
            selectTAgW2 = selectTAgW.getItems();
            selectLecture2 = selectLectureW.getItems();
            selectGroupW1 = selectGroupW.getItems();
            selectSubjectWq = selectSubjectW.getItems();
            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {
                studentTMS2.add((addSessionDTO.getSubjectName()));
                selectTAgW2.add((addSessionDTO.getTagName()));
                selectGroupW1.add((addSessionDTO.getGroupId()));
                selectLecture2.add((addSessionDTO.getLectureOne()));
                selectSubjectWq.add((addSessionDTO.getSubjectCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObservableList<String> studentTMS2;
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadConsSessionTable();
            studentTMS2 = selectCSession.getItems();
            Set<String> strings = new HashSet<>();

            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {
                if (!strings.contains(addSessionDTO.getId())) {
                    strings.add(addSessionDTO.getId());
                    studentTMS2.add((addSessionDTO.getId() + "-" + addSessionDTO.getSubjectName()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            List<AddLocationsDTO> addLocationsDTOList = addLocationsBO.findAllLocations();

            ObservableList<String> lecturerTMS = selectSessionWRoom.getItems();
            ObservableList<String> lecturerTMS2 = selectTAgWRoom.getItems();
            ObservableList<String> lecturerTMS3 = selectLectureWRoom.getItems();
            ObservableList<String> lecturerTMS4 = selectGroupWRoom.getItems();
            ObservableList<String> lecturerTMS5 = selectSubjectWRoom.getItems();
            ObservableList<String> lecturerTMS6 = scelectCSeasonRoom.getItems();

            for (AddLocationsDTO addLecturerDtO : addLocationsDTOList) {
                lecturerTMS.add(addLecturerDtO.getRoomName());
                lecturerTMS2.add(addLecturerDtO.getRoomName());
                lecturerTMS3.add(addLecturerDtO.getRoomName());
                lecturerTMS4.add(addLecturerDtO.getRoomName());
                lecturerTMS5.add(addLecturerDtO.getRoomName());
                lecturerTMS6.add(addLecturerDtO.getRoomName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

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

    public void btnSubmitOnAction() {
        String val1 = selectSessionW.getValue();
        String val2 = selectTAgW.getValue();
        String val3 = selectLectureW.getValue();
        String val4 = selectGroupW.getValue();
        String val5 = selectSubjectW.getValue();
        String val6 = selectCSession.getValue();


        if (val1 != null) {

            String val1R = selectLectureWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select  Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateLectureRoom(val3, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room  Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something wrong").show();
                }
            }
//            /////////////////////////////

        } else if (val2 != null) {

            String val1R = selectTAgWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select  Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateTagRoom(val2, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room  Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something wrong").show();
                }
            }
        } else if (val3 != null) {

            String val1R = selectSessionWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a  Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateSessionRoom(val1, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added ").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong ").show();
                }
            }
        } else if (val4 != null) {

            String val1R = selectGroupWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room  ").show();
            } else {
                try {
                    sessionManageBO.setUpdateGroupRoom(val4, val1R);
                    new Alert(Alert.AlertType.INFORMATION, " Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, " went wrong").show();
                }
            }
        } else if (val5 != null) {

            String val1R = selectSubjectWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateSubjectRoom(val5, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                }
            }


        } else if (val6 != null) {

            String val1R = scelectCSeasonRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateConstRoom(val6, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                }
            }


        }


    }

    public void selectSessionWOnAction() {
        clickEve();
    }

    void clickEve() {
        String val = "";
        String val1 = selectSessionWRoom.getValue();
        String val2 = selectTAgW.getValue();
        String val3 = selectTAgWRoom.getValue();
        String val4 = selectLectureW.getValue();
        String val5 = selectLectureWRoom.getValue();
        String val6 = selectGroupW.getValue();
        String val7 = selectGroupWRoom.getValue();
        String val8 = selectSubjectW.getValue();
        String val9 = selectSubjectWRoom.getValue();
        String val10 = selectSessionW.getValue();
        String val11 = selectCSession.getValue();
        String val12 = scelectCSeasonRoom.getValue();
        if (val1 == null) {
            val1 = "";
        }
        if (val2 == null) {
            val2 = "";
        }
        if (val3 == null) {
            val3 = "";
        }
        if (val4 == null) {
            val4 = "";
        }
        if (val5 == null) {
            val5 = "";
        }
        if (val6 == null) {
            val6 = "";
        }
        if (val7 == null) {
            val7 = "";
        }
        if (val8 == null) {
            val8 = "";
        }
        if (val9 == null) {
            val9 = "";
        }
        if (val10 == null) {
            val10 = "";
        }
        if (val11 == null) {
            val11 = "";
        }
        if (val12 == null) {
            val12 = "";
        }
        val = val10 + " - " + val1 + " , "
                + val2 + " - " + val3 + " , "
                + val4 + " - " + val5 + " , "
                + val6 + " - " + val7 + " , "
                + val8 + " - " + val9 + " , "
                + val11 + " - " + val12;
        selectedSessionTxt.setText(val);
    }

    public void selectTAgWOnAction() {
        clickEve();
    }

    public void selectSessionWRoomOnAction() {
        clickEve();
    }

    public void selectTAgWRoomOnAction() {
        clickEve();
    }

    public void selectLectureWOnAction() {
        clickEve();
    }

    public void selectLectureWRoomOnAction() {
        clickEve();
    }

    public void selectGroupWOnAction() {
        clickEve();
    }

    public void selectGroupWRoomOnAction() {
        clickEve();
    }

    public void selectSubjectWOnAction() {
        clickEve();
    }

    public void selectSubjectWRoomOnAction() {
        clickEve();
    }

    public void scelectCSessonRoomOnAction() {
        clickEve();
    }

    public void selectCSessionOnActon() {
        clickEve();
    }
}
