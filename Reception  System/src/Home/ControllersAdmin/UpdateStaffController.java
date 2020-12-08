package Home.ControllersAdmin;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;


import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateStaffController implements Initializable {

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


    public TableView<Staff> MyTable=new TableView<>();
    public TableColumn<Staff, Long> IdCol;
    public TableColumn<Staff,String> LastNameCol;
    public TableColumn<Staff, String> FirstNameCol;
    public TableColumn<Staff, String> UsernameCol;
    public TableColumn<Staff, String> EmailCol;
    public TableColumn<Staff, Long> PhoneCol;
    public TableColumn<Staff, Long> RoleIdCol;
    public TableColumn<Staff,Button> EditCol;

    public TextField LastnameText=new TextField();
    public TextField FirstnameText=new TextField();



    ObservableList<Staff> oblist = FXCollections.observableArrayList();
    ObservableList<Staff>oblist2 = FXCollections.observableArrayList();

    CallableStatement callstatement = null;





    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }



    public void filltable()throws SQLException {
        MyTable.getItems().clear();
        Connection con=DbConnection.getConnection();
        String query = "{call getallstaff()}";
        callstatement = con.prepareCall(query);
        callstatement.execute();
        ResultSet staff = callstatement.getResultSet();
        while (staff.next()){
            oblist.add(new Staff(staff.getLong("Id"),staff.getString("lastName"),staff.getString("firstName"),
                    staff.getString("UserName"),staff.getString("email"),
                    staff.getLong("PhoneNumber"),staff.getLong("RoleId"),new Button("Save")));


        }
        IdCol.setCellValueFactory(new PropertyValueFactory("Id"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory("UserName"));
        EmailCol.setCellValueFactory(new PropertyValueFactory("email"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
        RoleIdCol.setCellValueFactory(new PropertyValueFactory("RoleId"));
        EditCol.setCellValueFactory(new PropertyValueFactory("Edit"));

        editablecols();
        MyTable.setItems(oblist);

    }

    public void editablecols(){
        FirstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        FirstNameCol.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstName(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

        });


        LastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        LastNameCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLastName(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });
        UsernameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        UsernameCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setUserName(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });




        EmailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        EmailCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);

        });
        PhoneCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));

        PhoneCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhoneNumber(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });

        RoleIdCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));

        RoleIdCol.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoleId(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);});





        MyTable.setEditable(true);
    }

    public void searchStaff() throws SQLException{
        MyTable.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call checkstaffbyname(?,?)}";
        callstatement = c.prepareCall(query);
        callstatement.setString(2,LastnameText.getText());
        callstatement.setString(1,FirstnameText.getText());
        callstatement.executeQuery();

        ResultSet staff = callstatement.getResultSet();
        while (staff.next()){
            oblist2.add(new Staff(staff.getLong("Id"),staff.getString("lastName"),staff.getString("firstName"),
                    staff.getString("UserName"),staff.getString("email"),
                    staff.getLong("PhoneNumber"),staff.getLong("RoleId"),new Button("Save")));


        }
        IdCol.setCellValueFactory(new PropertyValueFactory("Id"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory("UserName"));
        EmailCol.setCellValueFactory(new PropertyValueFactory("email"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
        RoleIdCol.setCellValueFactory(new PropertyValueFactory("RoleId"));
        EditCol.setCellValueFactory(new PropertyValueFactory("Edit"));

        editablecols();
        MyTable.setItems(oblist2);
        callstatement.close();
        c.close();
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

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));

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
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/MainAdmin.fxml"));
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






        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }


    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();
            UsernameLabelV.setText("User: "+LoginController.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}



}




