/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author etudiant
 */
public class Connexion {
    public Connection seConnecter() throws ClassNotFoundException {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/maintenance";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch(Exception e) {
        e.printStackTrace();
        }
        return conn;
    }
}
