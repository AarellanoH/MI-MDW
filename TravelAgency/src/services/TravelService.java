package services;

import models.*;

import java.sql.Timestamp;

import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class TravelService {
	/*SELECTS*/
	public Trip[] getAllTrips(){
		return DB.selectTrips();
	}
	
	public FlightBooking[] getAllFlightBookings(){
		return DB.selectFlightBookings();
	}
	
	public TripBooking[] getBookingsFromTrip(int tripId){
		Trip trip = DB.selectTrip(tripId);
		TripBooking[] bookings = DB.selectBookingsFromTrip(trip);
		return bookings;
	}

	
	/*INSERTS*/
	public boolean insertTrip(String title, int capacity, int occupation){
		Trip trip = new Trip(title, capacity, occupation);
		return DB.insertTrip(trip);
	}
	
	public boolean insertFlightBooking(String passengerName, String departureDate, String departureAirport, String arrivalDate, String arrivalAirport){
		try{
			FlightBooking flightBooking = new FlightBooking(passengerName, departureDate, departureAirport, arrivalDate, arrivalAirport);
			return DB.insertFlightBooking(flightBooking);
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean bookTrip(int tripId, String personName){
		Trip trip = DB.selectTrip(tripId);
		TripBooking tripBooking = new TripBooking(trip, personName);
		
		boolean correctInsert = DB.insertBooking(tripBooking);
		
		if (correctInsert){
			trip.setOccupation(trip.getOccupation() + 1);
			DB.updateTrip(trip);
		}
		
		return correctInsert;
		
	}
	
	public Object makeBooking(String type, String personName, int destinationId){
		return GeneralBooker.makeBooking(type, personName, destinationId);
	}
	
	/*UPDATES*/
	public boolean updateFlightBooking(int id, String passengerName, String departureDate, String departureAirport, String arrivalDate, String arrivalAirport){
		try{			
			FlightBooking flightBooking = DB.selectFlightBooking(id);
			flightBooking.setPassengerName(passengerName);
			flightBooking.setDepartureDate(departureDate);
			flightBooking.setDepartureAirport(departureAirport);
			flightBooking.setArrivalDate(arrivalDate);
			flightBooking.setArrivalAirport(arrivalAirport);
			
			return DB.updateFlightBooking(flightBooking);
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/*DELETES*/
	public boolean deleteFlightBooking(int id){
		try{
			FlightBooking flightBooking = DB.selectFlightBooking(id);
			if (flightBooking != null){
				return DB.deleteFlightBooking(flightBooking);
			}
			else
				return false;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}