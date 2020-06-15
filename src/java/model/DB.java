package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author Krm
 */
public class DB {

    // Declare Global Variable 
    private static Connection connect;
    private static final String DB_NAME = "fci";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/";
    private static final String UNICODE = "?autoReconnect=true&amp;useEncoding=true&amp;characterEncoding=UTF-8";
    private static String url;

    // Method for set Full URL in String url
    private static String setURL() {
        url = DB_PATH + DB_NAME;
        return url;
    }

//     Method for set Connection to Database and put the 
//     result in con Variable to use it when we need 
//     to make a connection to database
    public static Connection setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection(setURL(), "root", "root");
        } catch (SQLException var1) {
            System.out.println(var1.getMessage());
        } catch (ClassNotFoundException var2) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, (String) null, var2);
        } catch (InstantiationException var3) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, (String) null, var3);
        } catch (IllegalAccessException var4) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return connect;
    }

    //Check username and password for user and admin
    public static boolean checkEmailAndPassword(Connection con, String email, String password) {
        try {
            Statement statement = con.createStatement();
            String strCheck = "select * from admins where admin_username= '" + email + "' and admin_password= '" + password + "'";
            statement.executeQuery(strCheck);

            return statement.getResultSet().next();

        } catch (SQLException var4) {

            System.out.println(var4.getMessage());
            return false;
        }

    }

// Method to do some fast query in database
    public static boolean excuteQuery(Connection con, String sqlStatement) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(sqlStatement);
            return true;
        } catch (SQLException var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public static boolean deleteById(Connection con, String table_name, String coulmn_name, String id) {

        String strDelete = "delete from " + table_name + " where "
                + coulmn_name + " = " + id;
        boolean isDeleted = DB.excuteQuery(con, strDelete);
        return isDeleted;

    }

    /*
     method to get the last id in database and increase it by 1 to get the following id
    @param String tableName
    @param String coulmnName
    @param Connection con
     */
    public static String AutoIncrementCoulmn(Connection con, String tableName, String coulmnName) {
        try {
            Statement statement = con.createStatement();
            String strDBcode = "select max(" + coulmnName + ") +1 AS auto from " + tableName;
            statement.executeQuery(strDBcode);

            String num;
            for (num = ""; statement.getResultSet().next(); num = statement.getResultSet().getString("auto")) {
            }

            return num != null && !"".equals(num) ? num : "1";
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
            return null;
        }
    }

    /*
     method to get any coulmn in any table in database
    @param String tableName
    @param String coulmnName
    @param String condition
    @param Connection con
    
    and return with array of String contain coulmn data
     */
    public static String[] getCoulmnData(Connection con, String tableName, String coulmnName, String condition) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "select " + coulmnName + " from "
                    + tableName + " " + condition;
            ResultSet rs = stmt.executeQuery(strSelect);
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            String[] values = new String[rowCount];

            for (int i = 0; rs.next(); ++i) {
                values[i] = rs.getString(1);
            }
            return values;
        } catch (SQLException var10) {
            System.out.println(var10.getMessage());
            return null;
        }

    }

    public static ArrayList<Admins> getAdmins(Connection con, String sql) {

        try {
            ArrayList<Admins> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Admins item = new Admins();
                item.setAdmin_id(rs.getInt("admin_id"));
                item.setAdmin_prof_id(rs.getInt("admin_prof_id"));
                item.setAdmin_username(rs.getString("admin_username"));
                item.setAdmin_password(rs.getString("admin_password"));
                item.setAdmin_addedDate(rs.getString("admin_addedDate"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }

    }

    public static ArrayList<Events> getEvents(Connection con, String sql) {

        try {
            ArrayList<Events> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Events item = new Events();
                item.setEvent_id(rs.getInt("event_id"));
                item.setEvent_organizer(rs.getString("event_organizer"));
                item.setEvent_description(rs.getString("event_description"));
                item.setEvent_tittle(rs.getString("event_tittle"));
                item.setDay(rs.getInt("day"));
                item.setEvent_addedBy(rs.getInt("event_addedBy"));
                item.setEvent_tittle(rs.getString("month"));
                item.setEvent_tittle(rs.getString("location"));
                item.setHomePage(rs.getInt("homePage"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }

    }

    public static ArrayList<Professors> getProf(Connection con, String sql) {

        try {
            ArrayList<Professors> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Professors p = new Professors();

                p.setProf_id(rs.getInt("prof_id"));
                p.setDept_id(rs.getInt("prof_dept_id"));
                p.setManagers(rs.getString("managers"));
                p.setProf_about(rs.getString("prof_about"));
                p.setProf_addedBy(rs.getInt("prof_addedBy"));
                p.setProf_address1(rs.getString("prof_address1"));
                p.setProf_address2(rs.getString("prof_address2"));
                p.setProf_age(rs.getInt("prof_age"));
                p.setProf_email(rs.getString("prof_email"));
                p.setProf_username(rs.getString("prof_username"));
                p.setProf_password(rs.getString("prof_password"));
                p.setProf_fullname_arabic(rs.getString("prof_fullname_arabic"));
                p.setProf_fullname_english(rs.getString("prof_fullname_english"));
                p.setProf_image(rs.getString("prof_img"));
                p.setProf_nationality(rs.getString("prof_nationality"));
                p.setProf_religion(rs.getString("prof_religion"));
                p.setProf_status(rs.getString("prof_status"));

                data.add(p);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }

    }

    public static ArrayList<News> getNews(Connection con, String sql) {

        try {
            ArrayList<News> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                News item = new News();
                item.setNews_id(rs.getInt("news_id"));
                item.setNews_tittle(rs.getString("news_tittle"));
                item.setNews_description(rs.getString("news_description"));
                item.setNews_date(rs.getString("news_date"));
                item.setNews_addedBy(rs.getInt("news_addedBy"));
                item.setHomePage(rs.getInt("homePage"));
                InputStream inputStream = ((rs.getBlob("image").getBinaryStream()));

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                inputStream.close();
                outputStream.close();

                item.setBaseImg(base64Image);

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
