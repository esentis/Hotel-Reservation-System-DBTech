package Home.LogsControllers;

import Home.DbConnection;
import Home.Dwmatio;
import Home.Login.LoginController;
import Home.Pelatis;
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

public class RoomsLogsController implements Initializable {

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

    public Label UsernameLabelV=new Label();

    ObservableList<Dwmatio> oblist = FXCollections.observableArrayList();
    CallableStatement callstatement = null;


    //Rooms Logs UI
    public TableView<Dwmatio> table;
    public TableColumn<Pelatis, String> col_operation;
    public TableColumn<Pelatis, Timestamp> col_timestamp;
    public TableColumn<Pelatis, String> col_user;
    public TableColumn<Dwmatio, Long> col_roomid;
    public TableColumn<Dwmatio, Integer> col_floor;
    public TableColumn<Dwmatio, Integer> col_roomnumber;
    public TableColumn<Dwmatio, Long> col_roomtype;

    public void filltable() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call roomlogs()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet logrooms = callstatement.getResultSet();

        while (logrooms.next()){
            oblist.add(new Dwmatio(logrooms.getString("operation"),logrooms.getTimestamp("time_stamp"),logrooms.getString("userid"),
                    logrooms.getLong("roomid"),logrooms.getInt("floornumber"),logrooms.getInt("roomnumber"),logrooms.getLong("roomtypeid")
            ));
        }

        col_operation.setCellValueFactory(new PropertyValueFactory("operation"));
        col_timestamp.setCellValueFactory(new PropertyValueFactory("time_stamp"));
        col_user.setCellValueFactory(new PropertyValueFactory("userid"));
        col_roomid.setCellValueFactory(new PropertyValueFactory("roomid"));
        col_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_roomnumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_roomtype.setCellValueFactory(new PropertyValueFactory("roomtypeid"));



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



    public void onclickhndle(ActionEvent event)throws IOException, SQLException {
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




