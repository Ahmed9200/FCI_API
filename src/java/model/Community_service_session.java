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
public class Community_service_session {

    private int css_id;
    private String css_name_arabic;
    private String css_name_english;
    private String css_about;
    private String css_addedDate;
    private String css_school_year;
    private int css_addedBy;
    private String css_description;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into community_service_session values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "community_service_session", "css_id")));
            ps.setString(2, css_name_arabic);
            ps.setString(3, css_name_english);
            ps.setString(4, css_about);
            ps.setString(5, css_addedDate);
            ps.setString(6, css_school_year);
            ps.setInt(7, css_addedBy);
            ps.setString(8, css_description);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getCss_id() {
        return css_id;
    }

    public void setCss_id(int css_id) {
        this.css_id = css_id;
    }

    public String getCss_name_arabic() {
        return css_name_arabic;
    }

    public void setCss_name_arabic(String css_name_arabic) {
        this.css_name_arabic = css_name_arabic;
    }

    public String getCss_name_english() {
        return css_name_english;
    }

    public void setCss_name_english(String css_name_english) {
        this.css_name_english = css_name_english;
    }

    public String getCss_about() {
        return css_about;
    }

    public void setCss_about(String css_about) {
        this.css_about = css_about;
    }

    public String getCss_addedDate() {
        return css_addedDate;
    }

    public void setCss_addedDate(String css_addedDate) {
        this.css_addedDate = css_addedDate;
    }

    public String getCss_school_year() {
        return css_school_year;
    }

    public void setCss_school_year(String css_school_year) {
        this.css_school_year = css_school_year;
    }

    public int getCss_addedBy() {
        return css_addedBy;
    }

    public void setCss_addedBy(int css_addedBy) {
        this.css_addedBy = css_addedBy;
    }

    public String getCss_description() {
        return css_description;
    }

    public void setCss_description(String css_description) {
        this.css_description = css_description;
    }

}
