package Home.ControllersReceptionist;

import Home.DbConnection;
import Home.Krathsh;
import Home.Login.LoginController;
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
import javafx.scene.input.MouseEvent;
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
    public Button SignOutButton=new Button();

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



    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }

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
                    krathsh.getString("firstname"), krathsh.getTimestamp("checkindate"), krathsh.getDate("checkoutdate")));
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

    public void logoclick(MouseEvent event) throws IOException{

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Main.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();



    }



    public  void mouseEnter1(MouseEvent event){
        String evt=((Button) event.getSource()).getId();
        switch (evt){
            case "NKbutton":NKbutton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "updateButton":updateButton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "deleteButton":deleteButton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "SEbutton":SEbutton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "MainButton":MainButton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "SignOutButton":SignOutButton.setStyle("-fx-background-color: #6a25cc;");
                break;

        }


    }

    public void mouseExit1(MouseEvent event){
        String evt=((Button) event.getSource()).getId();
        switch (evt){
            case "NKbutton":NKbutton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "updateButton":updateButton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "deleteButton":deleteButton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "SEbutton":SEbutton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "MainButton":MainButton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "SignOutButton":SignOutButton.setStyle("-fx-background-color:  #3F2B63;");
                break;


        }

    }



    public void onclickhndle(ActionEvent event)throws IOException,SQLException {
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
            case "SignOutButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/Login/Login.fxml"));
                SignOut();
                break;


        }   Scene scene=new Scene(rootparent);
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




