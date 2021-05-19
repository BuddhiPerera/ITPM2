package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.AddLecturerDAO;
import lk.sliit.itpmProject.entity.AddLectureWorkingDays;
import lk.sliit.itpmProject.entity.AddLecturer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddLecturerDAOImpl implements AddLecturerDAO {
    @Override
    public List<AddLecturer> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM AddLecturer");
        List<AddLecturer> addLecturerList = new ArrayList<>();
        while (resultSet.next()){
            addLecturerList.add(new AddLecturer(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

                    ) );
        }
        return addLecturerList;
    }

    @Override
    public AddLecturer find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AddLecturer entity) throws Exception {
        return CrudUtil.execute("INSERT INTO AddLecturer VALUES (?,?,?,?,?,?,?,?,?)",
                entity.getId(),
                entity.getEmpId(),
                entity.getlName(),
                entity.getDepartment(),
                entity.getFaculty(),
                entity.getCenter(),
                entity.getBuildingNo(),
                entity.getLevel(),
                entity.getRank()
        );
    }

    @Override
    public boolean update(AddLecturer entity) throws Exception {
        return CrudUtil.execute("UPDATE AddLecturer SET empId = ?, lName = ?, department =?, faculty =?, center =?, buildingNO = ?, `level` =?, `rank` =? WHERE id = ?",
                entity.getEmpId(),
                entity.getlName(),
                entity.getDepartment(),
                entity.getFaculty(),
                entity.getCenter(),
                entity.getBuildingNo(),
                entity.getLevel(),
                entity.getRank(),
                entity.getId()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM AddLecturer WHERE id=?", s);
    }

    @Override
    public int getLastLecturerID() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AddLecturer ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    @Override
    public List<AddLecturer> findAllNames() throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM AddLecturer");
        List<AddLecturer> addLecturerList = new ArrayList<>();
        while (resultSet.next()){
            addLecturerList.add(new AddLecturer(
                    resultSet.getString(3)
            ) );
        }
        return addLecturerList;
    }

    @Override
    public int checkExists(String val) throws Exception {
       int i = 0;
        ResultSet rst = null;
        rst = CrudUtil.execute("SELECT id FROM AddLecturer where empId =?",val);
        while(rst.next()){
            i = 1;
        }
        return i;
    }

    @Override
    public boolean saveDays(AddLectureWorkingDays addLectureWorkingDays) throws Exception {
        return CrudUtil.execute("INSERT INTO LecturerWorkDay VALUES (?,?,?,?,?,?,?,?)",
                addLectureWorkingDays.getEmpId(),
                addLectureWorkingDays.isSundayCB(),
                addLectureWorkingDays.isMondayCB(),
                addLectureWorkingDays.isTuesdayCB(),
                addLectureWorkingDays.isWednesdayCB(),
                addLectureWorkingDays.isThursdayCB(),
                addLectureWorkingDays.isFridayCB(),
                addLectureWorkingDays.isSaturdayCB()
        );
    }
}
