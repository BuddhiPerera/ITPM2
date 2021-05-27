package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddLocationsDTO;

import java.sql.SQLException;
import java.util.List;

public interface AddLocationsBO extends SuperBO {
    void saveLocation (AddLocationsDTO addLocationsDTO) throws SQLException;

    int getLastLocationId() throws SQLException;

    List<AddLocationsDTO> findAllLocations() throws SQLException;

    boolean deleteItem(int id) throws SQLException;

    void updateLocation(AddLocationsDTO addLocationsDTO) throws SQLException;
}
