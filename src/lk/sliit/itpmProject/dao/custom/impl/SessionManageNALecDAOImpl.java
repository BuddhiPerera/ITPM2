package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.SessionManageNALecDAO;
import lk.sliit.itpmProject.entity.SessionManageNALec;

import java.util.List;

public class SessionManageNALecDAOImpl implements SessionManageNALecDAO {

    @Override
    public List<SessionManageNALec> findAll() throws Exception {
        return null;
    }

    @Override
    public SessionManageNALec find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(SessionManageNALec entity) throws Exception {
        return CrudUtil.execute("INSERT INTO NotAvbSessionLec VALUES (?,?,?,?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureGroupValue1(),
                entity.getNaTimeLectureGroupValue(),
                entity.getNaTimeLectureSessionIdTxtValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public boolean update(SessionManageNALec entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }
}
