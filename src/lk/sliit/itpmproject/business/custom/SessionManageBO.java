package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddSessionDTO;
import lk.sliit.itpmproject.dto.AddSessionNALectureDTO;
import lk.sliit.itpmproject.dto.LoadSessionDataDTO;
import lk.sliit.itpmproject.dto.ManageNotAvbTimeDTO;

import java.sql.SQLException;
import java.util.List;

public interface SessionManageBO extends SuperBO {
    int getLastItemCode() throws SQLException;

    void saveSession(AddSessionDTO addSessionDTO) throws SQLException;

    List<LoadSessionDataDTO> loadSessionTable() throws SQLException;


    AddSessionDTO findAllSessions(String id) throws SQLException;

    int getLastNotAvbLectures() throws SQLException;

    void saveNASessionLec(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException;

    List<ManageNotAvbTimeDTO> findAllData() throws SQLException;

    void updateSession(AddSessionDTO addSessionDTO2) throws SQLException;


    boolean deleteItem(String id) throws SQLException;

    List<AddSessionDTO> findAllSessions() throws SQLException;

    void saveNATimeAlocations(List<LoadSessionDataDTO> dtos) throws SQLException;

    List<LoadSessionDataDTO> loadSessionTableSearch(int i, String val) throws SQLException;

    void saveRoom(String val1, String val2) throws SQLException;

    void savetblParallel(List<LoadSessionDataDTO> dtos) throws SQLException;

    void savetblNonOverLapping(List<LoadSessionDataDTO> dtos) throws SQLException;

    void saveNASessionGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException;

    int getLastNotAvbGroups() throws SQLException;

    int getLastNotAvbSubGroups() throws SQLException;

    void saveNASessionSubGroup(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException;

    int getLastNARoom() throws SQLException;

    void saveNASessionRoom(AddSessionNALectureDTO addSessionNALectureDTO) throws SQLException;

    void setUpdateSessionRoom(String val1, String val1R) throws SQLException;

    void setUpdateTagRoom(String val2, String val1R)throws SQLException;

    void setUpdateLectureRoom(String val3, String val1R) throws SQLException;

    void setUpdateGroupRoom(String val4, String val1R)throws SQLException;

    void setUpdateSubjectRoom(String val5, String val1R)throws SQLException;

    List<LoadSessionDataDTO> loadConsSessionTable()throws SQLException;

    void setUpdateConstRoom(String val6, String val1R)throws SQLException;

    List<AddSessionDTO> loadSessionLec(String empId)throws SQLException;

    List<AddSessionDTO> loadSessionStd(String s) throws SQLException;

    List<AddSessionDTO> loadSessionLoc(String s)throws SQLException;

    void deleteItemNaLec(int id)throws SQLException;

    List<ManageNotAvbTimeDTO> findAllDataSes()throws SQLException;

    void deleteItemNaGroup(int id)throws SQLException;

    List<ManageNotAvbTimeDTO> findAllDataSUbGroup()throws SQLException;

    void deleteItemNaSubGroup(int id)throws SQLException;

    void deleteItemNaLecRoom(int id)throws SQLException;

    List<ManageNotAvbTimeDTO> findAllDataRom()throws SQLException;
}
