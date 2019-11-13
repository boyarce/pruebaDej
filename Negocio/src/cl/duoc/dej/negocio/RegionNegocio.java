/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.negocio;

import cl.duoc.dej.persistencia.dao.RegionDAO;
import cl.duoc.dej.persistencia.entity.RegionEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CETECOM
 */
public class RegionNegocio {
    
    private RegionDAO regionDAO;
    
    public RegionNegocio() throws SQLException{
    
        regionDAO = new RegionDAO();
    }
    
    public void actualizar(RegionEntity region){
    
        regionDAO.actualizar(region);
    }    
        
    
    public void borrar(int codigo){
    
        regionDAO.borrar(codigo);
    }    
    
    public void crear(RegionEntity region){
    
        regionDAO.crear(region);
    }
    
    public List<RegionEntity> buscarTodo(){
    
        return regionDAO.buscarTodo();
    }
}
