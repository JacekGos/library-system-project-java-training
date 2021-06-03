package com.jacekg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDataBaseConnection {

    static String url = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v1;integratedSecurity=true";

    static Connection connObj;

    public static void sqlConnection(){

        try {
            connObj = DriverManager.getConnection(url);
            System.out.println("Connected to MS Sql Server");
        } catch (SQLException throwables) {
            System.out.println("Connection error");
            throwables.printStackTrace();
        }

    }



}
