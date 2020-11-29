package Home.ControllersAdmin;

import Home.DbConnection;
import Home.Dwmatio;
import Home.Krathsh;
import Home.Pelatis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DelRoomController implements Initializable {

    //menu
    public Button NewRoomButton = new Button();
    public Button updateRoomButton = new Button();
    public Button deleteRoomButton = new Button();
    public Button deleteCustButton = new Button();
    public Button MainButton = new Button();
    public Button LogsButton = new Button();
    public Button SignOutButton = new Button();


    DbConnection db = new DbConnection();
    static CallableStatement callstatement = null;

    //Diagrafh Dwmatiou
    public TextField DeleteTextField = new TextField();
    public Button searchButton = new Button();
    public Button deleteButton = new Button();

    public TableView<Dwmatio> table;
    public TableColumn<Dwmatio, Integer> col_RoomNumber;
    public TableColumn<Dwmatio, Integer> col_Floor;
    public TableColumn<Dwmatio, Long> col_RoomType;

    public Label LabelToChange = new Label();

    private int beds;
    ObservableList<Dwmatio> oblist = FXCollections.observableArrayList();
    ObservableList<Dwmatio> oblist2 = FXCollections.observableArrayList();

    public void filltable() throws SQLException {
        table.getItems().clear();
        Connection c = DbConnection.getConnection();
        String query = "{call getrooms ()}";
        callstatement = c.prepareCall(query);
        callstatement.executeQuery();
        ResultSet rooms = callstatement.getResultSet();

        while (rooms.next()) {
            oblist.add(new Dwmatio(rooms.getInt("roomnumber"), rooms.getInt("floor"), rooms.getInt("beds")));

        }

        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_RoomType.setCellValueFactory(new PropertyValueFactory("beds"));

        table.setItems(oblist);
    }


    public void SearchIfRoomExists() throws SQLException {

        table.getItems().clear();
        Connection con = DbConnection.getConnection();
        String query = "{call findroom(?)}";
        callstatement = con.prepareCall(query);

        callstatement.setInt(1, Integer.parseInt(DeleteTextField.getText()));
        callstatement.executeQuery();

        ResultSet room = callstatement.getResultSet();

        while (room.next()) {
            oblist2.add(new Dwmatio(room.getInt("floor"), room.getInt("roomnumber"), room.getInt("beds")));

            col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
            col_Floor.setCellValueFactory(new PropertyValueFactory("floor"));
            col_RoomType.setCellValueFactory(new PropertyValueFactory("beds"));

            table.setItems(oblist2);

            LabelToChange.setText("Το δωμάτιο υπάρχει στην Βάση");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("green"));
        }

        if (room.next()) {

            LabelToChange.setText("Δεν υπάρχει το Δωμάτιο στην Βάση πραγματοποιήστε εισαγωγή νέου δωματίου");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("red"));
            DeleteTextField.setText(DeleteTextField.getText());
        }


        callstatement.close();

        con.close();


    }


    public void deleteRoom() throws SQLException {
        Dwmatio dwmatio = table.getSelectionModel().getSelectedItem();
        int roomnumber = dwmatio.getRoomnumber();

        Connection c = null;
        c = DbConnection.getConnection();
        String query = "{call deleteroomwithnumber(?)}";
        callstatement = c.prepareCall(query);
        callstatement.setInt(1, roomnumber);
        callstatement.execute();
        callstatement.close();
        c.close();
        ObservableList<Dwmatio> row, allRows;
        allRows = table.getItems();
        row = table.getSelectionModel().getSelectedItems();
        row.forEach(allRows::remove);
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

        Parent rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/DeleteRoom.fxml"));

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            filltable();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}




