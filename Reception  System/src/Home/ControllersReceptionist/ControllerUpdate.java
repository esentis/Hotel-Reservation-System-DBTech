package Home.ControllersReceptionist;

import Home.DbConnection;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.ComboBoxTableCell.forTableColumn;

public class ControllerUpdate implements Initializable {
    //Menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();
    public Button SignOutButton=new Button();



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






    ObservableList<Pelatis>oblist = FXCollections.observableArrayList();
    ObservableList<Pelatis>oblist2 = FXCollections.observableArrayList();





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
                    costumer.getLong("phoneNumber"),new Button("Save Changes")));


        }
        idCol.setCellValueFactory(new PropertyValueFactory("Id"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        editCol.setCellValueFactory(new PropertyValueFactory("edit"));

        editablecols();
        Mytable.setItems(oblist);

    }

    public void editablecols(){
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        firstnameCol.setOnEditCommit(e-> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstname(e.getNewValue());
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

                });


        lastnameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        lastnameCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLastname(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        emailCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

        });
        phoneCol.setCellFactory(TextFieldTableCell.<Pelatis,Long>forTableColumn(new LongStringConverter()));

        phoneCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhoneNumber(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
            });



        Mytable.setEditable(true);
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
                    pelatis.getString("email"), pelatis.getLong("phonenumber"),new Button("Save Changes")));}

        idCol.setCellValueFactory(new PropertyValueFactory("Id"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
        editCol.setCellValueFactory(new PropertyValueFactory("Edit"));


        Mytable.setItems(oblist2);
        editablecols();
        callstatement.close();

        c.close();



    }




    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    public void logoclick(MouseEvent event) throws IOException{

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Main.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();



    }

    //Menu Handlers
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



    public void onclickhndle(ActionEvent event)throws IOException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Update.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/NeaKrathsh.fxml"));
                break;
            case "updateButton":rootparent=FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Update.fxml"));
                break;
            case "deleteButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Delete.fxml"));
                break;
            case "SEbutton":rootparent = FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/SE.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Main.fxml"));
                break;
            case "SignOutButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/Login/Login.fxml"));
                break;



        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



}



