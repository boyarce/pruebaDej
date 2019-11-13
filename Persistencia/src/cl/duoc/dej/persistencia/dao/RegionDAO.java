/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.persistencia.dao;

import cl.duoc.dej.persistencia.entity.RegionEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CETECOM
 */
public class RegionDAO extends UtilDAO {

    private static final Logger LOGGER = Logger.
            getLogger(RegionDAO.class.getName());

    public RegionDAO() throws SQLException {
        super();
    }

    public void actualizar(RegionEntity region) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("UPDATE REGION SET NOMBRE = ?,  DESCRIPCION = ? ").
                append(" WHERE CODIGO = ? ");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());
            ps.setString(1, region.getNombre());
            ps.setString(2, region.getDescripcion());
            ps.setInt(3, region.getCodigo());
            
            boolean ejecucion = ps.execute();

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void borrar(int codigo) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("DELETE FROM REGION WHERE CODIGO = ? ");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());
            ps.setInt(1, codigo);

            boolean ejecucion = ps.execute();

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void crear(RegionEntity region) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("INSERT INTO REGION (CODIGO, NOMBRE, DESCRIPCION) ").
                append(" VALUES (?, ?, ?) ");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());
            ps.setInt(1, region.getCodigo());
            ps.setString(2, region.getNombre());
            ps.setString(3, region.getDescripcion());

            boolean ejecucion = ps.execute();

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<RegionEntity> buscarTodo() {

        List<RegionEntity> regiones = new ArrayList<>();

        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT CODIGO, NOMBRE, DESCRIPCION ").
                append("FROM REGION ");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RegionEntity region = new RegionEntity(
                        rs.getInt("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"));

                regiones.add(region);
            }

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return regiones;
    }
}
