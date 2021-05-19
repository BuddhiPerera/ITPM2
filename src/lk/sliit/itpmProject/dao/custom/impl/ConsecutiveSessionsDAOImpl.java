package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.ConsecutiveSessionsDAO;
import lk.sliit.itpmProject.entity.AddSession;
import lk.sliit.itpmProject.entity.AddSubject;
import lk.sliit.itpmProject.entity.ConsecutiveSessions;
import lk.sliit.itpmProject.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSessionsDAOImpl implements ConsecutiveSessionsDAO {
    @Override
    public List<ConsecutiveSessions> findAll() throws Exception {
        return null;
    }

    @Override
    public ConsecutiveSessions find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(ConsecutiveSessions entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(ConsecutiveSessions entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }
/*
* CREATE TABLE `Consecutive`
(
    `id`             int NOT NULL,
    `rowId`            int          NOT NULL,
    `lectureOne`     varchar(250)  NOT NULL,
    `lectureTwo`    varchar(250) NOT NULL,
    `subjectCode`      varchar(200) NOT NULL,
    `subject`         varchar(405) NOT NULL,
    `groupId`          varchar(405) NOT NULL,
    `tag`               varchar(200) NOT NULL,
    PRIMARY KEY (`id`, `rowId`)
);
* */
    @Override
    public void addconsecutiveSessions(List<ConsecutiveSessions> entity1) throws Exception {
       int id =0;
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM Consecutive ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            id = resultSet.getInt(1);
        }
        for (ConsecutiveSessions entity: entity1) {
            CrudUtil.execute("INSERT INTO Consecutive VALUES (?,?,?,?,?,?,?,?)",
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
    public void savetblNonOverLapping(List<ConsecutiveSessions> consecutiveSessions) throws Exception {
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
    public void savetblParallel(List<ConsecutiveSessions> consecutiveSessions) throws Exception {

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
    public List<ConsecutiveSessions> loadConsSessionTable() throws Exception {
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
