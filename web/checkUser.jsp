<%@page import="java.io.InputStream"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    InputStream tst = request.getInputStream();
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    System.out.println("password = " + tst.toString());
    

    if (DB.checkEmailAndPassword(con, username, password)) {
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
    out.print(output);
%>