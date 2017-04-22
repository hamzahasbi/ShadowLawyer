package avocat.model;

/**
 * Created by HaMzA on 11/02/2017.
 */

import java.sql.*;
import java.util.*;
import java.lang.*;
public class Data_Base {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/local_app";
    static final String DB_server = "jdbc:mysql://localhost:3306";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    static Connection conn = null;
    static Statement stmt = null;
    public Data_Base() {


        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //STEP 3: Open a connection
            System.out.println("Connecting to server...");
            conn = DriverManager.getConnection(DB_server, USER, PASS);
            System.out.println("Connected...");
            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS local_app";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            /// table avocat
            sql = "CREATE TABLE IF NOT EXISTS avocat(id_avocat VARCHAR(8) NOT NULL ," +
                    "   nom VARCHAR(100) NOT NULL," +
                    " prenom VARCHAR(100) NOT NULL, " +
                    " mdp VARCHAR(100) NOT NULL, " +
                    " email VARCHAR(100) NOT NULL," +
                    "num_tel VARCHAR(100) NOT NULL," +
                    "   PRIMARY KEY (id_avocat));";

            stmt.executeUpdate(sql);
            System.out.println("Table created successfully....");
            ///table dossier


            sql = "CREATE TABLE IF NOT EXISTS dossier(id_dossier INT NOT NULL AUTO_INCREMENT," +
                    " tribunal_num VARCHAR(100) NOT NULL," +
                    " type_doss VARCHAR (100) NOT NULL ,"+
                    " nom_adv VARCHAR(100) NOT NULL, " +
                    " date_audience VARCHAR(100) NOT NULL, " +
                    "decision VARCHAR(1000) NOT NULL," +
                    " responsable VARCHAR(100) NOT NULL," +
                    " proprio VARCHAR(100) NOT NULL," +
                    "   PRIMARY KEY (id_dossier));";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully....");

            ///table client


            sql = "CREATE TABLE IF NOT EXISTS client(id_client VARCHAR(8) NOT NULL ," +
                    "   nom VARCHAR(100) NOT NULL," +
                    " prenom VARCHAR(100) NOT NULL, " +
                    " email VARCHAR(100) ," +
                    " profession VARCHAR (100) ,"+
                    "num_tel VARCHAR(100) NOT NULL," +
                    "file INT NOT NULL," +
                    "   PRIMARY KEY (id_client));";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully....");

            ///table Calendrier


            sql = "CREATE TABLE IF NOT EXISTS calendrier(id_calendrier INT NOT NULL AUTO_INCREMENT," +
                    " startLocalDateTime VARCHAR(100) NOT NULL," +
                    "endLocalDateTime VARCHAR(100) NOT NULL," +
                    "summary VARCHAR(1000) NOT NULL," +
                    "description VARCHAR(1000) NOT NULL," +
                    "StyleClass VARCHAR(1000) NOT NULL,"+
                    "   PRIMARY KEY (id_calendrier));";
            stmt.executeUpdate(sql);
            System.out.println("Table Calendrier created successfully....");
            
            // table Assurance
            sql = "CREATE TABLE IF NOT EXISTS assurance(CIN VARCHAR(20) NOT NULL ," +
            		" type_doss VARCHAR(100) ," +
            		" tribunal_num VARCHAR(100) ," +
                    " nom VARCHAR(100) NOT NULL," +
                    " date_audience VARCHAR(100) NOT NULL, " +
                    " email VARCHAR(100) ," +
                    " nom_adv VARCHAR (100) ,"+
                    " decision VARCHAR (1000) ,"+
                    "num_tel VARCHAR(100) NOT NULL," +
                    "   PRIMARY KEY (CIN));";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully....");
            
            /// ADD contraints




           /* sql = "ALTER TABLE dossier add FOREIGN KEY (responsable) REFERENCES avocat(id_avocat)ON DELETE NO ACTION " +
                    "ON UPDATE NO ACTION;";
            stmt.executeUpdate(sql);

            sql = "ALTER TABLE dossier add FOREIGN KEY (proprio) REFERENCES client(id_client)ON DELETE NO ACTION " +
                    "ON UPDATE NO ACTION;";
            stmt.executeUpdate(sql);

            sql = "ALTER TABLE client add FOREIGN KEY (file) REFERENCES dossier(id_dossier)ON DELETE NO ACTION " +
                    "ON UPDATE NO ACTION;";
            stmt.executeUpdate(sql);
*/

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }
    //    (startLocalDateTime,endLocalDateTime,summary,description,StyleClass)
    public static void inserer(String table, Calendrier calendrier) {
        try{
            String sql = "INSERT INTO "+table+
                    " (startLocalDateTime,endLocalDateTime,summary,description,StyleClass) VALUES ('"+
                    calendrier.getStartLocalDateTime()+"','"+
                    calendrier.getEndLocalDateTime()+"','"+
                    calendrier.getSummary()+"','"+
                    calendrier.getDescription()+"','"+
                    calendrier.getStyleClass()+
                    "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
    // insert in assurance
    
    public static void inserer(String table, Assurance assurance) {
        try{
            String sql = "INSERT INTO "+table+
                    " (CIN,type_doss,tribunal_num,nom,num_tel,email,date_audience,nom_adv,decision) VALUES ('"+
                    assurance.getId_dossier()+"','"+
                    assurance.getType_doss()+"','"+
                    assurance.getTribunal_num()+"','"+
                    assurance.getNom()+"','"+
                    assurance.getNum_tel()+"','"+
                    assurance.getEmail()+"','"+
                    assurance.getDate_audience()+"','"+
                    assurance.getNom_adv()+"','"+
                    assurance.getDecision()+
                    "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public static void delete(String table, Calendrier calendrier) {
        try{
            String sql = "delete from "+ table+" where startLocalDateTime='"+
                    calendrier.getStartLocalDateTime()+"' AND endLocalDateTime='"+
                    calendrier.getEndLocalDateTime()+"' AND summary='"+
                    calendrier.getSummary()+"' AND description='"+
                    calendrier.getDescription()+"' AND StyleClass='"+
                    calendrier.getStyleClass()+
                    "';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(SQLException se) {
            se.printStackTrace();
        }
    }
    public static  ArrayList<Calendrier> selectAll(String table) {
        ArrayList<Calendrier> l = new ArrayList<Calendrier>();
        String sql = "select * from "+table;
        try{
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                Calendrier h = new Calendrier(
                        Integer.parseInt(res.getString("id_calendrier")),
                        res.getString("startLocalDateTime"),
                        res.getString("endLocalDateTime"),
                        res.getString("summary"),
                        res.getString("description"),
                        res.getString("StyleClass"));
                l.add(h);
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }

        return l;
    }
}