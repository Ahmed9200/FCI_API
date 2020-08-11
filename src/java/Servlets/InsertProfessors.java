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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.DB;
import model.Professors;

/**
 *
 * @author 20102
 */
public class InsertProfessors extends HttpServlet {

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
        Professors n = new Professors();
        try {
//            n.setProf_id(Integer.parseInt(request.getParameter("prof_id")));
            n.setProf_fullname_arabic(request.getParameter("prof_fullname_arabic"));
            n.setProf_fullname_english(request.getParameter("prof_fullname_english"));
            n.setDept_id(Integer.parseInt(request.getParameter("dept_id")));
            n.setProf_email(request.getParameter("prof_email"));
            n.setProf_age(Integer.parseInt(request.getParameter("prof_age")));
            n.setProf_nationality(request.getParameter("prof_nationality"));
            n.setProf_address1(request.getParameter("prof_address1"));
            n.setProf_religion(request.getParameter("prof_religion"));
            n.setProf_username(request.getParameter("prof_username"));
            n.setProf_password(request.getParameter("prof_password"));
            n.setProf_about(request.getParameter("prof_about"));
            n.setProf_address2(request.getParameter("prof_address2"));
            n.setProf_addedBy(Integer.parseInt(request.getParameter("prof_addedBy")));
            n.setProf_status(request.getParameter("prof_status"));

            InputStream img = null;
            Part part = request.getPart("dept_image");
            // Check if user enter no img !
            if (part != null) {
                // if user choose an IMG , set item_img variable
                img = part.getInputStream();
            }
            n.setProf_image(img);

        } catch (Exception e) {
        }
        if (n.add(con)) {
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
