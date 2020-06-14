/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 20102
 */
public class Events {

    private int event_id;
    private String event_organizer;
    private String event_tittle;
    private String event_description;
    private int day;
    private int event_addedBy;
    private String month;
    private String location;
    private int homePage;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into events values(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "events", "event_id")));
            ps.setString(2, event_organizer);
            ps.setString(3, event_tittle);
            ps.setString(4, event_description);
            ps.setInt(5, day);
            ps.setInt(6, event_addedBy);
            ps.setString(7, month);
            ps.setString(8, location);
            ps.setInt(9, homePage);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_organizer() {
        return event_organizer;
    }

    public void setEvent_organizer(String event_organizer) {
        this.event_organizer = event_organizer;
    }

    public String getEvent_tittle() {
        return event_tittle;
    }

    public void setEvent_tittle(String event_tittle) {
        this.event_tittle = event_tittle;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public int getEvent_addedBy() {
        return event_addedBy;
    }

    public void setEvent_addedBy(int event_addedBy) {
        this.event_addedBy = event_addedBy;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHomePage() {
        return homePage;
    }

    public void setHomePage(int homePage) {
        this.homePage = homePage;
    }
}
