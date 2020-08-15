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
public class Student_affairs_session {

    private int sas_id;
    private String sas_name_arabic;
    private String sas_name_english;
    private String sas_about;
    private String sas_description;
    private String sas_school_year;
    private String sas_addedDate;
    private int sas_addedBy;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into student_affairs_session values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "student_affairs_session", "sas_id")));
            ps.setString(2, sas_name_arabic);
            ps.setString(3, sas_name_english);
            ps.setString(4, sas_about);
            ps.setString(5, sas_description);
            ps.setString(6, sas_school_year);
            ps.setString(7, sas_addedDate);
            ps.setInt(8, sas_addedBy);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getSas_id() {
        return sas_id;
    }

    public void setSas_id(int sas_id) {
        this.sas_id = sas_id;
    }

    public String getSas_name_arabic() {
        return sas_name_arabic;
    }

    public void setSas_name_arabic(String sas_name_arabic) {
        this.sas_name_arabic = sas_name_arabic;
    }

    public String getSas_name_english() {
        return sas_name_english;
    }

    public void setSas_name_english(String sas_name_english) {
        this.sas_name_english = sas_name_english;
    }

    public String getSas_about() {
        return sas_about;
    }

    public void setSas_about(String sas_about) {
        this.sas_about = sas_about;
    }

    public String getSas_description() {
        return sas_description;
    }

    public void setSas_description(String sas_description) {
        this.sas_description = sas_description;
    }

    public String getSas_school_year() {
        return sas_school_year;
    }

    public void setSas_school_year(String sas_school_year) {
        this.sas_school_year = sas_school_year;
    }

    public String getSas_addedDate() {
        return sas_addedDate;
    }

    public void setSas_addedDate(String sas_addedDate) {
        this.sas_addedDate = sas_addedDate;
    }

    public int getSas_addedBy() {
        return sas_addedBy;
    }

    public void setSas_addedBy(int sas_addedBy) {
        this.sas_addedBy = sas_addedBy;
    }

    public boolean update(Connection con) {

        try {

            String query = "update student_affairs_session set sas_name_arabic = ? , "
                    + "sas_name_english = ? , "
                    + "sas_about = ? , "
                    + "sas_description = ? , "
                    + "sas_school_year = ? , "
                    + "sas_addedDate = ? , "
                    + "sas_addedBy = ? "
                    + "  \n where sas_id = ? ;";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, sas_name_arabic);
            ps.setString(2, sas_name_english);
            ps.setString(3, sas_about);
            ps.setString(4, sas_description);
            ps.setString(5, sas_school_year);
            ps.setString(6, sas_addedDate);
            ps.setInt(7, sas_addedBy);
            ps.setInt(8, sas_id);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
