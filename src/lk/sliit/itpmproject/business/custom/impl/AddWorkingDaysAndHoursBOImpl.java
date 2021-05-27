package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.AddWorkingDaysAndHoursBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.AddWorkingDaysDAO;
import lk.sliit.itpmproject.dto.AddWorkingDaysAndHoursDTO;
import lk.sliit.itpmproject.entity.AddWorkingDaysAndHours;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddWorkingDaysAndHoursBOImpl implements AddWorkingDaysAndHoursBO {

    private final AddWorkingDaysDAO workingDaysDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADD_WORKING_DAYS_DAO);

    @Override
    public void saveWorkingDaysAndHours(AddWorkingDaysAndHoursDTO andHoursDTO) throws SQLException {
        workingDaysDAO.save(new AddWorkingDaysAndHours(
                andHoursDTO.getId(),
                andHoursDTO.getNoOfWorkingDays(),
                andHoursDTO.isSunday(), andHoursDTO.isMonday(),
                andHoursDTO.isTuesday(), andHoursDTO.isWednesday(),
                andHoursDTO.isThursday(), andHoursDTO.isFriday(), andHoursDTO.isSaturday(),
                andHoursDTO.getHours(), andHoursDTO.getMinutes()));
    }

    @Override
    public boolean updateWorkingDaysAndHours(AddWorkingDaysAndHoursDTO andHoursDTO) throws SQLException {
        return workingDaysDAO.update(new AddWorkingDaysAndHours(
                andHoursDTO.getId(),
                andHoursDTO.getNoOfWorkingDays(),
                andHoursDTO.isSunday(),andHoursDTO.isMonday(),
                andHoursDTO.isTuesday(),andHoursDTO.isWednesday(),
                andHoursDTO.isThursday(),andHoursDTO.isFriday(),andHoursDTO.isSaturday(),
                andHoursDTO.getHours(),andHoursDTO.getMinutes()
        ));
    }

    @Override
    public List<AddWorkingDaysAndHoursDTO> findAllWorkingDays() throws SQLException {
        List<AddWorkingDaysAndHours> all = workingDaysDAO.findAll();
        List<AddWorkingDaysAndHoursDTO> dtos = new ArrayList<>();
        for (AddWorkingDaysAndHours addWorkingDaysAndHours : all) {
            dtos.add(new AddWorkingDaysAndHoursDTO(
                    addWorkingDaysAndHours.getId(),
                    addWorkingDaysAndHours.getNoOfWorkingDays(),
                    addWorkingDaysAndHours.isSunday(),
                    addWorkingDaysAndHours.isMonday(),
                    addWorkingDaysAndHours.isTuesday(),
                    addWorkingDaysAndHours.isWednesday(),
                    addWorkingDaysAndHours.isThursday(),
                    addWorkingDaysAndHours.isFriday(),
                    addWorkingDaysAndHours.isSaturday(),
                    addWorkingDaysAndHours.getHours(),
                    addWorkingDaysAndHours.getMinutes()
            ));
        }
        return dtos;
    }

}
