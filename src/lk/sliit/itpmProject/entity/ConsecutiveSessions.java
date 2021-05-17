package lk.sliit.itpmProject.entity;

public class ConsecutiveSessions implements SuperEntity {
String id;
String lectureOne;
String lectureTwo;
String subjectCode;
String subjectName;
String groupId;
String tagName;


    public ConsecutiveSessions(String id, String lectureOne, String lectureTwo, String subjectCode, String subjectName, String groupId, String tagName) {
        this.id = id;
        this.lectureOne = lectureOne;
        this.lectureTwo = lectureTwo;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.groupId = groupId;
        this.tagName = tagName;
    }

    public ConsecutiveSessions() {
    }

    @Override
    public String toString() {
        return "ConsecutiveSessions{" +
                "id='" + id + '\'' +
                ", lectureOne='" + lectureOne + '\'' +
                ", lectureTwo='" + lectureTwo + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLectureOne() {
        return lectureOne;
    }

    public void setLectureOne(String lectureOne) {
        this.lectureOne = lectureOne;
    }

    public String getLectureTwo() {
        return lectureTwo;
    }

    public void setLectureTwo(String lectureTwo) {
        this.lectureTwo = lectureTwo;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
