package Home;

import javafx.scene.control.Button;

import java.sql.*;

public class Pelatis {
    private long Id;
    private String firstname;
    private String lastname;
    private String email;
    private long phoneNumber;
    private Button Edit;
    private String operation;
    private Timestamp time_stamp;
    private String userid;
    private Long customerid;



    CallableStatement callstatement = null;

    public Pelatis(long Id, String lastname, String firstname, String email, long phoneNumber) {
        this.Id = Id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;}

    public Pelatis(long Id, String lastname, String firstname, String email, long phoneNumber, Button b1) {
        this.Id = Id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.Edit =b1;
        b1.setDisable(true);
        b1.setOnAction(e->{
            b1.setDisable(true);
            
            try{
                Connection con=DbConnection.getConnection();

                String query = "{call updatecustomer(?,?,?,?,?)}";
                callstatement = con.prepareCall(query);
                callstatement.setLong(1,getId());
                callstatement.setString(2,getFirstname());
                callstatement.setString(3,getLastname());
                callstatement.setString(4,getEmail());
                callstatement.setLong(5,getPhoneNumber());
                callstatement.execute();


            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }});}

            public Pelatis() {
    }



    public Pelatis(String operation, Timestamp time_stamp, String userid, long id, String firstname, String lastname, String email, long PhoneNumber) {
        this.operation = operation;
        this.time_stamp=time_stamp;
        this.userid = userid;
        this.Id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = PhoneNumber;

    }


    public long getId() {
        return Id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Button getEdit() {
        return Edit;
    }

    public void setEdit(boolean b) { this.Edit.setDisable(b);}


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

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "Pelatis{" +
                "pelatisId=" + Id +
                ", name='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
