package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.AddTagDAO;
import lk.sliit.itpmproject.entity.AddTag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddTagDAOImpl implements AddTagDAO {
    @Override
    public List<AddTag> findAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM AddTags");
        List<AddTag> addTagList = new ArrayList<>();
        while(resultSet.next()){
            addTagList.add(new AddTag(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            ));
        }
        return addTagList;
    }

    @Override
    public AddTag find(String s)  {
        return null;
    }

    @Override
    public boolean save(AddTag entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO AddTags VALUES (?,?,?,?)",
                entity.getId(),
                entity.getTagName(),
                entity.getTagCode(),
                entity.getRelatedTag()
        );
    }

    @Override
    public boolean update(AddTag entity) throws SQLException {
        return CrudUtil.execute("UPDATE AddTags SET tagName = ?, tagCode = ?, relatedTag = ? WHERE id = ?",
                entity.getTagName(),
                entity.getTagCode(),
                entity.getRelatedTag(),
                entity.getId()
                );
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM AddTags WHERE id=?", s);
    }

    @Override
    public int getLastTagID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AddTags ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }
}
