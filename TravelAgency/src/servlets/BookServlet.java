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
        name = "BookServlet", mapping = {"/bookServlet"}
)
public class BookServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        Trip trip = DB.selectTrip(tripId);
        
        

        request.setAttribute("trip", trip);
        request.getRequestDispatcher("./tripDetails.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tripId = Integer.parseInt(request.getParameter("tripId"));
		String personName = request.getParameter("personName");
		Trip trip = DB.selectTrip(tripId);
		
		TripBooking tripBooking = new TripBooking(trip, personName);
		
		DB.insertBooking(tripBooking);
		
		trip.setOccupation(trip.getOccupation() + 1);
		DB.updateTrip(trip);
		
		request.getRequestDispatcher("./index.jsp").forward(request, response);
		
	}
	
	
 
}