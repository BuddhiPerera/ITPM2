package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.TimeTableDAO;
import lk.sliit.itpmProject.entity.AddWorkingDaysAndHours;
import lk.sliit.itpmProject.entity.TimeTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TimeTableDAOImpl implements TimeTableDAO {
    @Override
    public List<TimeTable> findAll() throws Exception {
        return null;
    }

    @Override
    public TimeTable find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(TimeTable entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(TimeTable entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public TimeTable genLecTimeTable(String s) throws Exception {

        String employeeId ="";
        ResultSet rst = null;
        rst = CrudUtil.execute("SELECT empId FROM AddLecturer where lName =? ORDER BY empId DESC LIMIT 1",s);
        while(rst.next()){
            employeeId =  rst.getString(1);
        }

        rst = CrudUtil.execute("SELECT * FROM MFkwg22AgC.LecturerWorkDay where empId=? ORDER BY empId DESC LIMIT 1",employeeId);
     TimeTable timeTables = new TimeTable();
        while(rst.next()) {
            timeTables.setTimeSlot(rst.getString(1));
            timeTables.setMonday(rst.getBoolean(3));
            timeTables.setTuesday(rst.getBoolean(4));
            timeTables.setWednesday(rst.getBoolean(5));
            timeTables.setThursday(rst.getBoolean(6));
            timeTables.setFriday(rst.getBoolean(7));
            timeTables.setSaturday(rst.getBoolean(8));
            timeTables.setSunday(rst.getBoolean(2));
        }
        return timeTables;
}


}
