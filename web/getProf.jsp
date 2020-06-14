<%@page import="model.Professors"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Professors> event = DB.getProf(con, "select * from Professors");
    output += "{\"result\": [";
    for (int i = 0; i < event.size(); i++) {
        output += "{";
        output += "\"prof_id\":\"" + event.get(i).getProf_id() + "\",";
        output += "\"prof_dept_id\":\"" + event.get(i).getDept_id() + "\",";
        output += "\"managers\":\"" + event.get(i).getManagers() + "\",";
        output += "\"prof_about\":\"" + event.get(i).getProf_about() + "\",";
        output += "\"prof_addedBy\":\"" + event.get(i).getProf_addedBy() + "\",";
        output += "\"prof_address1\":\"" + event.get(i).getProf_address1() + "\",";
        output += "\"prof_address2\":\"" + event.get(i).getProf_address2() + "\",";
        output += "\"prof_age\":\"" + event.get(i).getProf_age() + "\",";
        output += "\"prof_email\":\"" + event.get(i).getProf_email() + "\",";
        output += "\"prof_username\":\"" + event.get(i).getProf_username() + "\",";
        output += "\"prof_password\":\"" + event.get(i).getProf_password() + "\",";
        output += "\"prof_fullname_arabic\":\"" + event.get(i).getProf_fullname_arabic() + "\",";
        output += "\"prof_fullname_enflish\":\"" + event.get(i).getProf_fullname_english() + "\",";
        output += "\"prof_img\":\"" + event.get(i).getProf_image() + "\",";
        output += "\"prof_nationality\":\"" + event.get(i).getProf_nationality() + "\",";
        output += "\"prof_religion\":\"" + event.get(i).getProf_religion() + "\",";
        output += "\"prof_status\":\"" + event.get(i).getProf_status() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
    out.print(output);
%>