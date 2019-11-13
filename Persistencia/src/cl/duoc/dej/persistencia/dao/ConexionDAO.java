/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.persistencia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CETECOM
 */
public class ConexionDAO {

    private static Connection conn;
    String url = "jdbc:oracle:thin:@localhost:1521/XE";
    String usuario = "EJERCICIO_BD";
    String password = "EJERCICIO_BD";

    private ConexionDAO() throws SQLException {
        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        conn = DriverManager.getConnection(url, usuario, password);
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            new ConexionDAO();
        }
        return conn;
    }

}
