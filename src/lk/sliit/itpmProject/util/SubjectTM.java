package lk.sliit.itpmProject.util;

public class SubjectTM {

    private int id;
    private int offredYear;
    boolean semester1,semester2;
    private int NoOfLecHrs;
    private int NoOfTutHrs;
    private int NoOflabHrs;
    private String subName;
    private int NoOfEvlHrs;
    private String SubCode;

    public SubjectTM(int id, int offredYear, boolean semester1, boolean semester2, int noOfLecHrs, int noOfTutHrs, int noOflabHrs, String subName, int noOfEvlHrs, String subCode) {
        this.id = id;
        this.offredYear = offredYear;
        this.semester1 = semester1;
        this.semester2 = semester2;
        NoOfLecHrs = noOfLecHrs;
        NoOfTutHrs = noOfTutHrs;
        NoOflabHrs = noOflabHrs;
        this.subName = subName;
        NoOfEvlHrs = noOfEvlHrs;
        SubCode = subCode;
    }

    public SubjectTM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffredYear() {
        return offredYear;
    }

    public void setOffredYear(int offredYear) {
        this.offredYear = offredYear;
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
        return NoOfLecHrs;
    }

    public void setNoOfLecHrs(int noOfLecHrs) {
        NoOfLecHrs = noOfLecHrs;
    }

    public int getNoOfTutHrs() {
        return NoOfTutHrs;
    }

    public void setNoOfTutHrs(int noOfTutHrs) {
        NoOfTutHrs = noOfTutHrs;
    }

    public int getNoOflabHrs() {
        return NoOflabHrs;
    }

    public void setNoOflabHrs(int noOflabHrs) {
        NoOflabHrs = noOflabHrs;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getNoOfEvlHrs() {
        return NoOfEvlHrs;
    }

    public void setNoOfEvlHrs(int noOfEvlHrs) {
        NoOfEvlHrs = noOfEvlHrs;
    }

    public String getSubCode() {
        return SubCode;
    }

    public void setSubCode(String subCode) {
        SubCode = subCode;
    }

    @Override
    public String toString() {
        return "SubjectTM{" +
                "id=" + id +
                ", offredYear=" + offredYear +
                ", semester1=" + semester1 +
                ", semester2=" + semester2 +
                ", NoOfLecHrs=" + NoOfLecHrs +
                ", NoOfTutHrs=" + NoOfTutHrs +
                ", NoOflabHrs=" + NoOflabHrs +
                ", subName='" + subName + '\'' +
                ", NoOfEvlHrs=" + NoOfEvlHrs +
                ", SubCode='" + SubCode + '\'' +
                '}';
    }
}
