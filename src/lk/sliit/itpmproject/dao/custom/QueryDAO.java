package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.SuperDAO;

import lk.sliit.itpmproject.entity.CustomEntity;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getInfo() throws SQLException;

    List<CustomEntity> getInfoSelect(int i, String val) throws SQLException;
}
