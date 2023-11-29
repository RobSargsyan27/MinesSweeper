package DBConnection;

import java.sql.*;

public abstract class DBConnection {
    DBConnection(){}

    public static Connection initiliseConnection(){
        String URL = "jdbc:postgresql://localhost:5432/datasampledb?currentSchema=minesweeper";
        String username = "admin";
        String password = "Admin.1";

        try{
            return DriverManager.getConnection(URL, username, password);
        }catch(SQLException e){
            System.out.println("Connection error occurs: " + e.getMessage());
        }

        return null;
    }

}
