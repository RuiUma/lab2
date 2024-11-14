package com.algonquincollege.lab2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
 * This is a simple servlet that will use JDBC to gather all of the Indy 500
 * winner information from a database and format it into an HTML table.
 * No guarantees of meeting:
 *			Thread safety
 *			Does not adhere to "SOLID:
 *			No DAO pattern etc.
 *			No page scolling
 * This is "quick and dirty" simple DB table query, formats DB resultset to
 * an HTML table format
 */
@WebServlet(urlPatterns = {"/IndyWinnerSimpleSV"})
public class IndyWinnerSimpleSV extends HttpServlet {

    private IndyWinnerDAO dao;

    @Override
    public void init() {
        dao = new IndyWinnerDAOImpl();
    }

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

    private void formatPageHeader(StringBuilder buffer) {
        buffer.append("<html><head><title>Indy 500 Winners</title></head>");
        buffer.append("<h2><center>Indianapolis 500 Winners</center></h2><br>");
    }

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