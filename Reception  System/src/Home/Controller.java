package Home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();

    //nea krathsh
    public TextField txtfield=new TextField();
    public TextField textfield2=new TextField();
    public Button button2=new Button();

    DbConnection db = new DbConnection();
public void systemprint(){



}




    public void mouseEnter(){
        db.createTable();
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
        String textfieldValue=txtfield.getText();

        System.out.println(textfieldValue);}

        public void onclickhndle(ActionEvent event)throws IOException {
            String evt=((Button) event.getSource()).getId();

            Parent rootparent= FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));

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

            

        }   Scene scene=new Scene(rootparent);
            window.setScene(scene);
            window.show();


        }



}


