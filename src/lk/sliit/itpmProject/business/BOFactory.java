package lk.sliit.itpmProject.business;

import lk.sliit.itpmProject.business.custom.impl.AddLecturerBOImpl;
import lk.sliit.itpmProject.business.custom.impl.AddStudentBOImpl;
import lk.sliit.itpmProject.business.custom.impl.AddSubjectBOImpl;
import lk.sliit.itpmProject.business.custom.impl.AddWorkingDaysAndHoursBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null)? (boFactory = new BOFactory()): boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case AddWorkingDays:
                return (T) new AddWorkingDaysAndHoursBOImpl();
            case AddStudent:
                return (T) new AddStudentBOImpl();
            case AddLecturer:
                return (T) new AddLecturerBOImpl();
            case AddSubject:
                return (T) new AddSubjectBOImpl();

            default:
                return null;
        }
    }

}
