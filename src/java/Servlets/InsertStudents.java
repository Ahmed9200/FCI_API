/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.DB;
import model.Professors;
import model.Students;

/**
 *
 * @author 20102
 */
public class InsertStudents extends HttpServlet {

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
        Students n = new Students();
        try {
            n.setStud_id(Integer.parseInt(request.getParameter("stud_id")));
            n.setStud_fname(request.getParameter("stud_fname"));
            n.setStud_lname(request.getParameter("stud_lname"));
            n.setStud_name_english(request.getParameter("stud_name_english"));
            n.setStud_age(Integer.parseInt(request.getParameter("stud_age")));
            n.setStud_email(request.getParameter("stud_email"));
            n.setStud_religion(request.getParameter("stud_religion"));
            n.setStud_nationality(request.getParameter("stud_nationality"));
            n.setStud_address1(request.getParameter("stud_address1"));
            n.setStud_address2(request.getParameter("stud_address2"));
            n.setStud_ssid(Integer.parseInt(request.getParameter("stud_ssid")));
            n.setStud_collage_year(Integer.parseInt(request.getParameter("stud_collage_year")));
            n.setStud_dept_id(Integer.parseInt(request.getParameter("stud_dept_id")));
            n.setStud_username(request.getParameter("stud_username"));
            n.setStud_password(request.getParameter("stud_password"));

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
