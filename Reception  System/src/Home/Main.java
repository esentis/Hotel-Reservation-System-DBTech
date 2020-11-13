package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Μain.fxml"));
        primaryStage.setTitle("HOTEL");





        primaryStage.setScene(new Scene(root, 1150, 708));
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}

