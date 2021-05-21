package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;

import lk.sliit.itpmProject.entity.TimeTable;

import java.util.List;

public interface TimeTableDAO  extends CrudDAO<TimeTable, String> {
    TimeTable genLecTimeTable(String s) throws Exception;


}
