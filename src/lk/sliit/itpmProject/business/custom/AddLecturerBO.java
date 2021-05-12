package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddLecturerDTO;

import java.util.List;

public interface AddLecturerBO extends SuperBO {
    void saveLecturer (AddLecturerDTO addLecturerDTO) throws Exception;

    int getLastItemCode() throws Exception;

    List<AddLecturerDTO> findAllLecturers() throws Exception;

    boolean updateLecturer(AddLecturerDTO addLecturerDTO) throws Exception;

    boolean deleteItem(int id) throws Exception;

    List<AddLecturerDTO> findAllLecturersName() throws Exception;
}
