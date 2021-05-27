package lk.sliit.itpmproject.business.custom;


import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddStudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface AddStudentBO extends SuperBO {
    void saveStudent(AddStudentDTO addStudentDTO) throws SQLException;


    int getLastItemCode() throws SQLException;

    List<AddStudentDTO> findAllStudent() throws SQLException;

    void updateStudent(AddStudentDTO addStudentDTO) throws SQLException;

    boolean deleteItem(int id) throws SQLException;
}
