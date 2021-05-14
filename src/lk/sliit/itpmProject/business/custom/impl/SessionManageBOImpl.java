package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.QueryDAO;
import lk.sliit.itpmProject.dao.custom.SessionManageDAO;
import lk.sliit.itpmProject.dao.custom.SessionManageNALecDAO;
import lk.sliit.itpmProject.dto.*;
import lk.sliit.itpmProject.entity.AddSession;
import lk.sliit.itpmProject.entity.AddTag;
import lk.sliit.itpmProject.entity.CustomEntity;
import lk.sliit.itpmProject.entity.SessionManageNALec;

import java.util.ArrayList;
import java.util.List;

public class SessionManageBOImpl  implements SessionManageBO {
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private final SessionManageDAO sessionManageDAO  = DAOFactory.getInstance().getDAO(DAOTypes.AddSessions);
    private final SessionManageNALecDAO sessionManageNALecDAO  = DAOFactory.getInstance().getDAO(DAOTypes.SessionManageNaLec);

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
                addSessionDTO.getDurationHrs()
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
        sessionManageNALecDAO.save(new SessionManageNALec(
                addSessionNALectureDTO.getMaxCode(),
                addSessionNALectureDTO.getLectureComboValue(),
                addSessionNALectureDTO.getNaTimeLectureGroupValue1(),
                addSessionNALectureDTO.getNaTimeLectureGroupValue(),
                addSessionNALectureDTO.getNaTimeLectureSessionIdTxtValue(),
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


}
