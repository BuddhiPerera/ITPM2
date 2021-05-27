package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.SessionManageNALecDAO;
import lk.sliit.itpmproject.entity.SessionManageNALec;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionManageNALecDAOImpl implements SessionManageNALecDAO {

    @Override
    public List<SessionManageNALec> findAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM NotAvbSessionLec");
        List<SessionManageNALec> addTagList = new ArrayList<>();
        while(resultSet.next()){
            addTagList.add(new SessionManageNALec(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return addTagList;
    }

    @Override
    public SessionManageNALec find(String s) {
        return null;
    }

    @Override
    public boolean save(SessionManageNALec entity) throws SQLException {
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
    public boolean update(SessionManageNALec entity)  {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionLec WHERE id = ?", s);
    }

    @Override
    public void saveLec(SessionManageNALec entity) throws SQLException {
        CrudUtil.execute("INSERT INTO NotAvbSessionLec VALUES (?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public void saveGroup(SessionManageNALec entity) throws SQLException {
        CrudUtil.execute("INSERT INTO NotAvbSessionGroup VALUES (?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public void saveSubGroup(SessionManageNALec entity) throws SQLException {
        CrudUtil.execute("INSERT INTO NotAvbSessionSibGroup VALUES (?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public void saveNASessionRoom(SessionManageNALec sessionManageNALec) throws SQLException {
        CrudUtil.execute("INSERT INTO NotAvbSessionRooms VALUES (?,?,?)",
                sessionManageNALec.getMaxCode(),
                sessionManageNALec.getLectureComboValue(),
                sessionManageNALec.getNaTimeLectureTxtText()
        );
    }

    @Override
    public List<SessionManageNALec> findAllDataSes() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM NotAvbSessionGroup");
        List<SessionManageNALec> addTagList = new ArrayList<>();
        while(resultSet.next()){
            addTagList.add(new SessionManageNALec(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return addTagList;
    }

    @Override
    public void deleteGroup(String valueOf) throws SQLException {
        CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionGroup WHERE id = ?", valueOf);
    }

    @Override
    public List<SessionManageNALec> findAllDataSUbGroup() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM MFkwg22AgC.NotAvbSessionSibGroup");
        List<SessionManageNALec> addTagList = new ArrayList<>();
        while(resultSet.next()){
            addTagList.add(new SessionManageNALec(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return addTagList;
    }

    @Override
    public void deleteSGroup(String valueOf) throws SQLException {
        CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionSibGroup WHERE id = ?", valueOf);
    }

    @Override
    public List<SessionManageNALec> findAllDataRoom() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM MFkwg22AgC.NotAvbSessionRooms");
        List<SessionManageNALec> addTagList = new ArrayList<>();
        while(resultSet.next()){
            addTagList.add(new SessionManageNALec(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return addTagList;
    }

    @Override
    public void deleteRoomNa(String valueOf) throws SQLException {
        CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionRooms WHERE id = ?", valueOf);
    }
}
