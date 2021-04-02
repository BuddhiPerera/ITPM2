package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;

public interface AddLocationsBO extends SuperBO {
    void saveLocation (AddLocationsDTO addLocationsDTO) throws Exception;
}
