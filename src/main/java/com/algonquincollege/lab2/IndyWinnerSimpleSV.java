package com.algonquincollege.lab2;


import com.algonquincollege.lab2.DAO.IndyWinnerDAO;
import com.algonquincollege.lab2.DAO.impl.IndyWinnerDAOImpl;
import com.algonquincollege.lab2.Model.IndyWinner;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
//import java.sql.*;
import javax.servlet.annotation.WebServlet;


/**
 * <p>
 * A simple servlet that retrieves Indy 500 winner information from a database 
 * and formats it into an HTML table.
 * </p>
 */
@WebServlet(urlPatterns = {"/IndyWinnerSimpleSV"})
public class IndyWinnerSimpleSV extends HttpServlet {

    private IndyWinnerDAO dao;

    /**
     * Initializes the servlet and sets up the DAO for database access.
     */
    @Override
    public void init() {
        dao = new IndyWinnerDAOImpl();
    }
    

    /**
     * Handles HTTP GET requests to retrieve and display Indy 500 winners.
     *
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String pageParam = request.getParameter("page");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int limit = 10;
        int offset = (page - 1) * limit;

        List<IndyWinner> winners = dao.getIndyWinners(offset, limit);

        StringBuilder buffer = new StringBuilder();
        formatPageHeader(buffer);

        if (winners.isEmpty()) {
            buffer.append("<p>No winners found.</p>");
        } else {
            formatWinnersTable(buffer, winners);
        }

        buffer.append("<br>");
        buffer.append("<form action=\"IndyWinnerSimpleSV\" method=\"GET\">");
        buffer.append("<input type=\"hidden\" name=\"page\" value=\"" + (page + 1) + "\">");
        buffer.append("<input type=\"submit\" value=\"Continue\">");
        buffer.append("</form>");
        buffer.append("</html>");

        response.getWriter().write(buffer.toString());
    }

    /**
     * Formats the page header for the HTML output.
     *
     * @param buffer the StringBuilder to append the header content to
     */
    private void formatPageHeader(StringBuilder buffer) {
        buffer.append("<html><head><title>Indy 500 Winners</title></head>");
        buffer.append("<h2><center>Indianapolis 500 Winners</center></h2><br>");
    }

    /**
     * Formats the winners table in HTML.
     *
     * @param buffer  the StringBuilder to append the table content to
     * @param winners the list of IndyWinner objects to display
     */
    private void formatWinnersTable(StringBuilder buffer, List<IndyWinner> winners) {
        buffer.append("<center><table border>");
        buffer.append("<tr><th>Year</th><th>Driver</th><th>Average Speed</th><th>Country</th></tr>");

        for (IndyWinner winner : winners) {
            buffer.append("<tr>");
            buffer.append("<td>").append(winner.getYear()).append("</td>");
            buffer.append("<td>").append(winner.getDriver()).append("</td>");
            buffer.append("<td>").append(winner.getAverageSpeed()).append("</td>");
            buffer.append("<td>").append(winner.getCountry()).append("</td>");
            buffer.append("</tr>");
        }

        buffer.append("</table></center>");
    }
}