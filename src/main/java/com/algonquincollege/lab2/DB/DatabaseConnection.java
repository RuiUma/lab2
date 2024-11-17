
package com.algonquincollege.lab2.DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for managing database connections.
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/IndyWinners?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private DatabaseConnection() { }


    /**
     * Retrieves the singleton instance of the database connection.
     *
     * @return a Connection object
     */
    public static Connection getInstance() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                        System.out.println("Database connection established.");
                    } catch (ClassNotFoundException e) {
                        System.err.println("MySQL JDBC Driver not found.");
                        e.printStackTrace();
                    } catch (SQLException e) {
                        System.err.println("Failed to connect to database.");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }


    
    /**
     * Closes the database connection.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close database connection.");
                e.printStackTrace();
            }
        }
    }
}