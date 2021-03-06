package data_access;

import classes.LibraryWorker;
import classes.Request;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDataAccess {

    static Connection connection;

    static {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int insertRequest(Request request) {
        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Request]"
                + "VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, request.getBorrowingId());
            preparedStatement.setTimestamp(2, request.getRequestDate());
            preparedStatement.setInt(3,request.getStatusId());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<Request> getAllRequests(LibraryWorker libraryWorker) {

        List<Request> requestList = new ArrayList<Request>();

        String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Request]";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Request request = new Request();
                request.setRequestId(resultSet.getInt(1));
                request.setBorrowingId(resultSet.getInt(2));
                request.setRequestDate(resultSet.getTimestamp(3));
                request.setStatusId(resultSet.getInt(4));

                requestList.add(request);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return requestList;
    }

    public static int getUserIdByBorrowingId(LibraryWorker libraryWorker, int borrowingId) {
        int userId = 0;

        String sqlQuery = "SELECT [library_user_id] FROM [LibraryProject_v2].[dbo].[Borrowings] AS b"
                + " INNER JOIN [LibraryProject_v2].[dbo].[Request] AS r"
                + " ON b.borrowing_id = r.borrowing_id "
                + " WHERE b.borrowing_id LIKE ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, borrowingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userId;
    }

    public static int getLibraryElementIdByRequestId(LibraryWorker libraryWorker, int requestId) {

        int libraryElementId = 0;

        String sqlQuery = "SELECT [element_id] FROM [LibraryProject_v2].[dbo].[Borrowings] AS b"
                + " INNER JOIN [LibraryProject_v2].[dbo].[Request] AS r"
                + " ON b.borrowing_id = r.borrowing_id "
                + " WHERE request_id LIKE ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, requestId);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                libraryElementId = resultSet.getInt(1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryElementId;
    }

    public static int updateRequestStatus(int requestId, byte option) {
        String sqlQuery = "UPDATE [LibraryProject_v2].[dbo].[Request]" +
                "SET status_id = 4 WHERE request_id LIKE ?";

        String sqlQuery2 = "UPDATE [LibraryProject_v2].[dbo].[Request]" +
                "SET status_id = 5 WHERE request_id LIKE ?";

        int status = 0;

        try {
            PreparedStatement preparedStatement = null;

            if (option == 1) {
                
                preparedStatement = connection.prepareStatement(sqlQuery);
                
            } else if (option == 2) {

                preparedStatement = connection.prepareStatement(sqlQuery2);
            }

            preparedStatement.setInt(1, requestId);

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;

    }

    public static int deleteRequest(int requestId) {
        int status = 0;

        try {
            String sqlQuery = "DELETE FROM [LibraryProject_v2].[dbo].[Request] WHERE request_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, requestId);

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }


}
