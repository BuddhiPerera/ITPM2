package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.TimeTableBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.TimeTableDAO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;
import lk.sliit.itpmProject.dto.TimeTableDTO;
import lk.sliit.itpmProject.entity.CustomEntity;
import lk.sliit.itpmProject.entity.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class TimeTableBOImpl implements TimeTableBO {
    private final TimeTableDAO timeTableDAO  = DAOFactory.getInstance().getDAO(DAOTypes.TimeTable);


    @Override
    public TimeTableDTO loadTimeTable(String s) throws Exception {
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
