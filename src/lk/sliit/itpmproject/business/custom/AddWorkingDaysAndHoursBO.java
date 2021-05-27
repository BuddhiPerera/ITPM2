package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.AddWorkingDaysAndHoursDTO;

import java.sql.SQLException;
import java.util.List;

public interface AddWorkingDaysAndHoursBO extends SuperBO {
    void saveWorkingDaysAndHours(AddWorkingDaysAndHoursDTO andHoursDTO) throws SQLException;
    boolean updateWorkingDaysAndHours(AddWorkingDaysAndHoursDTO andHoursDTO) throws SQLException;
    List<AddWorkingDaysAndHoursDTO> findAllWorkingDays() throws SQLException;

}
