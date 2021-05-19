package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.SessionManageDAO;
import lk.sliit.itpmProject.entity.AddSession;

import java.sql.ResultSet;
import java.util.ArrayList;
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
        ResultSet rst = CrudUtil.execute("SELECT * FROM addsession WHERE id=?", s);

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
    public boolean save(AddSession addSession) throws Exception {
        return CrudUtil.execute("INSERT INTO addsession VALUES (?,?,?,?,?,?,?,?,?)",
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
    public int getLastNotAvbLectures() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionLec ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    @Override
    public void updateRoom(String val1, String val2) throws Exception {
         CrudUtil.execute("UPDATE addsession SET room=? WHERE id=?", val2,val1);
    }

    @Override
    public int getLastNotAvbGroups() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionGroup ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    @Override
    public int getLastNotAvbSubGroups() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NotAvbSessionSibGroup ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }
    @Override
    public boolean update(AddSession entity) throws Exception {
        return CrudUtil.execute("UPDATE addsession SET lecture1=?, SelectTag=?, lecture2=? ,SelectGroup=?,NoOFStudent=?,SelectSubject=?,DurationHrs=?,room=? WHERE id=?", entity.getSelectLecture(), entity.getSelectTag(), entity.getSelectedLecturer()
                , entity.getSelectGroup(),entity.getNoOfStudent(),entity.getSelectSubject(),entity.getDurationHrs(),"",entity.getId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM addsession WHERE id = ?", s);
    }
}
