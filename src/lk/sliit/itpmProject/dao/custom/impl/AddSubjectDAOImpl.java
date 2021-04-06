package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.AddSubjectDAO;
import lk.sliit.itpmProject.entity.AddStudent;
import lk.sliit.itpmProject.entity.AddSubject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddSubjectDAOImpl implements AddSubjectDAO {
    @Override
    public List<AddSubject> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM AddSubject");
        List<AddSubject> addSubjectList = new ArrayList<>();
        while(resultSet.next()){
            addSubjectList.add(new AddSubject(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getBoolean(3),
                    resultSet.getBoolean(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getString(8),
                    resultSet.getInt(9),
                    resultSet.getString(10)
            ));
        }
        return addSubjectList;
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
        return CrudUtil.execute("UPDATE AddSubject SET offeredYear =?, semester1 =?, semester2 =?, NoOFLectureHrs=?, NoOfTutHrs=?, NoOFlabHrs =?,  SubName =?, NoOfEvlHrs =?, SubCode =? WHERE id = ? ",
                entity.getOffredYear(),
                entity.isSemester1(),
                entity.isSemester2(),
                entity.getNoOfLecHrs(),
                entity.getNoOfTutHrs(),
                entity.getNoOflabHrs(),
                entity.getSubName(),
                entity.getNoOfEvlHrs(),
                entity.getSubCode(),
                entity.getId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM AddSubject WHERE id=?", s);
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
