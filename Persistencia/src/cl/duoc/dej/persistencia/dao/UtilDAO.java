/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author CETECOM
 */
public class UtilDAO {

    private Connection conexion;

    public UtilDAO(Connection conexion) throws SQLException {
        this.conexion = conexion;
    }

    public UtilDAO() throws SQLException {
        this.conexion = ConexionDAO.getConnection();        
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
