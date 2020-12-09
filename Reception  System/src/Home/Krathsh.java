package Home;

import javafx.scene.control.Button;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class Krathsh {

    private long Id;
    private int roomNumber;
    private String lastname;
    private String firstname;
    private String checkindate;
    private String checkoutdate;
    BigDecimal totalCost;
    Button Edit;
    long roomId;
    long CustomerId;

    private String operation;
    private Timestamp time_stamp;
    private String userid;
    private long reservationid;
    private long customerid;
    private  long roomid;



    public Krathsh(long id, int roomnumber, String lastname, String firstname, String checkindate, String checkoutdate, BigDecimal totalCost) {
        this.Id = id;
        this.roomNumber = roomnumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.totalCost=totalCost;
    }
    static CallableStatement callstatement = null;
    public Krathsh(long id, int roomnumber, String lastname, String firstname, String checkindate, String checkoutdate, BigDecimal totalCost,Button Edit) {
        this.Id = id;
        this.roomNumber = roomnumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.totalCost=totalCost;
        this.Edit=Edit;
        Edit.setDisable(true);
        Edit.setOnAction(e->{
            Edit.setDisable(true);
            try{
                Connection con=DbConnection.getConnection();
                String query="{call updatereservation(?,?,?,?,?,?) }";
                callstatement=con.prepareCall(query);
                callstatement.setLong(1,getId());
                callstatement.setDate(2,java.sql.Date.valueOf(getCheckindate()));
                callstatement.setDate(3,java.sql.Date.valueOf(getCheckoutdate()));
                callstatement.setLong(4,getRoomId());
                callstatement.setLong(5,getCustomerId());
                callstatement.setBigDecimal(6,getTotalCost());
                callstatement.execute();


            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
    public Krathsh() {
    }

    public Krathsh(String operation, Timestamp time_stamp, String userid, long reservationid,long customerid, long roomid, String checkindate, String checkoutdate) {
        this.operation=operation;
        this.time_stamp=time_stamp;
        this.userid = userid;
        this.reservationid = reservationid;
        this.customerid=customerid;
        this.roomid=roomid;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
    }



    public long getReservationid() {
        return reservationid;
    }

    public void setReservationid(long reservationid) {
        this.reservationid = reservationid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Button getEdit() {
        return Edit;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setEdit(Boolean b) {
       this.Edit.setDisable(b);
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(long customerId) {
        CustomerId = customerId;
    }
}

