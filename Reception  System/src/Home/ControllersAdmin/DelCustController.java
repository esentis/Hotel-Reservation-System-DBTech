package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Krathsh;
import Home.Pelatis;
import com.sun.org.omg.CORBA.Initializer;
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

public class DelCustController implements Initializable {

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

    CallableStatement callstatement = null;


    public TableView<Pelatis> Mytable;
    public TableColumn<Pelatis, Long> idCol;
    public TableColumn<Pelatis, String> lastnameCol;
    public TableColumn<Pelatis, String> firstnameCol;
    public TableColumn<Pelatis, String> emailCol;
    public TableColumn<Pelatis, Long> phoneCol;
    public TableColumn<Pelatis, Button> editCol;
    public TextField LastnameTxt=new TextField();
    public TextField FirstnameTxt=new TextField();
    public Label LabelToChange=new Label();







    ObservableList<Pelatis> oblist = FXCollections.observableArrayList();
    ObservableList<Pelatis>oblist2 = FXCollections.observableArrayList();

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





    DbConnection db = new DbConnection();

    public void filltable()throws SQLException {
        Mytable.getItems().clear();
        Connection con=db.getConnection();
        String query = "{call getCustomers()}";
        callstatement = con.prepareCall(query);
        callstatement.execute();
        ResultSet costumer = callstatement.getResultSet();
        while (costumer.next()){
            oblist.add(new Pelatis(costumer.getLong("Id"),costumer.getString("lastName"),costumer.getString("firstName"),
                    costumer.getString("email"),
                    costumer.getLong("phoneNumber")));


        }
        idCol.setCellValueFactory(new PropertyValueFactory("Id"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phoneNumber"));



        Mytable.setItems(oblist);

    }
    public void SearchCustomer() throws SQLException{
        Mytable.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call searchcustomer(?,?)}";
        callstatement = c.prepareCall(query);

        callstatement.setString(1,LastnameTxt.getText());
        callstatement.setString(2,FirstnameTxt.getText());

        callstatement.executeQuery();

        ResultSet pelatis = callstatement.getResultSet();

        while (pelatis.next()){
            oblist2.add(new Pelatis(pelatis.getLong("id"), pelatis.getString("lastName"), pelatis.getString("firstName"),
                    pelatis.getString("email"), pelatis.getLong("phonenumber")));}

        idCol.setCellValueFactory(new PropertyValueFactory("Id"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));



        Mytable.setItems(oblist2);
        callstatement.close();

        c.close();



    }
    public void deleteCustomer() throws SQLException {
        Pelatis pelatis = Mytable.getSelectionModel().getSelectedItem();
        long id = pelatis.getId();
        int m=checkIfCustIsInRes();
        if(m>0){
        Connection c = null;
        c = DbConnection.getConnection();
        String query = "{call deletecustomer(?)}";
        callstatement = c.prepareCall(query);
        callstatement.setLong(1, id);
        callstatement.execute();
        callstatement.close();
        c.close();
        ObservableList<Pelatis> row, allRows;
        allRows = Mytable.getItems();
        row = Mytable.getSelectionModel().getSelectedItems();
        row.forEach(allRows::remove);}
    }

    public int  checkIfCustIsInRes() throws SQLException{
        Pelatis pelatis = Mytable.getSelectionModel().getSelectedItem();
        long id = pelatis.getId();
        Connection con=DbConnection.getConnection();
        String query="{call searchspecificreservationwithid (?)}";
        callstatement= con.prepareCall(query);
        callstatement.setLong(1,id);
        callstatement.execute();
        ResultSet rs=callstatement.getResultSet();
        if(rs.next()){
            LabelToChange.setText("Ο πέλατης έχει κάνει κράτηση δεν επιτρέπεται διαγραφή");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("red"));
            return 0;
        }
        LabelToChange.setText("Η διαγραφή έγινε επιτυχώς");
        LabelToChange.setVisible(true);
        LabelToChange.setTextFill(Paint.valueOf("green"));
        return 1;

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

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteCustomer.fxml"));

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
            LabelToChange.setVisible(false);
            getLoggedUser();
            UsernameLabelV.setText("User: "+UserName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }}




