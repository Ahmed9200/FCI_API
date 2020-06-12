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

    e.setEvent_tittle(request.getParameter("event_tittle"));
    e.setEvent_description(request.getParameter("event_description"));
    e.setEvent_organizer(request.getParameter("event_organizer"));
    e.setEvent_date(request.getParameter("event_date"));
    e.setEvent_addedBy(Integer.parseInt(request.getParameter("event_addedBy")));

    if (e.add(con)) {

        output += "{\"success\": [";
        output += "{";
        output += "\"text\":\"" + "The user is exist and data is correct" + "\"";
        output += "},";

    } else {
        output += "{\"error\": [";
        output += "{";
        output += "\"text\":\"" + "There is no User have these data " + "\"";
        output += "},";

    }

    output = output.substring(0, output.length() - 1);
    output += "]}";
    out.print(output);
%>