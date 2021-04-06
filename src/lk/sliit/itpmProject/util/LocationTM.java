package lk.sliit.itpmProject.util;

public class LocationTM {
    private int id;
    private String buildingName;
    private String roomName;
    private boolean lectureHall;
    private boolean laboratory;
    private String capacity;

    public LocationTM(int id, String buildingName, String roomName, boolean lectureHall, boolean laboratory, String capacity) {
        this.id = id;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.lectureHall = lectureHall;
        this.laboratory = laboratory;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(boolean lectureHall) {
        this.lectureHall = lectureHall;
    }

    public boolean isLaboratory() {
        return laboratory;
    }

    public void setLaboratory(boolean laboratory) {
        this.laboratory = laboratory;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public LocationTM() {
    }

    @Override
    public String toString() {
        return "LocationTM{" +
                "id=" + id +
                ", buildingName='" + buildingName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", lectureHall=" + lectureHall +
                ", laboratory=" + laboratory +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
