package lk.sliit.itpmproject.util;

public class SubjectTM {

    private int id;
    private int offeredYear;
    boolean semester1,semester2;
    private int noOfLecHrs;
    private int noOfTutHrs;
    private int noOfLabHrs;
    private String subName;
    private int noOfEvlHrs;
    private String subCode;

    public SubjectTM(int id, int offeredYear, boolean semester1, boolean semester2, int noOfLecHrs, int noOfTutHrs, int noOfLabHrs, String subName, int noOfEvlHrs, String subCode) {
        this.id = id;
        this.offeredYear = offeredYear;
        this.semester1 = semester1;
        this.semester2 = semester2;
        this.noOfLecHrs = noOfLecHrs;
        this.noOfTutHrs = noOfTutHrs;
        this.noOfLabHrs = noOfLabHrs;
        this.subName = subName;
        this.noOfEvlHrs = noOfEvlHrs;
        this.subCode = subCode;
    }

    public SubjectTM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOfferedYear() {
        return offeredYear;
    }

    public void setOfferedYear(int offeredYear) {
        this.offeredYear = offeredYear;
    }

    public boolean isSemester1() {
        return semester1;
    }

    public void setSemester1(boolean semester1) {
        this.semester1 = semester1;
    }

    public boolean isSemester2() {
        return semester2;
    }

    public void setSemester2(boolean semester2) {
        this.semester2 = semester2;
    }

    public int getNoOfLecHrs() {
        return noOfLecHrs;
    }

    public void setNoOfLecHrs(int noOfLecHrs) {
        this.noOfLecHrs = noOfLecHrs;
    }

    public int getNoOfTutHrs() {
        return noOfTutHrs;
    }

    public void setNoOfTutHrs(int noOfTutHrs) {
        this.noOfTutHrs = noOfTutHrs;
    }

    public int getNoOfLabHrs() {
        return noOfLabHrs;
    }

    public void setNoOfLabHrs(int noOfLabHrs) {
        this.noOfLabHrs = noOfLabHrs;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getNoOfEvlHrs() {
        return noOfEvlHrs;
    }

    public void setNoOfEvlHrs(int noOfEvlHrs) {
        this.noOfEvlHrs = noOfEvlHrs;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    @Override
    public String toString() {
        return "SubjectTM{" +
                "id=" + id +
                ", offeredYear=" + offeredYear +
                ", semester1=" + semester1 +
                ", semester2=" + semester2 +
                ", noOfLecHrs=" + noOfLecHrs +
                ", noOfTutHrs=" + noOfTutHrs +
                ", noOfLabHrs=" + noOfLabHrs +
                ", subName='" + subName + '\'' +
                ", noOfEvlHrs=" + noOfEvlHrs +
                ", subCode='" + subCode + '\'' +
                '}';
    }
}
