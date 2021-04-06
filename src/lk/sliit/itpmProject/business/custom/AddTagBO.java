package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddTagDTO;

import java.util.List;

public interface AddTagBO extends SuperBO {
    boolean saveTag(AddTagDTO addTagDTO) throws Exception;

    int getLastItemCode() throws Exception;

    List<AddTagDTO> findAllTags() throws Exception;

    boolean updateTags(AddTagDTO addTagDTO) throws Exception;

    boolean deleteItem(int id) throws Exception;
}
