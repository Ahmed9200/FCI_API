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
public class Students {

    private int stud_id;
    private String stud_fname;
    private String stud_lname;
    private String stud_name_english;
    private int stud_age;
    private String stud_email;
    private String stud_religion;
    private String stud_nationality;
    private String stud_address1;
    private String stud_address2;
    private int stud_ssid;
    private int stud_collage_year;
    private int stud_dept_id;
    private String stud_username;
    private String stud_password;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into students values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "students", "stud_id")));
            ps.setString(2, stud_fname);
            ps.setString(3, stud_lname);
            ps.setString(4, stud_name_english);
            ps.setInt(5, stud_age);
            ps.setString(6, stud_email);
            ps.setString(7, stud_religion);
            ps.setString(8, stud_nationality);
            ps.setString(9, stud_address1);
            ps.setString(10, stud_address2);
            ps.setInt(11, stud_ssid);
            ps.setInt(12, stud_collage_year);
            ps.setInt(13, stud_dept_id);
            ps.setString(14, stud_username);
            ps.setString(15, stud_password);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getStud_fname() {
        return stud_fname;
    }

    public void setStud_fname(String stud_fname) {
        this.stud_fname = stud_fname;
    }

    public String getStud_lname() {
        return stud_lname;
    }

    public void setStud_lname(String stud_lname) {
        this.stud_lname = stud_lname;
    }

    public String getStud_name_english() {
        return stud_name_english;
    }

    public void setStud_name_english(String stud_name_english) {
        this.stud_name_english = stud_name_english;
    }

    public int getStud_age() {
        return stud_age;
    }

    public void setStud_age(int stud_age) {
        this.stud_age = stud_age;
    }

    public String getStud_email() {
        return stud_email;
    }

    public void setStud_email(String stud_email) {
        this.stud_email = stud_email;
    }

    public String getStud_religion() {
        return stud_religion;
    }

    public void setStud_religion(String stud_religion) {
        this.stud_religion = stud_religion;
    }

    public String getStud_nationality() {
        return stud_nationality;
    }

    public void setStud_nationality(String stud_nationality) {
        this.stud_nationality = stud_nationality;
    }

    public String getStud_address1() {
        return stud_address1;
    }

    public void setStud_address1(String stud_address1) {
        this.stud_address1 = stud_address1;
    }

    public String getStud_address2() {
        return stud_address2;
    }

    public void setStud_address2(String stud_address2) {
        this.stud_address2 = stud_address2;
    }

    public int getStud_ssid() {
        return stud_ssid;
    }

    public void setStud_ssid(int stud_ssid) {
        this.stud_ssid = stud_ssid;
    }

    public int getStud_collage_year() {
        return stud_collage_year;
    }

    public void setStud_collage_year(int stud_collage_year) {
        this.stud_collage_year = stud_collage_year;
    }

    public int getStud_dept_id() {
        return stud_dept_id;
    }

    public void setStud_dept_id(int stud_dept_id) {
        this.stud_dept_id = stud_dept_id;
    }

    public String getStud_username() {
        return stud_username;
    }

    public void setStud_username(String stud_username) {
        this.stud_username = stud_username;
    }

    public String getStud_password() {
        return stud_password;
    }

    public void setStud_password(String stud_password) {
        this.stud_password = stud_password;
    }

    public boolean update(Connection con) {

        try {

            String query = "update students set stud_fname = ? , "
                    + "stud_lname = ? , "
                    + "stud_name_english = ? , "
                    + "stud_age = ? , "
                    + "stud_email = ? , "
                    + "stud_religion = ? , "
                    + "stud_nationality = ? , "
                    + "stud_address1 = ? , "
                    + "stud_address2 = ? , "
                    + "stud_ssid = ? , "
                    + "stud_collage_year = ? , "
                    + "stud_dept_id = ? , "
                    + "stud_username = ? , "
                    + "stud_password = ? "
                    + "  \n where stud_id = ? ;";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, stud_fname);
            ps.setString(2, stud_lname);
            ps.setString(3, stud_name_english);
            ps.setInt(4, stud_age);
            ps.setString(5, stud_email);
            ps.setString(6, stud_religion);
            ps.setString(7, stud_nationality);
            ps.setString(8, stud_address1);
            ps.setString(9, stud_address2);
            ps.setInt(10, stud_ssid);
            ps.setInt(11, stud_collage_year);
            ps.setInt(12, stud_dept_id);
            ps.setString(13, stud_username);
            ps.setString(14, stud_password);
            ps.setInt(15, stud_id);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
