package Home.ControllerQueries;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Query9Controller  implements Initializable {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();
    public Button SignOutButton=new Button();
    public Button updateButton2=new Button();
    public Button ChangePassB=new Button();

    CallableStatement callstatement = null;

    public TextField PriceField=new TextField();

    public TableView<Krathsh> table;
    public TableColumn<Krathsh, Long> col_ResrvID;
    public TableColumn<Krathsh, Integer> col_RoomNumber;
    public TableColumn<Pelatis, String> col_Lastname;
    public TableColumn<Pelatis, String> col_Name;
    public TableColumn<Krathsh, String> col_From;
    public TableColumn<Krathsh, String> col_To;
    public TableColumn<Krathsh, BigDecimal> col_TotalCost;

    ObservableList<Krathsh> oblist = FXCollections.observableArrayList();

    public Label UsernameLabelV=new Label();

    public void LessThan() throws SQLException {
        String PriceString= PriceField.getText();
        int Price=Integer.parseInt(PriceString);
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call reservationcostless (?)}";
        callstatement = c.prepareCall(query);
        callstatement.setInt(1,Price);
        callstatement.executeQuery();

        ResultSet krathsh = callstatement.getResultSet();

        while (krathsh.next()){
            oblist.add(new Krathsh(krathsh.getLong("reservationid"), krathsh.getInt("roomNumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getString("checkindate"), krathsh.getString("checkoutdate"),krathsh.getBigDecimal("Cost")));
        }

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomNumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));
        col_TotalCost.setCellValueFactory(new PropertyValueFactory("totalCost"));

        table.setItems(oblist);
        table.setVisible(true);
        callstatement.close();

        c.close();


    }
    public void GreaterThan() throws SQLException {
        String PriceString= PriceField.getText();
        int Price=Integer.parseInt(PriceString);
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call reservationcostmore (?)}";
        callstatement = c.prepareCall(query);
        callstatement.setInt(1,Price);
        callstatement.executeQuery();

        ResultSet krathsh = callstatement.getResultSet();

        while (krathsh.next()){
            oblist.add(new Krathsh(krathsh.getLong("ReservationId"), krathsh.getInt("RoomNumber"), krathsh.getString("lastname"),
                    krathsh.getString("firstname"), krathsh.getString("checkInDate"), krathsh.getString("checkOutDate"),krathsh.getBigDecimal("Cost")));
        }

        col_ResrvID.setCellValueFactory(new PropertyValueFactory("Id"));
        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomNumber"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_Name.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_From.setCellValueFactory(new PropertyValueFactory("checkindate"));
        col_To.setCellValueFactory(new PropertyValueFactory("checkoutdate"));
        col_TotalCost.setCellValueFactory(new PropertyValueFactory("totalCost"));

        table.setItems(oblist);
        table.setVisible(true);
        callstatement.close();

        c.close();


    }






    public void SignOut() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

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
        UsernameLabelV.setText("User: "+ LoginController.getUsername());
        table.setVisible(false);


    }



}


