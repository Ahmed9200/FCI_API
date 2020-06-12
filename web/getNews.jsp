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
    ArrayList<News> event = DB.getNews(con, "select * from news");
    output += "{\"result\": [";
    for (int i = 0; i < event.size(); i++) {
        output += "{";
        output += "\"news_id\":\"" + event.get(i).getNews_id() + "\",";
        output += "\"news_tittle\":\"" + event.get(i).getNews_tittle() + "\",";
        output += "\"news_description\":\"" + event.get(i).getNews_description() + "\",";
        output += "\"news_date\":\"" + event.get(i).getNews_date() + "\",";
        output += "\"news_addedBy\":\"" + event.get(i).getNews_addedBy() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
    out.print(output);
%>