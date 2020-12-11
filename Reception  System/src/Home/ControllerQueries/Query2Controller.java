package Home.ControllerQueries;

import Home.DbConnection;
import Home.Dwmatio;
import Home.Krathsh;
import Home.Login.LoginController;
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

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Query2Controller implements Initializable {
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

    public DatePicker FromField=new DatePicker();
    public DatePicker ToField=new DatePicker();
    public TableView<Dwmatio> MyTable=new TableView<>();
    public TableColumn<Long,Dwmatio> col_ID;
    public TableColumn<Long,Dwmatio> col_floor;
    public TableColumn<Long,Dwmatio> col_RoomNumber;
    public TableColumn<Long,Dwmatio> col_Beds;

    ObservableList<Dwmatio> oblist= FXCollections.observableArrayList();



    public Label UsernameLabelV=new Label();

    public void SignOut() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();

    }

    public void getFreeRooms() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call getallfreerooms(?,?) }";
        callstatement=con.prepareCall(query);
        callstatement.setDate(1,java.sql.Date.valueOf(ToField.getValue().toString()));
        callstatement.setDate(2,java.sql.Date.valueOf(FromField.getValue().toString()));
        callstatement.execute();

        ResultSet rs=callstatement.getResultSet();

        while (rs.next()){
            oblist.add(new Dwmatio(rs.getLong("RoomId"),rs.getInt("FloorNumber"),rs.getInt("RoomNumber"),rs.getInt("Beds")));
        }
        col_ID.setCellValueFactory(new PropertyValueFactory("roomid"));
        col_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_RoomNumber.setCellValueFactory(new PropertyValueFactory("roomnumber"));
        col_Beds.setCellValueFactory(new PropertyValueFactory("beds"));

        MyTable.setItems(oblist);
        MyTable.setVisible(true);



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
        MyTable.setVisible(false);
        UsernameLabelV.setText("User: "+ LoginController.getUsername());}



}


