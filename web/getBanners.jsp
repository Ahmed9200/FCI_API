<%@page import="model.Bannars"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Bannars> b = DB.getBanners(con, "select * from banners");
    output += "{\"result\": [";
    for (int i = 0; i < b.size(); i++) {
        output += "{";
        output += "\"banner_id\":\"" + b.get(i).getBanner_id() + "\",";
        output += "\"banner_tittle\":\"" + b.get(i).getBanner_tittle() + "\",";
        output += "\"banner_description\":\"" + b.get(i).getBanner_description() + "\",";
        output += "\"banner_img\":\"" + b.get(i).getBase64_img() + "\",";
        output += "\"banner_date\":\"" + b.get(i).getBanner_date() + "\",";
        output += "\"banner_addedBy\":\"" + b.get(i).getBanner_addedBy() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>