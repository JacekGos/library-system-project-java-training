package com.jacekg;

import java.sql.*;

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
            //insertSqlDb();
            //selectSqlDb();
            removeSqlDb("Dominik");

            connObj.close();

        } catch (SQLException throwables) {
            System.out.println("Connection error");
            throwables.printStackTrace();
        }

    }

    public static void insertSqlDb(){

        String sql = "INSERT INTO Librarian (name, surname, login, password, account_type)"
                + "VALUES (?, 'Gos', ?, 'a', 1)";

        try {
//            connObj = DriverManager.getConnection(url);

            PreparedStatement statement = connObj.prepareStatement(sql);

            statement.setString(2,"Jacek");
            statement.setString(1,"Gos");

            int rows = statement.executeUpdate();

            if (rows > 0){
                System.out.println("Rows has to be inserted");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void selectSqlDb(){

        String sql = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian] WHERE name = ?";

        try {

            System.out.println("Trying to select data");

            PreparedStatement statement = connObj.prepareStatement(sql);
            statement.setString(1,"Jacek");

            //Statement statement = connObj.createStatement();


            ResultSet result = statement.executeQuery();
            //ResultSet result = statement.getResultSet();

            while (result.next()) {

                int workerId = result.getInt("librarian_id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String login = result.getString("login");
/*                String password = result.getString("password");
                String account_type = result.getString("account_type");*/

                System.out.printf("Worker%d: %s %s; %s%n", workerId, name, surname, login);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeSqlDb(String param1) {

        String sqlQuery = "DELETE FROM [LibraryProject_v2].[dbo].[Librarian]"
                        + "WHERE name = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connObj.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param1);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.printf("%d rows affected%n", rows);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
