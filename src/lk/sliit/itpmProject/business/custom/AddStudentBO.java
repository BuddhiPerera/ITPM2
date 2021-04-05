package lk.sliit.itpmProject.business.custom;


import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddStudentDTO;

import java.util.List;

public interface AddStudentBO extends SuperBO {
    boolean saveStudent(AddStudentDTO addStudentDTO) throws Exception;


    int getLastItemCode() throws Exception;

    List<AddStudentDTO> findAllStudent() throws Exception;

    boolean updateStudent(AddStudentDTO addStudentDTO) throws Exception;

    boolean deleteItem(int id) throws Exception;
}
