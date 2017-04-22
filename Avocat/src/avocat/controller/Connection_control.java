package avocat.controller;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by HaMzA on 07/02/2017.
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.time.Duration;
import java.util.*;

public class Connection_control {
    @FXML private Text res;

    @FXML private PasswordField psswd_field;
    @FXML private TextField login_field;
    @FXML private TextField nom_mod,prenom_mod,tel_mod,email_mod;
    @FXML private PasswordField mdp_mod;
    static String current_avc;
    final String DB_URL = "jdbc:mysql://localhost:3306/local_app";
    final String USER = "root";
    final String PASS = "";



    @FXML protected void Go(ActionEvent event) throws IOException {
        boolean flag=false;
        res.setText("STATUT : Chargement");
        res.setFill(Color.GREEN);

        String curr=psswd_field.getText();
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
        String query="select * from avocat where email='";
        query+=login_field.getText()+"';";

        ResultSet answer=null;
        try {
            answer = stamt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.print(login_field.getText());
        try {
            while(answer.next()){
                if(answer.getString("mdp").equals(curr)) {

                    current_avc=answer.getString("id_avocat");
                    res.setText("STATUT : Connecté");
                    res.setFill(Color.GREEN);

                    flag=true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    Platform.runLater(() -> {
                        Parent home_page_parent = null;
                        try {
                            home_page_parent = FXMLLoader.load(getClass().getResource("../view/Accueil.fxml"));
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
        else {
            res.setFill(Color.RED);
            res.setText("Email ou mot de passe incorrect!!");
        }
    }

    @FXML protected void creation (ActionEvent event){
        Parent home_page_parent = null;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("../view/Inscription.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}






