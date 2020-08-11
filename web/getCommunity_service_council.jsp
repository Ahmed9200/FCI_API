<%@page import="model.Community_service_council"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Community_service_council> b = DB.getCommunity_service_council(con, "select * from Community_service_council");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"csc_id\":\"" + b.get(i).getCsc_id() + "\",";
        output += "\"csc_prof_id\":\"" + b.get(i).getCsc_prof_id() + "\",";
        output += "\"csc_prof_state\":\"" + b.get(i).getCsc_prof_state() + "\",";
        output += "\"csc_addedBy\":\"" + b.get(i).getCsc_addedBy() + "\",";
        output += "\"name\":\"" + b.get(i).getName() + "\",";
        output += "\"position\":\"" + b.get(i).getPosition() + "\",";
        output += "\"image\":\"" + b.get(i).getBase64_image() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>