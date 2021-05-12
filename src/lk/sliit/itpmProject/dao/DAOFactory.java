package lk.sliit.itpmProject.dao;


import lk.sliit.itpmProject.business.custom.impl.StudentStaticsBOImpl;
import lk.sliit.itpmProject.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory == null)? (daoFactory = new DAOFactory()): daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case AddWorkingDaysDAO:
                return (T) new AddWorkingDaysDAOImpl();
            case AddStudent:
                return (T) new AddStudentDAOImpl();
            case AddLecturer:
                return (T) new AddLecturerDAOImpl();
            case  AddSubject:
                return (T) new AddSubjectDAOImpl();
            case AddTag:
                return (T) new AddTagDAOImpl();
            case AddLocations:
                return (T) new AddLocationsDAOImpl();
            case  AddSessions:
                return (T) new SessionManageDAOImpl();
            case  StudentStatics:
                return (T) new StudentStaticsDAOImpl();


            default:
                return null;
        }
    }

}
