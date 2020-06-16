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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.DB;
import model.News;

/**
 *
 * @author 20102
 */
@WebServlet(name = "InsertNews", urlPatterns = ("/InsertNews"))
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)// 1.5 MB
public class InsertNews extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        Connection con = DB.setConnection();
        String output = "";
        News n = new News();
        try {
            n.setNews_tittle(request.getParameter("news_tittle"));
            n.setNews_description(request.getParameter("news_description"));
            n.setNews_date(request.getParameter("news_date"));
            n.setNews_addedBy(Integer.parseInt(request.getParameter("news_addedBy")));
            n.setHomePage(Integer.parseInt(request.getParameter("homePage")));
            InputStream img = null;
            Part part = request.getPart("image");
            // Check if user enter no img !
            if (part != null) {
                // if user choose an IMG , set item_img variable
                img = part.getInputStream();
            }

            n.setImg(img);

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
//    out.print(output);
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
