package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.AddTagDAO;
import lk.sliit.itpmProject.entity.AddTag;

import java.sql.ResultSet;
import java.util.List;

public class AddTagDAOImpl implements AddTagDAO {
    @Override
    public List<AddTag> findAll() throws Exception {
        return null;
    }

    @Override
    public AddTag find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AddTag entity) throws Exception {
        return CrudUtil.execute("INSERT INTO AddTags VALUES (?,?,?,?)",
                entity.getId(),
                entity.getTagName(),
                entity.getTagCode(),
                entity.getRelatedTag()
        );
    }

    @Override
    public boolean update(AddTag entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public int getLastTagID() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AddTags ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }
}
