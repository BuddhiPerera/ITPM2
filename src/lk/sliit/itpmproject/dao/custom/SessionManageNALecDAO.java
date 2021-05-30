package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.SessionManageNALec;

import java.sql.SQLException;
import java.util.List;

public interface SessionManageNALecDAO extends CrudDAO<SessionManageNALec, String> {
    void saveLec(SessionManageNALec sessionManageNALec) throws  SQLException;

    void saveGroup(SessionManageNALec sessionManageNALec) throws SQLException;

    void saveSubGroup(SessionManageNALec sessionManageNALec) throws SQLException;

    void saveNASessionRoom(SessionManageNALec sessionManageNALec) throws SQLException;

    List<SessionManageNALec> findAllDataSes()throws SQLException;

    void deleteGroup(String valueOf)throws SQLException;

    List<SessionManageNALec> findAllDataSUbGroup()throws SQLException;

    void deleteSGroup(String valueOf)throws SQLException;

    List<SessionManageNALec> findAllDataRoom()throws SQLException;

    void deleteRoomNa(String valueOf)throws SQLException;

    void saveNASession(SessionManageNALec sessionManageNALec)throws SQLException;
}
