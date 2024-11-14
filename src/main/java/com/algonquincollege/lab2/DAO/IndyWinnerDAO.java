/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DAO;

/**
 *
 * @author mzr_u
 */
import com.algonquincollege.lab2.Model.IndyWinner;
import java.util.List;

public interface IndyWinnerDAO {
    List<IndyWinner> getIndyWinners(int offset, int limit);
}
