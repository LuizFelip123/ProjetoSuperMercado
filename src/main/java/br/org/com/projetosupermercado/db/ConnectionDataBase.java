/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliente
 */
public class ConnectionDataBase {
    private static final String url ="jdbc:mysql://localhost:3306/supermercado?verifyServerCertificate=false&useSSL=true";
    private static final String password ="abc123";
    private static final String usuario ="root";
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private ConnectionDataBase(){}
   
    
    public static Connection getConnection(){
        try {
            try {
                Class.forName(jdbcDriver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
            }

            return DriverManager.getConnection(url,usuario , password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
