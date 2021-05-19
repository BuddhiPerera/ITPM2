package lk.sliit.itpmProject.dao.custom;

import lk.sliit.itpmProject.dao.CrudDAO;
import lk.sliit.itpmProject.entity.AddWorkingDaysAndHours;
import lk.sliit.itpmProject.entity.ConsecutiveSessions;

import java.util.List;

public interface ConsecutiveSessionsDAO extends CrudDAO<ConsecutiveSessions, String> {
    void addconsecutiveSessions(List<ConsecutiveSessions> consecutiveSessions) throws Exception;

    void savetblNonOverLapping(List<ConsecutiveSessions> consecutiveSessions) throws Exception;

    void savetblParallel(List<ConsecutiveSessions> consecutiveSessions) throws Exception;
}
