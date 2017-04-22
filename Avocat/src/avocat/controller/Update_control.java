package avocat.controller;

/**
 * Created by HaMzA on 25/02/2017.
 */


import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.sql.*;
import avocat.model.Data_Base;
import avocat.model.Avocat;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

public class Update_control{

    @FXML private TextField nom_mod,prenom_mod,tel_mod,email_mod;
    @FXML private PasswordField mdp_mod;
    static Connection_control cc=new Connection_control();
    final String DB_URL = "jdbc:mysql://localhost:3306/local_app";
    final String USER = "root";
    final String PASS = "";
    public Update_control() throws IOException {
    }

    @FXML protected void initialize(){
        String sql="select * from avocat where id_avocat='";
        sql+=cc.current_avc+"' ;";
        ResultSet ans=null;
        try {
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

            Statement st=null;
            try {
               
                st=conne.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ans=st.executeQuery(sql);
            while(ans.next()){
                nom_mod.setText(ans.getString("nom"));
                prenom_mod.setText(ans.getString("prenom"));
                mdp_mod.setText(ans.getString("mdp"));
                email_mod.setText(ans.getString("Email"));
                tel_mod.setText(ans.getString("num_tel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML protected void update_acc(ActionEvent event) throws IOException {


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





        String query="update avocat set nom='";
        query+=nom_mod.getText()+"',prenom='"+prenom_mod.getText()+"',mdp='"+mdp_mod.getText()+"',email='"+email_mod.getText()+"',num_tel='"+
                tel_mod.getText()+"' ";
        query+="where id_avocat='"+cc.current_avc+"';";
        //query+=login_field.getText()+"';";
        try {

            stamt.executeUpdate(query);
            System.out.println("Done"+cc.current_avc);
        } catch (SQLException e) {
            //System.out.print("Erreur");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText("compte : ");
        alert.setContentText("votre compte a été modifié");

        alert.showAndWait();
}
}
