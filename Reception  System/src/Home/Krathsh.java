package Home;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.*;
import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class Krathsh {

    private long Id;
    private int roomnumber;
    private String lastname;
    private String firstname;
    private Date checkindate;
    private Date checkoutdate;

    private String operation;
    private Timestamp time_stamp;
    private String userid;
    private long reservationid;
    private long customerid;
    private  long roomid;

    public Krathsh(long id, int roomnumber, String lastname, String firstname, Date checkindate, Date checkoutdate) {
        this.Id = id;
        this.roomnumber = roomnumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
    }
    static CallableStatement callstatement = null;
    public Krathsh() {
    }

    public Krathsh(String operation, Timestamp time_stamp, String userid, long reservationid,long customerid, long roomid, java.sql.Date checkindate, java.sql.Date checkoutdate) {
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

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
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

    public Date getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(Date cheeckindate) {
        this.checkindate = checkindate;
    }

    public Date getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
    }





}

