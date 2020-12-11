package Home.ControllersReceptionist;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.math.BigDecimal;


public class AlertBox {
    public static void display(String title, BigDecimal TotalCost){
        Stage window=new Stage();
        window.getIcons().add(new Image("/Home/ImageViews/cut_logo2-v4.png"));

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        Label label=new Label();
        label.setText("Η καταχώρηση έγινε με επιτυχία \n"+"Συνολικό Ποσό:"+TotalCost+"€");

        Button okButton=new Button("OK");
        okButton.setOnAction(e->window.close());

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label,okButton);
        layout.setAlignment(Pos.CENTER);


        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
