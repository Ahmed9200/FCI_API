/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 20102
 */
public class Departments {

    private int dept_id;
    private String dept_name_arabic;
    private String dept_name_english;
    private String dept_description;
    private String dept_price;
    private String dept_date;
    private int dept_prof_id;
    private int dept_addedBy;
    private String prof;
    private InputStream dept_image;
    private InputStream prof_image;
    private String base64_dept;
    private String base64_prof;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into departments values(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "departments", "dept_id")));
            ps.setString(2, dept_name_arabic);
            ps.setString(3, dept_name_english);
            ps.setString(4, dept_description);
            ps.setString(5, dept_price);
            ps.setString(6, dept_date);
            ps.setInt(7, dept_prof_id);
            ps.setInt(8, dept_addedBy);
            ps.setString(9, prof);
            ps.setBlob(10, dept_image);
            ps.setBlob(11, prof_image);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name_arabic() {
        return dept_name_arabic;
    }

    public void setDept_name_arabic(String dept_name_arabic) {
        this.dept_name_arabic = dept_name_arabic;
    }

    public String getDept_name_english() {
        return dept_name_english;
    }

    public void setDept_name_english(String dept_name_english) {
        this.dept_name_english = dept_name_english;
    }

    public String getDept_description() {
        return dept_description;
    }

    public void setDept_description(String dept_description) {
        this.dept_description = dept_description;
    }

    public String getDept_price() {
        return dept_price;
    }

    public void setDept_price(String dept_price) {
        this.dept_price = dept_price;
    }

    public String getDept_date() {
        return dept_date;
    }

    public void setDept_date(String dept_date) {
        this.dept_date = dept_date;
    }

    public int getDept_prof_id() {
        return dept_prof_id;
    }

    public void setDept_prof_id(int dept_prof_id) {
        this.dept_prof_id = dept_prof_id;
    }

    public int getDept_addedBy() {
        return dept_addedBy;
    }

    public void setDept_addedBy(int dept_addedBy) {
        this.dept_addedBy = dept_addedBy;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public InputStream getDept_image() {
        return dept_image;
    }

    public void setDept_image(InputStream dept_image) {
        this.dept_image = dept_image;
    }

    public InputStream getProf_image() {
        return prof_image;
    }

    public void setProf_image(InputStream prof_image) {
        this.prof_image = prof_image;
    }

    public String getBase64_dept() {
        return base64_dept;
    }

    public void setBase64_dept(String base64_dept) {
        this.base64_dept = base64_dept;
    }

    public String getBase64_prof() {
        return base64_prof;
    }

    public void setBase64_prof(String base64_prof) {
        this.base64_prof = base64_prof;
    }

    public boolean update(Connection con) {

        try {

            String query = "update departments set dept_name_arabic = ? , "
                    + "dept_name_english = ? , "
                    + "dept_description = ? , "
                    + "dept_price = ? , "
                    + "dept_date = ? , "
                    + "dept_prof_id = ? , "
                    + "dept_addedBy = ? , "
                    + "prof = ? , "
                    + "dept_image = ? , "
                    + "prof_image = ? "
                    + "  \n where dept_id = ? ;";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, dept_name_arabic);
            ps.setString(2, dept_name_english);
            ps.setString(3, dept_description);
            ps.setString(4, dept_price);
            ps.setString(5, dept_date);
            ps.setInt(6, dept_prof_id);
            ps.setInt(7, dept_addedBy);
            ps.setString(8, prof);
            ps.setBlob(9, dept_image);
            ps.setBlob(10, prof_image);
            ps.setInt(11, dept_id);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
