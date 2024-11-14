/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DAO.impl;

/**
 *
 * @author mzr_u
 */
import com.algonquincollege.lab2.DAO.IndyWinnerDAO;
import com.algonquincollege.lab2.DB.DatabaseConnection;
import com.algonquincollege.lab2.Model.IndyWinner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndyWinnerDAOImpl implements IndyWinnerDAO {
    private static final String DB_URL = "jdbc:mysql://localhost/IndyWinners";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    @Override
    public List<IndyWinner> getIndyWinners(int offset, int limit) {
        List<IndyWinner> winners = new ArrayList<>();
        String query = "SELECT * FROM IndyWinners ORDER BY year DESC LIMIT ? OFFSET ?";

  
        
        try {
            Connection con = DatabaseConnection.getInstance();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    winners.add(new IndyWinner(
                        rs.getInt("YEAR"),
                        rs.getString("DRIVER"),
                        rs.getDouble("AVERAGESPEED"),
                        rs.getString("COUNTRY")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return winners;
    }
}