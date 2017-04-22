package avocat.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import avocat.model.Assurance;
import avocat.model.Client;
import avocat.model.Dossier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Assurance_control {
        @FXML
        TextField search;
        @FXML
        TableColumn nom_assur,cin_resp,email;
        @FXML
        TableView table;
        
        Assurance temp;
    	Date lastClickTime;
    	static Assurance row;
    	@FXML protected void delete(KeyEvent keyEvent ){
        	if(keyEvent.getCode().equals(KeyCode.F5) ){
        		initialize();
        	}
        	row  = (Assurance)table.getSelectionModel().getSelectedItem();
            if(keyEvent.getCode().equals( KeyCode.DELETE )) {
                final String DB_URL = "jdbc:mysql://localhost:3306/local_app";
                final String USER = "root";
                final String PASS = "";
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("connection.fxml"));
                //Parent root = (Parent) loader.load();
                //cc=(Connection_control) loader.getController();
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
                Statement st=null;
                try {
                    stamt = conne.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String req="delete from assurance where CIN='";
                req+=row.getId_dossier()+"' ;";
                
                ResultSet res=null;
                try {
                    stamt.executeUpdate(req);
                } catch (SQLException e) {
                    e.printStackTrace();
                }                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Information");
                alert.setHeaderText("suppression : ");
                alert.setContentText("le champ sélectionner été supprimée avec succès");

                alert.showAndWait();
                
            }
        }

    	@FXML
    	private int doubleClick() throws IOException{
    		row = (Assurance)table.getSelectionModel().getSelectedItem();
    	    if (row==null) return 0;
    	    if(row!=temp){
    	        temp=row;
    	        lastClickTime=new Date();
    	    } else if(row==temp) {
    	        Date now = new Date();
    	        long diff = now.getTime() - lastClickTime.getTime();
    	        if (diff < 300){ 
    	             System.out.println("Edit dialog");

    	             FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/updateAssurance.fxml"));
    	             AnchorPane page = (AnchorPane) loader.load();
    	             Stage dialogStage = new Stage();
    	             dialogStage.setTitle("Edit Person");
    	             dialogStage.initModality(Modality.WINDOW_MODAL);
//    	     	    Window primaryStage;
//    	     		dialogStage.initOwner(primaryStage);
    	             Scene scene = new Scene(page);
    	             dialogStage.setScene(scene);
    	             dialogStage.showAndWait();
    	            

    	        } else {
    	            lastClickTime = new Date();
    	        }
    	    }
    	    return 0;
    	}	
    	
	public void addAssurance() throws IOException{
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/newAssurance.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Assurance");
        dialogStage.initModality(Modality.WINDOW_MODAL);
//	    Window primaryStage;
//		dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

    }


        public ObservableList<Assurance> utility(){
                ObservableList<Assurance> temp= FXCollections.observableArrayList();
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
                String query="select * from assurance ;";
                String req="select * from assurance where nom='";
                req+=search.getText()+"';";
                ResultSet answer=null;
                try {
                        if(!search.getText().isEmpty()) answer=stamt.executeQuery(req);
                        else answer = stamt.executeQuery(query);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                //System.out.print(login_field.getText());
                String te="null";
                try {
                        while(answer.next()){
                                te=answer.getString("CIN");
                                Assurance cl=new Assurance(answer.getString("CIN"),answer.getString("type_doss"),
                                        answer.getString("tribunal_num"),answer.getString("nom"),
                                        answer.getString("num_tel"),answer.getString("email"),
                                        answer.getString("date_audience"),answer.getString("nom_adv"),
                                        answer.getString("decision"));
                                temp.add(cl);
                        }
                }
                catch (Exception e) {
                        System.out.println(te);
                }
                return temp;
        }
        public void initialize(){
        	cin_resp.setCellValueFactory(new PropertyValueFactory<>("id_dossier"));
            nom_assur.setCellValueFactory(new PropertyValueFactory<>("nom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));


            table.setItems(utility());
        }
        @FXML protected void select_cl(ActionEvent event){
                cin_resp.setCellValueFactory(new PropertyValueFactory<>("id_dossier"));
                nom_assur.setCellValueFactory(new PropertyValueFactory<>("nom"));
                email.setCellValueFactory(new PropertyValueFactory<>("email"));


                table.setItems(utility());
                //table.getColumns().addAll(client_cin,client_nom,client_prenom,client_tel,client_email,client_profession);
        }
}
