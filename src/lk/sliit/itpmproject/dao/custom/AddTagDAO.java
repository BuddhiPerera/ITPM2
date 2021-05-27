package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddTag;

import java.sql.SQLException;

public interface AddTagDAO extends CrudDAO<AddTag, String> {
    int getLastTagID() throws SQLException;
}
