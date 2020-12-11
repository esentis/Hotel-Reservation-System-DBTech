package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogsController implements Initializable {

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
    public Button ChangePassB=new Button();

    public Button CustLogs=new Button();
    public Button ResLogs=new Button();
    public Button RoomsLogs=new Button();
    public Button StaffLogs=new Button();

    CallableStatement callstatement = null;



    DbConnection db = new DbConnection();



    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

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
            case "ChangePassB":ChangePassB.setStyle("-fx-background-color: #2771d9;");
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
            case "ChangePassB":ChangePassB.setStyle("-fx-background-color:  #1855ab;");
                break;



        }

    }

    public  void ButtonsHandle(ActionEvent event)throws IOException{
        String evt=((Button) event.getSource()).getId();
        System.out.println();
        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/Logs.fxml"));
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        switch (evt){
            case "CustLogs":rootparent= FXMLLoader.load(getClass().getResource("/Home/LogsFXML/CustomerLogs.fxml"));
                break;
            case "ResLogs":rootparent= FXMLLoader.load(getClass().getResource("/Home/LogsFXML/ReservationsLogs.fxml"));
                break;
            case "RoomsLogs":rootparent= FXMLLoader.load(getClass().getResource("/Home/LogsFXML/RoomsLogs.fxml"));
                break;
            case "StaffLogs":rootparent= FXMLLoader.load(getClass().getResource("/Home/LogsFXML/StaffLogs.fxml"));
                break;}
            Scene scene=new Scene(rootparent);
            window.setScene(scene);
            window.show();
        }


    public void MouseEnter(MouseEvent event){
        String evt=((Button) event.getSource()).getId();

        switch (evt){
            case "CustLogs":CustLogs.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "ResLogs":ResLogs.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "RoomsLogs":RoomsLogs.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "StaffLogs":StaffLogs.setStyle("-fx-background-color: #C8C8C8");
                break;}}


    public void MouseExit(MouseEvent event){
        String evt=((Button) event.getSource()).getId();

        switch (evt){
            case "CustLogs":CustLogs.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "ResLogs":ResLogs.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "RoomsLogs":RoomsLogs.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "StaffLogs":StaffLogs.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;}}

    public void onclickhndle(ActionEvent event)throws IOException,SQLException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/Logs.fxml"));

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
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/ChangePassword.fxml"));
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
            case "ChangePassB":rootparent = FXMLLoader.load(getClass().getResource("/Home/Adminfxml/ChangePassword.fxml"));
                break;






        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

            UsernameLabelV.setText("User: "+ LoginController.getUsername());


    }



}




