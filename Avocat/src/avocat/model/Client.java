package avocat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by HaMzA on 09/02/2017.
 */
public class Client {
    private String id_client,nom,prenom,num_tel,email,profession;
    private int file;

    public Client(String id_client, String nom, String prenom, String num_tel, String email,String profession, int file) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.email = email;
        this.file = file;
        this.profession=profession;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
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

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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
        String s="insert into client values ('";
        s+=this.getId_client()+"','"+this.getNom()+"','"+this.getPrenom()+"','"+this.getEmail()+"','"+this.getProfession()+"','"+
                this.getNum_tel()+"'," + Integer.toString(this.getFile())+");";
        System.out.println("requette " + s);
        try {
            stamt.executeUpdate(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
