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
            String strCheck = "select * from users where email= '" + email + "' and password= '" + password + "'";
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

    /*
    this method for get data from database table 
    and put it in arraylist and return this arraylist
     */
//    public static ArrayList<User> getUsersData(Connection con, String sql) {
//
//        try {
//            ArrayList<User> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("name"));
//                user.setNationality(rs.getString("nationality"));
//                user.setGender(rs.getString("gender"));
//                user.setAge(rs.getString("age"));
//                user.setEmail(rs.getString("email"));
//                user.setPhone(rs.getString("phone"));
//                user.setPassword(rs.getString("password"));
//                // set img in base 64 to show it in img tag in html
//                InputStream inputStream = rs.getBlob("img").getBinaryStream();
//
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead = -1;
//
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//
//                byte[] imageBytes = outputStream.toByteArray();
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//                inputStream.close();
//                outputStream.close();
//
//                user.setBase64img(base64Image);
//
//                data.add(user);
//            }
//
//            return data;
//        } catch (SQLException | IOException ex) {
//
//            ex.printStackTrace();
//            return null;
//
//        }
//
//    }
//
//    public static ArrayList<Invoice_details> getInvoiceDetailsData(Connection con, String sql) {
//
//        try {
//            ArrayList<Invoice_details> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//
//                Invoice_details user = new Invoice_details();
//                user.setId(rs.getInt("id"));
//                user.setFname(rs.getString("fname"));
//                user.setLname(rs.getString("lname"));
//                user.setPhone(rs.getString("phone"));
//                user.setEmail(rs.getString("email"));
//                user.setAddress_line1(rs.getString("address_line1"));
//                user.setAddress_line2(rs.getString("address_line2"));
//                user.setTown(rs.getString("town"));
//                user.setPostcode(rs.getString("postcode"));
//
//                data.add(user);
//            }
//
//            return data;
//        } catch (SQLException ex) {
//
//            ex.printStackTrace();
//            return null;
//
//        }
//
//    }
//
//    public static ArrayList<Item> getItemData(Connection con, String sql) {
//
//        try {
//            ArrayList<Item> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//
//                Item item = new Item();
//                int id = rs.getInt("item_id");
//                item.setItem_id(id);
//                item.setItem_name(rs.getString("item_name"));
//                item.setCat_name(rs.getString("cat_name"));
//                item.setCat_id(rs.getInt("item_category_id"));
//                item.setItem_buyprice(rs.getDouble("item_buyprice"));
//                item.setItem_sellprice(rs.getDouble("item_sellprice"));
//                item.setItem_quantity(rs.getInt("item_quantity"));
//                item.setItem_offer(rs.getInt("item_offer"));
//                item.setItem_notes(rs.getString("item_notes"));
//                item.setItem_date(rs.getString("item_date"));
//                // set img in base 64 to show it in img tag in html
//                InputStream inputStream = rs.getBlob("item_img").getBinaryStream();
//
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead = -1;
//
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//
//                byte[] imageBytes = outputStream.toByteArray();
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//                inputStream.close();
//                outputStream.close();
//
//                item.setBase64img(base64Image);
//                item.setItem_feedback(getFeedbacksData(con, "select * from Feedbacks"
//                        + " where item_id=" + id));
//                data.add(item);
//            }
//
//            return data;
//        } catch (SQLException | IOException ex) {
//
//            ex.printStackTrace();
//            return null;
//
//        }
//
//    }
//
//    public static ArrayList<Category> getCategoriesData(Connection con, String sql) {
//
//        try {
//            ArrayList<Category> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//
//                Category item = new Category();
//                int cat_id = rs.getInt("cat_id");
//                item.setCat_id(cat_id);
//                item.setCat_name(rs.getString("cat_name"));
//                item.setCat_date(rs.getString("cat_date"));
//                // set img in base 64 to show it in img tag in html
//                InputStream inputStream = rs.getBlob("cat_img").getBinaryStream();
//
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead = -1;
//
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//
//                byte[] imageBytes = outputStream.toByteArray();
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//                inputStream.close();
//                outputStream.close();
//
//                item.setBase64img(base64Image);
//
//                item.setCat_items_byName(getItemData(con, "select * from items_view"
//                        + " where item_category_id=" + cat_id + " ORDER BY item_name ASC"));
//
//                item.setCat_items_byPrice(getItemData(con, "select * from items_view"
//                        + " where item_category_id=" + cat_id + " ORDER BY item_sellprice ASC"));
//
//                data.add(item);
//            }
//
//            return data;
//        } catch (SQLException | IOException ex) {
//
//            ex.printStackTrace();
//
//            return null;
//
//        }
//
//    }
//
//    public static ArrayList<Poster> getPostersData(Connection con) {
//
//        try {
//            ArrayList<Poster> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from Poster_view");
//            while (rs.next()) {
//
//                Poster item = new Poster();
//                item.setId(rs.getInt("id"));
//                item.setItem_name(rs.getString("item_name"));
//                item.setItem_id(rs.getInt("item_id"));
//                item.setNotes(rs.getString("notes"));
//                // set img in base 64 to show it in img tag in html
//                InputStream inputStream = rs.getBlob("img").getBinaryStream();
//
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead = -1;
//
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//
//                byte[] imageBytes = outputStream.toByteArray();
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//                inputStream.close();
//                outputStream.close();
//
//                item.setBase64img(base64Image);
//                data.add(item);
//            }
//
//            return data;
//        } catch (SQLException | IOException ex) {
//
//            return null;
//
//        }
//
//    }
    public static ArrayList<Admins> getFeedbacksData(Connection con, String sql) {

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

//    public static ArrayList<Invoice> getInvoiceData(Connection con, String sql) {
//
//        try {
//            ArrayList<Invoice> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//
//                Invoice invoice = new Invoice();
//                int id = rs.getInt("id");
//                int details_id = rs.getInt("invoice_details_id");
//                invoice.setId(id);
//                invoice.setTotal_price(rs.getDouble("total_price"));
//                invoice.setShipping(rs.getDouble("shipping"));
//                invoice.setTotal_after_shipping(rs.getDouble("total_after_shipping"));
//                invoice.setUser_id(rs.getInt("user_id"));
//                invoice.setStatus(rs.getString("status"));
//                invoice.setDate(rs.getString("date"));
//                invoice.setInvoice_details_id(details_id);
//                invoice.setInvoice_details(getInvoiceDetailsData(con,
//                        "select * from invoice_details where id =" + details_id).get(0));
//                invoice.setInvoiceItems(getOrderd_itemsData(con, "select * from orderd_items where invoice_id=" + id));
//
//                data.add(invoice);
//            }
//
//            return data;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//
//        }
//
//    }
//
//    public static ArrayList<Orderd_items> getOrderd_itemsData(Connection con, String sql) {
//
//        try {
//            ArrayList<Orderd_items> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//
//                Orderd_items item = new Orderd_items();
//                item.setId(rs.getInt("id"));
//                int item_id = rs.getInt("item_id");
//                item.setItem_id(item_id);
//                item.setSold_quantity(rs.getInt("sold_quantity"));
//
//                item.setItem(getItemData(con, "select * from items_view where item_id=" + item_id).get(0));
//
//                data.add(item);
//            }
//
//            return data;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//
//        }
//
//    }
//
//    public static ArrayList<Shipping> getShippingData(Connection con, String sql) {
//
//        try {
//            ArrayList<Shipping> data = new ArrayList<>();
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//
//                Shipping item = new Shipping();
//                item.setId(rs.getInt("id"));
//                item.setCity(rs.getString("city"));
//                item.setShipping(rs.getDouble("shipping"));
//
//                data.add(item);
//            }
//
//            return data;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//
//        }
//
//    }
}
