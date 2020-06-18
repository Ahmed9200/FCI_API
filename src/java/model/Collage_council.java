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
public class Collage_council {

    private int cc_id;
    private int cc_prof_id;
    private String cc_prof_state;
    private int cc_addedBy;
    private String name;
    private String position;
    private InputStream image;
    private String base64_img;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into collage_council values(?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "collage_council", "cc_id")));
            ps.setInt(2, cc_prof_id);
            ps.setString(3, cc_prof_state);
            ps.setInt(4, cc_addedBy);
            ps.setString(5, name);
            ps.setString(6, position);
            ps.setBlob(7, image);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getCc_id() {
        return cc_id;
    }

    public void setCc_id(int cc_id) {
        this.cc_id = cc_id;
    }

    public int getCc_prof_id() {
        return cc_prof_id;
    }

    public void setCc_prof_id(int cc_prof_id) {
        this.cc_prof_id = cc_prof_id;
    }

    public String getCc_prof_state() {
        return cc_prof_state;
    }

    public void setCc_prof_state(String cc_prof_state) {
        this.cc_prof_state = cc_prof_state;
    }

    public int getCc_addedBy() {
        return cc_addedBy;
    }

    public void setCc_addedBy(int cc_addedBy) {
        this.cc_addedBy = cc_addedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getBase64_img() {
        return base64_img;
    }

    public void setBase64_img(String base64_img) {
        this.base64_img = base64_img;
    }

}
