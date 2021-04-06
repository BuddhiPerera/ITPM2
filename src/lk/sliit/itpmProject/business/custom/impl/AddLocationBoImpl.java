package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddLocationsDAO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.entity.AddLocations;

import java.util.ArrayList;
import java.util.List;

public class AddLocationBoImpl implements AddLocationsBO {
    private final AddLocationsDAO addLocationsDAO = DAOFactory.getInstance().getDAO(DAOTypes.AddLocations);

    @Override
    public void saveLocation(AddLocationsDTO addLocationsDTO) throws Exception {

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
    public int getLastLocationId() throws Exception {
        return addLocationsDAO.getLastLocationID();
    }

    @Override
    public List<AddLocationsDTO> findAllLocations() throws Exception {
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
    public boolean deleteItem(int id) throws Exception {
        return addLocationsDAO.delete(String.valueOf(id));
    }

    @Override
    public boolean updateLocation(AddLocationsDTO addLocationsDTO) throws Exception {
        return addLocationsDAO.update(new AddLocations(
                addLocationsDTO.getId(),
                addLocationsDTO.getBuildingName(),
                addLocationsDTO.getRoomName(),
                addLocationsDTO.isLectureHall(),
                addLocationsDTO.isLaboratory(),
                addLocationsDTO.getCapacity()
        ));
    }
}
