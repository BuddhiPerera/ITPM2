package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.TimeTableDAO;
import lk.sliit.itpmproject.entity.AddSession;
import lk.sliit.itpmproject.entity.TimeTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableDAOImpl implements TimeTableDAO {
    @Override
    public List<TimeTable> findAll()  {
        List<TimeTable> timeTables = null;
        timeTables = new ArrayList<>();
        return timeTables;
    }

    @Override
    public TimeTable find(String s) {
        return null;
    }

    @Override
    public boolean save(TimeTable entity)  {
        return false;
    }

    @Override
    public boolean update(TimeTable entity)  {
        return false;
    }

    @Override
    public boolean delete(String s)  {
        return false;
    }

    @Override
    public TimeTable genLecTimeTable(String s) throws SQLException {

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
