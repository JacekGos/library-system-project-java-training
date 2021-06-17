package data_access;

import classes.Book;
import classes.LibraryUser;
import classes.LibraryWorker;
import classes.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDataAccess {

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

    public static int insertRequest(Request request) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Request]"
                + "VALUES (?, ?, ?)";

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, request.getBorrowingId());
            preparedStatement.setTimestamp(2, request.getRequestDate());
            preparedStatement.setInt(3,request.getStatusId());

            status = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<Request> getAllRequests(LibraryWorker libraryWorker) {

        List<Request> requestList = new ArrayList<Request>();

        String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Request]";

        try {

            Connection connection = getConnection();

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

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return requestList;

    }


}
