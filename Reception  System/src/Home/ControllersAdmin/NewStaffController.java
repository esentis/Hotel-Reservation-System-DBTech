package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewStaffController implements Initializable {

    //menu
    public Button NewRoomButton=new Button();
    public Button updateRoomButton=new Button();
    public Button deleteRoomButton=new Button();
    public Button deleteCustButton=new Button();
    public Button MainButton=new Button();
    public Button LogsButton =new Button();
    public Button SignOutButton =new Button();
    public Button NewStaff=new Button();
    public Button UpdateStaff=new Button();
    public Button DeleteStaff=new Button();

    //Dhmiourgia Neou Ypallhlou
    public TextField FirstnameTxt = new TextField();
    public TextField LastnameTxt = new TextField();
    public TextField EmailTxt = new TextField();
    public TextField PhoneTxt = new TextField();
    public TextField UsernameTxt = new TextField();
    public PasswordField Passwordtxt = new PasswordField();

    public Label LabelToChange1 = new Label();
    public Label LabelToChange2 = new Label();

    public Button SaveButton=new Button();

    private long Roleid;

    DbConnection db = new DbConnection();
    CallableStatement callstatement = null;


    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }

    public void SearchIfEmailExists() throws SQLException {
        Connection con = DbConnection.getConnection();
        String query = "{call checkstaffbyname(?,?)}";
        callstatement = con.prepareCall(query);
        callstatement.setString(1, FirstnameTxt.getText());
        callstatement.setString(2, LastnameTxt.getText());
        callstatement.executeQuery();

        ResultSet room = callstatement.getResultSet();
        if (room.next()) {

            LabelToChange1.setText("Υπάρχει ήδη καταχώρηση με αυτό το email παρακαλώ πληκτρολογήστε ξανά.");
            LabelToChange1.setVisible(true);
            LabelToChange1.setTextFill(Paint.valueOf("red"));
            EmailTxt.setText(EmailTxt.getText());
        }
        callstatement.close();

        con.close();
    }
    public void addStaff() throws SQLException {
        Roleid=1;

        Connection con = DbConnection.getConnection();
        String query = "{call addstaff(?,?,?,?,?,?,?)}";
        callstatement = con.prepareCall(query);
        callstatement.setString(3, LastnameTxt.getText());
        callstatement.setString(2, FirstnameTxt.getText());
        callstatement.setString(1, EmailTxt.getText());
        callstatement.setString(4, Passwordtxt.getText());


        long Phone=Long.parseLong(PhoneTxt.getText());
        callstatement.setLong(5,Phone);
        callstatement.setLong(6, Roleid);
        callstatement.setString(7, UsernameTxt.getText());

        callstatement.executeQuery();

        LabelToChange2.setText("Ο υπάλληλος δημιουργήθηκε επιτυχώς στην βάση.");
        LabelToChange2.setVisible(true);
        LabelToChange2.setTextFill(Paint.valueOf("green"));

        LabelToChange1.setVisible(false);
    }

    public void logoclick(MouseEvent event) throws IOException{

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();



    }

    public  void mouseEnter1(MouseEvent event){
        String evt=((Button) event.getSource()).getId();
        switch (evt){
            case "NewRoomButton":NewRoomButton.setStyle("-fx-background-color: #2771d9;");
                break;
            case "updateRoomButton":updateRoomButton.setStyle("-fx-background-color: #2771d9;");
                break;
            case "deleteRoomButton": deleteRoomButton.setStyle("-fx-background-color: #2771d9;");
                break;
            case "deleteCustButton":deleteCustButton.setStyle("-fx-background-color: #2771d9;");
                break;
            case "LogsButton":LogsButton.setStyle("-fx-background-color: #2771d9;");
                break;
            case "SignOutButton":SignOutButton.setStyle("-fx-background-color: #2771d9;");
                break;
            case "NewStaff":NewStaff.setStyle("-fx-background-color: #2771d9;");
                break;
            case "UpdateStaff":UpdateStaff.setStyle("-fx-background-color: #2771d9;");
                break;
            case "DeleteStaff":DeleteStaff.setStyle("-fx-background-color: #2771d9;");
                break;

        }


    }

    public void mouseExit1(MouseEvent event){
        String evt=((Button) event.getSource()).getId();
        switch (evt){
            case "NewRoomButton":NewRoomButton.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "updateRoomButton":updateRoomButton.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "deleteRoomButton": deleteRoomButton.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "deleteCustButton":deleteCustButton.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "LogsButton":LogsButton.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "SignOutButton":SignOutButton.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "NewStaff":NewStaff.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "UpdateStaff":UpdateStaff.setStyle("-fx-background-color:  #1855ab;");
                break;
            case "DeleteStaff":DeleteStaff.setStyle("-fx-background-color:  #1855ab;");
                break;


        }

    }

    public void onclickhndle(ActionEvent event)throws IOException,SQLException{
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NewRoomButton":rootparent=FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));
                break;
            case "updateRoomButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/UpdateRoom.fxml"));
                break;
            case "deleteRoomButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteRoom.fxml"));
                break;
            case "deleteCustButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/DeleteCustomer.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/MainAdmin.fxml"));
                break;
            case "LogsButton":rootparent=FXMLLoader.load(getClass().getResource("/Home/Adminfxml/Logs.fxml"));
                break;
            case "SignOutButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/Login/Login.fxml"));
                SignOut();
                break;
            case "NewStaff":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/NewStaff.fxml"));
                break;
            case "UpdateStaff":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/UpdateStaff.fxml"));
                break;
            case "DeleteStaff":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/DeleteStaff.fxml"));
                break;






        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
            UsernameLabelV.setText("User: "+ LoginController.getUsername());

    }



}




