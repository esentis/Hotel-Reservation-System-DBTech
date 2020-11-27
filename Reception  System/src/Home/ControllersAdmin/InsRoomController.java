package Home.ControllersAdmin;

import Home.DbConnection;
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

        message.setText("Το δωμάτιο υπάρχει στην Βάση");
        message.setVisible(true);
        message.setTextFill(Paint.valueOf("green"));


    }


    public void mouseEnter1() {
        MainButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit1() {
        MainButton.setStyle("-fx-background-color:  #1855ab;");

    }

    public void mouseEnter() {

        NewRoomButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit() {
        NewRoomButton.setStyle("-fx-background-color:  #1855ab;");
    }

    public void mouseEnter2() {
        updateRoomButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit2() {
        updateRoomButton.setStyle("-fx-background-color:  #1855ab;");
    }

    public void mouseEnter3() {
        deleteRoomButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit3() {
        deleteRoomButton.setStyle("-fx-background-color:  #1855ab;");
    }

    public void mouseEnter4() {
        deleteCustButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit4() {
        deleteCustButton.setStyle("-fx-background-color:  #1855ab;");
    }

    public void mouseEnter5() {
        LogsButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit5() {
        LogsButton.setStyle("-fx-background-color:  #1855ab;");
    }

    public void mouseEnter6() {
        SignOutButton.setStyle("-fx-background-color: #2771d9;");

    }

    public void mouseExit6() {
        SignOutButton.setStyle("-fx-background-color:  #1855ab;");
    }


    public void prints() {
    }

    public void onclickhndle(ActionEvent event) throws IOException {
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


        }
        Scene scene = new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }

    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> oblist2 = FXCollections
                .observableArrayList("Δίκλινο", "Τρίκλινο", "Σουίτα");

        ObservableList<String> oblist3 = FXCollections
                .observableArrayList("Ισόγειο", "1ος", "2ος");

        RoomTypeCombo.setItems(oblist2);
        FloorCombo.setItems(oblist3);

    }

}




