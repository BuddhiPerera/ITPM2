package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddLocationsBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddLocationsDAO;
import lk.sliit.itpmProject.dto.AddLocationsDTO;
import lk.sliit.itpmProject.entity.AddLocations;

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
}
