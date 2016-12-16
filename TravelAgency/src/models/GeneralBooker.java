package models;

public class GeneralBooker {
	public static Object makeBooking(String type, String personName, int destinationId){
		if (type.equals("trip")){
			try{
				Trip trip = DB.selectTrip(destinationId);
				if (trip != null){
					if (trip.getCapacity() >= trip.getOccupation() + 1){
						TripBooking tripBooking = new TripBooking(trip, personName);
						trip.setOccupation(trip.getOccupation() + 1);
						DB.updateTrip(trip);
						DB.insertBooking(tripBooking);
						return tripBooking;
					}
					else
						return "Sorry, trip is full";
				}
				else
					return "Trip does not exist";
			}
			catch (Exception e){
				e.printStackTrace();
				return "Error making trip booking";
			}
		}
		else if(type.equals("flight")){
			try{
				if(FlightDestinations.getFlights().containsKey(destinationId)){
					Flight flight = FlightDestinations.getFlights().get(destinationId);
					if (flight.getCapacity() >= flight.getOccupation() +1){
						flight.setOccupation(flight.getOccupation() +1);
						FlightDestinations.getFlights().put(destinationId, flight);
						FlightBooking flightBooking = new FlightBooking(personName, flight.getDepartureDate(), flight.getDepartureAirport(),
								flight.getArrivalDate(), flight.getArrivalAirport());
						DB.insertFlightBooking(flightBooking);
						return flightBooking;
					}
					else
						return "Sorry, flight is full";
				}
				else
					return "Flight does not exist";
			}
			catch(Exception e){
				e.printStackTrace();
				return "Error making flight booking";
			}
		}
		else
			return "Unvalid type entered";
	}
}
