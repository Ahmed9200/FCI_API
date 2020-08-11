<%@page import="model.Collage_council"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Collage_council> b = DB.getCollage_council(con, "select * from Collage_council");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"cc_id\":\"" + b.get(i).getCc_id() + "\",";
        output += "\"cc_prof_id\":\"" + b.get(i).getCc_prof_id() + "\",";
        output += "\"cc_prof_state\":\"" + b.get(i).getCc_prof_state() + "\",";
        output += "\"cc_addedBy\":\"" + b.get(i).getCc_addedBy() + "\",";
        output += "\"name\":\"" + b.get(i).getName() + "\",";
        output += "\"position\":\"" + b.get(i).getPosition() + "\",";
        output += "\"image\":\"" + b.get(i).getBase64_img() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>