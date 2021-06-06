package data_access;

import classes.LibraryElement;
import classes.LibraryWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryElementDataAccess {

    public static Connection getConnection() {

        String urlConnection = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v2;integratedSecurity=true";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlConnection);

            System.out.println("Connected to data base");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;

    }

    public static int insertLibraryElement (LibraryElement libraryElement) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Librarian]"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, libraryElement.getUserName());
            preparedStatement.setString(2, libraryElement.getUserSurName());
            preparedStatement.setString(3, libraryElement.getLogin());
            preparedStatement.setString(4, libraryElement.getPassword());
            preparedStatement.setInt(5, libraryElement.getAccountType());

            status = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }


}
