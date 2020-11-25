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

