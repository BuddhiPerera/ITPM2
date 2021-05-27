package lk.sliit.itpmproject.util;

public class StudentTM {
    private int id;
    private int year;
    private int semester;
    private String programme;
    private int groupNo;
    private int subGroupNo;
    private String groupId;
    private String subGroupId;

    public StudentTM(int id, int year, int semester, String programme, int groupNo, int subGroupNo, String groupId, String subGroupId) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.programme = programme;
        this.groupNo = groupNo;
        this.subGroupNo = subGroupNo;
        this.groupId = groupId;
        this.subGroupId = subGroupId;
    }

    public StudentTM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getSubGroupNo() {
        return subGroupNo;
    }

    public void setSubGroupNo(int subGroupNo) {
        this.subGroupNo = subGroupNo;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubGroupId() {
        return subGroupId;
    }

    public void setSubGroupId(String subGroupId) {
        this.subGroupId = subGroupId;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "id=" + id +
                ", year=" + year +
                ", semester=" + semester +
                ", programme='" + programme + '\'' +
                ", groupNo=" + groupNo +
                ", subGroupNo=" + subGroupNo +
                ", groupId='" + groupId + '\'' +
                ", subGroupId='" + subGroupId + '\'' +
                '}';
    }
}
