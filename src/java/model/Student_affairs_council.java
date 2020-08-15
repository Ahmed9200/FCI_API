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
public class Student_affairs_council {

    private int sac_id;
    private int sac_prof_id;
    private String sac_prof_state;
    private int sac_addedBy;
    private String name;
    private String position;
    private InputStream image;
    private String base64_image;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into student_affairs_council values(?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "student_affairs_council", "sac_id")));
            ps.setInt(2, sac_prof_id);
            ps.setString(3, sac_prof_state);
            ps.setInt(4, sac_addedBy);
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

    public int getSac_id() {
        return sac_id;
    }

    public void setSac_id(int sac_id) {
        this.sac_id = sac_id;
    }

    public int getSac_prof_id() {
        return sac_prof_id;
    }

    public void setSac_prof_id(int sac_prof_id) {
        this.sac_prof_id = sac_prof_id;
    }

    public String getSac_prof_state() {
        return sac_prof_state;
    }

    public void setSac_prof_state(String sac_prof_state) {
        this.sac_prof_state = sac_prof_state;
    }

    public int getSac_addedBy() {
        return sac_addedBy;
    }

    public void setSac_addedBy(int sac_addedBy) {
        this.sac_addedBy = sac_addedBy;
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

    public boolean update(Connection con) {

        try {

            String query = "update student_affairs_council set sac_prof_id = ? , "
                    + "sac_prof_state = ? , "
                    + "sac_addedBy = ? , "
                    + "name = ? , "
                    + "position = ? , "
                    + "image = ? "
                    + "  \n where sac_id = ? ;";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sac_prof_id);
            ps.setString(2, sac_prof_state);
            ps.setInt(3, sac_addedBy);
            ps.setString(4, name);
            ps.setString(5, position);
            ps.setBlob(6, image);
            ps.setInt(7, sac_id);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
