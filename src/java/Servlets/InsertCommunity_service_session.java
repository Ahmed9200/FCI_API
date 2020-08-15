/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Community_service_session;
import model.DB;

/**
 *
 * @author 20102
 */
@WebServlet(name = "InsertCommunity_service_session", urlPatterns = ("/InsertCommunity_service_session"))
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)// 1.5 MB
public class InsertCommunity_service_session extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        Connection con = DB.setConnection();
        String output = "";
        Community_service_session n = new Community_service_session();
        try {
//            n.setCss_id(Integer.parseInt(request.getParameter("css_id")));
            n.setCss_name_arabic(request.getParameter("css_name_arabic"));
            n.setCss_name_english(request.getParameter("css_name_english"));
            n.setCss_about(request.getParameter("css_about"));
            n.setCss_addedDate(request.getParameter("css_addedDate"));
            n.setCss_school_year(request.getParameter("css_school_year"));
            n.setCss_addedBy(Integer.parseInt(request.getParameter("css_addedBy")));
            n.setCss_description(request.getParameter("css_description"));

        } catch (Exception e) {
        }

        boolean tst = false;

        if (request.getParameter("op").equals("1")) {

            tst = n.add(con);

        } else if (request.getParameter("op").equals("2")) {
            n.setCss_id(Integer.parseInt(request.getParameter("css_id")));
            tst = n.update(con);

        }

        if (tst) {
            output += "{\"result\": [";
            output += "{";
            output += "\"text\":\"" + "success" + "\"";
            output += "},";
        } else {
            output += "{\"result\": [";
            output += "{";
            output += "\"text\":\"" + "error" + "\"";
            output += "},";
        }

        output = output.substring(0, output.length() - 1);
        output += "]}";
        response.getWriter().print(output);

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
