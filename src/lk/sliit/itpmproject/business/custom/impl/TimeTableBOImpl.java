package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.TimeTableBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.TimeTableDAO;
import lk.sliit.itpmproject.dto.TimeTableDTO;
import lk.sliit.itpmproject.entity.TimeTable;

import java.sql.SQLException;

public class TimeTableBOImpl implements TimeTableBO {
    private final TimeTableDAO timeTableDAO  = DAOFactory.getInstance().getDAO(DAOTypes.TIME_TABLE);


    @Override
    public TimeTableDTO loadTimeTable(String s) throws SQLException {
       TimeTable timeTable = timeTableDAO.genLecTimeTable(s);
        TimeTableDTO timeTables = new TimeTableDTO();


        timeTables.setTimeSlot(timeTable.getTimeSlot());
        timeTables.setMonday(timeTable.getMonday());
        timeTables.setTuesday(timeTable.getTuesday());
        timeTables.setWednesday(timeTable.getWednesday());
        timeTables.setThursday(timeTable.getThursday());
        timeTables.setFriday(timeTable.getFriday());
        timeTables.setSaturday(timeTable.getSaturday());
        timeTables.setSunday(timeTable.getSunday());

        return timeTables;
    }


}
