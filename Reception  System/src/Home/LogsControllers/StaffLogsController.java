package Home.LogsControllers;

import Home.DbConnection;
import Home.Login.LoginController;
import Home.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StaffLogsController implements Initializable {

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
    public Button ChangePassB=new Button();


    CallableStatement callstatement = null;
    ObservableList<Staff> oblist = FXCollections.observableArrayList();

    //Staff Logs UI
    public TableView<Staff> table;
    public TableColumn<Staff, String> col_operation;
    public TableColumn<Staff, Timestamp> col_timestamp;
    public TableColumn<Staff, String> col_user;
    public TableColumn<Staff, Long> col_staffid;
    public TableColumn<Staff, String> col_firstname;
    public TableColumn<Staff, String> col_lastname;
    public TableColumn<Staff, String> col_username;
    public TableColumn<Staff, String> col_Email;
    public TableColumn<Staff, String> col_password;
    public TableColumn<Staff, Long> col_phone;
    public TableColumn<Staff, Long> col_roleid;


    public Label UsernameLabelV=new Label();

    public void filltable() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call stafflogs()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet stafflogs = callstatement.getResultSet();

        while (stafflogs.next()){
            oblist.add(new Staff(stafflogs.getString("operation"),stafflogs.getTimestamp("time_stamp"),stafflogs.getString("userid"),
                    stafflogs.getLong("staffid"),stafflogs.getString("FirstName"),stafflogs.getString("LastName"),stafflogs.getString("UserName"),
                    stafflogs.getString("email"),
                    stafflogs.getString("password"),stafflogs.getLong("PhoneNumber"),stafflogs.getLong("RoleId")
            ));
        }

        col_operation.setCellValueFactory(new PropertyValueFactory("operation"));
        col_timestamp.setCellValueFactory(new PropertyValueFactory("time_stamp"));
        col_user.setCellValueFactory(new PropertyValueFactory("userid"));
        col_staffid.setCellValueFactory(new PropertyValueFactory("staffid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory("FirstName"));
        col_lastname.setCellValueFactory(new PropertyValueFactory("LastName"));
        col_username.setCellValueFactory(new PropertyValueFactory("UserName"));
        col_Email.setCellValueFactory(new PropertyValueFactory("email"));
        //col_password.setCellValueFactory(new PropertyValueFactory("password"));
        col_phone.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
        col_roleid.setCellValueFactory(new PropertyValueFactory("RoleId"));



        table.setItems(oblist);
        callstatement.close();

        c.close();

    }












    public void SignOut() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

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
            case "ChangePassB":ChangePassB.setStyle("-fx-background-color: #2771d9;");
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
            case "ChangePassB":ChangePassB.setStyle("-fx-background-color:  #1855ab;");
                break;


        }

    }



    public void onclickhndle(ActionEvent event)throws IOException,SQLException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/ChangePassword.fxml"));

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
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/ChangePassword.fxml"));
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
            case "ChangePassB":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/ChangePassword.fxml"));
                break;







        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            UsernameLabelV.setText("User: "+ LoginController.getUsername());
            filltable();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}




