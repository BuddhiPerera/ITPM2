package lk.sliit.itpmproject.business.custom;

import lk.sliit.itpmproject.business.SuperBO;
import lk.sliit.itpmproject.dto.TimeTableDTO;

import java.sql.SQLException;

public interface TimeTableBO extends SuperBO {
     TimeTableDTO loadTimeTable(String s) throws SQLException;


}
