package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.StudentStaticsDAO;
import lk.sliit.itpmproject.entity.AddStudent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentStaticsDAOImpl  implements StudentStaticsDAO {
    String noData = "No Data";
    @Override
    public List<AddStudent> findAll() {
        List<AddStudent> addStudents = null;
        addStudents = new ArrayList<>();
        return addStudents;
    }

    @Override
    public AddStudent find(String s)  {
        return null;
    }

    @Override
    public boolean save(AddStudent entity)  {
        return false;
    }

    @Override
    public boolean update(AddStudent entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public int findLecturerCount() throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddLecturer");
        int i ;
        if (rs.next()) {
             i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findStudentCount() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddStudentSubGroups");
        int i ;
        if (rs.next()) {
             i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findSubjectCount() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddSubject");
        int i;
        if (rs.next()) {
             i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findRegisteredRooms() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddLocations");
       int i;
        if (rs.next()) {
             i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public String findLatestLecturer() throws SQLException {
         ResultSet rs = CrudUtil.execute("SELECT lName AS val FROM AddLecturer ORDER BY id DESC LIMIT 1");
        String i;
         if (rs.next()) {
             i = rs.getString("val");
            return i;
        }
        return noData;
    }

    @Override
    public String findLatestGroup() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT subGroupId AS val FROM AddStudentSubGroups ORDER BY id DESC LIMIT 1");
        String i;
        if (rs.next()) {
             i = rs.getString("val");
            return i;
        }
        return noData;
    }

    @Override
    public String findLatestSubject() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT SubName AS val FROM AddSubject ORDER BY id DESC LIMIT 1");
        String i;
        if (rs.next()) {
             i = rs.getString("val");
            return i;
        }
        return noData;
    }

    @Override
    public int findLecturerHallCount() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddLocations WHERE lectureHall = true");
       int i ;
        if (rs.next()) {
             i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findLabCount() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddLocations WHERE laboratory = true");
        int i;
        if (rs.next()) {
             i = rs.getInt("val");
            return i;
        }
        return 0;
    }
}
