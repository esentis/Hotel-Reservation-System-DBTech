package Home.Login;


import Home.DbConnection;
import Home.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

public class LoginController implements Initializable {

     public TextField UsernameField=new TextField();
     public PasswordField PasswordField=new PasswordField();
     public Button SignInB=new Button();
     public Label LabelError=new Label();
     private static Staff username=new Staff();
     private static Staff Id=new Staff();




     CallableStatement callstatement = null;

     public void initialize(URL location, ResourceBundle resources) {
          LabelError.setVisible(false);


     }
     public void login(ActionEvent event)throws SQLException, IOException {
          Parent rootparent= FXMLLoader.load(getClass().getResource("/Home/Login/Login.fxml"));
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

          Connection con= DbConnection.getConnection();
          String query="{call checkstaff(?,?)}";
          callstatement=con.prepareCall(query);
          callstatement.setString(1,UsernameField.getText().trim());
          callstatement.setString(2,PasswordField.getText());
          callstatement.execute();
          ResultSet rs=callstatement.getResultSet();


          if(!rs.next()){
               LabelError.setVisible(true);
               LabelError.setText("Λάθος Όνομα Χρήστη/Κωδικός");
               LabelError.setTextFill(Paint.valueOf("red"));
          }
          else {
               username.setUserName(rs.getString("usernametext"));
               Id.setId(rs.getLong("Id"));
               SignInStaff(rs.getLong("Id"));
               if(rs.getString("RoleText").equals("user")){
                    rootparent= FXMLLoader.load(getClass().getResource("/Home/ReceptionistFXML/Main.fxml"));
               }


               else if(rs.getString("RoleText").equals("admin")) {
                    rootparent = FXMLLoader.load(getClass().getResource("/Home/AdminFXML/InsertRoom.fxml"));
               }
               Scene scene=new Scene(rootparent);
               window.setScene(scene);
               window.show();

          }
     }
     public void MouseEnter(){
          SignInB.setStyle("-fx-background-color: #909090;");
     }
     public void MouseExit(){SignInB.setStyle("-fx-background-color: #D0D0D0;");}

     public void SignInStaff(long id) throws SQLException{
          Connection con=DbConnection.getConnection();
          String query="{call signintstaff(?)}";
          callstatement=con.prepareCall(query);
          callstatement.setLong(1,id);
          callstatement.execute();
     }

     public static  String getUsername() {
          return username.getUserName();
     }

     public static long getId() {
          return Id.getId();
     }
}

