package Home;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.control.Button;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Dwmatio {
    private long roomid;
    private int roomnumber;
    private int floor;
    private int beds;
    private long roomtypeid;
    Button Edit;

    CallableStatement callstatement = null;



    public Dwmatio(long roomid, int roomnumber, int floor, long roomtypeid, Button b1) {
        this.roomid = roomid;
        this.roomnumber = roomnumber;
        this.floor = floor;
        this.beds = beds;
        this.roomtypeid = roomtypeid;
        this.Edit =b1;
        b1.setDisable(true);
        b1.setOnAction(e->{
            b1.setDisable(true);

            try{
                Connection con=DbConnection.getConnection();

                String query = "{call updateroom (?,?,?,?)}";
                callstatement = con.prepareCall(query);
                callstatement.setLong(1,getRoomid());
                callstatement.setInt(2,getFloor());
                callstatement.setInt(3,getRoomnumber());
                callstatement.setLong(4,getRoomtypeid());
                callstatement.execute();


            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }});}





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

    public void setEdit(Boolean b) {
        this.Edit.setDisable(b);
    }

    public Button getEdit() {
        return Edit;
    }
}
