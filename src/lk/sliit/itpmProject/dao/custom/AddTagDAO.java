package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddTag;

public interface AddTagDAO extends CrudDAO<AddTag, String> {
    int getLastTagID() throws Exception;
}
