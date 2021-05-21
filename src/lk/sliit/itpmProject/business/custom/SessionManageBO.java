package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddSessionDTO;
import lk.sliit.itpmProject.dto.AddSessionNALectureDTO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;
import lk.sliit.itpmProject.dto.ManageNotAvbTimeDTO;

import java.util.List;

public interface SessionManageBO extends SuperBO {
    int getLastItemCode() throws Exception;

    void saveSession(AddSessionDTO addSessionDTO) throws Exception;

    List<LoadSessionDataDTO> loadSessionTable() throws Exception;


    AddSessionDTO findAllSessions(String id) throws Exception;

    int getLastNotAvbLectures() throws Exception;

    void saveNASessionLec(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception;

    List<ManageNotAvbTimeDTO> findAllData() throws Exception;

    void updateSession(AddSessionDTO addSessionDTO2) throws Exception;


    boolean deleteItem(String id) throws Exception;

    List<AddSessionDTO> findAllSessions() throws Exception;

    void saveNATimeAlocations(List<LoadSessionDataDTO> dtos) throws Exception;

    List<LoadSessionDataDTO> loadSessionTableSearch(int i, String val) throws Exception;

    void saveRoom(String val1, String val2) throws Exception;

    void savetblParallel(List<LoadSessionDataDTO> dtos) throws Exception;

    void savetblNonOverLapping(List<LoadSessionDataDTO> dtos) throws Exception;

    void saveNASessionGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception;

    int getLastNotAvbGroups() throws Exception;

    int getLastNotAvbSubGroups() throws Exception;

    void saveNASessionSubGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception;

    int getLastNARoom() throws Exception;

    void saveNASessionRoom(AddSessionNALectureDTO addSessionNALectureDTO) throws Exception;

    void setUpdateSessionRoom(String val1, String val1R) throws Exception;

    void setUpdateTagRoom(String val2, String val1R)throws Exception;

    void setUpdateLectureRoom(String val3, String val1R) throws Exception;

    void setUpdateGroupRoom(String val4, String val1R)throws Exception;

    void setUpdateSubjectRoom(String val5, String val1R)throws Exception;

    List<LoadSessionDataDTO> loadConsSessionTable()throws Exception;

    void setUpdateConstRoom(String val6, String val1R)throws Exception;

    List<AddSessionDTO> loadSessionLec(String empId)throws Exception;

    List<AddSessionDTO> loadSessionStd(String s) throws Exception;

    List<AddSessionDTO> loadSessionLoc(String s)throws Exception;
}
