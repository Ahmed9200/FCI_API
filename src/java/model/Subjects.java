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
public class Subjects {

    int sub_id;
    String sub_code;
    int sub_term_no;
    String sub_name_arabic;
    String sub_name_english;
    String sub_description;
    double sub_low_degree;
    double sub_high_degree;
    String sub_recourse_link;
    int sub_collage_year;
    int sub_dept_id;
    String sub_addedDate;
    int sub_addedBy;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into subjects values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "subjects", "sub_id")));
            ps.setString(2, sub_code);
            ps.setInt(3, sub_term_no);
            ps.setString(4, sub_name_arabic);
            ps.setString(5, sub_name_english);
            ps.setString(6, sub_description);
            ps.setDouble(7, sub_low_degree);
            ps.setDouble(8, sub_high_degree);
            ps.setString(9, sub_recourse_link);
            ps.setInt(10, sub_collage_year);
            ps.setInt(11, sub_dept_id);
            ps.setString(12, sub_addedDate);
            ps.setInt(13, sub_addedBy);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public int getSub_term_no() {
        return sub_term_no;
    }

    public void setSub_term_no(int sub_term_no) {
        this.sub_term_no = sub_term_no;
    }

    public String getSub_name_arabic() {
        return sub_name_arabic;
    }

    public void setSub_name_arabic(String sub_name_arabic) {
        this.sub_name_arabic = sub_name_arabic;
    }

    public String getSub_name_english() {
        return sub_name_english;
    }

    public void setSub_name_english(String sub_name_english) {
        this.sub_name_english = sub_name_english;
    }

    public String getSub_description() {
        return sub_description;
    }

    public void setSub_description(String sub_description) {
        this.sub_description = sub_description;
    }

    public double getSub_low_degree() {
        return sub_low_degree;
    }

    public void setSub_low_degree(double sub_low_degree) {
        this.sub_low_degree = sub_low_degree;
    }

    public double getSub_high_degree() {
        return sub_high_degree;
    }

    public void setSub_high_degree(double sub_high_degree) {
        this.sub_high_degree = sub_high_degree;
    }

    public String getSub_recourse_link() {
        return sub_recourse_link;
    }

    public void setSub_recourse_link(String sub_recourse_link) {
        this.sub_recourse_link = sub_recourse_link;
    }

    public int getSub_collage_year() {
        return sub_collage_year;
    }

    public void setSub_collage_year(int sub_collage_year) {
        this.sub_collage_year = sub_collage_year;
    }

    public int getSub_dept_id() {
        return sub_dept_id;
    }

    public void setSub_dept_id(int sub_dept_id) {
        this.sub_dept_id = sub_dept_id;
    }

    public String getSub_addedDate() {
        return sub_addedDate;
    }

    public void setSub_addedDate(String sub_addedDate) {
        this.sub_addedDate = sub_addedDate;
    }

    public int getSub_addedBy() {
        return sub_addedBy;
    }

    public void setSub_addedBy(int sub_addedBy) {
        this.sub_addedBy = sub_addedBy;
    }

}
