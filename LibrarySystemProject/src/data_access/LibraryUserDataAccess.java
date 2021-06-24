package data_access;

import classes.LibraryUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryUserDataAccess {

    static Connection connection;

    static {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int insertLibraryUser(LibraryUser libraryUser) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Library_user]"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, libraryUser.getUserName());
            preparedStatement.setString(2, libraryUser.getUserSurName());
            preparedStatement.setString(3, libraryUser.getLogin());
            preparedStatement.setString(4, libraryUser.getPassword());
            preparedStatement.setDouble(5, libraryUser.getPenalty());
            preparedStatement.setInt(6, libraryUser.getAccountType());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static int updateLibraryUser(LibraryUser libraryUser) {

        int status = 0;

        try {
            String sqlQuery = "UPDATE [LibraryProject_v2].[dbo].[Library_user]" +
                    "SET name = ?, surname = ?, login = ?, password = ?, penalty = ?, account_type = ? WHERE library_user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, libraryUser.getUserName());
            preparedStatement.setString(2, libraryUser.getUserSurName());
            preparedStatement.setString(3, libraryUser.getLogin());
            preparedStatement.setString(4, libraryUser.getPassword());
            preparedStatement.setDouble(5, libraryUser.getPenalty());
            preparedStatement.setInt(6, libraryUser.getAccountType());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static int deleteLibraryUser(int libraryUserId) {

        int status = 0;

        try {
            String sqlQuery = "DELETE FROM [LibraryProject_v2].[dbo].[Library_user] WHERE library_user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, libraryUserId);

            status = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static LibraryUser getLibraryUserByLogin(String login) {

        LibraryUser libraryUser = new LibraryUser();

        try {
            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user] WHERE login = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                libraryUser.setUserId(resultSet.getInt(1));
                libraryUser.setUserName(resultSet.getString(2));
                libraryUser.setUserSurName(resultSet.getString(3));
                libraryUser.setLogin(resultSet.getString(4));
                libraryUser.setPassword(resultSet.getString(5));
                libraryUser.setPenalty(resultSet.getDouble(6));
                libraryUser.setAccountType(resultSet.getInt(7));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUser;

    }

    public static int getNumberOfLibraryUsersByNameAndSurname(String name, String surName) {
        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user] WHERE name = ? AND surname = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LibraryUser libraryUser = new LibraryUser();
                libraryUser.setUserId(resultSet.getInt(1));
                libraryUser.setUserName(resultSet.getString(2));
                libraryUser.setUserSurName(resultSet.getString(3));
                libraryUser.setLogin(resultSet.getString(4));
                libraryUser.setPassword(resultSet.getString(5));
                libraryUser.setPenalty(resultSet.getDouble(6));
                libraryUser.setAccountType(resultSet.getInt(7));

                libraryUserList.add(libraryUser);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUserList.size();
    }

    public static List<LibraryUser> getAllLibraryUsersByNameSurNameId(String name, String surName, int userId) {
        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user] WHERE name LIKE ? OR surname LIKE ? OR library_user_id = ?";

            name = "%" + name + "%";
            surName = "%" + surName + "%";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surName);
            preparedStatement.setInt(3, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LibraryUser libraryUser = new LibraryUser();
                libraryUser.setUserId(resultSet.getInt(1));
                libraryUser.setUserName(resultSet.getString(2));
                libraryUser.setUserSurName(resultSet.getString(3));
                libraryUser.setLogin(resultSet.getString(4));
                libraryUser.setPassword(resultSet.getString(5));
                libraryUser.setPenalty(resultSet.getDouble(6));
                libraryUser.setAccountType(resultSet.getInt(7));

                libraryUserList.add(libraryUser);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUserList;
    }

}
