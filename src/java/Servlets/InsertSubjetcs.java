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
import model.DB;
import model.Subjects;

/**
 *
 * @author 20102
 */
@WebServlet(name = "InsertSubjetcs", urlPatterns = ("/InsertSubjetcs"))
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)// 1.5 MB
public class InsertSubjetcs extends HttpServlet {

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
        Subjects n = new Subjects();
        try {

            n.setSub_code(request.getParameter("sub_code"));
            n.setSub_term_no(Integer.parseInt(request.getParameter("sub_term_no")));
            n.setSub_name_arabic(request.getParameter("sub_name_arabic"));
            n.setSub_name_english(request.getParameter("sub_name_english"));
            n.setSub_description((request.getParameter("sub_description")));
            n.setSub_low_degree(Double.parseDouble(request.getParameter("sub_low_degree")));
            n.setSub_high_degree(Double.parseDouble(request.getParameter("sub_high_degree")));
            n.setSub_recourse_link(request.getParameter("sub_recourse_link"));
            n.setSub_collage_year(Integer.parseInt(request.getParameter("sub_collage_year")));
            n.setSub_dept_id(Integer.parseInt(request.getParameter("sub_dept_id")));
            n.setSub_addedDate((request.getParameter("sub_addedDate")));
            n.setSub_addedBy(Integer.parseInt(request.getParameter("sub_addedBy")));

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
