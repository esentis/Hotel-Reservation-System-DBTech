package Home;

import javafx.scene.control.Button;

import javax.swing.text.Style;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Pelatis {
    private long Id;
    private String firstname;
    private String lastname;
    private String email;
    private long phoneNumber;
    private Button Edit;


    CallableStatement callstatement = null;

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
