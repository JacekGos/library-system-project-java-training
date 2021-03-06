package data_access;

import classes.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDataAccess {

    static Connection connection;

    static {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int insertBook(Book book) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Library_element]"
                + "VALUES (?, ?, ?, ?, NULL, ?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getTypeId());
            preparedStatement.setInt(3, book.getSortId());
            preparedStatement.setInt(4, book.getPagesNumber());
            preparedStatement.setInt(5, book.getStatusId());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<Book> getAllBooksByTitleAndSort(String title, String sort) {

        List<Book> bookList = new ArrayList<Book>();

        title = "%" + title + "%";
        sort = "%" + sort + "%";

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element] AS le" +
                            " INNER JOIN [LibraryProject_v2].[dbo].[Library_element_sort] AS s ON s.library_element_sort_id = le.sort_id" +
                            " WHERE title LIKE ?" +
                            " OR s.sort LIKE  ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                byte checkType = resultSet.getByte(3);

                if (checkType == 1) {
                    Book book = new Book();
                    book.setLibraryElementId(resultSet.getInt(1));
                    book.setTitle(resultSet.getString(2));
                    book.setTypeId(resultSet.getByte(3));
                    book.setSortId(resultSet.getInt(4));
                    book.setPagesNumber(resultSet.getInt(5));
                    book.setStatusId(resultSet.getInt(7));

                    bookList.add(book);
                }

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bookList;
    }

    public static Book getBookById(int bookId) {

        Book book = new Book();

        try {
            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element] WHERE library_element_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, bookId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                book.setLibraryElementId(resultSet.getInt(1));
                book.setTitle(resultSet.getString(2));
                book.setTypeId(resultSet.getByte(3));
                book.setSortId(resultSet.getInt(4));
                book.setPagesNumber(resultSet.getInt(5));
                book.setStatusId(resultSet.getInt(7));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return book;

    }

    //This method is used for Book and Movie element type
    public static int updateLibraryElementStatusById(int bookId, int statusId) {

        int status = 0;

        try {
            System.out.println("Update...");

            String sqlQuery = "UPDATE [LibraryProject_v2].[dbo].[Library_element]" +
                    "SET status_id = ? WHERE library_element_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, bookId);

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;

    }

    //This method is used for Book and Movie element type
    public static int deleteLibraryElement(int libraryElementId) {

        int status = 0;

        try {

            String sqlQuery = "DELETE FROM [LibraryProject_v2].[dbo].[Library_element] WHERE library_element_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, libraryElementId);

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

}
