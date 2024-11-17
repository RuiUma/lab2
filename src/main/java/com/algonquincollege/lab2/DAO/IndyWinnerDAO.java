
package com.algonquincollege.lab2.DAO;

/**
 * Data Access Object interface for Indy 500 winners.
 */
import com.algonquincollege.lab2.Model.IndyWinner;
import java.util.List;

public interface IndyWinnerDAO {
    /**
     * Retrieves a list of Indy 500 winners from the database.
     *
     * @param offset the offset for pagination
     * @param limit  the maximum number of results to retrieve
     * @return a list of IndyWinner objects
     */
    List<IndyWinner> getIndyWinners(int offset, int limit);
}
