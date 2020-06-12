<%@page import="model.Admins"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Admins> admins = DB.getFeedbacksData(con, "select * from admins");
    output += "{\"result\": [";
    for (int i = 0; i < admins.size(); i++) {
        output += "{";
        output += "\"admin_id\":\"" + admins.get(i).getAdmin_id() + "\",";
        output += "\"admin_addedDate\":\"" + admins.get(i).getAdmin_addedDate() + "\",";
        output += "\"admin_password\":\"" + admins.get(i).getAdmin_password() + "\",";
        output += "\"admin_username\":\"" + admins.get(i).getAdmin_username() + "\",";
        output += "\"admin_prof_id\":\"" + admins.get(i).getAdmin_prof_id() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>

