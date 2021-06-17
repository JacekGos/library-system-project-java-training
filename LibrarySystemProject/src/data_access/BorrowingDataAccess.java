package data_access;

import classes.LibraryUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BorrowingDataAccess {

    public static Connection getConnection() {

        String urlConnection = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v2;integratedSecurity=true";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;

    }

    public static int insertBorrowing(int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Borrowings]"
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, libraryElementId);
            preparedStatement.setTimestamp(2, borrowingDate);
            preparedStatement.setInt(3, borrowingStatusId);
            preparedStatement.setInt(4, libraryUserId);

            status = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static void getAllBorrowingsByUserId(int userId, LibraryUser libraryUser) {

        String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Borrowings] WHERE library_user_id = ?";

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt(1);
                int elementId = resultSet.getInt(2);
                java.sql.Timestamp date = resultSet.getTimestamp(3);
                int statusId = resultSet.getInt(4);
                int libraryUserId = resultSet.getInt(5);

                libraryUser.addBorrowing(borrowingId, elementId, date, statusId, libraryUserId);

            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
