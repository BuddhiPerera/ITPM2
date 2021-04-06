package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.AddSubjectDAO;
import lk.sliit.itpmProject.entity.AddSubject;

import java.sql.ResultSet;
import java.util.List;

public class AddSubjectDAOImpl implements AddSubjectDAO {
    @Override
    public List<AddSubject> findAll() throws Exception {
        return null;
    }

    @Override
    public AddSubject find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AddSubject entity) throws Exception {
        return CrudUtil.execute("INSERT INTO AddSubject VALUES (?,?,?,?,?,?,?,?,?,?)",
                entity.getId(),
                entity.getOffredYear(),
                entity.isSemester1(),
                entity.isSemester2(),
                entity.getNoOfLecHrs(),
                entity.getNoOfTutHrs(),
                entity.getNoOflabHrs(),
                entity.getSubName(),
                entity.getNoOfEvlHrs(),
                entity.getSubCode()
        );
    }

    @Override
    public boolean update(AddSubject entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public int getLastSubjectId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AddSubject ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }
}
