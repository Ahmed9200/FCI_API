/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Krm
 */
public class Contacts {

    private int contact_id;
    private String contact_username;
    private String contact_user_email;
    private String contact_message;
    private String date;

    public Contacts() {
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_username() {
        return contact_username;
    }

    public void setContact_username(String contact_username) {
        this.contact_username = contact_username;
    }

    public String getContact_user_email() {
        return contact_user_email;
    }

    public void setContact_user_email(String contact_user_email) {
        this.contact_user_email = contact_user_email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContact_message() {
        return contact_message;
    }

    public void setContact_message(String contact_message) {
        this.contact_message = contact_message;
    }

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into contacts values(?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "contacts", "contact_id")));
            ps.setString(2, contact_username);
            ps.setString(3, contact_user_email);
            ps.setString(4, contact_message);
            ps.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
