package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.ConsecutiveSessionsDAO;
import lk.sliit.itpmProject.entity.ConsecutiveSessions;

import java.sql.ResultSet;
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
}
