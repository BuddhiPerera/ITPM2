package lk.sliit.itpmproject.demo_data;

public class AddSessionDemo {


     static String selectLecturer;
     static int id;
     static String selectTag;
     static String selectedLecturer;
     static String selectGroup;
     static int student;
     static String subjectId;
     static int durationHrs;

    private AddSessionDemo() {
    }

    public static String getSelectLecturer() {
        return selectLecturer;
    }

    public static void setSelectLecturer(String selectLecturer) {
        AddSessionDemo.selectLecturer = selectLecturer;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AddSessionDemo.id = id;
    }

    public static String getSelectTag() {
        return selectTag;
    }

    public static void setSelectTag(String selectTag) {
        AddSessionDemo.selectTag = selectTag;
    }

    public static String getSelectedLecturer() {
        return selectedLecturer;
    }

    public static void setSelectedLecturer(String selectedLecturer) {
        AddSessionDemo.selectedLecturer = selectedLecturer;
    }

    public static String getSelectGroup() {
        return selectGroup;
    }

    public static void setSelectGroup(String selectGroup) {
        AddSessionDemo.selectGroup = selectGroup;
    }

    public static int getStudent() {
        return student;
    }

    public static void setStudent(int student) {
        AddSessionDemo.student = student;
    }

    public static String getSubjectId() {
        return subjectId;
    }

    public static void setSubjectId(String subjectId) {
        AddSessionDemo.subjectId = subjectId;
    }

    public static int getDurationHrs() {
        return durationHrs;
    }

    public static void setDurationHrs(int durationHrs) {
        AddSessionDemo.durationHrs = durationHrs;
    }
}
