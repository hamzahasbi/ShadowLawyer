package avocat.controller;

/**
 * Created by HaMzA on 28/02/2017.
 */


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.sun.corba.se.pept.transport.EventHandler;

import avocat.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.util.Date;
import javafx.scene.input.KeyEvent;
public class Clients_control {

	
    @FXML
    TableView table;
    @FXML
    TableColumn client_cin,client_nom,client_prenom,client_email,client_profession,client_tel;
    @FXML
    ComboBox choice;
    @FXML
    TextField search;

    @FXML protected void delete(KeyEvent keyEvent ){
    	if(keyEvent.getCode().equals(KeyCode.F5) ){
    		initialize();
    	}
        Client row  = (Client)table.getSelectionModel().getSelectedItem();
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
                st=conne.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String req="select id_dossier from dossier where proprio='";
            req+=row.getId_client()+"' ;";
            ResultSet res=null;
            try {
                res=stamt.executeQuery(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ArrayList<String> requete=new ArrayList<String>();
            try {
                while(res.next()){
                    String a="delete from dossier where id_dossier=";
                    a+=res.getInt("id_dossier")+" ;";
                    requete.add(a);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for(String m:requete){
                try {
                    st.executeUpdate(m);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            String sql="delete from client where id_client='";
            sql+=row.getId_client()+"' ;";
            try {
                stamt = conne.createStatement();
                stamt.executeUpdate(sql);
            }
            catch(Exception e){
                e.printStackTrace();
            }
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
    public void addClients() throws IOException{
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/newClient.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
//	    Window primaryStage;
//		dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

    }


    public void initialize(){
        choice.getItems().addAll("Aucun","Tribunal","Nom du client","Numéro de dossier en tribunal");
        choice.getSelectionModel().selectFirst();
        
        client_cin.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        client_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        client_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        client_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        client_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        client_profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        System.out.println(search.getText()+" "+choice.getValue());
        table.setItems(utility());
    }
    public ObservableList<Client> utility(){

        ObservableList<Client> temp= FXCollections.observableArrayList();
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
        String a,b,c,d,f,g,h;
        ArrayList<String> requete=new ArrayList<String>();
        String query="select * from client ;";
        a="select proprio from dossier where type_doss='";
        a+=search.getText()+"';";
        b="select * from client where nom='";
        b+=search.getText()+"';";
        c="select proprio from dossier where tribunal_num='";
        c+=search.getText()+"';";
        ResultSet ans=null;
        ResultSet answer=null;
        try {
            if(search.getText().isEmpty())   requete.add(query);
            else if (choice.getValue().equals("Aucun"))   requete.add(query);

            else{
                if(!search.getText().isEmpty()){
                    if(choice.getValue().equals("Tribunal")) {
                        ans=stamt.executeQuery(a);
                        while(ans.next()){
                            f="select * from client where id_client='";
                            f+=ans.getString("proprio")+"';";
                            requete.add(f);
                        }

                    }
                    else if(choice.getValue().equals("Nom du client")) {
                      requete.add(b);

                    }
                    else if(choice.getValue().equals("Numéro de dossier en tribunal")) {
                        ans=stamt.executeQuery(c);
                        while(ans.next()){
                            f="select * from client where id_client='";
                            f+=ans.getString("proprio")+"';";
                            requete.add(f);
                        }

                    }
                    else requete.add(query);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.print(login_field.getText());
        String te="null";
        try {
           for(String sql:requete){
               answer=stamt.executeQuery(sql);
               while(answer.next()){
                   te=answer.getString("id_client");
                   Client cl=new Client(answer.getString("id_client"),answer.getString("nom"),
                           answer.getString("prenom"),answer.getString("num_tel"),
                           answer.getString("email"),answer.getString("profession"),
                           answer.getInt("file"));

                   temp.add(cl);
               }
           }
        }
        catch (Exception e) {
            System.out.println(te);
        }
        return temp;
    }


    @FXML protected void Go_search(ActionEvent event){
        client_cin.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        client_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        client_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        client_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        client_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        client_profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        System.out.println(search.getText()+" "+choice.getValue());
        table.setItems(utility());
    }
}