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
public class Community_service_council {

    private int csc_id;
    private int csc_prof_id;
    private String csc_prof_state;
    private int csc_addedBy;
    private String name;
    private String position;
    private InputStream image;
    private String base64_image;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into community_service_council values(?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "community_service_council", "csc_id")));
            ps.setInt(2, csc_prof_id);
            ps.setString(3, csc_prof_state);
            ps.setInt(4, csc_addedBy);
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

    public int getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(int csc_id) {
        this.csc_id = csc_id;
    }

    public int getCsc_prof_id() {
        return csc_prof_id;
    }

    public void setCsc_prof_id(int csc_prof_id) {
        this.csc_prof_id = csc_prof_id;
    }

    public String getCsc_prof_state() {
        return csc_prof_state;
    }

    public void setCsc_prof_state(String csc_prof_state) {
        this.csc_prof_state = csc_prof_state;
    }

    public int getCsc_addedBy() {
        return csc_addedBy;
    }

    public void setCsc_addedBy(int csc_addedBy) {
        this.csc_addedBy = csc_addedBy;
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

            String query = "update community_service_council set csc_prof_id = ? , "
                    + "csc_prof_state = ? , "
                    + "csc_addedBy = ? , "
                    + "name = ? , "
                    + "position = ? , "
                    + "image = ? , "
                    + "csc_id = ?  "
                    + "  \n where ccs_id = ? ;";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, csc_prof_id);
            ps.setString(2, csc_prof_state);
            ps.setInt(3, csc_addedBy);
            ps.setString(4, name);
            ps.setString(5, position);
            ps.setBlob(6, image);
            ps.setInt(7, csc_id);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
