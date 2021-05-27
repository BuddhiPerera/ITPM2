package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddLectureWorkingDays;
import lk.sliit.itpmproject.entity.AddLecturer;

import java.sql.SQLException;
import java.util.List;

public interface AddLecturerDAO extends CrudDAO<AddLecturer,String> {
    int getLastLecturerID() throws SQLException;

    List<AddLecturer> findAllNames() throws SQLException;

    int checkExists(String empId) throws SQLException;

    boolean saveDays(AddLectureWorkingDays addLectureWorkingDays) throws SQLException;
}
