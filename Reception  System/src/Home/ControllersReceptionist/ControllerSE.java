package Home.ControllersReceptionist;

import Home.DbConnection;
import Home.Login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
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

public class ControllerSE implements Initializable {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();
    public Button SignOutButton=new Button();

    public Button Query1=new Button();
    public Button Query2=new Button();
    public Button Query3=new Button();
    public Button Query4=new Button();
    public Button Query5=new Button();
    public Button Query6=new Button();
    public Button Query7=new Button();
    public Button Query8=new Button();
    public Button Query9=new Button();
    public Button Query10=new Button();

    CallableStatement callstatement = null;


    public Label UsernameLabelV=new Label();

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


    public  void queriesButton(ActionEvent event)throws IOException{
        String evt=((Button) event.getSource()).getId();
        System.out.println();
        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/SE.fxml"));
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        switch (evt){
            case "Query1":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query1.FXML"));
                break;
            case "Query2":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query2.FXML"));
                break;
            case "Query3":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query3.FXML"));
                break;
            case "Query4":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query4.FXML"));
                break;
            case "Query5":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query5.FXML"));
                break;
            case "Query6":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query6.FXML"));
                break;
            case "Query7":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query7.FXML"));
                break;
            case "Query8":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query8.FXML"));
                break;
            case "Query9":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query9.FXML"));
                break;
            case "Query10":rootparent= FXMLLoader.load(getClass().getResource("/Home/QueriesFXML/Query10.FXML"));
                break;
        }
        Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();

    }
    public void MouseEnter(MouseEvent event){
        String evt=((Button) event.getSource()).getId();

        switch (evt){
            case "Query1":Query1.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query2":Query2.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query3":Query3.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query4":Query4.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query5":Query5.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query6":Query6.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query7":Query7.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query8":Query8.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query9":Query9.setStyle("-fx-background-color: #C8C8C8");
                break;
            case "Query10":Query10.setStyle("-fx-background-color: #C8C8C8");
                break;

        }


    }

    public void MouseExit(MouseEvent event){
        String evt=((Button) event.getSource()).getId();

        switch (evt){
            case "Query1":Query1.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query2":Query2.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query3":Query3.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query4":Query4.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query5":Query5.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query6":Query6.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query7":Query7.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query8":Query8.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query9":Query9.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;
            case "Query10":Query10.setStyle("-fx-border-color: #787878; -fx-background-color: #FFFFFF;");
                break;

        }
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

        Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/SE.fxml"));

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
        UsernameLabelV.setText("User: "+ LoginController.getUsername());


    }



}


