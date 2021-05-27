package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.AddSubject;

import java.sql.SQLException;

public interface AddSubjectDAO extends CrudDAO<AddSubject, String> {
    int getLastSubjectId() throws SQLException;

    String findOne(String selectSubject) throws SQLException;
}
