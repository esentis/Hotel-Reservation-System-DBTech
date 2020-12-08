package Home;

import javafx.scene.control.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Staff {

    private long Id;
    private String FirstName;
    private String LastName;
    private String UserName;
    private String email;
    private String password;
    private long PhoneNumber;
    private long RoleId;
    private Button Edit;


    private String operation;
    private Timestamp time_stamp;
    private String userid;
    private int status;
    private long staffid;

    CallableStatement callstatement = null;


    public Staff() {
    }

    public Staff(String userName) {
        this.UserName = userName;
    }


    public Staff(long id, String lastName, String firstName, String userName, String email, long phoneNumber, long roleId, Button b1) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        this.email = email;
        this.password = password;
        PhoneNumber = phoneNumber;
        RoleId = roleId;
        this.Edit = b1;
        b1.setDisable(true);
        b1.setOnAction(e -> {
            b1.setDisable(true);

            try {
                Connection con = DbConnection.getConnection();

                String query = "{call updatestaff(?,?,?,?,?,?,?)}";
                callstatement = con.prepareCall(query);
                callstatement.setLong(1, getId());
                callstatement.setString(2, getFirstName());
                callstatement.setString(3, getLastName());
                callstatement.setString(4, getUserName());
                callstatement.setString(5, getEmail());
                callstatement.setLong(6, getPhoneNumber());
                callstatement.setLong(7, getRoleId());

                callstatement.execute();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


    }

    //Dhmiourgia domhth gia thn deletestaff
    public Staff(long id, String lastName, String firstName, String userName, String password, String email, long phoneNumber, long roleId) {

        Id = id;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        this.email = email;
        this.password = password;
        PhoneNumber = phoneNumber;
        RoleId = roleId;
    }


    public Staff(String operation, Timestamp time_stamp, String userid, long staffid, String firstname, String lastname, String username, String email, String password, long phonenumber, long roleId) {
        this.operation = operation;
        this.time_stamp = time_stamp;
        this.userid = userid;
        this.staffid = staffid;
        FirstName = firstname;
        LastName = lastname;
        UserName = username;
        this.email = email;
        this.password = password;
        this.PhoneNumber = phonenumber;
        RoleId = roleId;
    }


    public Button getEdit() {
        return Edit;
    }

    public void setEdit(Boolean b) {
        this.Edit.setDisable(b);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public long getRoleId() {
        return RoleId;
    }

    public void setRoleId(long roleId) {
        RoleId = roleId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStaffid() {
        return staffid;
    }

    public void setStaffid(long staffid) {
        this.staffid = staffid;
    }
}