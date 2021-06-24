package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String url = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v2;integratedSecurity=true";
    static Connection conn = null;

    private static DBConnection instance;

    private DBConnection() throws SQLException {
        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static synchronized DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }

        return instance;
    }






    //Other method of Singleton DB connection
   /* private DBConnection() {}

    public static Connection getDBConnection() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url);
                System.out.println("connection opened");
            } else if (conn.isClosed()) {
                conn = DriverManager.getConnection(url);
                System.out.println("connection opened");
            }
        } catch (SQLException ex) {
            System.out.println("smth went wrong");
            ex.printStackTrace();
        }

        return conn;
    }*/

}
