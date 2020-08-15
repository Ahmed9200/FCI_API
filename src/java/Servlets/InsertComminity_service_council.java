/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Community_service_council;
import model.DB;

/**
 *
 * @author 20102
 */
@WebServlet(name = "InsertComminity_service_council", urlPatterns = ("/InsertComminity_service_council"))
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)// 1.5 MB
public class InsertComminity_service_council extends HttpServlet {

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
        Community_service_council n = new Community_service_council();
        try {
//            n.setCsc_id(Integer.parseInt(request.getParameter("csc_id")));
            n.setCsc_prof_id(Integer.parseInt(request.getParameter("csc_prof_id")));
            n.setCsc_prof_state(request.getParameter("csc_prof_state"));
            n.setCsc_addedBy(Integer.parseInt(request.getParameter("csc_addedBy")));
            n.setName(request.getParameter("name"));
            n.setPosition(request.getParameter("position"));
            InputStream img = null;
            Part part = request.getPart("image");
            // Check if user enter no img !
            if (part != null) {
                // if user choose an IMG , set item_img variable
                img = part.getInputStream();
            }

            n.setImage(img);

        } catch (Exception e) {
        }

        boolean tst = false;

        if (request.getParameter("op").equals("1")) {

            tst = n.add(con);

        } else if (request.getParameter("op").equals("2")) {
            n.setCsc_id(Integer.parseInt(request.getParameter("csc_id")));
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
