<%@page import="model.Departments"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Departments> d = DB.getDepartments(con, "select * from departments");
    output += "{\"result\": [";
    for (int i = 0; i < d.size(); i++) {
        output += "{";
        output += "\"dept_id\":\"" + d.get(i).getDept_id() + "\",";
        output += "\"dept_name_arabic\":\"" + d.get(i).getDept_name_arabic() + "\",";
        output += "\"dept_name_english\":\"" + d.get(i).getDept_name_english() + "\",";
        output += "\"dept_description\":\"" + d.get(i).getDept_description() + "\",";
        output += "\"dept_price\":\"" + d.get(i).getDept_price() + "\",";
        output += "\"dept_date\":\"" + d.get(i).getDept_date() + "\",";
        output += "\"dept_prof_id\":\"" + d.get(i).getDept_prof_id() + "\",";
        output += "\"dept_addedBy\":\"" + d.get(i).getDept_addedBy() + "\",";
        output += "\"prof\":\"" + d.get(i).getProf() + "\",";
        output += "\"dept_image\":\"" + d.get(i).getBase64_dept() + "\",";
        output += "\"prof_image\":\"" + d.get(i).getBase64_prof() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>