package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddLocations;

import java.sql.SQLException;

public interface AddLocationsDAO extends CrudDAO<AddLocations,String> {

    int getLastLocationID() throws SQLException, Exception;
}
