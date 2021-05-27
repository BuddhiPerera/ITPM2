package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;

import java.sql.SQLException;

public interface StudentStaticsBO extends SuperBO {
    int findLectureCount() throws SQLException;

    int findStudentCount() throws SQLException;

    int findSubjhectCount() throws SQLException;

    int findRegisteredRoomCount() throws SQLException;

    String findLatestLecturer() throws SQLException;

    String findLatestGroup()throws SQLException;

    String findLatestSubject()throws SQLException;

    int findLecturerHallCount() throws SQLException;

    int findLabCount() throws SQLException;
}
