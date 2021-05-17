package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.SuperDAO;

import lk.sliit.itpmProject.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getInfo() throws Exception;

    List<CustomEntity> getInfoSelect(int i, String val) throws Exception;
}
