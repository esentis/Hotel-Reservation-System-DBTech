package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Dwmatio;
import Home.Pelatis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdRoomController implements Initializable {

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
    
    
    //Edit Table
   public TextField TextFieldSearch=new TextField();
    public TableView<Dwmatio> MyTable;
    public TableColumn<Dwmatio,Long> IdCol;
    public TableColumn<Dwmatio,Integer> RoomNumberCol;
    public TableColumn<Dwmatio,Integer> FloorCol;
    public TableColumn<Dwmatio,Long> RoomTypeCol;
    public TableColumn<Dwmatio,Button> EditCol;

    ObservableList<Dwmatio> oblist = FXCollections.observableArrayList();
    ObservableList<Dwmatio> oblist2 = FXCollections.observableArrayList();

    CallableStatement callableStatement=null;
    CallableStatement callstatement = null;

    String UserName;

    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff (?)}";
        callstatement=con.prepareCall(query);
        callstatement.setString(1,UserName);
        callstatement.execute();
        callstatement.close();

    }






    public void filltable() throws SQLException {
        MyTable.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call getrooms()}";
        callableStatement = c.prepareCall(query);
        callableStatement.executeQuery();
        ResultSet rooms = callableStatement.getResultSet();

        while (rooms.next()) {
            oblist.add(new Dwmatio(rooms.getLong("roomid"),rooms.getInt("roomnumber"),rooms.getInt("floor"),rooms.getLong("roomtypeid"),
                    new Button("Save Changes")));

        }

        IdCol.setCellValueFactory(new PropertyValueFactory("roomid"));
        RoomNumberCol.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        FloorCol.setCellValueFactory(new PropertyValueFactory("floor"));
        RoomTypeCol.setCellValueFactory(new PropertyValueFactory("roomtypeid"));
        EditCol.setCellValueFactory(new PropertyValueFactory("edit"));
        editablecols();
        MyTable.setItems(oblist);
    }

    public void editablecols(){
        RoomNumberCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        RoomNumberCol.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomnumber(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

        });


        FloorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        FloorCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFloor(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });
        RoomTypeCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));

        RoomTypeCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomtypeid(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

        });



        MyTable.setEditable(true);

    }

    public void SearchRoom()throws SQLException{
        MyTable.getItems().clear();
        Connection con = DbConnection.getConnection();
        String query = "{call findroomforupdate(?)}";
        callableStatement = con.prepareCall(query);
        callableStatement.setInt(1, Integer.parseInt(TextFieldSearch.getText()));
        callableStatement.executeQuery();
        ResultSet rooms = callableStatement.getResultSet();
        while (rooms.next()){
            oblist2.add(new Dwmatio(rooms.getLong("roomid"),rooms.getInt("roomnumber"),rooms.getInt("floornumber"),rooms.getLong("roomtypeid"),
                    new Button("Save Changes")));

        }

        IdCol.setCellValueFactory(new PropertyValueFactory("roomid"));
        RoomNumberCol.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        FloorCol.setCellValueFactory(new PropertyValueFactory("floornumber"));
        RoomTypeCol.setCellValueFactory(new PropertyValueFactory("roomtypeid"));
        EditCol.setCellValueFactory(new PropertyValueFactory("edit"));
        editablecols();
        MyTable.setItems(oblist2);

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

    public void onclickhndle(ActionEvent event)throws IOException,SQLException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/UpdateRoom.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NewRoomButton":rootparent=FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));

                break;
            case "updateRoomButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/UpdateRoom.fxml"));
                break;
            case "deleteRoomButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteRoom.fxml"));
                break;
            case "deleteCustButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteCustomer.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));
                break;
            case "LogsButton":rootparent=FXMLLoader.load(getClass().getResource("/Home/AdminFXML/Logs.fxml"));
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
    public void getLoggedUser()throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call getLoggedUser()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        ResultSet rs=callstatement.getResultSet();
        while (rs.next()){
            UserName=rs.getString("UserName");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();
            getLoggedUser();
            UsernameLabelV.setText("User: "+UserName);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}




