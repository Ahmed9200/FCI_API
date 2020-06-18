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
public class Graduate_studies_council {

    private int gsc_id;
    private int gsc_prof_id;
    private String gsc_prof_state;
    private int gsc_addedBy;
    private String name;
    private String position;
    private InputStream image;
    private String base64_image;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into graduate_studies_council values(?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "graduate_studies_council", "gsc_id")));
            ps.setInt(2, gsc_prof_id);
            ps.setString(3, gsc_prof_state);
            ps.setInt(4, gsc_addedBy);
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

    public int getGsc_id() {
        return gsc_id;
    }

    public void setGsc_id(int gsc_id) {
        this.gsc_id = gsc_id;
    }

    public int getGsc_prof_id() {
        return gsc_prof_id;
    }

    public void setGsc_prof_id(int gsc_prof_id) {
        this.gsc_prof_id = gsc_prof_id;
    }

    public String getGsc_prof_state() {
        return gsc_prof_state;
    }

    public void setGsc_prof_state(String gsc_prof_state) {
        this.gsc_prof_state = gsc_prof_state;
    }

    public int getGsc_addedBy() {
        return gsc_addedBy;
    }

    public void setGsc_addedBy(int gsc_addedBy) {
        this.gsc_addedBy = gsc_addedBy;
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

    public String getBase64_image() {
        return base64_image;
    }

    public void setBase64_image(String base64_image) {
        this.base64_image = base64_image;
    }

}
