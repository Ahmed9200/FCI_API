<%@page import="model.News"%>
<%@page import="model.Events"%>
<%@page import="model.Admins"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (DB.checkEmailAndPassword(con, username, password)) {

        output += "{\"success\": [";
        output += "{";
        output += "\"text\":\"" + "The user is exist and data is correct" + "\"";
        output += "},";

    } else {
        output += "{\"error\": [";
        output += "{";
        output += "\"text\":\"" + "There is no User have these data " + "\"";
        output += "},";

    }

    output = output.substring(0, output.length() - 1);
    output += "]}";
    out.print(output);
%>