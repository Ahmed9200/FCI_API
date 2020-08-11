<%@page import="model.Students"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Students> s = DB.getStudents(con, "select * from Students");
    output += "{\"result\": [";
    for (int i = 0; i < s.size(); i++) {
        output += "{";
        output += "\"stud_id\":\"" + s.get(i).getStud_id() + "\",";
        output += "\"stud_fname\":\"" + s.get(i).getStud_fname() + "\",";
        output += "\"stud_lname\":\"" + s.get(i).getStud_lname() + "\",";
        output += "\"stud_name_english\":\"" + s.get(i).getStud_name_english() + "\",";
        output += "\"stud_age\":\"" + s.get(i).getStud_age() + "\",";
        output += "\"stud_email\":\"" + s.get(i).getStud_email() + "\",";
        output += "\"stud_religion\":\"" + s.get(i).getStud_religion() + "\",";
        output += "\"stud_nationality\":\"" + s.get(i).getStud_nationality() + "\",";
        output += "\"stud_address1\":\"" + s.get(i).getStud_address1() + "\",";
        output += "\"stud_address2\":\"" + s.get(i).getStud_address2() + "\",";
        output += "\"stud_ssid\":\"" + s.get(i).getStud_ssid() + "\",";
        output += "\"stud_collage_year\":\"" + s.get(i).getStud_collage_year() + "\",";
        output += "\"stud_dept_id\":\"" + s.get(i).getStud_dept_id() + "\",";
        output += "\"stud_username\":\"" + s.get(i).getStud_username() + "\",";
        output += "\"stud_password\":\"" + s.get(i).getStud_password() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>