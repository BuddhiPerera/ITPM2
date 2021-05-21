package lk.sliit.itpmProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.sliit.itpmProject.business.BOFactory;
import lk.sliit.itpmProject.business.BOTypes;
import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;

public class ManageSessionRoomsController implements Initializable {

    public TextArea selectedSessionTxt;
    public JFXComboBox selectSessionW;
    public JFXComboBox selectTAgW;
    public JFXComboBox selectTAgWRoom;
    public JFXComboBox selectLectureW;
    public JFXComboBox selectSessionWRoom;
    public JFXComboBox selectSubjectWRoom;
    public JFXComboBox selectSubjectW;
    public JFXComboBox selectGroupWRoom;
    public JFXComboBox selectGroupW;
    public JFXComboBox selectLectureWRoom;
    public JFXComboBox selectCSession;
    public JFXComboBox scelectCSeasonRoom;
    @FXML
    private AnchorPane root;


    private final AddLocationsBO addLocationsBO = BOFactory.getInstance().getBO(BOTypes.AddLocations);

    private final AddLocationsBO addLocationBO = BOFactory.getInstance().getBO(BOTypes.AddLocations);
    private final SessionManageBO sessionManageBO = BOFactory.getInstance().getBO(BOTypes.AddSession);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadSessionTable();
            ObservableList studentTMS2 = selectSessionW.getItems();
            ObservableList selectTAgW2 = selectTAgW.getItems();
            ObservableList selectLecture2 = selectLectureW.getItems();
            ObservableList selectGroupW1 = selectGroupW.getItems();
            ObservableList selectSubjectWq = selectSubjectW.getItems();
            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {
                studentTMS2.add((addSessionDTO.getSubjectName()));
                selectTAgW2.add((addSessionDTO.getTagName()));
                selectGroupW1.add((addSessionDTO.getGroupId()));
                selectLecture2.add((addSessionDTO.getLectureOne()));
                selectSubjectWq.add((addSessionDTO.getSubjectCode()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            List<LoadSessionDataDTO> addStudentDTOList = sessionManageBO.loadConsSessionTable();
            ObservableList studentTMS2 = selectCSession.getItems();
            Set<String> strings = new HashSet<>();

            for (LoadSessionDataDTO addSessionDTO : addStudentDTOList) {
                if(!strings.contains(addSessionDTO.getId())){
                    strings.add(addSessionDTO.getId());
                    studentTMS2.add((addSessionDTO.getId() +"-" +addSessionDTO.getSubjectName()));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
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
        }


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

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        String val1 = (String) selectSessionW.getValue();
        String val2 = (String) selectTAgW.getValue();
        String val3 = (String) selectLectureW.getValue();
        String val4 = (String) selectGroupW.getValue();
        String val5 = (String) selectSubjectW.getValue();
        String val6 = (String) selectCSession.getValue();
        int i = 0;

        if (val1 != null) {
            i = 3;
            String val1R = (String) selectLectureWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateLectureRoom(val3, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                }
            }
//            /////////////////////////////

        } else if (val2 != null) {
            i = 2;
            String val1R = (String) selectTAgWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateTagRoom(val2, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                }
            }
        } else if (val3 != null) {
            i = 1;
            String val1R = (String) selectSessionWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateSessionRoom(val1, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                }
            }
        } else if (val4 != null) {
            i = 4;
            String val1R = (String) selectGroupWRoom.getValue();
            if (val1R == null) {
                new Alert(Alert.AlertType.ERROR, "Select a Room").show();
            } else {
                try {
                    sessionManageBO.setUpdateGroupRoom(val4, val1R);
                    new Alert(Alert.AlertType.INFORMATION, "Room Added").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
                }
            }
        } else if (val5 != null) {
            i = 5;
            String val1R = (String) selectSubjectWRoom.getValue();
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
            i = 6;
            String val1R = (String) scelectCSeasonRoom.getValue();
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

    public void selectSessionWOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    void clickEve() {
        String val = "";
        String val1 = (String) selectSessionWRoom.getValue();
        String val2 = (String) selectTAgW.getValue();
        String val3 = (String) selectTAgWRoom.getValue();
        String val4 = (String) selectLectureW.getValue();
        String val5 = (String) selectLectureWRoom.getValue();
        String val6 = (String) selectGroupW.getValue();
        String val7 = (String) selectGroupWRoom.getValue();
        String val8 = (String) selectSubjectW.getValue();
        String val9 = (String) selectSubjectWRoom.getValue();
        String val10 = (String) selectSessionW.getValue();
        String val11 = (String) selectCSession.getValue();
        String val12 = (String) scelectCSeasonRoom.getValue();
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
        val = (String) val10 + " - " + val1 + " , "
                + val2 + " - " + val3 + " , "
                + val4 + " - " + val5 + " , "
                + val6 + " - " + val7 + " , "
                + val8 + " - " + val9 + " , "
                + val11 + " - " + val12;
        selectedSessionTxt.setText(val);
    }

    public void selectTAgWOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectSessionWRoomOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectTAgWRoomOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectLectureWOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectLectureWRoomOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectGroupWOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectGroupWRoomOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectSubjectWOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectSubjectWRoomOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void scelectCSessonRoomOnAction(ActionEvent actionEvent) {
        clickEve();
    }

    public void selectCSessionOnActon(ActionEvent actionEvent) {
        clickEve();
    }
}
