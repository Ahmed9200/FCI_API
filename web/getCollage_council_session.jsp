<%@page import="model.Collage_council_session"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Collage_council_session> b = DB.getCollage_council_session(con, "select * from Collage_council_session");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"ccs_id\":\"" + b.get(i).getCcs_id() + "\",";
        output += "\"ccs_name_arabic\":\"" + b.get(i).getCcs_name_arabic() + "\",";
        output += "\"ccs_name_english\":\"" + b.get(i).getCcs_name_english() + "\",";
        output += "\"ccs_about\":\"" + b.get(i).getCcs_about() + "\",";
        output += "\"ccs_school_year\":\"" + b.get(i).getCcs_school_year() + "\",";
        output += "\"ccs_addedBy\":\"" + b.get(i).getCcs_addedBy() + "\",";
        output += "\"ccs_addedDate\":\"" + b.get(i).getCcs_addedDate() + "\",";
        output += "\"ccs_description\":\"" + b.get(i).getCcs_description() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>