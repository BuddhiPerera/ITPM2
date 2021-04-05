package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.AddLocationsDAO;
import lk.sliit.itpmProject.entity.AddLocations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddLocationsDAOImpl implements AddLocationsDAO {
    @Override
    public List<AddLocations> findAll() throws Exception {
        return null;
    }

    @Override
    public AddLocations find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AddLocations entity) throws Exception {

        return CrudUtil.execute("INSERT INTO AddLocations VALUES (?,?,?,?,?,?)",
                entity.getId(),
                entity.getBuildingName(),
                entity.getRoomName(),
                entity.isLectureHall(),
                entity.isLaboratory(),
                entity.getCapacity()
        );
    }

    @Override
    public boolean update(AddLocations entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public int getLastLocationID() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AddLocations ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }
}
