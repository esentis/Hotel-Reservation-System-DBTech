package Home;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.LongStringConverter;

import javafx.scene.paint.Color;
import java.lang.Object;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;

import static javafx.scene.control.cell.ComboBoxTableCell.forTableColumn;

public class ControllerUpdate implements Initializable {
    //Menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();

    public TableView<Pelatis> Mytable;

    CallableStatement callstatement = null;

    public TableColumn<Pelatis, Long> idCol;
    public TableColumn<Pelatis, String> firstnameCol;
    public TableColumn<Pelatis, String> LastnameCol;
    public TableColumn<Pelatis, String> emailCol;
    public TableColumn<Pelatis, Long> phoneCol;
    public TableColumn<Pelatis, Button> editCol;
    public TextField FirstnameTxt=new TextField();
    public TextField LastnameTxt=new TextField();

    Button b1;
    public Button testbutton=new Button();





    public Button SearchB=new Button();

    ObservableList<Pelatis>oblist=FXCollections.observableArrayList();
    public Pelatis pelatis=new Pelatis();

    public void unlockb(){
        b1.setDisable(false);
        testbutton.setDisable(false);

    }



    DbConnection db = new DbConnection();

    public void filltable()throws SQLException {
        Connection con=db.getConnection();
        String query = "{call getCustomers()}";
        callstatement = con.prepareCall(query);
        callstatement.execute();
        ResultSet costumer = callstatement.getResultSet();
        while (costumer.next()){
            oblist.add(new Pelatis(costumer.getLong("Id"), costumer.getString("firstname"),
                    costumer.getString("lastname"), costumer.getString("email"),
                    costumer.getLong("PhoneNumber"),b1=new Button("Save Changes")));


        }
        idCol.setCellValueFactory(new PropertyValueFactory("Id"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        LastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
        editCol.setCellValueFactory(new PropertyValueFactory("Edit"));

        editablecols();
        Mytable.setItems(oblist);

    }

    public void editablecols(){
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        firstnameCol.setOnEditCommit(e-> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstname(e.getNewValue());
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

                });


        LastnameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        LastnameCol.setOnEditCommit(e->{
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

    public void SearchCustomer(){
        oblist.addListener(new ListChangeListener<Pelatis>() {
            @Override
            public void onChanged(Change<? extends Pelatis> c) {
                while (c.next()){


                }


            }
        });


    }




    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();
            testbutton.setDisable(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    //Menu Handlers
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


    public void prints(){
    }

    public void onclickhndle(ActionEvent event)throws IOException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("fxml/Update.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("fxml/NeaKrathsh.fxml"));
                break;
            case "updateButton":rootparent=FXMLLoader.load(getClass().getResource("fxml/Update.fxml"));
                break;
            case "deleteButton":rootparent= FXMLLoader.load(getClass().getResource("fxml/Delete.fxml"));
                break;
            case "SEbutton":rootparent = FXMLLoader.load(getClass().getResource("fxml/SE.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
                break;



        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



}



