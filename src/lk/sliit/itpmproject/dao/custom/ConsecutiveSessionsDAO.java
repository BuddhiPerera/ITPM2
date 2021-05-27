package lk.sliit.itpmproject.dao.custom;

import lk.sliit.itpmproject.dao.CrudDAO;
import lk.sliit.itpmproject.entity.ConsecutiveSessions;

import java.sql.SQLException;
import java.util.List;

public interface ConsecutiveSessionsDAO extends CrudDAO<ConsecutiveSessions, String> {
    void addconsecutiveSessions(List<ConsecutiveSessions> consecutiveSessions) throws SQLException;

    void savetblNonOverLapping(List<ConsecutiveSessions> consecutiveSessions) throws SQLException;

    void savetblParallel(List<ConsecutiveSessions> consecutiveSessions) throws SQLException;

    List<ConsecutiveSessions> loadConsSessionTable()throws SQLException;
}
