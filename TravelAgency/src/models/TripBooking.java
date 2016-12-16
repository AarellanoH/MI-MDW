package models;

public class TripBooking {
	private int id;
	private Trip trip;
	private String personName;
	
	public TripBooking(){}
	
	public TripBooking(int id, Trip trip, String personName) {
		super();
		this.id = id;
		this.trip = trip;
		this.personName = personName;
	}
	
	public TripBooking(Trip trip, String personName){
		super();
		this.trip = trip;
		this.personName = personName;
	}
	
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
