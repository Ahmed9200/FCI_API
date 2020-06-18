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
public class Bannars {

    private int banner_id;
    private String banner_tittle;
    private String banner_description;
    private InputStream banner_img;
    private String base64_img;
    private String banner_date;
    private int banner_addedBy;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into banners values(?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "banners", "banner_id")));
            ps.setString(2, banner_tittle);
            ps.setString(3, banner_description);
            ps.setBlob(4, banner_img);
            ps.setString(5, banner_date);
            ps.setInt(6, banner_addedBy);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_tittle() {
        return banner_tittle;
    }

    public void setBanner_tittle(String banner_tittle) {
        this.banner_tittle = banner_tittle;
    }

    public String getBanner_description() {
        return banner_description;
    }

    public void setBanner_description(String banner_description) {
        this.banner_description = banner_description;
    }

    public InputStream getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(InputStream banner_img) {
        this.banner_img = banner_img;
    }

    public String getBase64_img() {
        return base64_img;
    }

    public void setBase64_img(String base64_img) {
        this.base64_img = base64_img;
    }

    public String getBanner_date() {
        return banner_date;
    }

    public void setBanner_date(String banner_date) {
        this.banner_date = banner_date;
    }

    public int getBanner_addedBy() {
        return banner_addedBy;
    }

    public void setBanner_addedBy(int banner_addedBy) {
        this.banner_addedBy = banner_addedBy;
    }

}
