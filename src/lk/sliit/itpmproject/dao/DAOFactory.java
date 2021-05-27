package lk.sliit.itpmproject.dao;


import lk.sliit.itpmproject.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if(daoFactory != null){
            return daoFactory;
        }else {
            daoFactory = new DAOFactory();
        }
     return daoFactory;

    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case ADD_WORKING_DAYS_DAO:
                return (T) new AddWorkingDaysDAOImpl();
            case ADD_STUDENT:
                return (T) new AddStudentDAOImpl();
            case ADD_LECTURER:
                return (T) new AddLecturerDAOImpl();
            case ADD_SUBJECT:
                return (T) new AddSubjectDAOImpl();
            case ADD_TAG:
                return (T) new AddTagDAOImpl();
            case ADD_LOCATIONS:
                return (T) new AddLocationsDAOImpl();
            case ADD_SESSIONS:
                return (T) new SessionManageDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case STUDENT_STATICS:
                return (T) new StudentStaticsDAOImpl();
            case CONSECUTIVE_SESSIONS:
                return (T) new ConsecutiveSessionsDAOImpl();
            case SESSION_MANAGE_NA_LEC:
                return (T) new SessionManageNALecDAOImpl();
            case TIME_TABLE:
                return (T) new TimeTableDAOImpl();
            default:
                return null;
        }
    }

}
