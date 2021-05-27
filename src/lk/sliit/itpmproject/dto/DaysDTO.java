package lk.sliit.itpmproject.dto;

public class DaysDTO {
    String empId;
    boolean saturdayCB;
    boolean sundayCB;
    boolean mondayCB;
    boolean thursdayCB;
    boolean wednesdayCB;
    boolean tuesdayCB;
    boolean fridayCB;

    public DaysDTO() {
    }

    public DaysDTO(String empId, boolean saturdayCB, boolean sundayCB, boolean mondayCB, boolean tuesdayCB, boolean wednesdayCB, boolean thursdayCB, boolean fridayCB) {
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

    public boolean getSaturdayCB() {
        return saturdayCB;
    }

    public void setSaturdayCB(boolean saturdayCB) {
        this.saturdayCB = saturdayCB;
    }

    public boolean getSundayCB() {
        return sundayCB;
    }

    public void setSundayCB(boolean sundayCB) {
        this.sundayCB = sundayCB;
    }

    public boolean getMondayCB() {
        return mondayCB;
    }

    public void setMondayCB(boolean mondayCB) {
        this.mondayCB = mondayCB;
    }

    public boolean getThursdayCB() {
        return thursdayCB;
    }

    public void setThursdayCB(boolean thursdayCB) {
        this.thursdayCB = thursdayCB;
    }

    public boolean getWednesdayCB() {
        return wednesdayCB;
    }

    public void setWednesdayCB(boolean wednesdayCB) {
        this.wednesdayCB = wednesdayCB;
    }

    public boolean getTuesdayCB() {
        return tuesdayCB;
    }

    public void setTuesdayCB(boolean tuesdayCB) {
        this.tuesdayCB = tuesdayCB;
    }

    public boolean getFridayCB() {
        return fridayCB;
    }

    public void setFridayCB(boolean fridayCB) {
        this.fridayCB = fridayCB;
    }

    @Override
    public String toString() {
        return "DaysDTO{" +
                "empId='" + empId + '\'' +
                ", saturdayCB='" + saturdayCB + '\'' +
                ", sundayCB='" + sundayCB + '\'' +
                ", mondayCB='" + mondayCB + '\'' +
                ", thursdayCB='" + thursdayCB + '\'' +
                ", wednesdayCB='" + wednesdayCB + '\'' +
                ", tuesdayCB='" + tuesdayCB + '\'' +
                ", fridayCB='" + fridayCB + '\'' +
                '}';
    }
}

