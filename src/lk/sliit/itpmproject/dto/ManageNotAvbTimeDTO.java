package lk.sliit.itpmproject.dto;

public class ManageNotAvbTimeDTO {
    int id;
    String duration;
    String lecture;
    String groupId;
    String subGroupId;
    String sessionId;

    public ManageNotAvbTimeDTO() {
    }

    public ManageNotAvbTimeDTO(int id, String duration, String lecture, String groupId, String subGroupId, String sessionId) {
        this.id = id;
        this.duration = duration;
        this.lecture = lecture;
        this.groupId = groupId;
        this.subGroupId = subGroupId;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
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
        return "ManageNotAvbTimeDTO{" +
                "id=" + id +
                ", duration='" + duration + '\'' +
                ", lecture='" + lecture + '\'' +
                ", groupId='" + groupId + '\'' +
                ", subGroupId='" + subGroupId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
