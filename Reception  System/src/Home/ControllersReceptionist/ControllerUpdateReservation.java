package Home.ControllersReceptionist;

import Home.DbConnection;
import Home.Krathsh;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class ControllerUpdateReservation<krathsh> implements Initializable {
    //menu
    public Button NKbutton = new Button();
    public Button updateButton = new Button();
    public Button deleteButton = new Button();
    public Button SEbutton = new Button();
    public Button MainButton = new Button();
    public Button SignOutButton=new Button();
    public Button updateButton2=new Button();
    public Button ChangePassB=new Button();

    //delete scene
    public TextField SearchFistNameTextField = new TextField();
    public TextField SearchLastNameTextField = new TextField();
    public Label RoomError=new Label();
    public Label CustError=new Label();

    public TableView<Krathsh> table;
    public TableColumn<Krathsh, Long> col_ResrvID;
    public TableColumn<Krathsh, Integer> col_RoomNumber;
    public TableColumn<Krathsh, String> col_Lastname;
    public TableColumn<Krathsh, String> col_Name;
    public TableColumn<Krathsh, String> col_From;
    public TableColumn<Krathsh, String> col_To;
    public TableColumn<Krathsh, BigDecimal> col_TotalCost;
    public TableColumn<Krathsh,Button> col_Edit;


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


    public void editablecols(){

        col_RoomNumber.setCellFactory(TextFieldTableCell.<Krathsh,Integer>forTableColumn(new IntegerStringConverter()));
        col_RoomNumber.setOnEditCommit (e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomNumber(e.getNewValue());
            long id=0;
            Connection con=DbConnection.getConnection();
            String query="{call getroomid(?)}";
            try {
                callstatement=con.prepareCall(query);
                callstatement.setInt(1,e.getTableView().getItems().get(e.getTablePosition().getRow()).getRoomNumber());
                callstatement.execute();
                ResultSet rs=callstatement.getResultSet();
                if (rs.next()){
                    id=rs.getLong("Id");
                    RoomError.setVisible(false);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomId(id);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
                }else {
                    RoomError.setVisible(true);
                    RoomError.setTextFill(Paint.valueOf("red"));
                    RoomError.setText("Δεν υπάρχει το δωμάτιο");
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(true);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        col_From.setCellFactory(TextFieldTableCell.forTableColumn());

        col_From.setOnEditCommit(e->{
            long id=0;
            Connection con=DbConnection.getConnection();
            String query="{call getroomid(?)}";
            try {
                callstatement=con.prepareCall(query);
                callstatement.setInt(1,e.getTableView().getItems().get(e.getTablePosition().getRow()).getRoomNumber());
                callstatement.execute();
                ResultSet rs=callstatement.getResultSet();
                if (rs.next()){
                    id=rs.getLong("Id");
                    RoomError.setVisible(false);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomId(id);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
                }else {
                    RoomError.setVisible(true);
                    RoomError.setTextFill(Paint.valueOf("red"));
                    RoomError.setText("Δεν υπάρχει το δωμάτιο");
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(true);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCheckindate(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });
        col_To.setCellFactory(TextFieldTableCell.forTableColumn());
        col_To.setOnEditCommit(e->{
            long id=0;
            Connection con=DbConnection.getConnection();
            String query="{call getroomid(?)}";
            try {
                callstatement=con.prepareCall(query);
                callstatement.setInt(1,e.getTableView().getItems().get(e.getTablePosition().getRow()).getRoomNumber());
                callstatement.execute();
                ResultSet rs=callstatement.getResultSet();
                if (rs.next()){
                    id=rs.getLong("Id");
                    RoomError.setVisible(false);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomId(id);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
                }else {
                    RoomError.setVisible(true);
                    RoomError.setTextFill(Paint.valueOf("red"));
                    RoomError.setText("Δεν υπάρχει το δωμάτιο");
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(true);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCheckoutdate(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });
        col_TotalCost.setCellFactory(TextFieldTableCell.<Krathsh,BigDecimal>forTableColumn(new BigDecimalStringConverter()));
        col_TotalCost.setOnEditCommit(e->{
            long id=0;
            Connection con=DbConnection.getConnection();
            String query="{call getroomid(?)}";
            try {
                callstatement=con.prepareCall(query);
                callstatement.setInt(1,e.getTableView().getItems().get(e.getTablePosition().getRow()).getRoomNumber());
                callstatement.execute();
                ResultSet rs=callstatement.getResultSet();
                if (rs.next()){
                    id=rs.getLong("Id");
                    RoomError.setVisible(false);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setRoomId(id);
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
                }else {
                    RoomError.setVisible(true);
                    RoomError.setTextFill(Paint.valueOf("red"));
                    RoomError.setText("Δεν υπάρχει το δωμάτιο");
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(true);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTotalCost(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEdit(false);
        });
    }

    public void filltable() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call getreservations ()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet krathsh = callstatement.getResultSet();

        while (krathsh.next()){
            oblist.add(new Krathsh(krathsh.getLong("Id"), krathsh.getInt("roomNumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getString("checkindate"), krathsh.getString("checkoutdate"),krathsh.getBigDecimal("totalCost"),new Button("save")));
        }

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomNumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));
        col_TotalCost.setCellValueFactory(new PropertyValueFactory("totalCost"));
        col_Edit.setCellValueFactory(new PropertyValueFactory("Edit"));

        table.setEditable(true);
        editablecols();
        table.setItems(oblist);

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
            oblist2.add(new Krathsh(krathsh.getLong("ReservationId"), krathsh.getInt("roomNumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getString("checkindate"), krathsh.getString("checkoutdate"),krathsh.getBigDecimal("totalCost"),new Button("save")));
        }

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));
        col_TotalCost.setCellValueFactory(new PropertyValueFactory("totalCost"));
        col_Edit.setCellValueFactory(new PropertyValueFactory("Edit"));

        table.setEditable(true);
        editablecols();
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
            case "updateButton2":updateButton2.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "deleteButton":deleteButton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "SEbutton":SEbutton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "MainButton":MainButton.setStyle("-fx-background-color: #6a25cc;");
                break;
            case "ChangePassB":ChangePassB.setStyle("-fx-background-color:  #6a25cc;");
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
            case "updateButton2":updateButton2.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "deleteButton":deleteButton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "SEbutton":SEbutton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "MainButton":MainButton.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "ChangePassB":ChangePassB.setStyle("-fx-background-color:  #3F2B63;");
                break;
            case "SignOutButton":SignOutButton.setStyle("-fx-background-color:  #3F2B63;");
                break;


        }

    }



    public void onclickhndle(ActionEvent event)throws IOException,SQLException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Main.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();





        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/NeaKrathsh.fxml"));

                break;
            case "updateButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/UpdateCustomer.fxml"));
                break;
            case "updateButton2":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/UpdateReservation.fxml"));
                break;
            case "deleteButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/DeleteReservation.fxml"));
                break;
            case "SEbutton":rootparent = FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/SE.fxml"));
                break;
            case "ChangePassB":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/ChangePassword.fxml"));
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
            CustError.setVisible(false);
            RoomError.setVisible(false);
            UsernameLabelV.setText("User: "+ LoginController.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}




