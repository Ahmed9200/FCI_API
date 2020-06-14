<%@page import="model.Professors"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    Professors p = new Professors();
    try {
        p.setDept_id(Integer.parseInt(request.getParameter("prof_dept_id")));
        p.setManagers((request.getParameter("managers")));
        p.setProf_about(request.getParameter("prof_about"));
        p.setProf_addedBy(Integer.parseInt(request.getParameter("prof_addedBy")));
        p.setProf_address1(request.getParameter("prof_address1"));
        p.setProf_address2(request.getParameter("prof_address2"));
        p.setProf_age(Integer.parseInt(request.getParameter("prof_age")));
        p.setProf_email(request.getParameter("prof_email"));
        p.setProf_username(request.getParameter("prof_username"));
        p.setProf_password(request.getParameter("prof_password"));
        p.setProf_fullname_arabic(request.getParameter("prof_fullname_arabic"));
        p.setProf_fullname_english(request.getParameter("prof_fullname_english"));
        p.setProf_image(request.getParameter("prof_img"));
        p.setProf_nationality(request.getParameter("prof_nationality"));
        p.setProf_religion(request.getParameter("prof_religion"));
        p.setProf_status(request.getParameter("prof_status"));
    } catch (Exception e) {
    }
    if (p.add(con)) {
        output += "{\"result\": [";
        output += "{";
        output += "\"text\":\"" + "success" + "\"";
        output += "},";
    } else {
        output += "{\"result\": [";
        output += "{";
        output += "\"text\":\"" + "error" + "\"";
        output += "},";
    }

    output = output.substring(0, output.length() - 1);
    output += "]}";
    out.print(output);
%>