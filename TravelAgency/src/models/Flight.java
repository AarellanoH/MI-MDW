package models;

public class Flight {
	private String destination;
	private int capacity;
	private int occupation;
	private String departureDate;
	private String departureAirport;
	private String arrivalDate;
	private String arrivalAirport;
	
	
	
	
	public Flight(String destination, int capacity, int occupation, String departureDate, String departureAirport,
			String arrivalDate, String arrivalAirport) {
		super();
		this.destination = destination;
		this.capacity = capacity;
		this.occupation = occupation;
		this.departureDate = departureDate;
		this.departureAirport = departureAirport;
		this.arrivalDate = arrivalDate;
		this.arrivalAirport = arrivalAirport;
	}




	public String getDestination() {
		return destination;
	}




	public void setDestination(String destination) {
		this.destination = destination;
	}




	public int getCapacity() {
		return capacity;
	}




	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}




	public int getOccupation() {
		return occupation;
	}




	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}




	public String getDepartureDate() {
		return departureDate;
	}




	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}




	public String getDepartureAirport() {
		return departureAirport;
	}




	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}




	public String getArrivalDate() {
		return arrivalDate;
	}




	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}




	public String getArrivalAirport() {
		return arrivalAirport;
	}




	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}




	@Override
	public String toString() {
		return "Flight [destination=" + destination + ", capacity=" + capacity + ", occupation=" + occupation
				+ ", departureDate=" + departureDate + ", departureAirport=" + departureAirport + ", arrivalDate="
				+ arrivalDate + ", arrivalAirport=" + arrivalAirport + "]";
	}
	
	
}
