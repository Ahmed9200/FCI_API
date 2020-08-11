<%@page import="model.Student_affairs_session"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Student_affairs_session> b = DB.getStudent_affairs_session(con, "select * from Student_affairs_session");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"sas_id\":\"" + b.get(i).getSas_id() + "\",";
        output += "\"sas_name_arabic\":\"" + b.get(i).getSas_name_arabic() + "\",";
        output += "\"sas_name_english\":\"" + b.get(i).getSas_name_english() + "\",";
        output += "\"sas_about\":\"" + b.get(i).getSas_about() + "\",";
        output += "\"sas_school_year\":\"" + b.get(i).getSas_school_year() + "\",";
        output += "\"sas_addedBy\":\"" + b.get(i).getSas_addedBy() + "\",";
        output += "\"sas_addedDate\":\"" + b.get(i).getSas_addedDate() + "\",";
        output += "\"sas_description\":\"" + b.get(i).getSas_description() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>