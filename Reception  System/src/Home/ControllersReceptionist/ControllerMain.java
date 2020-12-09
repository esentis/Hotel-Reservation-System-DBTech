package Home.ControllersReceptionist;

import Home.DbConnection;
import Home.Dwmatio;
import Home.Login.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {
    //menu
    public Button NKbutton=new Button();
    public Button updateButton=new Button();
    public Button deleteButton=new Button();
    public Button SEbutton=new Button();
    public Button MainButton=new Button();
    public Button SignOutButton=new Button();
    public  Label caption = new Label();
    public Button updateButton2=new Button();
    public Button ChangePassB=new Button();



    Connection con=DbConnection.getConnection();
    public PieChart pieChart;
    public BarChart barChart;



    XYChart.Series<Dwmatio,Integer> series=new XYChart.Series<>();
    ObservableList<PieChart.Data> oblist= FXCollections.observableArrayList();







    CallableStatement callstatement = null;




    public Label UsernameLabelV=new Label();


        public void SignOut() throws SQLException{
            String query="{call signoutstaff()}";
            callstatement=con.prepareCall(query);
            callstatement.execute();
            callstatement.close();

        }




    public void fillpiechart()throws SQLException{


        caption.setStyle("-fx-font: 24 arial;");
            String roomtype;

        String query="{call getgroupedreservedroomtypes()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();

        ResultSet rs=callstatement.getResultSet();

        while(rs.next()) {

            if (rs.getInt(1) == 2) {
                roomtype = "Δίκλινο";

            } else if (rs.getInt(1) == 3) {
                roomtype = "Τρίκλινο";

            }else{roomtype="Σουίτα";
                    }
            oblist.add(new PieChart.Data(roomtype,rs.getInt(2)));
        }
        pieChart.setData(oblist);


        for (final  PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int value=(int)data.getPieValue();
                    caption.setVisible(true);
                    caption.setText(value+ " Κράτησεις");



                }
            });}}

       public void fillbarChart() throws  SQLException{
        String query="{call getincomepermonth()}";
        callstatement=con.prepareCall(query);
        callstatement.execute();
        ResultSet rs=callstatement.getResultSet();
           DateFormatSymbols dfs=new DateFormatSymbols();
           String[] months= dfs.getMonths();
           String month="m";


        while (rs.next()){
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tm=rs.getDate(1).toString();
            LocalDate date=LocalDate.parse(tm,dtf);
            int monthValue=date.getMonthValue();
            switch (monthValue){
                case 1:month="Ιανουάριος";
                    break;
                case 2:month="Φεβρούαριος";
                    break;
                case 3:month="Μάρτιος";
                    break;
                case 4:month="Απρίλιος";
                    break;
                case 5:month="Μάιος";
                    break;
                case 6:month="Ιούνιος";
                    break;
                case 7:month="Ιούλιος";
                    break;
                case 8:month="Αύγουστος";
                    break;
                case 9:month="Σεπτέμβριος";
                    break;
                case 10:month="Οκτώβριος";
                    break;
                case 11:month="Νοέμβριος";
                    break;
                case 12:month="Δεκέμβριος";
                    break;

            }
            series.getData().add(new XYChart.Data(month,rs.getBigDecimal(2)));

        }
        barChart.setStyle(".default-color0.chart-bar { -fx-bar-fill:-fx-bar-fill: green");
        barChart.getData().add(series);

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

        try {
            caption.setVisible(false);
            fillpiechart();
            fillbarChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



}


