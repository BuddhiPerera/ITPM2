package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.SessionManageBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.ConsecutiveSessionsDAO;
import lk.sliit.itpmproject.dao.custom.QueryDAO;
import lk.sliit.itpmproject.dao.custom.SessionManageDAO;
import lk.sliit.itpmproject.dao.custom.SessionManageNALecDAO;
import lk.sliit.itpmproject.dto.*;

import lk.sliit.itpmproject.entity.AddSession;
import lk.sliit.itpmproject.entity.CustomEntity;
import lk.sliit.itpmproject.entity.SessionManageNALec;

import lk.sliit.itpmproject.entity.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionManageBOImpl  implements SessionManageBO {
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private final SessionManageDAO sessionManageDAO  = DAOFactory.getInstance().getDAO(DAOTypes.ADD_SESSIONS);
    private final SessionManageNALecDAO sessionManageNALecDAO  = DAOFactory.getInstance().getDAO(DAOTypes.SESSION_MANAGE_NA_LEC);
    private final ConsecutiveSessionsDAO consecutiveSessionsDAO  = DAOFactory.getInstance().getDAO(DAOTypes.CONSECUTIVE_SESSIONS);


    @Override
    public int getLastItemCode() throws SQLException {
        return sessionManageDAO.getLastSessionId();
    }

    @Override
    public void saveSession(AddSessionDTO addSessionDTO) throws SQLException {
        sessionManageDAO.save(new AddSession(
                addSessionDTO.getId(),
                addSessionDTO.getSelectLecture(),
                addSessionDTO.getSelectTag(),
                addSessionDTO.getSelectedLecturer(),
                addSessionDTO.getSelectGroup(),
                addSessionDTO.getNoOfStudent(),
                addSessionDTO.getSelectSubject(),
                addSessionDTO.getDurationHrs(),
                addSessionDTO.getRoom()
        ));
    }

    @Override
    public List<LoadSessionDataDTO> loadSessionTable() throws SQLException {
        List<CustomEntity> all = queryDAO.getInfo();
        List<LoadSessionDataDTO> dtos = new ArrayList<>();

        for (CustomEntity customEntity : all) {
            dtos.add(new LoadSessionDataDTO(
                    customEntity.getId(),
                    customEntity.getLectureOne(),
                    customEntity.getLectureTwo(),
                    customEntity.getSubjectCode(),
                    customEntity.getSubjectName(),
                    customEntity.getGroupId(),
                    customEntity.getTagName()
            ));
        }
        return dtos;
    }

    @Override
    public AddSessionDTO findAllSessions(String id) throws SQLException {
        AddSession addSessionDTO = sessionManageDAO.find(id);
        AddSessionDTO addSessionDTO1 = new AddSessionDTO();

        addSessionDTO1.setId(addSessionDTO.getId());
        addSessionDTO1.setSelectLecture(addSessionDTO.getSelectLecture());
        addSessionDTO1.setSelectTag(addSessionDTO.getSelectTag());
        addSessionDTO1.setSelectedLecturer(addSessionDTO.getSelectedLecturer());
        addSessionDTO1.setSelectGroup(addSessionDTO.getSelectGroup());
        addSessionDTO1.setNoOfStudent(addSessionDTO.getNoOfStudent());
        addSessionDTO1.setSelectSubject(addSessionDTO.getSelectSubject());
        addSessionDTO1.setDurationHrs(addSessionDTO.getDurationHrs());

        return addSessionDTO1;
    }

    @Override
    public int getLastNotAvbLectures() throws SQLException {
        return sessionManageDAO.getLastNotAvbLectures();
    }

    @Override
    public void saveNASessionLec(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException {
        sessionManageNALecDAO.saveLec(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public List<ManageNotAvbTimeDTO> findAllData() throws SQLException {
        List<SessionManageNALec> sessionManageNALecs = sessionManageNALecDAO.findAll();
        List<ManageNotAvbTimeDTO> addTagDTOList = new ArrayList<>();
        for (SessionManageNALec sessionManageNALec:sessionManageNALecs
        ) {
            addTagDTOList.add(new ManageNotAvbTimeDTO(
                    sessionManageNALec.getMaxCode(),
                    sessionManageNALec.getLectureComboValue(),
                    sessionManageNALec.getNaTimeLectureGroupValue1(),
                    sessionManageNALec.getNaTimeLectureGroupValue(),
                    sessionManageNALec.getNaTimeLectureSessionIdTxtValue(),
                    sessionManageNALec.getNaTimeLectureTxtText()
            ));
        }
        return addTagDTOList;
    }

    @Override
    public void updateSession(AddSessionDTO addSessionDTO) throws SQLException {
        sessionManageDAO.update(new AddSession(
                addSessionDTO.getId(),
                addSessionDTO.getSelectLecture(),
                addSessionDTO.getSelectTag(),
                addSessionDTO.getSelectedLecturer(),
                addSessionDTO.getSelectGroup(),
                addSessionDTO.getNoOfStudent(),
                addSessionDTO.getSelectSubject(),
                addSessionDTO.getDurationHrs(),
                addSessionDTO.getRoom()

        ));
    }

    @Override

    public boolean deleteItem(String id) throws SQLException {
        return sessionManageDAO.delete(id);
    }

    public void saveNATimeAlocations(List<LoadSessionDataDTO> addSessionDTOs) throws SQLException {

        List<ConsecutiveSessions> consecutiveSessions = new ArrayList<>();
        for (LoadSessionDataDTO customEntity : addSessionDTOs) {
           consecutiveSessions.add(
                   new ConsecutiveSessions(
                   customEntity.getId(),
                   customEntity.getLectureOne(),
                   customEntity.getLectureTwo(),
                   customEntity.getSubjectCode(),
                   customEntity.getSubjectName(),
                   customEntity.getGroupId(),
                   customEntity.getTagName()
           ));
        }
        consecutiveSessionsDAO.addconsecutiveSessions(consecutiveSessions);

    }

    @Override
    public List<LoadSessionDataDTO> loadSessionTableSearch(int i,String val) throws SQLException {
        List<CustomEntity> all = queryDAO.getInfoSelect( i,val);
        List<LoadSessionDataDTO> dtos = new ArrayList<>();
        for (CustomEntity customEntity : all) {
            dtos.add(new LoadSessionDataDTO(
                    customEntity.getId(),
                    customEntity.getLectureOne(),
                    customEntity.getLectureTwo(),
                    customEntity.getSubjectCode(),
                    customEntity.getSubjectName(),
                    customEntity.getGroupId(),
                    customEntity.getTagName()
            ));
        }
        return dtos;
    }

    @Override
    public void saveRoom(String val1, String val2) throws SQLException {
        sessionManageDAO.updateRoom(val1,val2);
    }

    @Override
    public void savetblParallel(List<LoadSessionDataDTO> dtos) throws SQLException {
        List<ConsecutiveSessions> consecutiveSessions = new ArrayList<>();
        for (LoadSessionDataDTO customEntity : dtos) {
            consecutiveSessions.add(
                    new ConsecutiveSessions(
                            customEntity.getId(),
                            customEntity.getLectureOne(),
                            customEntity.getLectureTwo(),
                            customEntity.getSubjectCode(),
                            customEntity.getSubjectName(),
                            customEntity.getGroupId(),
                            customEntity.getTagName()
                    ));
        }
        consecutiveSessionsDAO.savetblParallel(consecutiveSessions);

    }

    @Override
    public void savetblNonOverLapping(List<LoadSessionDataDTO> dtos) throws SQLException {
        List<ConsecutiveSessions> consecutiveSessions = new ArrayList<>();
        for (LoadSessionDataDTO customEntity : dtos) {
            consecutiveSessions.add(
                    new ConsecutiveSessions(
                            customEntity.getId(),
                            customEntity.getLectureOne(),
                            customEntity.getLectureTwo(),
                            customEntity.getSubjectCode(),
                            customEntity.getSubjectName(),
                            customEntity.getGroupId(),
                            customEntity.getTagName()
                    ));
        }
        consecutiveSessionsDAO.savetblNonOverLapping(consecutiveSessions);

    }

    @Override
    public void saveNASessionGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException {
        sessionManageNALecDAO.saveGroup(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public int getLastNotAvbGroups() throws SQLException {
        return sessionManageDAO.getLastNotAvbGroups();
    }

    @Override
    public int getLastNotAvbSubGroups() throws SQLException {
        return sessionManageDAO.getLastNotAvbSubGroups();
    }

    @Override
    public void saveNASessionSubGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException {
        sessionManageNALecDAO.saveSubGroup(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public int getLastNARoom() throws SQLException {
        return sessionManageDAO.getLastNARoom();
    }

    @Override
    public void saveNASessionRoom(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException {
        sessionManageNALecDAO.saveNASessionRoom(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public void setUpdateSessionRoom(String val1, String val1R) throws SQLException {
        sessionManageDAO.setUpdateSessionRoom( val1,  val1R);
    }

    @Override
    public void setUpdateTagRoom(String val2, String val1R) throws SQLException {
        sessionManageDAO.setUpdateTagRoom( val2,  val1R);

    }

    @Override
    public void setUpdateLectureRoom(String val3, String val1R) throws SQLException {
        sessionManageDAO.setUpdateLectRoom( val3,  val1R);
    }

    @Override
    public void setUpdateGroupRoom(String val4, String val1R) throws SQLException {
        sessionManageDAO.setUpdateGroupRoom( val4,  val1R);
    }

    @Override
    public void setUpdateSubjectRoom(String val5, String val1R) throws SQLException {
        sessionManageDAO.setUpdateSubjectRoom( val5,  val1R);
    }

    @Override
    public List<LoadSessionDataDTO> loadConsSessionTable() throws SQLException {
        List<ConsecutiveSessions> all = consecutiveSessionsDAO.loadConsSessionTable();
        List<LoadSessionDataDTO> dtos = new ArrayList<>();
        for (ConsecutiveSessions customEntity : all) {
            dtos.add(new LoadSessionDataDTO(
                    customEntity.getId(),
                    customEntity.getLectureOne(),
                    customEntity.getLectureTwo(),
                    customEntity.getSubjectCode(),
                    customEntity.getSubjectName(),
                    customEntity.getGroupId(),
                    customEntity.getTagName()
            ));
        }
        return dtos;
    }

    @Override
    public void setUpdateConstRoom(String val6, String val1R) throws SQLException {
        sessionManageDAO.setUpdateConstRoom( val6,  val1R);
    }

    @Override
    public List<AddSessionDTO> loadSessionLec(String empId) throws SQLException {
        List<AddSession> all = sessionManageDAO.loadSessionLec(empId);
        List<AddSessionDTO> dtos = new ArrayList<>();

        for (AddSession addSession : all) {
            dtos.add(new AddSessionDTO(
                    addSession.getId(),
                    addSession.getSelectLecture(),
                    addSession.getSelectTag(),
                    addSession.getSelectedLecturer(),
                    addSession.getSelectGroup(),
                    addSession.getNoOfStudent(),
                    addSession.getSelectSubject(),
                    addSession.getDurationHrs(),
                    addSession.getRoom()
            ));
        }
        return dtos;
    }

    @Override
    public List<AddSessionDTO> loadSessionStd(String s) throws SQLException {
        List<AddSession> all = sessionManageDAO.loadSessionStd(s);
        List<AddSessionDTO> dtos = new ArrayList<>();

        for (AddSession addSession : all) {
            dtos.add(new AddSessionDTO(
                    addSession.getId(),
                    addSession.getSelectLecture(),
                    addSession.getSelectTag(),
                    addSession.getSelectedLecturer(),
                    addSession.getSelectGroup(),
                    addSession.getNoOfStudent(),
                    addSession.getSelectSubject(),
                    addSession.getDurationHrs(),
                    addSession.getRoom()
            ));
        }
        return dtos;
    }

    @Override
    public List<AddSessionDTO> loadSessionLoc(String s) throws SQLException {
        List<AddSession> all = sessionManageDAO.loadSessionLoc(s);
        List<AddSessionDTO> dtos = new ArrayList<>();

        for (AddSession addSession : all) {
            dtos.add(new AddSessionDTO(
                    addSession.getId(),
                    addSession.getSelectLecture(),
                    addSession.getSelectTag(),
                    addSession.getSelectedLecturer(),
                    addSession.getSelectGroup(),
                    addSession.getNoOfStudent(),
                    addSession.getSelectSubject(),
                    addSession.getDurationHrs(),
                    addSession.getRoom()
            ));
        }
        return dtos;
    }

    @Override
    public void deleteItemNaLec(int id) throws SQLException {
        sessionManageNALecDAO.delete(String.valueOf(id));
    }

    @Override
    public List<ManageNotAvbTimeDTO> findAllDataSes() throws SQLException {
        List<SessionManageNALec> sessionManageNALecs = sessionManageNALecDAO.findAllDataSes();
        List<ManageNotAvbTimeDTO> addTagDTOList = new ArrayList<>();
        for (SessionManageNALec sessionManageNALec:sessionManageNALecs
        ) {
            addTagDTOList.add(new ManageNotAvbTimeDTO(
                    sessionManageNALec.getMaxCode(),
                    sessionManageNALec.getLectureComboValue(),
                    sessionManageNALec.getNaTimeLectureGroupValue1(),
                    sessionManageNALec.getNaTimeLectureGroupValue(),
                    sessionManageNALec.getNaTimeLectureSessionIdTxtValue(),
                    sessionManageNALec.getNaTimeLectureTxtText()
            ));
        }
        return addTagDTOList;
    }

    @Override
    public void deleteItemNaGroup(int id) throws SQLException {
        sessionManageNALecDAO.deleteGroup(String.valueOf(id));
    }

    @Override
    public List<ManageNotAvbTimeDTO> findAllDataSUbGroup() throws SQLException {
        List<SessionManageNALec> sessionManageNALecs = sessionManageNALecDAO.findAllDataSUbGroup();
        List<ManageNotAvbTimeDTO> addTagDTOList = new ArrayList<>();
        for (SessionManageNALec sessionManageNALec:sessionManageNALecs
        ) {
            addTagDTOList.add(new ManageNotAvbTimeDTO(
                    sessionManageNALec.getMaxCode(),
                    sessionManageNALec.getLectureComboValue(),
                    sessionManageNALec.getNaTimeLectureGroupValue1(),
                    sessionManageNALec.getNaTimeLectureGroupValue(),
                    sessionManageNALec.getNaTimeLectureSessionIdTxtValue(),
                    sessionManageNALec.getNaTimeLectureTxtText()
            ));
        }
        return addTagDTOList;
    }

    @Override
    public void deleteItemNaSubGroup(int id) throws SQLException {
        sessionManageNALecDAO.deleteSGroup(String.valueOf(id));
    }

    @Override
    public void deleteItemNaLecRoom(int id) throws SQLException {
        sessionManageNALecDAO.deleteRoomNa(String.valueOf(id));
    }

    @Override
    public List<ManageNotAvbTimeDTO> findAllDataRom() throws SQLException {
        List<SessionManageNALec> sessionManageNALecs = sessionManageNALecDAO.findAllDataRoom();
        List<ManageNotAvbTimeDTO> addTagDTOList = new ArrayList<>();
        for (SessionManageNALec sessionManageNALec:sessionManageNALecs
        ) {
            addTagDTOList.add(new ManageNotAvbTimeDTO(
                    sessionManageNALec.getMaxCode(),
                    sessionManageNALec.getLectureComboValue(),
                    sessionManageNALec.getNaTimeLectureGroupValue1(),
                    sessionManageNALec.getNaTimeLectureGroupValue(),
                    sessionManageNALec.getNaTimeLectureSessionIdTxtValue(),
                    sessionManageNALec.getNaTimeLectureTxtText()
            ));
        }
        return addTagDTOList;
    }


    @Override
    public List<AddSessionDTO> findAllSessions() throws SQLException {
        List<AddSession> addSessionList = sessionManageDAO.findAll();
        List<AddSessionDTO> addSessionDTOList = new ArrayList<>();
        for(AddSession addSession:addSessionList){
            addSessionDTOList.add(new AddSessionDTO(
                    addSession.getId(),
                    addSession.getSelectLecture(),
                    addSession.getSelectTag(),
                    addSession.getSelectedLecturer(),
                    addSession.getSelectGroup(),
                    addSession.getNoOfStudent(),
                    addSession.getSelectSubject(),
                    addSession.getDurationHrs(),
                    addSession.getRoom()
            ));
        }
        return addSessionDTOList;
    }
}