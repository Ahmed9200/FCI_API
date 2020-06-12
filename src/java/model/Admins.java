package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 20102
 */
public class Admins {

    private int admin_id;
    private int admin_prof_id;
    private String admin_username;
    private String admin_password;
    private String admin_addedDate;
//    Professor prof;

    public int getAdmin_prof_id() {
        return admin_prof_id;
    }

    public void setAdmin_prof_id(int admin_prof_id) {
        this.admin_prof_id = admin_prof_id;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_addedDate() {
        return admin_addedDate;
    }

    public void setAdmin_addedDate(String admin_addedDate) {
        this.admin_addedDate = admin_addedDate;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into admins values(?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "admins", "admin_id")));
            ps.setInt(2, admin_prof_id);
            ps.setString(3, admin_username);
            ps.setString(4, admin_password);
            ps.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
