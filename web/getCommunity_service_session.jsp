<%@page import="model.Community_service_session"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Community_service_session> b = DB.getCommunity_service_session(con, "select * from Community_service_session");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"css_id\":\"" + b.get(i).getCss_id() + "\",";
        output += "\"css_name_arabic\":\"" + b.get(i).getCss_name_arabic() + "\",";
        output += "\"css_name_english\":\"" + b.get(i).getCss_name_english() + "\",";
        output += "\"css_about\":\"" + b.get(i).getCss_about() + "\",";
        output += "\"css_school_year\":\"" + b.get(i).getCss_school_year() + "\",";
        output += "\"css_addedBy\":\"" + b.get(i).getCss_addedBy() + "\",";
        output += "\"css_addedDate\":\"" + b.get(i).getCss_addedDate() + "\",";
        output += "\"css_description\":\"" + b.get(i).getCss_description() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>