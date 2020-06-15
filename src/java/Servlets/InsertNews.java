/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        response.setContentType("text/html;charset=UTF-8");

        Connection con = DB.setConnection();
        String output = "";
        ArrayList<News> event = DB.getNews(con, "select * from news");
        output += "{\"result\": [";
        for (int i = 0; i < event.size(); i++) {
            output += "{";
            output += "\"news_id\":\"" + event.get(i).getNews_id() + "\",";
            output += "\"news_tittle\":\"" + event.get(i).getNews_tittle() + "\",";
            output += "\"news_description\":\"" + event.get(i).getNews_description() + "\",";
            output += "\"news_date\":\"" + event.get(i).getNews_date() + "\",";
            output += "\"image\":\"" + event.get(i).getImg() + "\",";
            output += "\"homePage\":\"" + event.get(i).getHomePage() + "\",";
            output += "\"news_addedBy\":\"" + event.get(i).getNews_addedBy() + "\"";
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
