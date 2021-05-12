package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.SessionManageDAO;
import lk.sliit.itpmProject.entity.AddSession;

import java.sql.ResultSet;
import java.util.List;

public class SessionManageDAOImpl implements SessionManageDAO {
    @Override
    public int getLastSessionId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM addsession ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    @Override
    public List<AddSession> findAll() throws Exception {
        return null;
    }

    @Override
    public AddSession find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AddSession addSession) throws Exception {
        return CrudUtil.execute("INSERT INTO addsession VALUES (?,?,?,?,?,?,?,?)",
                addSession.getId(),
                addSession.getSelectLecture(),
                addSession.getSelectTag(),
                addSession.getSelectedLecturer(),
                addSession.getSelectGroup(),
                addSession.getNoOfStudent(),
                addSession.getSelectSubject(),
                addSession.getDurationHrs()
        );
    }

    @Override
    public boolean update(AddSession entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }
}
