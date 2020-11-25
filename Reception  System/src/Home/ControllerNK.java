package Home;

import com.sun.prism.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.RadioButton;


import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerNK implements Initializable {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();


    //new costumer
    public Pane NewCustPane=new Pane();
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
    public Label LabelToChange=new Label();
    Long id;

    CallableStatement callstatement = null;



    public void SearchIfCostumerExists() throws SQLException {
        Connection con=DbConnection.getConnection();
        String query="{call getCustomerId(?,?)}";
        callstatement=con.prepareCall(query);
        callstatement.setString(1,EpithetoNKField.getText());
        callstatement.setString(2,OnomaNKField.getText());
        callstatement.execute();
        ResultSet customer=callstatement.getResultSet();
        if(customer.next()==false){
            LabelToChange.setText("Δεν υπάρχει ο πελάτης στην Βάση πραγματοποιήστε εισαγωγή νέου πελάτη");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("red"));
            NewCustPane.setVisible(true);
            OnomaField.setText(OnomaNKField.getText());
            EpithetoField.setText(EpithetoNKField.getText());


        }else{
            LabelToChange.setText("Ο πελάτης υπάρχει στην Βάση");
            LabelToChange.setVisible(true);
            LabelToChange.setTextFill(Paint.valueOf("green"));
            id=customer.getLong("id");
            System.out.println(id);
        }


    }

    public void AddCustomer()throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call addcustomer(?,?,?,?)}";
        callstatement=con.prepareCall(query);
    }



    public void initialize(URL location, ResourceBundle resources) {
        NewCustPane.setVisible(false);
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


