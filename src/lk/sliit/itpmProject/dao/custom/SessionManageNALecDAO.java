package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.SessionManageNALec;

import java.util.List;

public interface SessionManageNALecDAO extends CrudDAO<SessionManageNALec, String> {
    boolean saveLec(SessionManageNALec sessionManageNALec) throws Exception;

    boolean saveGroup(SessionManageNALec sessionManageNALec) throws Exception;

    boolean saveSubGroup(SessionManageNALec sessionManageNALec) throws Exception;

    boolean saveNASessionRoom(SessionManageNALec sessionManageNALec) throws Exception;

    List<SessionManageNALec> findAllDataSes()throws Exception;

    boolean deleteGroup(String valueOf)throws Exception;

    List<SessionManageNALec> findAllDataSUbGroup()throws Exception;

    boolean deleteSGroup(String valueOf)throws Exception;

    List<SessionManageNALec> findAllDataRoom()throws Exception;

    boolean deleteRoomNa(String valueOf)throws Exception;
}
