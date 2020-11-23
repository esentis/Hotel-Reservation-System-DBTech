package Home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.util.converter.LongStringConverter;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerDelete implements Initializable {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();

    //delete scene
    public Button DeleteButton = new Button();
    public Button SearchButton = new Button();

    public TextField SearchFistNameTextField = new TextField();
    public TextField SearchLastNameTextField = new TextField();

    public TableView<Krathsh> table;
    public TableColumn<Krathsh, Long> col_ResrvID;
    public TableColumn<Pelatis, Integer> col_RoomID;
    public TableColumn<Pelatis, String> col_Lastname;
    public TableColumn<Pelatis, String> col_Name;
    public TableColumn<Krathsh, Date> col_From;
    public TableColumn<Krathsh, Date> col_To;

    DbConnection db = new DbConnection();

    static CallableStatement callstatement = null;

    ObservableList<Krathsh> oblist= FXCollections.observableArrayList();

    public void filltable() throws SQLException {
        Connection c = null;
        c = DriverManager.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
                "it123973", "ad1e35c1368e4d298abae3a73f37a424");
        String query = "{call getreservations ()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet krathsh = callstatement.getResultSet();

        while (krathsh.next())
            oblist.add(new Krathsh(krathsh.getLong("Id"), krathsh.getInt("roomnumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getDate("checkindate"), krathsh.getDate("checkoutdate")));

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomID.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));

        table.setItems(oblist);

    }


    public void searchSpecificReservation(String lastName, String firstName) throws SQLException {
        Connection c = null;

        c = DriverManager.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
                "it123973", "ad1e35c1368e4d298abae3a73f37a424");
        String query = "{call searchspecificreservation(?,?)}";
        callstatement = c.prepareCall(query);
        callstatement.setString(1,lastName);
        callstatement.setString(2,firstName);
        callstatement.executeQuery();
        ResultSet krathsh = callstatement.getResultSet();
        while (krathsh.next())
            oblist.add(new Krathsh(krathsh.getLong("Id"), krathsh.getInt("roomnumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getDate("checkindate"), krathsh.getDate("checkoutdate")));

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomID.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));

        table.setItems(oblist);


    }

    public void deleteReservations() throws SQLException {

        Connection c = null;
        c = DriverManager.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
                "it123973", "ad1e35c1368e4d298abae3a73f37a424");
        String query = "{call deletereservation()}";
        callstatement = c.prepareCall(query);
        //callstatement.setInt(getResrvId());
        callstatement.executeQuery();

        callstatement.close();
        c.close();
        System.out.println("Called from deleteReservations");
    }




    public  void mouseEnter1(){
        MainButton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit1(){
        MainButton.setStyle("-fx-background-color:  #3F2B63;");

    }

    public void mouseEnter(){

        NKbutton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit(){
        NKbutton.setStyle("-fx-background-color:  #3F2B63;");}

    public void mouseEnter2(){
        updateButton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit2(){
        updateButton.setStyle("-fx-background-color:  #3F2B63;");}

    public void mouseEnter3(){
        deleteButton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit3(){
        deleteButton.setStyle("-fx-background-color:  #3F2B63;");}

    public void mouseEnter4(){
        SEbutton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit4(){
        SEbutton.setStyle("-fx-background-color:  #3F2B63;");}




    public void onclickhndle(ActionEvent event)throws IOException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("fxml/Delete.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("fxml/NeaKrathsh.fxml"));

                break;
            case "updateButton":rootparent= FXMLLoader.load(getClass().getResource("fxml/Update.fxml"));
                break;
            case "deleteButton": rootparent= FXMLLoader.load(getClass().getResource("fxml/Delete.fxml"));
                break;
            case "SEbutton":rootparent = FXMLLoader.load(getClass().getResource("fxml/SE.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
                break;


        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}




