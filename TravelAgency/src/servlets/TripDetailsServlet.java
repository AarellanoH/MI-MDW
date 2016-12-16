package servlets;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.*;
import weblogic.servlet.annotation.WLServlet;
 
@WLServlet (
        name = "tripDetails", mapping = {"/tripDetails"}
)
public class TripDetailsServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        int id = Integer.parseInt(request.getParameter("id"));
        Trip trip = DB.selectTrip(id);
        TripBooking[] bookings = DB.selectBookingsFromTrip(trip);

        request.setAttribute("trip", trip);
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("./tripDetails.jsp").forward(request, response);
    }
 
}