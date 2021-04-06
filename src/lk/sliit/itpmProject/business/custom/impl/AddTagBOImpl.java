package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddTagBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddTagDAO;
import lk.sliit.itpmProject.dto.AddTagDTO;
import lk.sliit.itpmProject.entity.AddTag;

import java.util.ArrayList;
import java.util.List;

public class AddTagBOImpl implements AddTagBO {
    private final AddTagDAO addTagDAO = DAOFactory.getInstance().getDAO(DAOTypes.AddTag);
    @Override
    public boolean saveTag(AddTagDTO addTagDTO) throws Exception {
        return addTagDAO.save(new AddTag(
                addTagDTO.getId(),
                addTagDTO.getTagName(),
                addTagDTO.getTagCode(),
                addTagDTO.getRelatedTag()
        ));
    }

    @Override
    public int getLastItemCode() throws Exception {
        return addTagDAO.getLastTagID();
    }

    @Override
    public List<AddTagDTO> findAllTags() throws Exception {
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
    public boolean updateTags(AddTagDTO addTagDTO) throws Exception {
        return addTagDAO.update(new AddTag(
                addTagDTO.getId(),
                addTagDTO.getTagName(),
                addTagDTO.getTagCode(),
                addTagDTO.getRelatedTag()
        ));
    }

    @Override
    public boolean deleteItem(int id) throws Exception {
        return addTagDAO.delete(String.valueOf(id));
    }
}
