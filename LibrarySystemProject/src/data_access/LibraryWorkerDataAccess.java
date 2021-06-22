package data_access;

import classes.LibraryWorker;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryWorkerDataAccess {

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

        public static LibraryWorker getLibraryWorkerById(int libraryWorkerId) {

            LibraryWorker libraryWorker = new LibraryWorker();

            try {
                Connection connection = getConnection();

                String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian] WHERE librarian_id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, libraryWorkerId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {

                    libraryWorker.setUserId(resultSet.getInt(1));
                    libraryWorker.setUserName(resultSet.getString(2));
                    libraryWorker.setUserSurName(resultSet.getString(3));
                    libraryWorker.setLogin(resultSet.getString(4));
                    libraryWorker.setPassword(resultSet.getString(5));
                    libraryWorker.setAccountType(resultSet.getInt(6));

                }

                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return libraryWorker;

        }

        public static LibraryWorker getLibraryWorkerByLogin(String login) {

            LibraryWorker libraryWorker = new LibraryWorker();

            try {

                Connection connection = getConnection();

                String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian] WHERE login = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, login);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {

                    libraryWorker.setUserId(resultSet.getInt(1));
                    libraryWorker.setUserName(resultSet.getString(2));
                    libraryWorker.setUserSurName(resultSet.getString(3));
                    libraryWorker.setLogin(resultSet.getString(4));
                    libraryWorker.setPassword(resultSet.getString(5));
                    libraryWorker.setAccountType(resultSet.getInt(6));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return libraryWorker;

        }

        public static List<LibraryWorker> getAllLibraryWorkers() {

            List<LibraryWorker> libraryWorkerList = new ArrayList<LibraryWorker>();

            Connection connection = getConnection();

            try {

                String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian]";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    LibraryWorker libraryWorker = new LibraryWorker();
                    libraryWorker.setUserId(resultSet.getInt(1));
                    libraryWorker.setUserName(resultSet.getString(2));
                    libraryWorker.setUserSurName(resultSet.getString(3));
                    libraryWorker.setLogin(resultSet.getString(4));
                    libraryWorker.setPassword(resultSet.getString(5));
                    libraryWorker.setAccountType(resultSet.getInt(6));

                    libraryWorkerList.add(libraryWorker);
                }

                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return libraryWorkerList;
        }

    public static int getNumberOfLibraryWorkersByNameAndSurname(String name, String surName) {

        List<LibraryWorker> libraryWorkerList = new ArrayList<LibraryWorker>();

        Connection connection = getConnection();

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian] WHERE name = ? AND surname = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LibraryWorker libraryWorker = new LibraryWorker();
                libraryWorker.setUserId(resultSet.getInt(1));
                libraryWorker.setUserName(resultSet.getString(2));
                libraryWorker.setUserSurName(resultSet.getString(3));
                libraryWorker.setLogin(resultSet.getString(4));
                libraryWorker.setPassword(resultSet.getString(5));
                libraryWorker.setAccountType(resultSet.getInt(6));

                libraryWorkerList.add(libraryWorker);
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryWorkerList.size();
    }

    public static List<LibraryWorker> getAllLibraryWorkerByNameSurNameId(String name, String surName, int userId) {

        List<LibraryWorker> libraryWorkerList = new ArrayList<LibraryWorker>();

        Connection connection = getConnection();

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian] WHERE name LIKE ? OR surname LIKE ? OR librarian_id = ?";

            name = "%" + name + "%";
            surName = "%" + surName + "%";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surName);
            preparedStatement.setInt(3, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LibraryWorker libraryWorker = new LibraryWorker();
                libraryWorker.setUserId(resultSet.getInt(1));
                libraryWorker.setUserName(resultSet.getString(2));
                libraryWorker.setUserSurName(resultSet.getString(3));
                libraryWorker.setLogin(resultSet.getString(4));
                libraryWorker.setPassword(resultSet.getString(5));
                libraryWorker.setAccountType(resultSet.getInt(6));

                libraryWorkerList.add(libraryWorker);
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryWorkerList;
    }

    public static int updateLibraryWorker(LibraryWorker libraryWorker) {

        int status = 0;

        try {

            Connection connection = getConnection();

            String sqlQuery = "UPDATE [LibraryProject_v2].[dbo].[Librarian]" +
                    "SET name = ?, surname = ?, login = ?, password = ?, account_type = ? WHERE librarian_id = ?";

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

    public static int deleteLibraryWorker(int libraryWorkerId) {

        int status = 0;

        try {

            Connection connection = getConnection();

            String sqlQuery = "DELETE FROM [LibraryProject_v2].[dbo].[Librarian] WHERE librarian_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, libraryWorkerId);

            status = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

}
