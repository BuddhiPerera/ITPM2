package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.SessionManageBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddLecturerDAO;
import lk.sliit.itpmProject.dao.custom.SessionManageDAO;
import lk.sliit.itpmProject.dto.AddSessionDTO;
import lk.sliit.itpmProject.entity.AddLecturer;
import lk.sliit.itpmProject.entity.AddSession;

public class SessionManageBOImpl  implements SessionManageBO {

    private final SessionManageDAO sessionManageDAO  = DAOFactory.getInstance().getDAO(DAOTypes.AddSessions);

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
}
