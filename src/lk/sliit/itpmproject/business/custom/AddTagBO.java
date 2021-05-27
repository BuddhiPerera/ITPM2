package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddTagDTO;

import java.sql.SQLException;
import java.util.List;

public interface AddTagBO extends SuperBO {
    void saveTag(AddTagDTO addTagDTO) throws SQLException;

    int getLastItemCode() throws SQLException;

    List<AddTagDTO> findAllTags() throws SQLException;

    void updateTags(AddTagDTO addTagDTO) throws SQLException;

    boolean deleteItem(int id) throws SQLException;
}
