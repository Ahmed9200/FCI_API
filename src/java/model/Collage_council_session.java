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
public class Collage_council_session {

    private int ccs_id;
    private String ccs_name_arabic;
    private String ccs_name_english;
    private String ccs_about;
    private String ccs_school_year;
    private int ccs_addedBy;
    private String ccs_addedDate;
    private String ccs_description;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into collage_council_session values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "collage_council_session", "ccs_id")));
            ps.setString(2, ccs_name_arabic);
            ps.setString(3, ccs_name_english);
            ps.setString(4, ccs_about);
            ps.setString(5, ccs_school_year);
            ps.setInt(6, ccs_addedBy);
            ps.setString(7, ccs_addedDate);
            ps.setString(8, ccs_description);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getCcs_id() {
        return ccs_id;
    }

    public void setCcs_id(int ccs_id) {
        this.ccs_id = ccs_id;
    }

    public String getCcs_name_arabic() {
        return ccs_name_arabic;
    }

    public void setCcs_name_arabic(String ccs_name_arabic) {
        this.ccs_name_arabic = ccs_name_arabic;
    }

    public String getCcs_name_english() {
        return ccs_name_english;
    }

    public void setCcs_name_english(String ccs_name_english) {
        this.ccs_name_english = ccs_name_english;
    }

    public String getCcs_about() {
        return ccs_about;
    }

    public void setCcs_about(String ccs_about) {
        this.ccs_about = ccs_about;
    }

    public String getCcs_school_year() {
        return ccs_school_year;
    }

    public void setCcs_school_year(String ccs_school_year) {
        this.ccs_school_year = ccs_school_year;
    }

    public int getCcs_addedBy() {
        return ccs_addedBy;
    }

    public void setCcs_addedBy(int ccs_addedBy) {
        this.ccs_addedBy = ccs_addedBy;
    }

    public String getCcs_addedDate() {
        return ccs_addedDate;
    }

    public void setCcs_addedDate(String ccs_addedDate) {
        this.ccs_addedDate = ccs_addedDate;
    }

    public String getCcs_description() {
        return ccs_description;
    }

    public void setCcs_description(String ccs_description) {
        this.ccs_description = ccs_description;
    }

}
