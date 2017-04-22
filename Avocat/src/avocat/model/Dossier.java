package avocat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by HaMzA on 09/02/2017.
 */
public class Dossier {

    private int id_dossier;
    private String tribunal_num,nom_adv,decision,responsable,proprio,date_audience,type_doss;


    public Dossier(int id_dossier, String tribunal_num, String type_doss, String nom_adv, String decision, String responsable, String proprio, String date_audience) {
        this.id_dossier = id_dossier;
        this.tribunal_num = tribunal_num;
        this.nom_adv = nom_adv;
        this.decision = decision;
        this.responsable = responsable;
        this.proprio = proprio;
        this.date_audience = date_audience;
        this.type_doss=type_doss;
    }

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public String getTribunal_num() {
        return tribunal_num;
    }

    public void setTribunal_num(String tribunal_num) {
        this.tribunal_num = tribunal_num;
    }

    public String getNom_adv() {
        return nom_adv;
    }

    public void setNom_adv(String nom_adv) {
        this.nom_adv = nom_adv;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getProprio() {
        return proprio;
    }

    public void setProprio(String proprio) {
        this.proprio = proprio;
    }

    public String getDate_audience() {
        return date_audience;
    }

    public void setDate_audience(String  date_audience) {
        this.date_audience = date_audience;
    }

    public String getType_doss() {
        return type_doss;
    }

    public void setType_doss(String type_doss) {
        this.type_doss = type_doss;
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
         
        String s="insert into dossier (tribunal_num,type_doss,nom_adv,date_audience,decision,responsable,proprio) values ('";
        s+=this.getTribunal_num()+"','"+this.getType_doss()+"','"+this.getNom_adv()+"','"+this.getDate_audience()+"','"+this.getDecision()+"','"
                +this.getResponsable()+"','"+this.getProprio()+"');";
        System.out.println("requette " + s);
        try {
            stamt.executeUpdate(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
