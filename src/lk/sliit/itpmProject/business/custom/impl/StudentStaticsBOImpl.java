package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.StudentStaticsBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddStudentDAO;
import lk.sliit.itpmProject.dao.custom.StudentStaticsDAO;

public class StudentStaticsBOImpl implements StudentStaticsBO {
    private final StudentStaticsDAO staticsDAO = DAOFactory.getInstance().getDAO(DAOTypes.StudentStatics);
    @Override
    public int findLectureCount() throws Exception {
        return  staticsDAO.findLecturerCount();
    }

    @Override
    public int findStudentCount() throws Exception {
        return  staticsDAO.findStudentCount();
    }

    @Override
    public int findSubjhectCount() throws Exception {
        return  staticsDAO.findSubjectCount();
    }

    @Override
    public int findRegisteredRoomCount() throws Exception {
        return  staticsDAO.findRegisteredRooms();
    }

    @Override
    public String findLatestLecturer() throws Exception {
        return  staticsDAO.findLatestLecturer();
    }

    @Override
    public String findLatestGroup() throws Exception {
        return  staticsDAO.findLatestGroup();
    }

    @Override
    public String findLatestSubject() throws Exception {
        return  staticsDAO.findLatestSubject();
    }
}
