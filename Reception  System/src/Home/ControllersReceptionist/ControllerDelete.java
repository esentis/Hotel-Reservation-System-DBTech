package Home.ControllersReceptionist;

import Home.DbConnection;
import Home.Krathsh;
import Home.Pelatis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;

public class ControllerDelete<krathsh> implements Initializable {
    //menu
    public Button NKbutton = new Button();
    public Button updateButton = new Button();
    public Button deleteButton = new Button();
    public Button SEbutton = new Button();
    public Button MainButton = new Button();

    //delete scene
    public Button DeleteButton = new Button();
    public Button SearchButton = new Button();

    public TextField SearchFistNameTextField = new TextField();
    public TextField SearchLastNameTextField = new TextField();

    public TableView<Krathsh> table;
    public TableColumn<Krathsh, Long> col_ResrvID;
    public TableColumn<Krathsh, Integer> col_RoomID;
    public TableColumn<Pelatis, String> col_Lastname;
    public TableColumn<Pelatis, String> col_Name;
    public TableColumn<Krathsh, Date> col_From;
    public TableColumn<Krathsh, Date> col_To;

    public Label label = new Label();

    DbConnection db = new DbConnection();

    static CallableStatement callstatement = null;

    ObservableList<Krathsh> oblist = FXCollections.observableArrayList();
    ObservableList<Krathsh> oblist2 = FXCollections.observableArrayList();
    private String firstname;
    private String lastname;

    public void filltable() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call getreservations ()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet krathsh = callstatement.getResultSet();

        while (krathsh.next()){
            oblist.add(new Krathsh(krathsh.getLong("Id"), krathsh.getInt("roomnumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getDate("checkindate"), krathsh.getDate("checkoutdate")));
            }

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomID.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));

        table.setItems(oblist);

    }




    public void deleteReservation() throws SQLException {
        Krathsh krathsh = table.getSelectionModel().getSelectedItem();
        long id = krathsh.getId();

        Connection c = null;
        c = DbConnection.getConnection();
        String query = "{call deletereservation (?)}";
        callstatement = c.prepareCall(query);
        callstatement.setLong(1, id);
        callstatement.execute();
        callstatement.close();
        c.close();
        ObservableList<Krathsh> row, allRows;
        allRows = table.getItems();
        row = table.getSelectionModel().getSelectedItems();
        row.forEach(allRows::remove);
    }



    public void searchSpecificReservation() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call searchspecificreservation(?,?)}";
        callstatement = c.prepareCall(query);

        callstatement.setString(1,SearchLastNameTextField.getText());
        callstatement.setString(2,SearchFistNameTextField.getText());

        callstatement.executeQuery();

        ResultSet krathsh = callstatement.getResultSet();

        while (krathsh.next()){
            oblist2.add(new Krathsh(krathsh.getLong("reservationid"), krathsh.getInt("roomnumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getDate("checkindate"), krathsh.getDate("checkoutdate")));
        }

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomID.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));

        table.setItems(oblist2);
        callstatement.close();

        c.close();


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

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Delete.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/NeaKrathsh.fxml"));

                break;
            case "updateButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Update.fxml"));
                break;
            case "deleteButton": rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Delete.fxml"));
                break;
            case "SEbutton":rootparent = FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/SE.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Main.fxml"));
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




