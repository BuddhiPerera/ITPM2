package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddSubjectDTO;

import java.sql.SQLException;
import java.util.List;

public interface AddSubjectBO extends SuperBO {
    void saveSubject(AddSubjectDTO addSubjectDTO) throws SQLException;

    int getLastItemCode() throws SQLException;

    List<AddSubjectDTO> findAllSubjects() throws SQLException;

    boolean deleteItem(int id) throws SQLException;

    void updateSubject(AddSubjectDTO addSubjectDTO) throws SQLException;

    String findSubjectName(String selectSubject) throws SQLException;
}
