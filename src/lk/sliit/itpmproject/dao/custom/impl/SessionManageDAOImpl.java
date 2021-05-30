package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.SessionManageDAO;
import lk.sliit.itpmproject.entity.AddSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionManageDAOImpl implements SessionManageDAO {
    @Override
    public int getLastSessionId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM MFkwg22AgC.AddSession ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public List<AddSession> findAll() {
        List<AddSession> sessions = null;
        sessions = new ArrayList<>();
        return sessions;
    }

    @Override
    public AddSession find(String s) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM MFkwg22AgC.AddSession WHERE id=?", s);

        if (rst.next()) {
            return new AddSession(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                    rst.getInt(8),
                    rst.getString(9)

            );
        }
        return null;
    }

    @Override
    public boolean save(AddSession addSession) throws SQLException {
        return CrudUtil.execute("INSERT INTO MFkwg22AgC.AddSession VALUES (?,?,?,?,?,?,?,?,?)",
                addSession.getId(),
                addSession.getSelectLecture(),
                addSession.getSelectTag(),
                addSession.getSelectedLecturer(),
                addSession.getSelectGroup(),
                addSession.getNoOfStudent(),
                addSession.getSelectSubject(),
                addSession.getDurationHrs(),
                ""
        );
    }

    @Override
    public int getLastNotAvbLectures() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionLec ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public void updateRoom(String val1, String val2) throws SQLException {
        CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET room=? WHERE id=?", val2, val1);
    }

    @Override
    public int getLastNotAvbGroups() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionGroup ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public int getLastNotAvbSubGroups() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionSibGroup ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public int getLastNARoom() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionRooms ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public void setUpdateSessionRoom(String val1, String val1R) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT SubCode FROM MFkwg22AgC.AddSubject WHERE SubName=?", val1);
        String code = "";
        if (rst.next()) {
            code = rst.getString(1);
        }

        CrudUtil.execute("UPDATE AddSession SET room=? WHERE SelectSubject=?", val1R, code);
    }

    @Override
    public void setUpdateTagRoom(String val2, String val1R) throws SQLException {

        CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET room=? WHERE SelectTag=?", val1R, val2);
    }

    @Override
    public void setUpdateLectRoom(String val3, String val1R) throws SQLException {
        CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET room=? WHERE lecture1=?", val1R, val3);
    }

    @Override
    public void setUpdateGroupRoom(String val4, String val1R) throws SQLException {
        CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET room=? WHERE SelectGroup=?", val1R, val4);
    }

    @Override
    public void setUpdateSubjectRoom(String val5, String val1R) throws SQLException {
        CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET room=? WHERE SelectSubject=?", val1R, val5);
    }

    @Override
    public void setUpdateConstRoom(String val6, String val1R) throws SQLException {
        String string = val6;
        String[] parts = string.split("-");
        String part1 = parts[0];
        ResultSet rst = CrudUtil.execute("SELECT subjectCode FROM consecutive WHERE id=?", part1);
        String code = "";
        if (rst.next()) {
            code = rst.getString(1);
        }
        CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET room=? WHERE SelectSubject=?", val1R, code);
    }

    @Override
    public List<AddSession> loadSessionLec(String empId) throws SQLException {
        String lectureName = "";
        ResultSet rst = null;
        rst = CrudUtil.execute("SELECT lName FROM AddLecturer where empId =? ORDER BY empId DESC LIMIT 1", empId);
        while (rst.next()) {
            lectureName = rst.getString(1);
        }
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM MFkwg22AgC.AddSession where lecture1=? || lecture2=?", lectureName, lectureName);
        List<AddSession> addTagList = new ArrayList<>();
        while (resultSet.next()) {
            addTagList.add(new AddSession(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getString(9)
            ));
        }
        return addTagList;
    }

    @Override
    public List<AddSession> loadSessionStd(String s) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM MFkwg22AgC.AddSession where SelectGroup=? ", s);
        List<AddSession> addTagList = new ArrayList<>();
        while (resultSet.next()) {
            addTagList.add(new AddSession(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getString(9)
            ));
        }
        return addTagList;
    }

    @Override
    public List<AddSession> loadSessionLoc(String s) throws SQLException {
        String[] parts = s.split("- ");
        String part1 = parts[1];
        s = part1;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM MFkwg22AgC.AddSession where room=? ", s);
        List<AddSession> addTagList = new ArrayList<>();
        while (resultSet.next()) {
            addTagList.add(new AddSession(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getString(9)
            ));
        }
        return addTagList;
    }

    @Override
    public int getLastNASession() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionD ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public boolean update(AddSession entity) throws SQLException {
        return CrudUtil.execute("UPDATE MFkwg22AgC.AddSession SET lecture1=?, SelectTag=?, lecture2=? ,SelectGroup=?,NoOFStudent=?,SelectSubject=?,DurationHrs=?,room=? WHERE id=?", entity.getSelectLecture(), entity.getSelectTag(), entity.getSelectedLecturer()
                , entity.getSelectGroup(), entity.getNoOfStudent(), entity.getSelectSubject(), entity.getDurationHrs(), "", entity.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM MFkwg22AgC.AddSession WHERE id = ?", s);
    }
}
