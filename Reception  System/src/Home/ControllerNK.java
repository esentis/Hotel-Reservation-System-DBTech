package Home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.RadioButton;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerNK {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();


    //new costumer
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
    public Button ResetButton=new Button();

    CallableStatement callstatement = null;



    public void SearchIfCostumerExists() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call getCustomerId(?,?)}";
        callstatement=con.prepareCall(query);
        callstatement.setString(1,EpithetoNKField.getText());
        callstatement.setString(2,OnomaNKField.getText());
        callstatement.execute();
        ResultSet customer=callstatement.getResultSet();


    }


















    public  void mouseEnter1(){
        MainButton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit1(){
        MainButton.setStyle("-fx-background-color:  #3F2B63;");

    }

    public void mouseEnter(){

        NKbutton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit(){
        NKbutton.setStyle("-fx-background-color:  #3F2B63;");}

    public void mouseEnter2(){
        updateButton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit2(){
        updateButton.setStyle("-fx-background-color:  #3F2B63;");}

    public void mouseEnter3(){
        deleteButton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit3(){
        deleteButton.setStyle("-fx-background-color:  #3F2B63;");}

    public void mouseEnter4(){
        SEbutton.setStyle("-fx-background-color: #6a25cc;");

    }
    public void mouseExit4(){
        SEbutton.setStyle("-fx-background-color:  #3F2B63;");}


    public void prints(){
       
        }

    public void onclickhndle(ActionEvent event)throws IOException {
        String evt=((Button) event.getSource()).getId();

        Parent rootparent= FXMLLoader.load(getClass().getResource("fxml/NeaKrathsh.fxml"));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();


        switch (evt){
            case "NKbutton":rootparent=FXMLLoader.load(getClass().getResource("fxml/NeaKrathsh.fxml"));

                break;
            case "updateButton":rootparent= FXMLLoader.load(getClass().getResource("fxml/Update.fxml"));
                break;
            case "deleteButton":rootparent= FXMLLoader.load(getClass().getResource("fxml/Delete.fxml"));
                break;
            case "SEbutton":rootparent = FXMLLoader.load(getClass().getResource("fxml/SE.fxml"));
                break;
            case "MainButton":rootparent = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
                break;


        }   Scene scene=new Scene(rootparent);
        window.setScene(scene);
        window.show();


    }



}


