package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.StudentStaticsBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.StudentStaticsDAO;

import java.sql.SQLException;

public class StudentStaticsBOImpl implements StudentStaticsBO {
    private final StudentStaticsDAO staticsDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT_STATICS);
    @Override
    public int findLectureCount() throws SQLException {
        return  staticsDAO.findLecturerCount();
    }

    @Override
    public int findStudentCount() throws SQLException {
        return  staticsDAO.findStudentCount();
    }

    @Override
    public int findSubjhectCount() throws SQLException {
        return  staticsDAO.findSubjectCount();
    }

    @Override
    public int findRegisteredRoomCount() throws SQLException {
        return  staticsDAO.findRegisteredRooms();
    }

    @Override
    public String findLatestLecturer() throws SQLException {
        return  staticsDAO.findLatestLecturer();
    }

    @Override
    public String findLatestGroup() throws SQLException {
        return  staticsDAO.findLatestGroup();
    }

    @Override
    public String findLatestSubject() throws SQLException {
        return  staticsDAO.findLatestSubject();
    }

    @Override
    public int findLecturerHallCount() throws SQLException {
        return  staticsDAO.findLecturerHallCount();
    }

    @Override
    public int findLabCount() throws SQLException {
        return  staticsDAO.findLabCount();
    }
}
