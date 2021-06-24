package data_access;

import classes.LibraryUser;

import java.sql.*;

public class BorrowingDataAccess {

    static Connection connection;

    static {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Creates entry with relation LibraryUser to LibraryElement.
     * @see LibraryUser
     * @see classes.LibraryElement
     *
     * @param libraryElementId int
     * @param borrowingDate
     * @param borrowingStatusId
     * @param libraryUserId
     * @return int
     *
     *
     */
    public static int insertBorrowing(int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Borrowings]"
                + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, libraryElementId);
            preparedStatement.setTimestamp(2, borrowingDate);
            preparedStatement.setInt(3, borrowingStatusId);
            preparedStatement.setInt(4, libraryUserId);

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static void getAllBorrowingsByUserId(int userId, LibraryUser libraryUser) {

        String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Borrowings] WHERE library_user_id = ?";

        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void getAllBorrowingsToReturnByUserId(int userId, LibraryUser libraryUser) {

        String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Borrowings] WHERE library_user_id = ? AND status_id = 5";

        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static int getLibraryElementByBorrowingId(int borrowingIdArg) {

        String sqlQuery = "SELECT [element_id] FROM [LibraryProject_v2].[dbo].[Borrowings] WHERE borrowing_id LIKE ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, borrowingIdArg);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int libraryElement = resultSet.getInt(1);

                return libraryElement;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static boolean checkIfBorrowingExists(int borrowingIdArg) {

        String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Borrowings] WHERE borrowing_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, borrowingIdArg);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt(1);

                if (borrowingId == borrowingIdArg) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int getLastBorrowingID(LibraryUser libraryUser) {

        String sqlQuery = "SELECT TOP(1) [borrowing_id] FROM [LibraryProject_v2].[dbo].[Borrowings] ORDER BY [borrowing_id] DESC";

        int lastBorrowingId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                lastBorrowingId = resultSet.getInt(1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lastBorrowingId;

    }

    public static int updateBorrowingStatus(int borrowingId, byte borrowingStatus) {

        String sqlQuery = "UPDATE [LibraryProject_v2].[dbo].[Borrowings]" +
                "SET status_id = ? WHERE borrowing_id LIKE ?";

        int status = 0;

        try {

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, borrowingStatus);
            preparedStatement.setInt(2, borrowingId);

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

}
