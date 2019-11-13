/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.negocio;

import cl.duoc.dej.persistencia.dao.ComunaDAO;
import cl.duoc.dej.persistencia.entity.ComunaEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CETECOM
 */
public class ComunaNegocio {
    
    private ComunaDAO comunaDAO;
    
    public ComunaNegocio() throws SQLException{
    
        comunaDAO = new ComunaDAO();
    }
    
    public void actualizar(ComunaEntity comuna){
    
        comunaDAO.actualizar(comuna);
    }    
        
    
    public void borrar(int codigo){
    
        comunaDAO.borrar(codigo);
    }    
    
    public void crear(ComunaEntity comuna){
    
        comunaDAO.crear(comuna);
    }
    
    public List<ComunaEntity> buscarTodo(){
    
        return comunaDAO.buscarTodo();
    }
}
