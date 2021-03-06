package data_access;

import classes.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MovieDataAccess {

    static Connection connection;

    static {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int insertMovie(Movie movie) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Library_element]"
                + "VALUES (?, ?, ?, NULL, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getTypeId());
            preparedStatement.setInt(3, movie.getSortId());
            preparedStatement.setInt(4, movie.getDurationTime());
            preparedStatement.setInt(5, movie.getStatusId());

            status = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<Movie> getAllMoviesByTitleAndSort(String title, String sort) {
        List<Movie> movieList = new ArrayList<Movie>();

        title = "%" + title + "%";
        sort = "%" + sort + "%";

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element] AS le" +
                            " INNER JOIN [LibraryProject_v2].[dbo].[Library_element_sort] AS s ON s.library_element_sort_id = le.sort_id" +
                            " WHERE title LIKE ?" +
                            " OR s.sort LIKE ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                byte checkType = resultSet.getByte(3);

                if (checkType == 2) {

                    Movie movie = new Movie();
                    movie.setLibraryElementId(resultSet.getInt(1));
                    movie.setTitle(resultSet.getString(2));
                    movie.setTypeId(resultSet.getByte(3));
                    movie.setSortId(resultSet.getInt(4));
                    movie.setDurationTime(resultSet.getInt(6));
                    movie.setStatusId(resultSet.getByte(7));

                    movieList.add(movie);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movieList;
    }

    public static Movie getMovieById(int movieId) {
        Movie movie = new Movie();

        try {
            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element] WHERE library_element_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, movieId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                movie.setLibraryElementId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setTypeId(resultSet.getByte(3));
                movie.setSortId(resultSet.getInt(4));
                movie.setDurationTime(resultSet.getInt(6));
                movie.setStatusId(resultSet.getInt(7));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movie;

    }




}
