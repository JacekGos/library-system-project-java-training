package com.jacekg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDataBaseConnection {

    static String url = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v2;integratedSecurity=true";

    static Connection connObj;

    /**
     * C - create - tworzy
     *
     */
    public static void sqlConnection(){

        try {
            connObj = DriverManager.getConnection(url);
            System.out.println("Connected to MS Sql Server");
        } catch (SQLException throwables) {
            System.out.println("Connection error");

            throwables.printStackTrace();
        }

    }

    public void insertSqlDb(){

        String sql = "INSERT INTO Librarian (name, surname, login, password, account_type)"
                + "VALUES ('Wojtek', 'Gos', 'wojtekg', 'a', 1)";

        Statement statement = null;
        try {
            connObj = DriverManager.getConnection(url);
            statement = connObj.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int rows = statement.executeUpdate(sql);

        if (rows > 0){
            System.out.println("Rows has to be inserted");
        }

        connObj.close();

    }



}
