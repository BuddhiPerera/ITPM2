package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.StudentStaticsDAO;
import lk.sliit.itpmProject.entity.AddStudent;

import java.sql.ResultSet;
import java.util.List;

public class StudentStaticsDAOImpl  implements StudentStaticsDAO {

    @Override
    public List<AddStudent> findAll() throws Exception {
        return null;
    }

    @Override
    public AddStudent find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AddStudent entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(AddStudent entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public int findLecturerCount() throws Exception {

        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddLecturer");
        if (rs.next()) {
            int i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findStudentCount() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddStudentSubGroups");
        if (rs.next()) {
            int i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findSubjectCount() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddSubject");
        if (rs.next()) {
            int i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public int findRegisteredRooms() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) AS val FROM AddLocations");
        if (rs.next()) {
            int i = rs.getInt("val");
            return i;
        }
        return 0;
    }

    @Override
    public String findLatestLecturer() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT lName AS val FROM AddLecturer ORDER BY id DESC LIMIT 1");
        if (rs.next()) {
            String i = rs.getString("val");
            return i;
        }
        return "No Data";
    }

    @Override
    public String findLatestGroup() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT subGroupId AS val FROM AddStudentSubGroups ORDER BY id DESC LIMIT 1");
        if (rs.next()) {
            String i = rs.getString("val");
            return i;
        }
        return "No Data";
    }

    @Override
    public String findLatestSubject() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT subName AS val FROM AddSubject ORDER BY id DESC LIMIT 1");
        if (rs.next()) {
            String i = rs.getString("val");
            return i;
        }
        return "No Data";
    }
}
