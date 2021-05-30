package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddSession;

import java.sql.SQLException;
import java.util.List;

public interface SessionManageDAO extends CrudDAO<AddSession,String> {
    int getLastSessionId() throws SQLException;

    boolean save(AddSession addSession) throws SQLException;

    int getLastNotAvbLectures() throws SQLException;

    void updateRoom(String val1, String val2) throws SQLException;

    int getLastNotAvbGroups() throws SQLException;

    int getLastNotAvbSubGroups()throws SQLException;

    int getLastNARoom() throws SQLException;

    void setUpdateSessionRoom(String val1, String val1R) throws SQLException;

    void setUpdateTagRoom(String val2, String val1R)throws SQLException;

    void setUpdateLectRoom(String val3, String val1R) throws SQLException;

    void setUpdateGroupRoom(String val4, String val1R) throws SQLException;

    void setUpdateSubjectRoom(String val5, String val1R) throws SQLException;

    void setUpdateConstRoom(String val6, String val1R) throws SQLException;

    List<AddSession> loadSessionLec(String empId) throws SQLException;

    List<AddSession> loadSessionStd(String s)throws SQLException;

    List<AddSession> loadSessionLoc(String s)throws SQLException;

    int getLastNASession()throws SQLException;
}
