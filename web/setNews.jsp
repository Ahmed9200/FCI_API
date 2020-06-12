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
    News n = new News();

    n.setNews_tittle(request.getParameter("news_tittle"));
    n.setNews_description(request.getParameter("news_description"));
    n.setNews_date(request.getParameter("news_date"));
    n.setNews_addedBy(Integer.parseInt(request.getParameter("news_addedBy")));

    if (n.add(con)) {

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