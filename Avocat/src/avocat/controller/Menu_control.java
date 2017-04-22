package avocat.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.time.Duration;
import java.util.*;

import avocat.view.Calendar;


public class Menu_control {
	@FXML private HBox test;
	
	@FXML 
    protected void disconnection(ActionEvent event) throws IOException {
        // temp

        Parent home_page_parent = null;
        home_page_parent = FXMLLoader.load(getClass().getResource("../view/connection.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
        //		System.out.println("Working Directory = " + System.getProperty("user.dir"))
    }
    @FXML 
    protected void GoAccount(ActionEvent event) throws IOException {
        // temp

        Parent home_page_parent = null;
        home_page_parent = FXMLLoader.load(getClass().getResource("../view/Account.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
        //		System.out.println("Working Directory = " + System.getProperty("user.dir"))
    }
    @FXML protected void GoAccueil(ActionEvent event) throws IOException {

    	Parent home_page_parent = null;
    	home_page_parent = FXMLLoader.load(getClass().getResource("../view/Accueil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
    @FXML protected void GoAssurance(ActionEvent event) throws IOException {
    	Parent home_page_parent = null;
    	home_page_parent = FXMLLoader.load(getClass().getResource("../view/Assurance.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML protected void GoCalendar(ActionEvent event) throws IOException {

    	Parent home_page_parent = null;
    	home_page_parent = FXMLLoader.load(getClass().getResource("../view/Calendar.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML protected void GoClients(ActionEvent event) throws IOException {

    	Parent home_page_parent = null;
    	home_page_parent = FXMLLoader.load(getClass().getResource("../view/Clients.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();



    }
    @FXML protected void GoMenu(ActionEvent event) throws IOException {

        Parent home_page_parent = null;
        home_page_parent = FXMLLoader.load(getClass().getResource("../view/Help.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}

