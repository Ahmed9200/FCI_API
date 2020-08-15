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
import model.DB;
import model.Departments;

/**
 *
 * @author 20102
 */
@WebServlet(name = "InsertDepts", urlPatterns = ("/InsertDepts"))
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)// 1.5 MB
public class InsertDepts extends HttpServlet {

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
        Departments n = new Departments();
        try {
//            n.setDept_id(Integer.parseInt(request.getParameter("dept_id")));
            n.setDept_name_arabic(request.getParameter("dept_name_arabic"));
            n.setDept_name_english(request.getParameter("dept_name_english"));
            n.setDept_description(request.getParameter("dept_description"));
            n.setDept_price(request.getParameter("dept_price"));
            n.setDept_date(request.getParameter("dept_date"));
            n.setDept_prof_id(Integer.parseInt(request.getParameter("dept_prof_id")));
            n.setDept_addedBy(Integer.parseInt(request.getParameter("dept_addedBy")));
            n.setProf(request.getParameter("prof"));

            InputStream img = null;
            InputStream img2 = null;
            Part part = request.getPart("dept_image");
            Part part2 = request.getPart("prof_image");
            // Check if user enter no img !
            if (part != null && part2 != null) {
                // if user choose an IMG , set item_img variable
                img = part.getInputStream();
                img2 = part2.getInputStream();
            } else if (part != null) {
                img = part.getInputStream();
            } else {
                img2 = part2.getInputStream();
            }

            n.setDept_image(img);
            n.setProf_image(img2);

        } catch (Exception e) {
        }
        boolean tst = false;

        if (request.getParameter("op").equals("1")) {

            tst = n.add(con);

        } else if (request.getParameter("op").equals("2")) {
            n.setDept_id(Integer.parseInt(request.getParameter("dept_id")));
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
