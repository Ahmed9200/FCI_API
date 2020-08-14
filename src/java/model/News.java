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
public class News {

    private int news_id;
    private String news_tittle;
    private String news_description;
    private String news_date;
    private int news_addedBy;
    private InputStream img;
    private int homePage;
    private String baseImg;

    public boolean add(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into news values(?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(DB.AutoIncrementCoulmn(con, "news", "news_id")));
            ps.setString(2, news_tittle);
            ps.setString(3, news_description);
            ps.setString(4, news_date);
            ps.setInt(5, news_addedBy);
            ps.setBlob(6, img);
            ps.setInt(7, homePage);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(Connection con) {
        try {
            String query = "UPDATE news \n"
                    + "SET \n"
                    + "    news_tittle = ? ,"
                    + "    news_description = ? ,"
                    + "    news_date = ? ,"
                    + "    news_addedBy = ? ,"
                    + "    img = ? ,"
                    + "    homePage = ? "
                    + "WHERE\n"
                    + "    news_id = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, news_tittle);
            ps.setString(2, news_description);
            ps.setString(3, news_date);
            ps.setInt(4, news_addedBy);
            ps.setBlob(5, img);
            ps.setInt(6, homePage);
            ps.setInt(7, news_id);

            int isAdded = ps.executeUpdate();

            return isAdded > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getBaseImg() {
        return baseImg;
    }

    public void setBaseImg(String baseImg) {
        this.baseImg = baseImg;
    }

    public InputStream getImg() {
        return img;
    }

    public void setImg(InputStream img) {
        this.img = img;
    }

    public int getHomePage() {
        return homePage;
    }

    public void setHomePage(int homePage) {
        this.homePage = homePage;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_tittle() {
        return news_tittle;
    }

    public void setNews_tittle(String news_tittle) {
        this.news_tittle = news_tittle;
    }

    public String getNews_description() {
        return news_description;
    }

    public void setNews_description(String news_description) {
        this.news_description = news_description;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public int getNews_addedBy() {
        return news_addedBy;
    }

    public void setNews_addedBy(int news_addedBy) {
        this.news_addedBy = news_addedBy;
    }
}
