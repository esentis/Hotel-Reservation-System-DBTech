package Home.ControllersReceptionist;

import Home.DbConnection;
import Home.Login.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;

import java.math.BigDecimal;
import java.sql.Date.*;
import java.sql.*;
import java.sql.Date;
import java.sql.Timestamp;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ControllerNK implements Initializable {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();
    public Button SignOutButton=new Button();


    //new costumer
    public Pane NewCustPane=new Pane();
    public TextField OnomaField=new TextField();
    public TextField EpithetoField=new TextField();
    public TextField EmailField=new TextField();
    public TextField PhoneField=new TextField();
    public Button AddButton=new Button();



    //nea krathsh
    public TextField OnomaNKField=new TextField();
    public TextField EpithetoNKField=new TextField();
    public Button SearchButton=new Button();
    public DatePicker FromField=new DatePicker();
    public DatePicker ToField=new DatePicker();
    public ToggleGroup group=new ToggleGroup();
    public RadioButton DiklinoRadio=new RadioButton();
    public RadioButton TriklinoRadio=new RadioButton();
    public RadioButton SouitaRadio=new RadioButton();
    public ComboBox RoomCombo=new ComboBox();
    public Button KataxwrhshButton=new Button();
    public Label LabelToChange=new Label();
    public Button AvailabilityB=new Button();
    public Label RoomLabel=new Label();
    Long id;
    public Button ResetB=new Button();
    ObservableList <Long>ComboInt= FXCollections.observableArrayList();

    CallableStatement callstatement = null;

    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }



    public long SearchIfCostumerExists() throws SQLException {
        Connection con= DbConnection.getConnection();
        String query="{call getCustomerId(?,?)}";
        callstatement=con.prepareCall(query);
        callstatement.setString(1,EpithetoNKField.getText());
        callstatement.setString(2,OnomaNKField.getText());
        callstatement.execute();
        ResultSet customer=callstatement.getResultSet();
        if(customer.next()==false){
            LabelToChange.setText("Δεν υπάρχει ο πελάτης στην Βάση πραγματοποιήστε εισαγωγή νέου πελάτη");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("red"));
            NewCustPane.setVisible(true);
            OnomaField.setText(OnomaNKField.getText());
            EpithetoField.setText(EpithetoNKField.getText());


        }else{
            LabelToChange.setText("Ο πελάτης υπάρχει στην Βάση");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("green"));
            id=customer.getLong("id");
            return id;

        }

        return 0;
    }

    public void AddCustomer()throws SQLException{
        NewCustPane.setVisible(false);
        Connection con=DbConnection.getConnection();
        String query="{call addcustomer(?,?,?,?)}";
        callstatement=con.prepareCall(query);
        callstatement.setString(1,EmailField.getText());
        callstatement.setString(2,OnomaField.getText());
        callstatement.setString(3,EpithetoField.getText());
        long Phone=Long.parseLong(PhoneField.getText());
        callstatement.setLong(4,Phone);
        callstatement.execute();

    }
    public void Kataxwrish()throws SQLException{
        long id;
        long roomid=getRoomid();
        id=SearchIfCostumerExists();
        BigDecimal price=calculatePrice();
        if(id>0) {
            Connection con=DbConnection.getConnection();
            String query="{call addreservation (?,?,?,?,?)}";
            callstatement=con.prepareCall(query);
            callstatement.setDate(1,java.sql.Date.valueOf(FromField.getValue().toString()));
            callstatement.setDate(2,java.sql.Date.valueOf(ToField.getValue().toString()));
            callstatement.setLong(3,id);
            callstatement.setLong(4,roomid);
            callstatement.setBigDecimal(5,price);
            callstatement.execute();
        }


    }

    public void Availability() throws SQLException{
        int Bednumber=getBeds();



        Connection con=DbConnection.getConnection();
        String query="{call getfreerooms(?,?,?) }";
        callstatement=con.prepareCall(query);
        callstatement.setDate(1,java.sql.Date.valueOf(ToField.getValue().toString()));
        callstatement.setDate(2,java.sql.Date.valueOf(FromField.getValue().toString()));
        callstatement.setInt(3,Bednumber);
        callstatement.executeQuery();
        ResultSet Rooms=callstatement.getResultSet();
        while (Rooms.next()){
            ComboInt.add(Rooms.getLong("RoomNumber"));

        }

        RoomCombo.setItems(ComboInt);

        ResetB.setVisible(true);
        KataxwrhshButton.setVisible(true);
        RoomLabel.setVisible(true);
        RoomCombo.setVisible(true);


    }
    public long getRoomid() throws SQLException{
        long id=0;
        String selected=RoomCombo.getValue().toString();
        int room=Integer.parseInt(selected);

        Connection con=DbConnection.getConnection();
        String query="{call getroomid(?)}";
        callstatement=con.prepareCall(query);
        callstatement.setInt(1,room);
        callstatement.execute();
        ResultSet rs=callstatement.getResultSet();
        while(rs.next()){
            id=rs.getLong("Id");}



        return id;
    }
    public void ResetTextFields(ActionEvent event)throws IOException{
        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/NeaKrathsh.fxml"));
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();
    }

    public int getBeds(){
        int Bednumber;
        RoomCombo.getItems().clear();

        if(DiklinoRadio.isSelected()){
            Bednumber=2;
        }else if(TriklinoRadio.isSelected()){
            Bednumber=3;
        }else{
            Bednumber=4;
        }
        return Bednumber;
    }

    public BigDecimal calculatePrice() throws SQLException{
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String From=FromField.getValue().toString();
        String To=ToField.getValue().toString();
        LocalDate date1=LocalDate.parse(From,dtf);
        LocalDate date2=LocalDate.parse(To,dtf);
        long diff= ChronoUnit.DAYS.between(date1,date2);


        BigDecimal roomTypePrice=new BigDecimal(0.0);

        Connection con=DbConnection.getConnection();
        String query="{call getroomtypeprice (?)}";
        callstatement=con.prepareCall(query);
        callstatement.setInt(1,getBeds());
        callstatement.execute();
        ResultSet rs=callstatement.getResultSet();
        while(rs.next()){
            roomTypePrice=rs.getBigDecimal("Price");
        }

        BigDecimal price=roomTypePrice.multiply(BigDecimal.valueOf(diff));
        System.out.println(price);
        return price;


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

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/NeaKrathsh.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();


        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/NeaKrathsh.fxml"));

                break;
            case "updateButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Update.fxml"));
                break;
            case "deleteButton":rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Delete.fxml"));
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
        NewCustPane.setVisible(false);
        RoomLabel.setVisible(false);
        KataxwrhshButton.setVisible(false);
        RoomCombo.setVisible(false);
        ResetB.setVisible(false);
        UsernameLabelV.setText("User: "+ LoginController.getUsername());




    }



}


