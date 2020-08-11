<%@page import="model.Student_affairs_council"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Student_affairs_council> b = DB.getStudent_affairs_council(con, "select * from Student_affairs_council");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"sac_id\":\"" + b.get(i).getSac_id() + "\",";
        output += "\"sac_prof_id\":\"" + b.get(i).getSac_prof_id() + "\",";
        output += "\"sac_prof_state\":\"" + b.get(i).getSac_prof_state() + "\",";
        output += "\"sac_addedBy\":\"" + b.get(i).getSac_addedBy() + "\",";
        output += "\"name\":\"" + b.get(i).getName() + "\",";
        output += "\"position\":\"" + b.get(i).getPosition() + "\",";
        output += "\"image\":\"" + b.get(i).getBase64_image() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>
