<%@page import="model.News"%>
<%@page import="model.Events"%>
<%@page import="model.Admins"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    Events e = new Events();
    try {
        e.setEvent_organizer(request.getParameter("event_organizer"));
        e.setEvent_description(request.getParameter("event_description"));
        e.setEvent_tittle(request.getParameter("event_tittle"));
        e.setDay(Integer.parseInt(request.getParameter("day")));
        e.setEvent_addedBy(Integer.parseInt(request.getParameter("event_addedBy")));
        e.setMonth(request.getParameter("month"));
        e.setLocation(request.getParameter("location"));
        e.setHomePage(Integer.parseInt(request.getParameter("homePage")));
    } catch (Exception ex) {
    }
    if (e.add(con)) {
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