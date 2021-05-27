package lk.sliit.itpmproject.business;

import lk.sliit.itpmproject.business.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {

    if(boFactory==null){
        boFactory = new BOFactory();
        return  boFactory;
    }
    else {
        return boFactory;
    }
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ADD_WORKING_DAYS:
                return (T) new AddWorkingDaysAndHoursBOImpl();
            case ADD_STUDENT:
                return (T) new AddStudentBOImpl();
            case ADD_LECTURER:
                return (T) new AddLecturerBOImpl();
            case ADD_SUBJECT:
                return (T) new AddSubjectBOImpl();
            case ADD_TAG:
                return (T) new AddTagBOImpl();
            case ADD_LOCATIONS:
                return (T) new AddLocationBoImpl();
            case STUDENT_STATICS:
                return (T) new StudentStaticsBOImpl();
            case ADD_SESSION:
                return (T) new SessionManageBOImpl();
            case TIME_TABLE:
                return (T) new TimeTableBOImpl();

            default:
                return null;
        }
    }

}
