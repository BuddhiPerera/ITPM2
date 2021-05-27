package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddStudent;

import java.sql.SQLException;


public interface AddStudentDAO extends CrudDAO<AddStudent, String> {

    int getLastStudentID() throws SQLException;
}
