package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;

import java.util.List;

public interface AddLocationsBO extends SuperBO {
    void saveLocation (AddLocationsDTO addLocationsDTO) throws Exception;

    int getLastLocationId() throws Exception;

    List<AddLocationsDTO> findAllLocations() throws Exception;
}
