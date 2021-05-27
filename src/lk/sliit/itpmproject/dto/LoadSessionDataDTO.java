package lk.sliit.itpmproject.dto;


import javafx.scene.control.CheckBox;

public class LoadSessionDataDTO {
    CheckBox checkBox;
    String id;
    String lectureOne;
    String lectureTwo;
    String subjectCode;
    String subjectName;
    String groupId;
    String tagName;

    public LoadSessionDataDTO() {
    }

    public LoadSessionDataDTO(String id, String lectureOne, String lectureTwo, String subjectCode, String subjectName, String groupId, String tagName) {
        this.id = id;
        this.lectureOne = lectureOne;
        this.lectureTwo = lectureTwo;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.groupId = groupId;
        this.tagName = tagName;
    }

    public LoadSessionDataDTO(CheckBox checkBox, String id, String lectureOne, String lectureTwo, String subjectCode, String subjectName, String groupId, String tagName) {
        this.checkBox = checkBox;
        this.id = id;
        this.lectureOne = lectureOne;
        this.lectureTwo = lectureTwo;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.groupId = groupId;
        this.tagName = tagName;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public String toString() {
        return "LoadSessionDataDTO{" +
                "checkBox=" + checkBox +
                ", id='" + id + '\'' +
                ", lectureOne='" + lectureOne + '\'' +
                ", lectureTwo='" + lectureTwo + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
