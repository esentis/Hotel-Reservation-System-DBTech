package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Login.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsRoomController<dwmatio> implements Initializable {

    //menu
    public Button NewRoomButton = new Button();
    public Button updateRoomButton = new Button();
    public Button deleteRoomButton = new Button();
    public Button deleteCustButton = new Button();
    public Button MainButton = new Button();
    public Button LogsButton = new Button();
    public Button SignOutButton = new Button();
    public Button NewStaff=new Button();
    public Button UpdateStaff=new Button();
    public Button DeleteStaff=new Button();


    DbConnection db = new DbConnection();

    //Eisagwgh neou dwmatiou
    public TextField roomnumbertxtfield = new TextField();

    public Button SearchButton = new Button();
    public Button KataxwrhshButton = new Button();
    public Button ResetButton = new Button();

    public Label LabelToChange = new Label();
    public Label message = new Label();

    public ComboBox RoomTypeCombo = new ComboBox();
    public ComboBox FloorCombo = new ComboBox();

    CallableStatement callstatement = null;

    ObservableList<String> oblist2 = FXCollections.observableArrayList();
    ObservableList<String> oblist3 = FXCollections.observableArrayList();


    long roomtypeid;
    int roomfloor;
    int roomnumber;


    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }




    public void SearchIfRoomExists() throws SQLException {
        Connection con = DbConnection.getConnection();
        String query = "{call getroomid(?)}";
        callstatement = con.prepareCall(query);
        callstatement.setInt(1, Integer.parseInt(roomnumbertxtfield.getText()));
        callstatement.execute();

        ResultSet room = callstatement.getResultSet();
        if (!room.next()) {
            LabelToChange.setText("Δεν υπάρχει το Δωμάτιο στην Βάση πραγματοποιήστε εισαγωγή νέου δωματίου");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("red"));
            roomnumbertxtfield.setText(roomnumbertxtfield.getText());
            
        } else {
            LabelToChange.setText("Το δωμάτιο υπάρχει στην Βάση");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("green"));

        }


    }


    public void addRoom() throws SQLException {


        String userChoice = (String) RoomTypeCombo.getSelectionModel().getSelectedItem();
        if (userChoice == "Δίκλινο") {
            roomtypeid = 1;

        } else if (userChoice == "Τρίκλινο") {
            roomtypeid = 2;
        } else
            roomtypeid = 3;

        String userChoice2 = (String) FloorCombo.getSelectionModel().getSelectedItem();
        if (userChoice2 == "Ισόγειο") {
            roomfloor = 0;

        } else if (userChoice2 == "1ος") {
            roomfloor = 1;
        }else{
            roomfloor=2;
        }


        Connection c = DbConnection.getConnection();
        String query = "{call addroom(?,?,?)}";
        callstatement = c.prepareCall(query);

        callstatement.setInt(2, Integer.parseInt(roomnumbertxtfield.getText()));
        callstatement.setLong(3, roomtypeid);
        callstatement.setInt(1, roomfloor);
        callstatement.executeQuery();

        message.setText("Το δωμάτιο δημιουργήθηκε στην Βάση");
        message.setVisible(true);
        message.setTextFill(Paint.valueOf("green"));


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

    public void onclickhndle(ActionEvent event) throws IOException,SQLException {
        String evt = ((Button) event.getSource()).getId();

        Parent rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


        switch (evt) {
            case "NewRoomButton":
                rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));

                break;
            case "updateRoomButton":
                rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/UpdateRoom.fxml"));
                break;
            case "deleteRoomButton":
                rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteRoom.fxml"));
                break;
            case "deleteCustButton":
                rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteCustomer.fxml"));
                break;
            case "MainButton":
                rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));
                break;
            case "LogsButton":
                rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/Logs.fxml"));
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


        }
        Scene scene = new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }




    public void initialize(URL location, ResourceBundle resources) {


        UsernameLabelV.setText("User: "+ LoginController.getUsername());

        ObservableList<String> oblist2 = FXCollections
                .observableArrayList("Δίκλινο", "Τρίκλινο", "Σουίτα");

        ObservableList<String> oblist3 = FXCollections
                .observableArrayList("Ισόγειο", "1ος", "2ος");

        RoomTypeCombo.setItems(oblist2);
        FloorCombo.setItems(oblist3);

    }

}




