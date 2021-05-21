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
                    resultSet.getString(3)

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
        return CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionLec WHERE id = ?", s);
    }

    @Override
    public boolean saveLec(SessionManageNALec entity) throws Exception {
        return CrudUtil.execute("INSERT INTO NotAvbSessionLec VALUES (?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public boolean saveGroup(SessionManageNALec entity) throws Exception {
        System.out.println(entity);
        return CrudUtil.execute("INSERT INTO NotAvbSessionGroup VALUES (?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public boolean saveSubGroup(SessionManageNALec entity) throws Exception {
        return CrudUtil.execute("INSERT INTO NotAvbSessionSibGroup VALUES (?,?,?)",
                entity.getMaxCode(),
                entity.getLectureComboValue(),
                entity.getNaTimeLectureTxtText()
        );
    }

    @Override
    public boolean saveNASessionRoom(SessionManageNALec sessionManageNALec) throws Exception {
        return CrudUtil.execute("INSERT INTO NotAvbSessionRooms VALUES (?,?,?)",
                sessionManageNALec.getMaxCode(),
                sessionManageNALec.getLectureComboValue(),
                sessionManageNALec.getNaTimeLectureTxtText()
        );
    }

    @Override
    public List<SessionManageNALec> findAllDataSes() throws Exception {
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
    public boolean deleteGroup(String valueOf) throws Exception {
        return CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionGroup WHERE id = ?", valueOf);
    }

    @Override
    public List<SessionManageNALec> findAllDataSUbGroup() throws Exception {
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
    public boolean deleteSGroup(String valueOf) throws Exception {
        return CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionSibGroup WHERE id = ?", valueOf);
    }

    @Override
    public List<SessionManageNALec> findAllDataRoom() throws Exception {
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
    public boolean deleteRoomNa(String valueOf) throws Exception {
        return CrudUtil.execute("DELETE FROM MFkwg22AgC.NotAvbSessionRooms WHERE id = ?", valueOf);
    }
}
