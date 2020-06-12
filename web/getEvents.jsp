<%@page import="model.Events"%>
<%@page import="model.Admins"%>
<%@page import="model.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    Connection con = DB.setConnection();
    String output = "";
    ArrayList<Events> event = DB.getEvents(con, "select * from events");
    output += "{\"result\": [";
    for (int i = 0; i < event.size(); i++) {
        output += "{";
        output += "\"event_id\":\"" + event.get(i).getEvent_id() + "\",";
        output += "\"event_organizer\":\"" + event.get(i).getEvent_organizer() + "\",";
        output += "\"event_description\":\"" + event.get(i).getEvent_description() + "\",";
        output += "\"event_tittle\":\"" + event.get(i).getEvent_tittle() + "\",";
        output += "\"event_date\":\"" + event.get(i).getEvent_date() + "\",";
        output += "\"event_addedBy\":\"" + event.get(i).getEvent_addedBy() + "\"";
        output += "},";
    }
    output = output.substring(0, output.length() - 1);
    output += "]}";
%>
<%=output%>