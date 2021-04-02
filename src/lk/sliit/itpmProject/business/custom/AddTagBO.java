package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddTagDTO;

public interface AddTagBO extends SuperBO {
    boolean saveTag(AddTagDTO addTagDTO) throws Exception;

    int getLastItemCode() throws Exception;
}
