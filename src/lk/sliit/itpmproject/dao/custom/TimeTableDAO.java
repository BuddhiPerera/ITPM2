package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.TimeTable;

import java.sql.SQLException;

public interface TimeTableDAO extends CrudDAO<TimeTable, String> {
    TimeTable genLecTimeTable(String s) throws SQLException;


}
