package data_access;

import classes.LibraryWorker;

import java.sql.*;


public class LibraryWorkerDataAccess {

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

        public static int insertLibraryWorker(LibraryWorker libraryWorker) {

            int status = 0;

            String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Librarian]"
                    + "VALUES (?, ?, ?, ?, ?)";

            try {

                Connection connection = getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, libraryWorker.getUserName());
                preparedStatement.setString(2, libraryWorker.getUserSurName());
                preparedStatement.setString(3, libraryWorker.getLogin());
                preparedStatement.setString(4, libraryWorker.getPassword());
                preparedStatement.setInt(5, libraryWorker.getAccountType());

                status = preparedStatement.executeUpdate();

                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return status;
        }

        public static int updateLibraryWorker(LibraryWorker libraryWorker) {

            int status = 0;

            String sqlQuery = "UPDATE [LibraryProject_v2].[dbo].[Librarian]" +
                    "SET name = ?, surname = ?, login = ?, password = ?, account_type = ? WHERE librarian_id = ?";


            try {

                Connection connection = getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, libraryWorker.getUserName());
                preparedStatement.setString(2, libraryWorker.getUserSurName());
                preparedStatement.setString(3, libraryWorker.getLogin());
                preparedStatement.setString(4, libraryWorker.getPassword());
                preparedStatement.setInt(5, libraryWorker.getAccountType());
                preparedStatement.setInt(6, libraryWorker.getUserId());

                status = preparedStatement.executeUpdate();

                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return status;

        }
}
