/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.controller;

import cl.duoc.dej.negocio.RegionNegocio;
import cl.duoc.dej.persistencia.entity.ComunaEntity;
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
public class CreateComunaServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateComunaServlet.class.getName());
    private List<ComunaEntity> comunas;
    private List<RegionEntity> regiones;
    private RegionNegocio regionNegocio;

    public CreateComunaServlet(){
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
    protected void createComuna(HttpServletRequest request,
            HttpServletResponse response, boolean isGet)
            throws ServletException, IOException {
        LOGGER.info("CREATE REGION");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        try {

            Object regionesSession = session.getAttribute("regiones");
            Object comunasSession = session.getAttribute("comunas");
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String idRegion = request.getParameter("cmb-regiones");

            String error = validateForm(id, nombre, descripcion, idRegion);

            if (!isGet) {
                if (!(error != null && !"".equals(error))) {

                    /*ComunaVO comuna = new ComunaVO();
                    comuna.setId(Short.valueOf(id));
                    comuna.setNombre(nombre);
                    comuna.setDescripcion(descripcion);*/

                    if (comunasSession != null && regionesSession != null) {

                        /*List<RegionEntity> regiones = (List<RegionVO>) regionesSession;
                        comunas = (List<ComunaEntity>) comunasSession;
                        RegionVO selectedRegion = regiones.stream().filter(
                                region -> region.getId() == Short.valueOf(idRegion)).findFirst().get();
                        comuna.setRegion(selectedRegion);*/

                    } else if (regionesSession != null) {
                        /*comunas = new ArrayList<>();
                        List<RegionVO> regiones = (List<RegionVO>) regionesSession;
                        RegionVO selectedRegion = regiones.stream().filter(
                                region -> region.getId() == Short.valueOf(idRegion)).findFirst().get();
                        comuna.setRegion(new RegionVO(selectedRegion));*/
                    }
                    session.setAttribute("errorCreateComuna", "");
                    //comunas.add(comuna);
                }
            } else {
                session.setAttribute("errorCreateComuna", error);
            }
            
            regiones = regionNegocio.buscarTodo();
            session.setAttribute("regiones", regiones);                
            session.setAttribute("comunas", comunas);
            response.sendRedirect("jsp/comuna-maintener.jsp");

        } catch (NumberFormatException ex) {
            session.setAttribute("errorCreateComuna", "ID debe ser númerico");
            response.sendRedirect("jsp/comuna-maintener.jsp");
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    public String validateForm(String id, String nombre, String descripcion, String idRegion) {

        String error = !(id != null && !"".equals(id)) ? "<p class='text-sm-left'>Debe ingresar ID</p>" : "";
        error = error + (!(nombre != null && !"".equals(nombre)) ? "<p class='text-sm-left'>Debe ingresar NOMBRE</p>" : "");
        error = error + (!(descripcion != null && !"".equals(descripcion)) ? "<p class='text-sm-left'>Debe ingresar DESCRIPCIÓN</p>" : "");
        error = error + (!(idRegion != null && !"".equals(idRegion)) ? "<p class='text-sm-left'>Debe ingresar REGIÓN</p>" : "");

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
        createComuna(request, response, true);
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
        createComuna(request, response, false);
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
