package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddStudent;

import java.sql.SQLException;


public interface StudentStaticsDAO extends CrudDAO<AddStudent, String> {
    int findLecturerCount() throws SQLException;

    int findStudentCount() throws SQLException;

    int findSubjectCount() throws SQLException;

    int findRegisteredRooms() throws SQLException;

    String findLatestLecturer() throws SQLException;

    String findLatestGroup()throws SQLException;

    String findLatestSubject()throws SQLException;

    int findLecturerHallCount() throws SQLException;

    int findLabCount() throws SQLException;
}
