package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;

import java.util.List;

public interface AddSubjectBO extends SuperBO {
    boolean saveSubject(AddSubjectDTO addSubjectDTO) throws Exception;

    int getLastItemCode() throws Exception;

    List<AddSubjectDTO> findAllSubjects() throws Exception;

    boolean deleteItem(int id) throws Exception;
}
