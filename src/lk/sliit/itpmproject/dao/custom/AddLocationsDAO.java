package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddLocations;

import java.sql.SQLException;

public interface AddLocationsDAO extends CrudDAO<AddLocations,String> {

    int getLastLocationID() throws SQLException;
}
