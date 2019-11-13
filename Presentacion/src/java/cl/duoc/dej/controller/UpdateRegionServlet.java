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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ccv
 */
public class UpdateRegionServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateRegionServlet.class.getName());
    private List<RegionEntity> regiones;
    private RegionNegocio regionNegocio;

    public UpdateRegionServlet(){
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
    protected void updateRegion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("UPDATE REGION");
        response.setContentType("text/html;charset=UTF-8");
        try {

            HttpSession session = request.getSession();
            Object regionesSession = session.getAttribute("regiones");
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");

            //update region
            if (regionesSession != null && id != null) {
                
                int codigo = Integer.parseInt(id);
                                
                RegionEntity region = new RegionEntity(codigo, nombre, descripcion);
                regionNegocio.actualizar(region);
                
            }
            regiones = regionNegocio.buscarTodo();
            session.setAttribute("regiones", regiones);
            response.sendRedirect("jsp/region-maintener.jsp");

        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }
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
        updateRegion(request, response);
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
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/error/error-404.jsp");
        rd.forward(request, response);
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
