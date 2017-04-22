package avocat.model;

/**
 * Created by HaMzA on 09/02/2017.
 */
import java.sql.*;
import java.lang.*;
import java.util.*;
import avocat.model.Data_Base;
import java.sql.*;
public class Avocat {
    private String id_avocat,nom,prenom,email,mdp,num_tel;

    public Avocat(String id_avocat, String nom, String prenom, String email, String mdp, String num_tel) {
        this.id_avocat = id_avocat;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.num_tel=num_tel;
    }
    public Avocat() {

    }

    public String getId_avocat() {
        return id_avocat;
    }

    public void setId_avocat(String id_avocat) {
        this.id_avocat = id_avocat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNum_tel() {
        return num_tel;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }
    public void insert_query()  {
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
        String s="insert into avocat values('";
        s+=this.getId_avocat()+"','"+this.getNom()+"','"+this.getPrenom()+"','"+this.getMdp()+"','"+this.getEmail()+"','"
                +this.getNum_tel()+"');";
        try {
            stamt.executeUpdate(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
