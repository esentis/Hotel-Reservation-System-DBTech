package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Dwmatio;
import Home.Krathsh;
import Home.Login.LoginController;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DelRoomController implements Initializable {

    //menu
    public Button NewRoomButton = new Button();
    public Button updateRoomButton = new Button();
    public Button deleteRoomButton = new Button();
    public Button deleteCustButton = new Button();
    public Button MainButton = new Button();
    public Button LogsButton = new Button();
    public Button SignOutButton = new Button();
    public Button NewStaff=new Button();
    public Button UpdateStaff=new Button();
    public Button DeleteStaff=new Button();


    DbConnection db = new DbConnection();
    static CallableStatement callstatement = null;

    //Diagrafh Dwmatiou
    public TextField DeleteTextField = new TextField();
    public Button searchButton = new Button();
    public Button deleteButton = new Button();

    public TableView<Dwmatio> table;
    public TableColumn<Dwmatio, Integer> col_RoomNumber;
    public TableColumn<Dwmatio, Integer> col_Floor;
    public TableColumn<Dwmatio, Long> col_RoomType;

    public Label LabelToChange = new Label();

    private int beds;
    ObservableList<Dwmatio> oblist = FXCollections.observableArrayList();
    ObservableList<Dwmatio> oblist2 = FXCollections.observableArrayList();



    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }

    public void filltable() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call getrooms ()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet rooms = callstatement.getResultSet();

        while (rooms.next()) {
            oblist.add(new Dwmatio(rooms.getInt("roomnumber"), rooms.getInt("floor"), rooms.getInt("beds")));

        }

        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_RoomType.setCellValueFactory(new PropertyValueFactory("beds"));

        table.setItems(oblist);
    }


    public void SearchIfRoomExists() throws SQLException {

        table.getItems().clear();
        Connection con = DbConnection.getConnection();
        String query = "{call findroom(?)}";
        callstatement = con.prepareCall(query);

        callstatement.setInt(1, Integer.parseInt(DeleteTextField.getText()));
        callstatement.executeQuery();

        ResultSet room = callstatement.getResultSet();

        while (room.next()) {
            oblist2.add(new Dwmatio(room.getInt("floor"), room.getInt("roomnumber"), room.getInt("beds")));

            col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
            col_Floor.setCellValueFactory(new PropertyValueFactory("floor"));
            col_RoomType.setCellValueFactory(new PropertyValueFactory("beds"));

            table.setItems(oblist2);

            LabelToChange.setText("Το δωμάτιο υπάρχει στην Βάση");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("green"));
        }

        if (room.next()) {

            LabelToChange.setText("Δεν υπάρχει το Δωμάτιο στην Βάση πραγματοποιήστε εισαγωγή νέου δωματίου");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("red"));
            DeleteTextField.setText(DeleteTextField.getText());
        }


        callstatement.close();

        con.close();


    }


    public void deleteRoom() throws SQLException {
        Dwmatio dwmatio = table.getSelectionModel().getSelectedItem();
        int roomnumber = dwmatio.getRoomnumber();

        Connection c = null;
        c = DbConnection.getConnection();
        String query = "{call deleteroomwithnumber(?)}";
        callstatement = c.prepareCall(query);
        callstatement.setInt(1, roomnumber);
        callstatement.execute();
        callstatement.close();
        c.close();
        ObservableList<Dwmatio> row, allRows;
        allRows = table.getItems();
        row = table.getSelectionModel().getSelectedItems();
        row.forEach(allRows::remove);
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

    public void onclickhndle(ActionEvent event) throws IOException,SQLException {
        String evt = ((Button) event.getSource()).getId();

        Parent rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteRoom.fxml"));

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


        switch (evt) {
            case "NewRoomButton": rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));
                break;
            case "updateRoomButton": rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/UpdateRoom.fxml"));
                break;
            case "deleteRoomButton": rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteRoom.fxml"));
                break;
            case "deleteCustButton": rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteCustomer.fxml"));
                break;
            case "MainButton": rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));
                break;
            case "LogsButton": rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/Logs.fxml"));
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


        }
        Scene scene = new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();
            UsernameLabelV.setText("User: "+ LoginController.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}




