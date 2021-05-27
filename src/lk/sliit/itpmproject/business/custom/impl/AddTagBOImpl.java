package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.AddTagBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.AddTagDAO;
import lk.sliit.itpmproject.dto.AddTagDTO;
import lk.sliit.itpmproject.entity.AddTag;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AddTagBOImpl implements AddTagBO {
    private final AddTagDAO addTagDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADD_TAG);
    @Override
    public void saveTag(AddTagDTO addTagDTO) throws SQLException {
        addTagDAO.save(new AddTag(
                addTagDTO.getId(),
                addTagDTO.getTagName(),
                addTagDTO.getTagCode(),
                addTagDTO.getRelatedTag()
        ));
    }

    @Override
    public int getLastItemCode() throws SQLException {
        return addTagDAO.getLastTagID();
    }

    @Override
    public List<AddTagDTO> findAllTags() throws SQLException {
        List<AddTag> addTagList = addTagDAO.findAll();
        List<AddTagDTO> addTagDTOList = new ArrayList<>();
        for (AddTag addTag:addTagList
             ) {
            addTagDTOList.add(new AddTagDTO(
                    addTag.getId(),
                    addTag.getTagName(),
                    addTag.getTagCode(),
                    addTag.getRelatedTag()
            ));
        }
        return addTagDTOList;
    }

    @Override
    public void updateTags(AddTagDTO addTagDTO) throws SQLException {
        addTagDAO.update(new AddTag(
                addTagDTO.getId(),
                addTagDTO.getTagName(),
                addTagDTO.getTagCode(),
                addTagDTO.getRelatedTag()
        ));
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        return addTagDAO.delete(String.valueOf(id));
    }
}
