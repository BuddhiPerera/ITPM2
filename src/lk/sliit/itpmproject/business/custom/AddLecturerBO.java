package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddLecturerDTO;
import lk.sliit.itpmproject.dto.DaysDTO;

import java.sql.SQLException;
import java.util.List;

public interface AddLecturerBO extends SuperBO {
    default void saveLecturer(AddLecturerDTO addLecturerDTO) throws SQLException {

    }

    int getLastItemCode() throws SQLException;

    List<AddLecturerDTO> findAllLecturers() throws SQLException;

    void updateLecturer(AddLecturerDTO addLecturerDTO) throws SQLException;

    boolean deleteItem(int id) throws SQLException;

    List<AddLecturerDTO> findAllLecturersName() throws SQLException;

    int checkExists(String empId) throws SQLException;

    boolean saveLecturerDays(DaysDTO daysDTO) throws SQLException;
}
