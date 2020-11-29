package Home;

import javafx.scene.control.ChoiceDialog;

import java.sql.CallableStatement;

public class Dwmatio {
    private long roomid;
    private int roomnumber;
    private int floor;
    private int beds;
    private long roomtypeid;


    public Dwmatio(long roomid, int roomnumber, int floor) {
        this.roomid = roomid;
        this.roomnumber = roomnumber;
        this.floor = floor;
        this.beds = beds;
        this.roomtypeid = roomtypeid;
    }

    public Dwmatio(int roomnumber, int floor, long roomtypeid) {
        this.roomnumber = roomnumber;
        this.floor = floor;
        this.roomtypeid = roomtypeid;
    }

    public Dwmatio(int roomnumber, int floor, int beds) {
        this.roomnumber = roomnumber;
        this.floor = floor;
        this.beds = beds;
    }

    public Dwmatio(int beds) {
        this.beds = beds;
    }

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public long getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(long roomtypeid) {
        this.roomtypeid = roomtypeid;
    }
}
