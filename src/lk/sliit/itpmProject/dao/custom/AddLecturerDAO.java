package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddLecturer;

import java.util.List;

public interface AddLecturerDAO extends CrudDAO<AddLecturer,String> {
    int getLastLecturerID() throws Exception;

    List<AddLecturer> findAllNames() throws Exception;
}
