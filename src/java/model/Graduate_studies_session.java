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
public class Graduate_studies_session {

    private int gss_id;
    private String gss_name_arabic;
    private String gss_name_english;
    private String gss_about;
    private String gss_description;
    private String gss_school_year;
    private String gss_addedDate;
    private int gss_addedBy;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into graduate_studies_session values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "graduate_studies_session", "gss_id")));
            ps.setString(2, gss_name_arabic);
            ps.setString(3, gss_name_english);
            ps.setString(4, gss_about);
            ps.setString(5, gss_description);
            ps.setString(6, gss_school_year);
            ps.setString(7, gss_addedDate);
            ps.setInt(8, gss_addedBy);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getGss_id() {
        return gss_id;
    }

    public void setGss_id(int gss_id) {
        this.gss_id = gss_id;
    }

    public String getGss_name_arabic() {
        return gss_name_arabic;
    }

    public void setGss_name_arabic(String gss_name_arabic) {
        this.gss_name_arabic = gss_name_arabic;
    }

    public String getGss_name_english() {
        return gss_name_english;
    }

    public void setGss_name_english(String gss_name_english) {
        this.gss_name_english = gss_name_english;
    }

    public String getGss_about() {
        return gss_about;
    }

    public void setGss_about(String gss_about) {
        this.gss_about = gss_about;
    }

    public String getGss_description() {
        return gss_description;
    }

    public void setGss_description(String gss_description) {
        this.gss_description = gss_description;
    }

    public String getGss_school_year() {
        return gss_school_year;
    }

    public void setGss_school_year(String gss_school_year) {
        this.gss_school_year = gss_school_year;
    }

    public String getGss_addedDate() {
        return gss_addedDate;
    }

    public void setGss_addedDate(String gss_addedDate) {
        this.gss_addedDate = gss_addedDate;
    }

    public int getGss_addedBy() {
        return gss_addedBy;
    }

    public void setGss_addedBy(int gss_addedBy) {
        this.gss_addedBy = gss_addedBy;
    }

}
