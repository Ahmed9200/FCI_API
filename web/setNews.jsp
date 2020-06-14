<%@page import="model.News"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    News n = new News();
    try {
        n.setNews_tittle(request.getParameter("news_tittle"));
        n.setNews_description(request.getParameter("news_description"));
        n.setNews_date(request.getParameter("news_date"));
        n.setNews_addedBy(Integer.parseInt(request.getParameter("news_addedBy")));
        n.setImg(request.getParameter("image"));
        n.setHomePage(Integer.parseInt(request.getParameter("homePage")));
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
    out.print(output);
%>