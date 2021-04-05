package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddTagBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddTagDAO;
import lk.sliit.itpmProject.dto.AddTagDTO;
import lk.sliit.itpmProject.entity.AddTag;

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
}
