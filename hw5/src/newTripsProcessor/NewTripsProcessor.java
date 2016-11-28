package newTripsProcessor;

import java.util.ArrayList;
import java.util.List;

import jmsTools.JMSConsumer;
import model.Order;
import model.Trip;

public class NewTripsProcessor {
	
	private static List<Trip> trips;
	
	public static void main(String[] args) throws Exception {
		String incomingQueueName = "mdw-hw5-new_trips_queue" ;
		trips = new ArrayList<Trip>();
		
		JMSConsumer consumer = new NewTripsProcessorConsumer();
        consumer.receive(incomingQueueName);
	}
	
	public static void addTrip(Trip trip) {
		trips.add(trip);
		System.out.println("Trip added: "+ trip);
	}

}
