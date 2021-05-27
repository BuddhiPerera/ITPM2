package lk.sliit.itpmproject.entity;

public class AddLectureWorkingDays {
    String empId;
    boolean saturdayCB;
    boolean sundayCB;
    boolean mondayCB;
    boolean thursdayCB;
    boolean wednesdayCB;
    boolean tuesdayCB;
    boolean fridayCB;

    public AddLectureWorkingDays() {
    }

    public AddLectureWorkingDays(String empId, boolean saturdayCB, boolean sundayCB, boolean mondayCB, boolean thursdayCB, boolean wednesdayCB, boolean tuesdayCB, boolean fridayCB) {
        this.empId = empId;
        this.saturdayCB = saturdayCB;
        this.sundayCB = sundayCB;
        this.mondayCB = mondayCB;
        this.thursdayCB = thursdayCB;
        this.wednesdayCB = wednesdayCB;
        this.tuesdayCB = tuesdayCB;
        this.fridayCB = fridayCB;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public boolean isSaturdayCB() {
        return saturdayCB;
    }

    public void setSaturdayCB(boolean saturdayCB) {
        this.saturdayCB = saturdayCB;
    }

    public boolean isSundayCB() {
        return sundayCB;
    }

    public void setSundayCB(boolean sundayCB) {
        this.sundayCB = sundayCB;
    }

    public boolean isMondayCB() {
        return mondayCB;
    }

    public void setMondayCB(boolean mondayCB) {
        this.mondayCB = mondayCB;
    }

    public boolean isThursdayCB() {
        return thursdayCB;
    }

    public void setThursdayCB(boolean thursdayCB) {
        this.thursdayCB = thursdayCB;
    }

    public boolean isWednesdayCB() {
        return wednesdayCB;
    }

    public void setWednesdayCB(boolean wednesdayCB) {
        this.wednesdayCB = wednesdayCB;
    }

    public boolean isTuesdayCB() {
        return tuesdayCB;
    }

    public void setTuesdayCB(boolean tuesdayCB) {
        this.tuesdayCB = tuesdayCB;
    }

    public boolean isFridayCB() {
        return fridayCB;
    }

    public void setFridayCB(boolean fridayCB) {
        this.fridayCB = fridayCB;
    }

    @Override
    public String toString() {
        return "AddLectureWorkingDays{" +
                "empId='" + empId + '\'' +
                ", saturdayCB=" + saturdayCB +
                ", sundayCB=" + sundayCB +
                ", mondayCB=" + mondayCB +
                ", thursdayCB=" + thursdayCB +
                ", wednesdayCB=" + wednesdayCB +
                ", tuesdayCB=" + tuesdayCB +
                ", fridayCB=" + fridayCB +
                '}';
    }
}
