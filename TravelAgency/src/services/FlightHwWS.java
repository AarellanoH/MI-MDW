package services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import models.*;

@WebService(name="FlightBookingHwWS")
public class FlightHwWS {
	@WebMethod(operationName="makeBooking")
    @WebResult(name="result")
    public FlightBooking hwFlightBooking(@WebParam(name="type") String type, @WebParam(name="name") String name, @WebParam(name="destination") int destination){
    	try{
			if(FlightDestinations.getFlights().containsKey(destination)){
				Flight flight = FlightDestinations.getFlights().get(destination);
				if (flight.getCapacity() >= flight.getOccupation() +1){
//					flight.setOccupation(flight.getOccupation() +1);
//					FlightDestinations.getFlights().put(destination, flight);
					FlightBooking flightBooking = new FlightBooking(name, flight.getDepartureDate(), flight.getDepartureAirport(),
							flight.getArrivalDate(), flight.getArrivalAirport());
//					DB.insertFlightBooking(flightBooking);
					System.out.println("Flight: " + flightBooking);
					return flightBooking;
				}
				else
//					return "Sorry, flight is full";
					return null;
			}
			else
//				return "Flight does not exist";
				return null;
		}
		catch(Exception e){
			e.printStackTrace();
//			return "Error making flight booking";
			return null;
		}
    }
}
