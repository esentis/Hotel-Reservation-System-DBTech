package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    CallableStatement callstatement = null;

    String UserName;




    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Home/Login/Login.fxml"));
        primaryStage.setTitle("HOTEL");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1017, 708));
        primaryStage.show();


    }
    @Override
    public void stop()throws SQLException{
       SignOut();
    }


    public void SignOut() throws SQLException{
        Connection con=DbConnection.getConnection();
        String query="{call signoutstaff ()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        callstatement.close();
    }




    public static void main(String[] args) {
        launch(args);
    }
}

