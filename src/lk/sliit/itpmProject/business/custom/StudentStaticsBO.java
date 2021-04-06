package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;

public interface StudentStaticsBO extends SuperBO {
    int findLectureCount() throws Exception;

    int findStudentCount() throws Exception;

    int findSubjhectCount() throws Exception;

    int findRegisteredRoomCount() throws Exception;

    String findLatestLecturer() throws Exception;

    String findLatestGroup()throws Exception;

    String findLatestSubject()throws Exception;

    int findLecturerHallCount() throws Exception;

    int findLabCount() throws Exception;
}
