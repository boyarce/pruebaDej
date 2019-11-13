/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.controller;

import java.io.IOException;
import java.util.logging.Logger;
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
    private static final String USER_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "123456";
    private static final String USER = "dsy";
    private static final String PASSWORD = "123456";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("VALIDATE");
        response.setContentType("text/html;charset=UTF-8");
        try {

            String user = request.getParameter("user");
            String pass = request.getParameter("pass");

            if ((user == null || "".equals(user)) || (pass == null || "".equals(pass))) {
                LOGGER.info("LOGIN ERROR");
                String loginError = "Debe ingresar un usuario y una contraseña";
                HttpSession session = request.getSession();
                session.setAttribute("error", loginError);
                response.sendRedirect("login.jsp");
            } else {

                if (USER_ADMIN.equals(user) && PASSWORD_ADMIN.equals(pass)) {
                    LOGGER.info("LOGIN SUCCESS");
                    LOGGER.info("REDIREC TO HOME");
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("jsp/home.jsp");
                } else if (USER.equals(user) && PASSWORD.equals(pass)) {
                    LOGGER.info("LOGIN SUCCESS");
                    LOGGER.info("REDIREC TO HOME");                    
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
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
        } catch (Exception ex) {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
