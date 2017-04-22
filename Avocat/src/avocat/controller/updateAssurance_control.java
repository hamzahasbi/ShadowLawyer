package avocat.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import avocat.model.Assurance;
import avocat.model.Data_Base;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class updateAssurance_control {
	@FXML
    private TextField type_doss;
    @FXML
    private TextField tribunal_num;
    @FXML
    private TextField nom;
    @FXML
    private TextField CIN;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField email;
    @FXML
    private TextField date_audience;
    @FXML
    private TextField nom_adv;
    
    @FXML
    private TextArea decision;
    @FXML
    private Button cancel;
    
    static Connection_control cc=new Connection_control();

    private Stage dialogStage;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() throws IOException {
        //Acceuil_control.utility();
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

        String query="select * from assurance where CIN='";
        query+=Assurance_control.row.getId_dossier()+"';";
        System.out.println(Assurance_control.row.getId_dossier());
        ResultSet answer=null;
        ResultSet ans=null;
        try {
            answer = stamt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
        	while(answer.next()){

        	
	        	type_doss.setText(""+answer.getString("type_doss"));
	        	tribunal_num.setText(answer.getString("tribunal_num"));
	        	nom.setText(answer.getString("nom"));
                CIN.setText(answer.getString("CIN"));
                num_tel.setText(answer.getString("num_tel"));
                email.setText(answer.getString("email"));
                date_audience.setText(answer.getString("date_audience"));
                nom_adv.setText(answer.getString("nom_adv"));
                decision.setText(answer.getString("decision"));

        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    
    
    
    @FXML
    private void handleOk() {
        System.out.println(cc.current_avc);
        
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
        String query="update assurance set CIN='";
        query+=CIN.getText()+"',nom='"+nom.getText()+"',num_tel='"+num_tel.getText()+"',email='"+
        		email.getText()+"',date_audience='"+date_audience.getText()+"',"
        		+ "decision='"+decision.getText()+ "',nom_adv='"+nom_adv.getText()+"' ";
        query+="where CIN='"+CIN.getText()+"' ;";

        System.out.println(query);
        try {
            stamt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //query+=login_field.getText()+"';";
        try {

            stamt.executeUpdate(query);
            System.out.println("Done"+cc.current_avc);
        } catch (SQLException e) {
            //System.out.print("Erreur");
        }
        
       
        Stage stage = (Stage) cancel.getScene().getWindow();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText("Modification : ");
        alert.setContentText("APPUYEZ SUR LA TOUCHE F5 POUR ACTUALISER!");

        alert.showAndWait();
     	stage.close();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        System.out.println("cancel");
        Stage stage = (Stage) cancel.getScene().getWindow();
     	stage.close();
//        dialogStage.close();
    }
}
