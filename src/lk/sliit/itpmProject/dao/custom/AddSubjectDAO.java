package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddSubject;

public interface AddSubjectDAO extends CrudDAO<AddSubject, String> {
    int getLastSubjectId() throws Exception;

    String findOne(String selectSubject) throws Exception;
}
