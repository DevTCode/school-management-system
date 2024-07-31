/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_ecole;
import java.sql.*;
/**
 *
 * @author hp
 */
public class Config {
    final static String USER="root";
       final static String URL="jdbc:mysql://localhost:3306/gest_notes";
       final static String PASSWORD="";
   public static Connection getConnection() {
       Connection cnx = null;
       try{
       Class.forName("com.mysql.cj.jdbc.Driver");
       cnx = DriverManager.getConnection(URL,USER,PASSWORD);
       System.out.println("connexion etablie");
       }catch(ClassNotFoundException |SQLException e){
           System.out.println(e.getMessage());
       }
       return cnx;
}
}
