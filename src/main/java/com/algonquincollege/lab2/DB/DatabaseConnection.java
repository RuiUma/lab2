/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DB;

/**
 *
 * @author mzr_u
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/IndyWinners?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    private DatabaseConnection() { }


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