package models;

import java.util.HashMap;

public class FlightDestinations {
	private static HashMap<Integer, Flight> flights = new HashMap<>();
	
	public static HashMap<Integer, Flight> getFlights(){
		if (flights.isEmpty()){
			initialize();
		}
		return flights;
			
	}
	
	private static void initialize(){
		flights.put(1, new Flight("France", 10, 0, "20-12-2016 8:00:0", "Prague International Airport", "20-12-2016 9:00:0", "France International Airport"));
		flights.put(2, new Flight("London", 20, 0, "21-12-2016 8:30:0", "Prague International Airport", "21-12-2016 10:30:0", "London International Airport"));
		flights.put(3, new Flight("Mexico", 5, 0, "21-12-2016 8:30:0", "Prague International Airport", "21-12-2016 20:00:0", "Mexico International Airport"));
		flights.put(4, new Flight("Brazil", 7, 7, "21-12-2016 8:30:0", "Prague International Airport", "21-12-2016 20:00:0", "Brazil International Airport"));
	}
}
