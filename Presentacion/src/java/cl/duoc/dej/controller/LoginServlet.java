/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.controller;

import cl.duoc.dej.negocio.LoginNegocio;
import cl.duoc.dej.persistencia.entity.UsuarioEntity;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author CETECOM
 */
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private LoginNegocio loginNegocio;

    public LoginServlet(){
        try {
            loginNegocio = new LoginNegocio();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Autentication requests for both HTTP <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void autentication(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("VALIDATE");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            
            LOGGER.info("USER -> " + user);
            LOGGER.info("PASS -> " + pass);
            

            if ((user == null || "".equals(user)) || (pass == null || "".equals(pass))) {

                LOGGER.info("LOGIN ERROR");

                String loginError = "Debe ingresar un usuario y una contraseña";
                HttpSession session = request.getSession();
                session.setAttribute("error", loginError);
                LOGGER.info("REDIRECT TO LOGIN");
                response.sendRedirect("login.jsp");
            } else {
                UsuarioEntity usuario = loginNegocio.
                        autenticacionEntity(user, pass);
                
                if (usuario != null){
                    LOGGER.info("LOGIN SUCCESS");
                    LOGGER.info("REDIRECT TO HOME");
                    HttpSession session = request.getSession();
                    session.setAttribute("user", usuario.
                            getNombreUsuario());
                    response.sendRedirect("jsp/home.jsp");
                } else {
                    LOGGER.info("LOGIN ERROR");

                    String loginError = "Usuario o contraseña incorrectos.";
                    HttpSession session = request.getSession(false);
                    session.setAttribute("error", loginError);
                    LOGGER.info("REDIRECT TO LOGIN");
                    response.sendRedirect("login.jsp");
                }
            }
        } catch (IOException ex) {
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
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/error/error-404.jsp");
        rd.forward(request, response);
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
        autentication(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for  sum calculate";
    }// </editor-fold>

}
