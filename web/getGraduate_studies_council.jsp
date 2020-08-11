<%@page import="model.Graduate_studies_council"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Graduate_studies_council> b = DB.getGraduate_studies_council(con, "select * from Graduate_studies_council");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"gsc_id\":\"" + b.get(i).getGsc_id() + "\",";
        output += "\"gsc_prof_id\":\"" + b.get(i).getGsc_prof_id() + "\",";
        output += "\"gsc_prof_state\":\"" + b.get(i).getGsc_prof_state() + "\",";
        output += "\"gsc_addedBy\":\"" + b.get(i).getGsc_addedBy() + "\",";
        output += "\"name\":\"" + b.get(i).getName() + "\",";
        output += "\"position\":\"" + b.get(i).getPosition() + "\",";
        output += "\"image\":\"" + b.get(i).getBase64_image() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>
