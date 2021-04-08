package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddStudent;


public interface StudentStaticsDAO extends CrudDAO<AddStudent, String> {
    int findLecturerCount() throws Exception;

    int findStudentCount() throws Exception;

    int findSubjectCount() throws Exception;

    int findRegisteredRooms() throws Exception;

    String findLatestLecturer() throws Exception;

    String findLatestGroup()throws Exception;

    String findLatestSubject()throws Exception;

    int findLecturerHallCount() throws Exception;

    int findLabCount() throws Exception;
}
