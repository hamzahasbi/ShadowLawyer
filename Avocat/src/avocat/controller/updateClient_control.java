package avocat.controller;

import avocat.model.Assurance;
import avocat.model.Client;
import avocat.model.Data_Base;
import avocat.model.Dossier;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class updateClient_control {
    @FXML
    private Label id_dossier;

    @FXML
    private TextField type_doss;
    @FXML
    private TextField tribunal_num;
    @FXML
    private TextField nom;
    @FXML
    private TextField CIN,profession;
    @FXML
    private TextField num_tel,prenom;
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
        Statement st=null;
        try {
            stamt = conne.createStatement();
            st=conne.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query="select * from dossier where id_dossier='";
        query+=Acceuil_control.row.getId_dossier()+"';";
        System.out.println(Acceuil_control.row.getId_dossier());
        ResultSet answer=null;
        ResultSet ans=null;
        try {
            answer = stamt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
        while(answer.next()){


            id_dossier.setText(""+answer.getInt("id_dossier"));
                type_doss.setText(answer.getString("type_doss"));
                tribunal_num.setText(answer.getString("tribunal_num"));
                CIN.setText(answer.getString("proprio"));
                date_audience.setText(answer.getString("date_audience"));
                nom_adv.setText(answer.getString("nom_adv"));
                decision.setText(answer.getString("decision"));
                String req="select nom,prenom,email,num_tel,profession from client where id_client='";
                req+=answer.getString("proprio")+"';";

                ans=st.executeQuery(req);
                while (ans.next()){
                    nom.setText(ans.getString("nom"));
                    prenom.setText(ans.getString("prenom"));
                    num_tel.setText(ans.getString("num_tel"));
                    email.setText(ans.getString("email"));
                    profession.setText(ans.getString("profession"));
                }
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
        Statement st=null;
        try {
            stamt = conne.createStatement();
            st=conne.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query="update dossier set tribunal_num='";
        query+=tribunal_num.getText()+"',type_doss='"+type_doss.getText()+"',nom_adv='"+nom_adv.getText()+"',date_audience='"+
                date_audience.getText()+"',decision='"+decision.getText()+"',proprio='"+CIN.getText()+"' ";
        query+="where id_dossier="+id_dossier.getText()+" ;";

        System.out.println(query);
        String req="update client set nom='";
        req+=nom.getText()+"',prenom='"+prenom.getText()+"',email='"+email.getText()+"',profession='"+profession.getText()+
                "',num_tel='"+num_tel.getText()+"',id_client='"+CIN.getText()+"' ";
        req+="where file="+id_dossier.getText()+" ;";
        System.out.println(req);
        try {
            stamt.executeUpdate(query);
            st.executeUpdate(req);
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
        alert.setContentText("APPUYEZ sur  F5 pour actualiser!");

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