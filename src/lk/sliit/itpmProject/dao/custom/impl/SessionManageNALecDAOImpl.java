package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.SessionManageNALecDAO;
import lk.sliit.itpmProject.entity.AddTag;
import lk.sliit.itpmProject.entity.SessionManageNALec;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SessionManageNALecDAOImpl implements SessionManageNALecDAO {

    @Override
    public List<SessionManageNALec> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM NotAvbSessionLec");
        List<SessionManageNALec> addTagList = new ArrayList<>();
        while(resultSet.next()){
            addTagList.add(new SessionManageNALec(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return addTagList;
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
