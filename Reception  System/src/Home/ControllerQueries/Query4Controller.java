package Home.ControllerQueries;

import Home.DbConnection;
import Home.Dwmatio;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Query4Controller implements Initializable {
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



    public Label UsernameLabelV=new Label();

    //query4 ui
    public TextField nametextfield = new TextField();
    public TextField lastnametextfield = new TextField();
    public Button search = new Button();
    public Label countlabel = new Label();

    ObservableList<Dwmatio> oblist = FXCollections.observableArrayList();


    public void checkcountcheckins() throws SQLException {
        Connection c = DbConnection.getConnection();
        String query = "{call totalcustomercheckins(?,?)}";
        callstatement = c.prepareCall(query);
        callstatement.setString(1,nametextfield.getText());
        callstatement.setString(2,lastnametextfield.getText());

        callstatement.executeQuery();

        ResultSet krathsh = callstatement.getResultSet();

        if (krathsh.next()) {
            oblist.add(new Dwmatio(krathsh.getInt("totalcheckins")));
            countlabel.setText(krathsh.getString("totalcheckins"));
            countlabel.setVisible(true);

        }else{
            countlabel.setText("Δεν υπάρχει κράτηση με αυτό το ονοματεπώνυμο!");
            countlabel.setVisible(true);
        }

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


    }



}


