<%@page import="model.Graduate_studies_session"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Graduate_studies_session> b = DB.getGraduate_studies_session(con, "select * from Graduate_studies_session");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"gss_id\":\"" + b.get(i).getGss_id() + "\",";
        output += "\"gss_name_arabic\":\"" + b.get(i).getGss_name_arabic() + "\",";
        output += "\"gss_name_english\":\"" + b.get(i).getGss_name_english() + "\",";
        output += "\"gss_about\":\"" + b.get(i).getGss_about() + "\",";
        output += "\"gss_school_year\":\"" + b.get(i).getGss_school_year() + "\",";
        output += "\"gss_addedBy\":\"" + b.get(i).getGss_addedBy() + "\",";
        output += "\"gss_addedDate\":\"" + b.get(i).getGss_addedDate() + "\",";
        output += "\"gss_description\":\"" + b.get(i).getGss_description() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>