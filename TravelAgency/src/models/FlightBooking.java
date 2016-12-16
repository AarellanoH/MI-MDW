package models;

public class FlightBooking {
	private int id;
	private String passengerName;
	private String departureDate;
	private String departureAirport;
	private String arrivalDate;
	private String arrivalAirport;
	
	public FlightBooking(){}
	
	public FlightBooking(String passengerName, String departureDate, String departureAirport, String arrivalDate,
			String arrivalAirport) {
		super();
		this.passengerName = passengerName;
		this.departureDate = departureDate;
		this.departureAirport = departureAirport;
		this.arrivalDate = arrivalDate;
		this.arrivalAirport = arrivalAirport;
	}

	public FlightBooking(int id, String passengerName, String departureDate, String departureAirport,
			String arrivalDate, String arrivalAirport) {
		super();
		this.id = id;
		this.passengerName = passengerName;
		this.departureDate = departureDate;
		this.departureAirport = departureAirport;
		this.arrivalDate = arrivalDate;
		this.arrivalAirport = arrivalAirport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
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
		return "FlightBooking [id=" + id + ", passengerName=" + passengerName + ", departureDate=" + departureDate
				+ ", departureAirport=" + departureAirport + ", arrivalDate=" + arrivalDate + ", arrivalAirport="
				+ arrivalAirport + "]";
	}
	
	

}
