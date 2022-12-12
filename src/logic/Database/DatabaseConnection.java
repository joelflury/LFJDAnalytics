package logic.Database;

import java.sql.*;

public class DatabaseConnection {

    private String url;
    private String user;
    private String pass;
    private Connection con;

    public DatabaseConnection(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;

    }

    public void connect() {
        try {
            con = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getArticles() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from article");
            while (rs.next()) {
//                new Article(rs.getInt("articleID"), rs.getString("articlename"), rs.getDouble("articleprice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
