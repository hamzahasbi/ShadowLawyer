package avocat.controller;

/**
 * Created by HaMzA on 09/02/2017.
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import avocat.model.Avocat;
import avocat.model.Data_Base;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class Account_control {
    @FXML private TextField nom_field;
    @FXML private TextField prenom_field;
    @FXML private TextField email_field;
    @FXML private TextField tel_field;
    @FXML private TextField cin_field;
    @FXML private PasswordField mdp_field;
    @FXML private Text check_account;
    @FXML protected void Create_account(ActionEvent event){
        String a,b,c,d,i,f;

        a=nom_field.getText();
        b=prenom_field.getText();
        c=mdp_field.getText();
        d=email_field.getText();
        i=tel_field.getText();
        f=cin_field.getText();

        Avocat personne=new Avocat(f,a,b,d,c,i);
        final String DB_URL = "jdbc:mysql://localhost:3306/local_app";
        final String USER = "root";
        final String PASS = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conne = null;
        try {
            conne = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stamt=null;
        try {
            stamt = conne.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql="select avocat.nom from avocat where id_avocat='";
        sql+=f+"';";
        ResultSet answer=null;
        try {
             answer=stamt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(answer.next()){
                check_account.setFill(Color.RED);
                check_account.setFont(Font.font("Ubuntu",15));
                check_account.setText("CIN déja existant!!");

            }
            else{
                personne.insert_query();
                check_account.setFill(Color.GREEN);
                check_account.setText("Compte créé avec succes!");
                check_account.setFont(Font.font("Ubuntu",15));
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {

                        Platform.runLater(() -> {
                Parent home_page_parent = null;
                try {
                    home_page_parent = FXMLLoader.load(getClass().getResource("../view/connection.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                        });
                    }

                };

                timer.schedule(task, 1000);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
