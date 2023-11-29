package DBConnection;

import java.sql.*;
import User.User;

public abstract class DBConnection {
    DBConnection(){}

    public static Connection initiliseConnection(){
        String URL = "jdbc:postgresql://localhost:5432/datasampledb?currentSchema=minesweeper";
        String username = "admin";
        String password = "Admin.1";

        try{
            Connection connection =  DriverManager.getConnection(URL, username, password);
            System.out.println("The connection was successful");

        }catch(SQLException e){
            System.out.println("Connection error occurs: " + e.getMessage());
        }
        return null;
    }

    public static User getUser(Connection connection, String username) {
        try {
            String preparedSQL = "SELECT * FROM users WHERE username = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("The user is not found!");
                resultSet.close();
                statement.close();
                return null;
            }

            User user = new User(
                    resultSet.getString("userName"),
                    resultSet.getString("bestGame"),
                    resultSet.getInt("gamesPlayed"),
                    resultSet.getInt("winsCount"),

            resultSet.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            System.out.println("Error occurred while getting a user: " + e.getMessage());
        }
        return null;
    }

    public static void setUser(Connection connection, String username){
        try{
            String preparedSQL = "INSERT INTO users(username) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            statement.setString(1, username);

            statement.executeUpdate();
            System.out.println("The user is successfully added!");
            statement.close();
        }catch (SQLException e){
            System.out.println("Error occurred while setting a user: " + e.getMessage());
        }
    }

    public static void updateUser(Connection connection, User user){
        try{
            String username = user.getUserName();
            int winsCount = user.getWinsCount();
            int gamesPlayed = user.getGamesPlayed();
            String bestGame = user.getBestGame();

            String preparedSQL = "UPDATE users SET winsCount = ?, gamedPlayed = ?, bestGame = ? WHERE userName = ?";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            statement.setInt(1, winsCount );
            statement.setInt(2, gamesPlayed );
            statement.setString(3, bestGame );
            statement.setString(4, username );
            statement.executeUpdate();

            System.out.println("The user is successfully updated!");
            statement.close();
        }catch (SQLException e){
            System.out.println("Error occurred while setting a user: " + e.getMessage());
        }
    }

    public static

}
