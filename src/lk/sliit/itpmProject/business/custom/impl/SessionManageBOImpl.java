package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.ConsecutiveSessionsDAO;
import lk.sliit.itpmProject.dao.custom.QueryDAO;
import lk.sliit.itpmProject.dao.custom.SessionManageDAO;
import lk.sliit.itpmProject.dao.custom.SessionManageNALecDAO;
import lk.sliit.itpmProject.dto.*;

import lk.sliit.itpmProject.entity.AddSession;
import lk.sliit.itpmProject.entity.CustomEntity;
import lk.sliit.itpmProject.entity.SessionManageNALec;

import lk.sliit.itpmProject.entity.*;


import java.util.ArrayList;
import java.util.List;

public class SessionManageBOImpl  implements SessionManageBO {
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private final SessionManageDAO sessionManageDAO  = DAOFactory.getInstance().getDAO(DAOTypes.AddSessions);
    private final SessionManageNALecDAO sessionManageNALecDAO  = DAOFactory.getInstance().getDAO(DAOTypes.SessionManageNaLec);
    private final ConsecutiveSessionsDAO consecutiveSessionsDAO  = DAOFactory.getInstance().getDAO(DAOTypes.ConsecutiveSessions);


    @Override
    public int getLastItemCode() throws Exception {
        return sessionManageDAO.getLastSessionId();
    }

    @Override
    public void saveSession(AddSessionDTO addSessionDTO) throws Exception {
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
    public List<LoadSessionDataDTO> loadSessionTable() throws Exception {
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
    public AddSessionDTO findAllSessions(String id) throws Exception {
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
    public int getLastNotAvbLectures() throws Exception {
        return sessionManageDAO.getLastNotAvbLectures();
    }

    @Override
    public void saveNASessionLec(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception {
        sessionManageNALecDAO.saveLec(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public List<ManageNotAvbTimeDTO> findAllData() throws Exception {
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
    public void updateSession(AddSessionDTO addSessionDTO) throws Exception {
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

    public boolean deleteItem(String id) throws Exception {
        return sessionManageDAO.delete(id);
    }

    public void saveNATimeAlocations(List<LoadSessionDataDTO> addSessionDTOs) throws Exception {

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
    public List<LoadSessionDataDTO> loadSessionTableSearch(int i,String val) throws Exception {
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
    public void saveRoom(String val1, String val2) throws Exception {
        sessionManageDAO.updateRoom(val1,val2);
    }

    @Override
    public void savetblParallel(List<LoadSessionDataDTO> dtos) throws Exception {
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
    public void savetblNonOverLapping(List<LoadSessionDataDTO> dtos) throws Exception {
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
    public void saveNASessionGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception {
        sessionManageNALecDAO.saveGroup(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public int getLastNotAvbGroups() throws Exception {
        return sessionManageDAO.getLastNotAvbGroups();
    }

    @Override
    public int getLastNotAvbSubGroups() throws Exception {
        return sessionManageDAO.getLastNotAvbSubGroups();
    }

    @Override
    public void saveNASessionSubGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception {
        sessionManageNALecDAO.saveSubGroup(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public int getLastNARoom() throws Exception {
        return sessionManageDAO.getLastNARoom();
    }

    @Override
    public void saveNASessionRoom(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception {
        sessionManageNALecDAO.saveNASessionRoom(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureTxtText()
        ));
    }

    @Override
    public void setUpdateSessionRoom(String val1, String val1R) throws Exception {
        sessionManageDAO.setUpdateSessionRoom( val1,  val1R);
    }

    @Override
    public void setUpdateTagRoom(String val2, String val1R) throws Exception {
        sessionManageDAO.setUpdateTagRoom( val2,  val1R);

    }

    @Override
    public void setUpdateLectureRoom(String val3, String val1R) throws Exception {
        sessionManageDAO.setUpdateLectRoom( val3,  val1R);
    }

    @Override
    public void setUpdateGroupRoom(String val4, String val1R) throws Exception {
        sessionManageDAO.setUpdateGroupRoom( val4,  val1R);
    }

    @Override
    public void setUpdateSubjectRoom(String val5, String val1R) throws Exception {
        sessionManageDAO.setUpdateSubjectRoom( val5,  val1R);
    }

    @Override
    public List<LoadSessionDataDTO> loadConsSessionTable() throws Exception {
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
    public void setUpdateConstRoom(String val6, String val1R) throws Exception {
        sessionManageDAO.setUpdateConstRoom( val6,  val1R);
    }


    @Override
    public List<AddSessionDTO> findAllSessions() throws Exception {
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