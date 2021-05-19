package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddSession;

public interface SessionManageDAO extends CrudDAO<AddSession,String> {
    int getLastSessionId() throws Exception;

    boolean save(AddSession addSession) throws Exception;

    int getLastNotAvbLectures() throws Exception;

    void updateRoom(String val1, String val2) throws Exception;

    int getLastNotAvbGroups() throws Exception;

    int getLastNotAvbSubGroups()throws Exception;

    int getLastNARoom() throws Exception;
}
