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

    public static ArrayList<Subjects> getSubjects(Connection con, String sql) {

        try {
            ArrayList<Subjects> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Subjects item = new Subjects();
                item.setSub_id(rs.getInt("sub_id"));
                item.setSub_code(rs.getString("sub_code"));
                item.setSub_term_no(rs.getInt("sub_term_no"));
                item.setSub_name_arabic(rs.getString("sub_name_arabic"));
                item.setSub_name_english(rs.getString("sub_name_english"));
                item.setSub_description(rs.getString("sub_description"));
                item.setSub_low_degree(rs.getDouble("sub_low_degree"));
                item.setSub_high_degree(rs.getDouble("sub_high_degree"));
                item.setSub_recourse_link(rs.getString("sub_recourse_link"));
                item.setSub_collage_year(rs.getInt("sub_collage_year"));
                item.setSub_dept_id(rs.getInt("sub_dept_id"));
                item.setSub_addedDate(rs.getString("sub_addedDate"));
                item.setSub_addedBy(rs.getInt("sub_addedBy"));
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
                p.setProf_nationality(rs.getString("prof_nationality"));
                p.setProf_religion(rs.getString("prof_religion"));
                p.setProf_status(rs.getString("prof_status"));

                InputStream inputStream = ((rs.getBlob("prof_img").getBinaryStream()));

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

                p.setBase64_image(base64Image);

                data.add(p);
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

    public static ArrayList<Bannars> getBanners(Connection con, String sql) {

        try {
            ArrayList<Bannars> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Bannars item = new Bannars();
                item.setBanner_id(rs.getInt("banner_id"));
                item.setBanner_tittle(rs.getString("banner_tittle"));
                item.setBanner_description(rs.getString("banner_description"));
                item.setBanner_date(rs.getString("banner_date"));
                item.setBanner_addedBy(rs.getInt("banner_addedBy"));
                InputStream inputStream = ((rs.getBlob("banner_img").getBinaryStream()));

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

                item.setBase64_img(base64Image);

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

    public static ArrayList<Collage_council> getCollage_council(Connection con, String sql) {

        try {
            ArrayList<Collage_council> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Collage_council item = new Collage_council();
                item.setCc_id(rs.getInt("cc_id"));
                item.setCc_prof_id(rs.getInt("cc_prof_id"));
                item.setCc_prof_state(rs.getString("cc_prof_state"));
                item.setCc_addedBy(rs.getInt("cc_addedBy"));
                item.setName(rs.getString("name"));
                item.setPosition(rs.getString("position"));
                try {
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

                    item.setBase64_img(base64Image);
                } catch (Exception e) {

                    item.setBase64_img("noImage");
                }
                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }

    }

    public static ArrayList<Community_service_council> getCommunity_service_council(Connection con, String sql) {

        try {
            ArrayList<Community_service_council> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Community_service_council item = new Community_service_council();
                item.setCsc_id(rs.getInt("csc_id"));
                item.setCsc_prof_id(rs.getInt("csc_prof_id"));
                item.setCsc_prof_state(rs.getString("csc_prof_state"));
                item.setCsc_addedBy(rs.getInt("csc_addedBy"));
                item.setName(rs.getString("name"));
                item.setPosition(rs.getString("position"));
                try {
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

                    item.setBase64_image(base64Image);
                } catch (Exception e) {

                    item.setBase64_image("noImage");
                }
                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Graduate_studies_council> getGraduate_studies_council(Connection con, String sql) {

        try {
            ArrayList<Graduate_studies_council> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Graduate_studies_council item = new Graduate_studies_council();
                item.setGsc_id(rs.getInt("gsc_id"));
                item.setGsc_prof_id(rs.getInt("gsc_prof_id"));
                item.setGsc_prof_state(rs.getString("gsc_prof_state"));
                item.setGsc_addedBy(rs.getInt("gsc_addedBy"));
                item.setName(rs.getString("name"));
                item.setPosition(rs.getString("position"));
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

                item.setBase64_image(base64Image);

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

    public static ArrayList<Student_affairs_council> getStudent_affairs_council(Connection con, String sql) {

        try {
            ArrayList<Student_affairs_council> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Student_affairs_council item = new Student_affairs_council();
                item.setSac_id(rs.getInt("sac_id"));
                item.setSac_prof_id(rs.getInt("sac_prof_id"));
                item.setSac_prof_state(rs.getString("sac_prof_state"));
                item.setSac_addedBy(rs.getInt("sac_addedBy"));
                item.setName(rs.getString("name"));
                item.setPosition(rs.getString("position"));
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

                item.setBase64_image(base64Image);

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

    public static ArrayList<Collage_council_session> getCollage_council_session(Connection con, String sql) {

        try {
            ArrayList<Collage_council_session> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Collage_council_session item = new Collage_council_session();
                item.setCcs_id(rs.getInt("ccs_id"));
                item.setCcs_name_arabic(rs.getString("ccs_name_arabic"));
                item.setCcs_name_english(rs.getString("ccs_name_english"));
                item.setCcs_about(rs.getString("ccs_about"));
                item.setCcs_school_year(rs.getString("ccs_school_year"));
                item.setCcs_addedBy(rs.getInt("ccs_addedBy"));
                item.setCcs_addedDate(rs.getString("ccs_addedDate"));
                item.setCcs_description(rs.getString("ccs_description"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Community_service_session> getCommunity_service_session(Connection con, String sql) {

        try {
            ArrayList<Community_service_session> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Community_service_session item = new Community_service_session();
                item.setCss_id(rs.getInt("css_id"));
                item.setCss_name_arabic(rs.getString("css_name_arabic"));
                item.setCss_name_english(rs.getString("css_name_english"));
                item.setCss_about(rs.getString("css_about"));
                item.setCss_school_year(rs.getString("css_school_year"));
                item.setCss_addedBy(rs.getInt("css_addedBy"));
                item.setCss_addedDate(rs.getString("css_addedDate"));
                item.setCss_description(rs.getString("css_description"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Student_affairs_session> getStudent_affairs_session(Connection con, String sql) {

        try {
            ArrayList<Student_affairs_session> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Student_affairs_session item = new Student_affairs_session();
                item.setSas_id(rs.getInt("sas_id"));
                item.setSas_name_arabic(rs.getString("sas_name_arabic"));
                item.setSas_name_english(rs.getString("sas_name_english"));
                item.setSas_about(rs.getString("sas_about"));
                item.setSas_school_year(rs.getString("sas_school_year"));
                item.setSas_addedBy(rs.getInt("sas_addedBy"));
                item.setSas_addedDate(rs.getString("sas_addedDate"));
                item.setSas_description(rs.getString("sas_description"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Graduate_studies_session> getGraduate_studies_session(Connection con, String sql) {

        try {
            ArrayList<Graduate_studies_session> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Graduate_studies_session item = new Graduate_studies_session();
                item.setGss_id(rs.getInt("gss_id"));
                item.setGss_name_arabic(rs.getString("gss_name_arabic"));
                item.setGss_name_english(rs.getString("gss_name_english"));
                item.setGss_about(rs.getString("gss_about"));
                item.setGss_school_year(rs.getString("gss_school_year"));
                item.setGss_addedBy(rs.getInt("gss_addedBy"));
                item.setGss_addedDate(rs.getString("gss_addedDate"));
                item.setGss_description(rs.getString("gss_description"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Students> getStudents(Connection con, String sql) {

        try {
            ArrayList<Students> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Students item = new Students();
                item.setStud_id(rs.getInt("stud_id"));
                item.setStud_fname(rs.getString("stud_fname"));
                item.setStud_lname(rs.getString("stud_lname"));
                item.setStud_name_english(rs.getString("stud_name_english"));
                item.setStud_age(rs.getInt("stud_age"));
                item.setStud_email(rs.getString("stud_email"));
                item.setStud_religion(rs.getString("stud_religion"));
                item.setStud_nationality(rs.getString("stud_nationality"));
                item.setStud_address1(rs.getString("stud_address1"));
                item.setStud_address2(rs.getString("stud_address2"));
                item.setStud_ssid(rs.getInt("stud_ssid"));
                item.setStud_collage_year(rs.getInt("stud_collage_year"));
                item.setStud_dept_id(rs.getInt("stud_dept_id"));
                item.setStud_username(rs.getString("stud_username"));
                item.setStud_password(rs.getString("stud_password"));

                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Departments> getDepartments(Connection con, String sql) {

        try {
            ArrayList<Departments> data = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Departments item = new Departments();
                item.setDept_id(rs.getInt("dept_id"));
                item.setDept_name_arabic(rs.getString("dept_name_arabic"));
                item.setDept_name_english(rs.getString("dept_name_english"));
                item.setDept_description(rs.getString("dept_description"));
                item.setDept_price(rs.getString("dept_price"));
                item.setDept_date(rs.getString("dept_date"));
                item.setDept_prof_id(rs.getInt("dept_prof_id"));
                item.setDept_addedBy(rs.getInt("dept_addedBy"));
                item.setProf(rs.getString("prof"));

                try {
                    InputStream inputStream = ((rs.getBlob("dept_image").getBinaryStream()));
                    InputStream inputStream2 = ((rs.getBlob("prof_image").getBinaryStream()));

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

                    item.setBase64_dept(base64Image);

                    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
                    byte[] buffer2 = new byte[4096];
                    int bytesRead2 = -1;

                    while ((bytesRead2 = inputStream2.read(buffer2)) != -1) {
                        outputStream2.write(buffer2, 0, bytesRead2);
                    }

                    byte[] imageBytes2 = outputStream2.toByteArray();
                    String base64Image2 = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream2.close();
                    outputStream2.close();

                    item.setBase64_prof(base64Image2);
                } catch (Exception e) {
                    item.setBase64_prof("noImage");
                    item.setBase64_dept("noImage");

                }
                data.add(item);
            }

            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }

    }
}
