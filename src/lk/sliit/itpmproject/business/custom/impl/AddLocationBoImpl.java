package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.AddLocationsBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.AddLocationsDAO;
import lk.sliit.itpmproject.dto.AddLocationsDTO;
import lk.sliit.itpmproject.entity.AddLocations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddLocationBoImpl implements AddLocationsBO {
    private final AddLocationsDAO addLocationsDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADD_LOCATIONS);

    @Override
    public void saveLocation(AddLocationsDTO addLocationsDTO) throws SQLException {

        addLocationsDAO.save(new AddLocations(
                addLocationsDTO.getId(),
                addLocationsDTO.getBuildingName(),
                addLocationsDTO.getRoomName(),
                addLocationsDTO.isLectureHall(),
                addLocationsDTO.isLaboratory(),
                addLocationsDTO.getCapacity()
        ));


    }

    @Override
    public int getLastLocationId() throws SQLException {
        return addLocationsDAO.getLastLocationID();
    }

    @Override
    public List<AddLocationsDTO> findAllLocations() throws SQLException {
        List<AddLocations> addLocationsList = addLocationsDAO.findAll();
        List<AddLocationsDTO> addLocationsDTOList = new ArrayList<>();
        for (AddLocations a:addLocationsList
        )
              {
            addLocationsDTOList.add(new AddLocationsDTO(
                a.getId(),
                a.getBuildingName(),
                a.getRoomName(),
                a.isLectureHall(),
                a.isLaboratory(),
                a.getCapacity()
            ));
        }
        return addLocationsDTOList;
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        return addLocationsDAO.delete(String.valueOf(id));
    }

    @Override
    public void updateLocation(AddLocationsDTO addLocationsDTO) throws SQLException {
        addLocationsDAO.update(new AddLocations(
                addLocationsDTO.getId(),
                addLocationsDTO.getBuildingName(),
                addLocationsDTO.getRoomName(),
                addLocationsDTO.isLectureHall(),
                addLocationsDTO.isLaboratory(),
                addLocationsDTO.getCapacity()
        ));
    }
}
