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
public class Professors {

    private int prof_id;
    private String prof_fullname_arabic;
    private String prof_fullname_english;
    private int dept_id;
    private String prof_email;
    private int prof_age;
    private String prof_nationality;
    private String prof_address1;
    private String prof_religion;
    private String prof_username;
    private String prof_password;
    private String prof_about;
    private String prof_status;
    private String prof_image;
    private int prof_addedBy;
    private String prof_address2;
    private String managers;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into professors values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "professors", "prof_id")));
            ps.setString(2, prof_fullname_arabic);
            ps.setString(3, prof_fullname_english);
            ps.setInt(4, dept_id);
            ps.setString(5, prof_email);
            ps.setInt(6, prof_age);
            ps.setString(7, prof_nationality);
            ps.setString(8, prof_address1);
            ps.setString(9, prof_religion);
            ps.setString(10, prof_username);
            ps.setString(11, prof_password);
            ps.setString(12, prof_about);
            ps.setString(13, prof_status);
            ps.setString(14, prof_image);
            ps.setInt(15, prof_addedBy);
            ps.setString(16, prof_address2);
            ps.setString(17, managers);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getProf_id() {
        return prof_id;
    }

    public void setProf_id(int prof_id) {
        this.prof_id = prof_id;
    }

    public String getProf_fullname_arabic() {
        return prof_fullname_arabic;
    }

    public void setProf_fullname_arabic(String prof_fullname_arabic) {
        this.prof_fullname_arabic = prof_fullname_arabic;
    }

    public String getProf_fullname_english() {
        return prof_fullname_english;
    }

    public void setProf_fullname_english(String prof_fullname_english) {
        this.prof_fullname_english = prof_fullname_english;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getProf_email() {
        return prof_email;
    }

    public void setProf_email(String prof_email) {
        this.prof_email = prof_email;
    }

    public int getProf_age() {
        return prof_age;
    }

    public void setProf_age(int prof_age) {
        this.prof_age = prof_age;
    }

    public String getProf_nationality() {
        return prof_nationality;
    }

    public void setProf_nationality(String prof_nationality) {
        this.prof_nationality = prof_nationality;
    }

    public String getProf_address1() {
        return prof_address1;
    }

    public void setProf_address1(String prof_address1) {
        this.prof_address1 = prof_address1;
    }

    public String getProf_religion() {
        return prof_religion;
    }

    public void setProf_religion(String prof_religion) {
        this.prof_religion = prof_religion;
    }

    public String getProf_username() {
        return prof_username;
    }

    public void setProf_username(String prof_username) {
        this.prof_username = prof_username;
    }

    public String getProf_password() {
        return prof_password;
    }

    public void setProf_password(String prof_password) {
        this.prof_password = prof_password;
    }

    public String getProf_about() {
        return prof_about;
    }

    public void setProf_about(String prof_about) {
        this.prof_about = prof_about;
    }

    public String getProf_status() {
        return prof_status;
    }

    public void setProf_status(String prof_status) {
        this.prof_status = prof_status;
    }

    public String getProf_image() {
        return prof_image;
    }

    public void setProf_image(String prof_image) {
        this.prof_image = prof_image;
    }

    public int getProf_addedBy() {
        return prof_addedBy;
    }

    public void setProf_addedBy(int prof_addedBy) {
        this.prof_addedBy = prof_addedBy;
    }

    public String getProf_address2() {
        return prof_address2;
    }

    public void setProf_address2(String prof_address2) {
        this.prof_address2 = prof_address2;
    }

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

}
