package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.SessionManageNALec;

public interface SessionManageNALecDAO extends CrudDAO<SessionManageNALec, String> {
    boolean saveLec(SessionManageNALec sessionManageNALec) throws Exception;

    boolean saveGroup(SessionManageNALec sessionManageNALec) throws Exception;

    boolean saveSubGroup(SessionManageNALec sessionManageNALec) throws Exception;

    boolean saveNASessionRoom(SessionManageNALec sessionManageNALec) throws Exception;
}
