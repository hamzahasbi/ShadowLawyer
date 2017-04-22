package avocat.controller;

import avocat.model.Client;
import avocat.model.Dossier;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

/**
 * Created by HaMzA on 01/03/2017.
 */
public class Acceuil_control {
	
	Dossier temp;
	Date lastClickTime;
	static Dossier row;

	@FXML
	private void doubleClick() throws IOException{
		
		row  = (Dossier)table.getSelectionModel().getSelectedItem();
	    if (row==null) return;
	    if(row!=temp){
	        temp=row;
	        lastClickTime=new Date();
	    } else if(row==temp) {
	        Date now = new Date();
	        long diff = now.getTime() - lastClickTime.getTime();
	        if (diff < 300){ 
	             System.out.println("Edit dialog");
	             FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/updateClient.fxml"));
	             AnchorPane page = (AnchorPane) loader.load();
	             Stage dialogStage = new Stage();
	             dialogStage.setTitle("Edit Person");
	             dialogStage.initModality(Modality.WINDOW_MODAL);
//	     	    Window primaryStage;
//	     		dialogStage.initOwner(primaryStage);
	             Scene scene = new Scene(page);
	             dialogStage.setScene(scene);
	             dialogStage.showAndWait();

	        } else {
	            lastClickTime = new Date();
	        }
	    }
	}
    @FXML 
    protected void delete(KeyEvent keyEvent ){
    	if(keyEvent.getCode().equals(KeyCode.F5) ){
    		initialize();
    	}
    		
            
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
            try {
                stamt = conne.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sql="delete from dossier where id_dossier=";
            sql+=row.getId_dossier()+" ;";

            try {
                stamt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("suppression : ");
            alert.setContentText("le champ sélectionné a été supprimée avec succès");

            alert.showAndWait();
        }
    }
	@FXML
    public TableView table;
    @FXML
    TableColumn num,num_trib,trib,cin_cl,date,decision;



    public ObservableList<Dossier> utility(){
        ObservableList<Dossier> temp= FXCollections.observableArrayList();
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
        String query="select * from dossier ;";

        ResultSet answer=null;
        try {
            answer = stamt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String te="null";
        try {
            while(answer.next()){
                te=Integer.toString(answer.getInt("id_dossier"));
                    Dossier d=new Dossier(answer.getInt("id_dossier"),answer.getString("tribunal_num"),
                        answer.getString("type_doss"),answer.getString("nom_adv"),
                        answer.getString("decision"),answer.getString("responsable"),
                        answer.getString("proprio"),answer.getString("date_audience"));
                temp.add(d);
            }
        }
     catch (Exception e) {
         
    }
    return temp;
}

    @FXML protected void initialize(){
        num.setCellValueFactory(new PropertyValueFactory<>("id_dossier"));
        num_trib.setCellValueFactory(new PropertyValueFactory<>("tribunal_num"));
        trib.setCellValueFactory(new PropertyValueFactory<>("type_doss"));
        cin_cl.setCellValueFactory(new PropertyValueFactory<>("proprio"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_audience"));
        decision.setCellValueFactory(new PropertyValueFactory<>("decision"));

        table.setItems(utility());
        //table.getColumns().addAll(client_cin,client_nom,client_prenom,client_tel,client_email,client_profession);
    }
}
