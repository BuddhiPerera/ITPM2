package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.AddStudentDAO;
import lk.sliit.itpmproject.entity.AddStudent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStudentDAOImpl implements AddStudentDAO {
    @Override
    public List<AddStudent> findAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM AddStudentSubGroups");
        List<AddStudent> addStudentList = new ArrayList<>();
        while(resultSet.next()){
            addStudentList.add(new AddStudent(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return addStudentList;
    }

    @Override
    public AddStudent find(String s)  {
        return null;
    }

    @Override
    public boolean save(AddStudent entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO AddStudentSubGroups VALUES (?,?,?,?,?,?,?,?)",
                entity.getId(),
                entity.getYear(),
                entity.getSemester(),
                entity.getProgramme(),
                entity.getGroupNo(),
                entity.getSubGroupNo(),
                entity.getGroupId(),
                entity.getSubGroupId()
        );
    }

    @Override
    public boolean update(AddStudent entity) throws SQLException {
        return CrudUtil.execute("UPDATE AddStudentSubGroups SET yearx = ?, semester = ?, programme = ?, groupNo = ?, subGroupNo = ?, groupId =?, subGroupId =? WHERE id = ?",
                entity.getYear(),
                entity.getSemester(),
                entity.getProgramme(),
                entity.getGroupNo(),
                entity.getSubGroupNo(),
                entity.getGroupId(),
                entity.getSubGroupId(),
                entity.getId()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM AddStudentSubGroups WHERE id=?", s);
    }

    @Override
    public int getLastStudentID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AddStudentSubGroups ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }
}
