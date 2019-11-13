/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.persistencia.dao;

import cl.duoc.dej.persistencia.entity.ComunaEntity;
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
public class ComunaDAO extends UtilDAO {

    private static final Logger LOGGER = Logger.
            getLogger(ComunaDAO.class.getName());

    public ComunaDAO() throws SQLException {
        super();
    }

    public void actualizar(ComunaEntity comuna) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("UPDATE COMUNA SET NOMBRE = ?,  DESCRIPCION = ?, CODIGO_REGION = ? ").
                append(" WHERE CODIGO = ? ");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());
            ps.setString(1, comuna.getNombre());
            ps.setString(2, comuna.getDescripcion());
            ps.setInt(3, comuna.getRegion().getCodigo());
            ps.setInt(4, comuna.getCodigo());            
            
            boolean ejecucion = ps.execute();

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void borrar(int codigo) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("DELETE FROM COMUNA WHERE CODIGO = ? ");

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

    public void crear(ComunaEntity comuna) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("INSERT INTO COMUNA (CODIGO, NOMBRE, DESCRIPCION, CODIGO_REGION) ").
                append(" VALUES (?, ?, ?, ?) ");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());
            ps.setInt(1, comuna.getCodigo());
            ps.setString(2, comuna.getNombre());
            ps.setString(3, comuna.getDescripcion());
            ps.setInt(4, comuna.getRegion().getCodigo());

            boolean ejecucion = ps.execute();

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<ComunaEntity> buscarTodo() {

        List<ComunaEntity> comunas = new ArrayList<>();

        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT C.CODIGO, C.NOMBRE, C.DESCRIPCION, R.CODIGO_REGION ").
                append(" R.NOMBRE, R.DESCRIPCION").                
                append(" FROM COMUNA C").
                append(" JOIN REGION R ON R.CODIGO = C.CODIGO_REGION");

        LOGGER.info("EJECUCIÓN -> " + consulta.toString());

        try {
            PreparedStatement ps = getConexion().
                    prepareStatement(consulta.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RegionEntity region = new RegionEntity(
                        rs.getInt("R.CODIGO_REGION"),
                        rs.getString("R.NOMBRE"),
                        rs.getString("R.DESCRIPCION"));

                ComunaEntity comuna = new ComunaEntity(
                rs.getInt("C.CODIGO"), rs.getString("C.NOMBRE"), 
                        rs.getString("C.DESCRIPCION"), region);                
                comunas.add(comuna);
            }

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return comunas;
    }
}
