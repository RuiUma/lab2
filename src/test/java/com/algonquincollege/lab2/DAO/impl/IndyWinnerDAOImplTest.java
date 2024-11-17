/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DAO.impl;

import com.algonquincollege.lab2.DAO.IndyWinnerDAO;
import com.algonquincollege.lab2.DB.DatabaseConnection;
import com.algonquincollege.lab2.Model.IndyWinner;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mzr_u
 */
public class IndyWinnerDAOImplTest {
    private IndyWinnerDAO dao = new IndyWinnerDAOImpl();
    
    @Test
    void testGetIndyWinnersWithLimit() {
        List<IndyWinner> winners = dao.getIndyWinners(0, 10);
        assertNotNull(winners, "Winners list should not be null");
        assertEquals(10, winners.size(), "Winners list size should be 3");
    }

}
