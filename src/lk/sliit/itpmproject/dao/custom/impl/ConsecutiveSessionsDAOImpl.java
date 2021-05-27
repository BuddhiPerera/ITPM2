package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.ConsecutiveSessionsDAO;
import lk.sliit.itpmproject.entity.ConsecutiveSessions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSessionsDAOImpl implements ConsecutiveSessionsDAO {
    @Override
    public List<ConsecutiveSessions> findAll(){
        List<ConsecutiveSessions> sessions = null;
        sessions = new ArrayList<>();
        return sessions;
    }

    @Override
    public ConsecutiveSessions find(String s)  {
        return null;
    }

    @Override
    public boolean save(ConsecutiveSessions entity) {
        return false;
    }

    @Override
    public boolean update(ConsecutiveSessions entity) {
        return false;
    }

    @Override
    public boolean delete(String s)  {
        return false;
    }

    @Override
    public void addconsecutiveSessions(List<ConsecutiveSessions> entity1) throws SQLException {
       int id =0;
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM consecutive ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            id = resultSet.getInt(1);
        }
        for (ConsecutiveSessions entity: entity1) {
            CrudUtil.execute("INSERT INTO consecutive VALUES (?,?,?,?,?,?,?,?)",
                    id+1,
                    entity.getId(),
                    entity.getLectureOne(),
                    entity.getLectureTwo(),
                    entity.getSubjectCode(),
                    entity.getSubjectName(),
                    entity.getGroupId(),
                    entity.getTagName()
            );
        }

    }

    @Override
    public void savetblNonOverLapping(List<ConsecutiveSessions> consecutiveSessions) throws SQLException {
        int id =0;
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM Parallel ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            id = resultSet.getInt(1);
        }
        for (ConsecutiveSessions entity: consecutiveSessions) {
            CrudUtil.execute("INSERT INTO Parallel VALUES (?,?,?,?,?,?,?,?)",
                    id+1,
                    entity.getId(),
                    entity.getLectureOne(),
                    entity.getLectureTwo(),
                    entity.getSubjectCode(),
                    entity.getSubjectName(),
                    entity.getGroupId(),
                    entity.getTagName()
            );
        }

    }

    @Override
    public void savetblParallel(List<ConsecutiveSessions> consecutiveSessions) throws SQLException {

        int id =0;
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM NonOverLapping ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            id = resultSet.getInt(1);
        }
        for (ConsecutiveSessions entity: consecutiveSessions) {
            CrudUtil.execute("INSERT INTO NonOverLapping VALUES (?,?,?,?,?,?,?,?)",
                    id+1,
                    entity.getId(),
                    entity.getLectureOne(),
                    entity.getLectureTwo(),
                    entity.getSubjectCode(),
                    entity.getSubjectName(),
                    entity.getGroupId(),
                    entity.getTagName()
            );
        }

    }

    @Override
    public List<ConsecutiveSessions> loadConsSessionTable() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM consecutive");

        List<ConsecutiveSessions> addSubjectList = new ArrayList<>();
        while(resultSet.next()){
            addSubjectList.add(new ConsecutiveSessions(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return addSubjectList;
    }
}
