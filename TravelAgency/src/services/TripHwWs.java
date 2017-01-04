package services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import models.*;

@WebService(name="TripBookingHwWS")
public class TripHwWs {
	@WebMethod(operationName="makeBooking")
    @WebResult(name="result")
    public Trip hwTripBooking(@WebParam(name="type") String type, @WebParam(name="name") String name, @WebParam(name="destination") int destination){
//		return GeneralBooker.makeBooking("trip", name, destination);
    	try{
			Trip trip = DB.selectTrip(destination);
			if (trip != null){
				if (trip.getCapacity() >= trip.getOccupation() + 1){
					TripBooking tripBooking = new TripBooking(trip, name);
//					trip.setOccupation(trip.getOccupation() + 1);
//					DB.updateTrip(trip);
//					DB.insertBooking(tripBooking);
					System.out.println("TripBooking: " + tripBooking);
					System.out.println("Trip: " + trip);
//					return "Here it is a response!";
//					return trip;
					ArrayList<Object> al =new ArrayList<>();
					Object[] objects = new Object[4];
					objects[0] = "AAA";
					al.add("AAA");
					Integer myInt = new Integer(10);
//					return new IllegalArgumentException("Test");
					return trip;
				}
				else
//					return "Sorry, trip is full";
					return null;
				
			}
			else
//				return "Trip does not exist";
				return null;
		}
		catch (Exception e){
			e.printStackTrace();
//			return "Error making trip booking";
			return null;
		}
//		System.out.println("HwWS - Trip: " + type);
//		return null;
    }
}
