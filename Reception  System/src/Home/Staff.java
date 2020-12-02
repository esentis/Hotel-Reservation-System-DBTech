package Home;

import javafx.scene.control.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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

    CallableStatement callstatement = null;


    public Staff(){}
    public Staff(String userName){this.UserName=userName;}

    

    public Staff(long id, String lastName, String firstName, String userName,String email, long phoneNumber, long roleId,Button b1) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        this.email = email;
        this.password = password;
        PhoneNumber = phoneNumber;
        RoleId = roleId;
        this.Edit=b1;
        b1.setDisable(true);
        b1.setOnAction(e->{
            b1.setDisable(true);

            try{
                Connection con=DbConnection.getConnection();

                String query = "{call updatestaff(?,?,?,?,?,?,?)}";
                callstatement = con.prepareCall(query);
                callstatement.setLong(1,getId());
                callstatement.setString(2,getFirstName());
                callstatement.setString(3,getLastName());
                callstatement.setString(4,getUserName());
                callstatement.setString(5,getEmail());
                callstatement.setLong(6,getPhoneNumber());
                callstatement.setLong(7,getRoleId());

                callstatement.execute();


            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }});


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

    public long getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public long getRoleId() {
        return RoleId;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setRoleId(long roleId) {
        RoleId = roleId;
    }

    public Button getEdit() {
        return Edit;
    }
    public void setEdit(Boolean b) {
        this.Edit.setDisable(b);
    }

}
