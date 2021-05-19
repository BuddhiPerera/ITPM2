package lk.sliit.itpmProject.entity;

public class SessionManageNALec implements SuperEntity {
    int maxCode;
    String lectureComboValue;
    String naTimeLectureGroupValue1;
    String naTimeLectureGroupValue;
    String naTimeLectureSessionIdTxtValue;
    String naTimeLectureTxtText;

    public SessionManageNALec(int maxCode, String lectureComboValue, String naTimeLectureGroupValue1, String naTimeLectureGroupValue, String naTimeLectureSessionIdTxtValue, String naTimeLectureTxtText) {
        this.maxCode = maxCode;
        this.lectureComboValue = lectureComboValue;
        this.naTimeLectureGroupValue1 = naTimeLectureGroupValue1;
        this.naTimeLectureGroupValue = naTimeLectureGroupValue;
        this.naTimeLectureSessionIdTxtValue = naTimeLectureSessionIdTxtValue;
        this.naTimeLectureTxtText = naTimeLectureTxtText;
    }

    public SessionManageNALec() {
    }

    public SessionManageNALec(int maxCode, String lectureComboValue, String naTimeLectureTxtText) {
        this.maxCode = maxCode;
        this.lectureComboValue = lectureComboValue;
        this.naTimeLectureTxtText = naTimeLectureTxtText;
    }

    public int getMaxCode() {
        return maxCode;
    }

    public void setMaxCode(int maxCode) {
        this.maxCode = maxCode;
    }

    public String getLectureComboValue() {
        return lectureComboValue;
    }

    public void setLectureComboValue(String lectureComboValue) {
        this.lectureComboValue = lectureComboValue;
    }

    public String getNaTimeLectureGroupValue1() {
        return naTimeLectureGroupValue1;
    }

    public void setNaTimeLectureGroupValue1(String naTimeLectureGroupValue1) {
        this.naTimeLectureGroupValue1 = naTimeLectureGroupValue1;
    }

    public String getNaTimeLectureGroupValue() {
        return naTimeLectureGroupValue;
    }

    public void setNaTimeLectureGroupValue(String naTimeLectureGroupValue) {
        this.naTimeLectureGroupValue = naTimeLectureGroupValue;
    }

    public String getNaTimeLectureSessionIdTxtValue() {
        return naTimeLectureSessionIdTxtValue;
    }

    public void setNaTimeLectureSessionIdTxtValue(String naTimeLectureSessionIdTxtValue) {
        this.naTimeLectureSessionIdTxtValue = naTimeLectureSessionIdTxtValue;
    }

    public String getNaTimeLectureTxtText() {
        return naTimeLectureTxtText;
    }

    public void setNaTimeLectureTxtText(String naTimeLectureTxtText) {
        this.naTimeLectureTxtText = naTimeLectureTxtText;
    }

    @Override
    public String toString() {
        return "SessionManageNALec{" +
                "maxCode=" + maxCode +
                ", lectureComboValue='" + lectureComboValue + '\'' +
                ", naTimeLectureGroupValue1=" + naTimeLectureGroupValue1 +
                ", naTimeLectureGroupValue='" + naTimeLectureGroupValue + '\'' +
                ", naTimeLectureSessionIdTxtValue='" + naTimeLectureSessionIdTxtValue + '\'' +
                ", naTimeLectureTxtText='" + naTimeLectureTxtText + '\'' +
                '}';
    }
}
