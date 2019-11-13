/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.controller;

import cl.duoc.dej.negocio.RegionNegocio;
import cl.duoc.dej.persistencia.entity.RegionEntity;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ccv
 */
public class CreateRegionServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateRegionServlet.class.getName());
    private List<RegionEntity> regiones;
    private RegionNegocio regionNegocio;
    
    public CreateRegionServlet(){
        try{
            regionNegocio = new RegionNegocio();
        }catch(Exception ex){        
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void createRegion(HttpServletRequest request, 
            HttpServletResponse response, boolean isGet)
            throws ServletException, IOException {
        LOGGER.info("CREATE REGION");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        try {

            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String error = validateForm(id, nombre, descripcion);
            if(!isGet){                        
                if (!(error != null && !"".equals(error))) {
                    RegionEntity region = new RegionEntity();
                    region.setCodigo(Short.valueOf(id));
                    region.setNombre(nombre);
                    region.setDescripcion(descripcion);

                    regionNegocio.crear(region);
                    session.setAttribute("errorCreateRegion", error);
                } else {
                    session.setAttribute("errorCreateRegion", error);
                }
            }else{
                session.setAttribute("errorCreateRegion", "");
            }

            regiones = regionNegocio.buscarTodo();

            session.setAttribute("regiones", regiones);            
            response.sendRedirect("jsp/region-maintener.jsp");

        } catch (NumberFormatException ex) {
            session.setAttribute("errorCreateRegion", "ID debe ser númerico");
            response.sendRedirect("jsp/region-maintener.jsp");
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    public String validateForm(String id, String nombre, String descripcion) {

        String error = !(id != null && !"".equals(id)) ? "<p class='text-sm-left'>Debe ingresar ID</p>" : "";
        error = error + (!(nombre != null && !"".equals(nombre)) ? "<p class='text-sm-left'>Debe ingresar NOMBRE</p>" : "");
        error = error + (!(descripcion != null && !"".equals(descripcion)) ? "<p class='text-sm-left'>Debe ingresar DESCRIPCIÓN</p>" : "");

        return error;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        createRegion(request, response, true);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        createRegion(request, response, false);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
