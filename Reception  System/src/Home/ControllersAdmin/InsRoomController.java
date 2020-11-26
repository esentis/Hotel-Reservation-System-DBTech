package Home.ControllersAdmin;

import Home.DbConnection;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InsRoomController{

    //menu
    public Button NewRoomButton=new Button();
    public Button updateRoomButton=new Button();
    public Button deleteRoomButton=new Button();
    public Button deleteCustButton=new Button();
    public Button MainButton=new Button();
    public Button LogsButton =new Button();
    public Button SignOutButton =new Button();


    DbConnection db = new DbConnection();





    public  void mouseEnter1(){
        MainButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit1(){
        MainButton.setStyle("-fx-background-color:  #1855ab;");

    }

    public void mouseEnter(){

        NewRoomButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit(){
        NewRoomButton.setStyle("-fx-background-color:  #1855ab;");}

    public void mouseEnter2(){
        updateRoomButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit2(){
        updateRoomButton.setStyle("-fx-background-color:  #1855ab;");}

    public void mouseEnter3(){
        deleteRoomButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit3(){
        deleteRoomButton.setStyle("-fx-background-color:  #1855ab;");}

    public void mouseEnter4(){
        deleteCustButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit4(){
        deleteCustButton.setStyle("-fx-background-color:  #1855ab;");}

    public void mouseEnter5(){
        LogsButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit5(){
        LogsButton.setStyle("-fx-background-color:  #1855ab;");}

    public void mouseEnter6(){
        SignOutButton.setStyle("-fx-background-color: #2771d9;");

    }
    public void mouseExit6(){
        SignOutButton.setStyle("-fx-background-color:  #1855ab;");}




    public void prints(){
    }

    public void onclickhndle(ActionEvent event)throws IOException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));

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
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/MainAdmin.fxml"));
                break;
            case "LogsButton":rootparent=FXMLLoader.load(getClass().getResource("/Home/AdminFXML/Logs.fxml"));
                break;






        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



}




