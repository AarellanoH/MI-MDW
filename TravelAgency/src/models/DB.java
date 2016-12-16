package models;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;

public class DB {
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String dbURL = "jdbc:derby:travelAgency";
	private static String tableName = "TRIP";
	// jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void main(String[] args){
    	try {
    		resetFlightBookingTable();
    		insertSampleFlightBookings();
    		System.out.println("All : " + Arrays.toString(DB.selectFlightBookings()));
    		
    		FlightBooking flightBooking = DB.selectFlightBooking(1);
    		flightBooking.setPassengerName("Alejandro");
    		DB.deleteFlightBooking(flightBooking);
    		
    		System.out.println("All : " + Arrays.toString(DB.selectFlightBookings()));
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    
    /*TABLES AND RELATED*/
	public static void resetDatabase() throws SQLException {
		createConnection();
        DatabaseMetaData dbmd = conn.getMetaData();
        
        //Drop the trip table in case it exists.
        ResultSet rs = dbmd.getTables(null, null, "TRIP", null);
        String sql;
        if (rs.next()) {
            sql = "DROP TABLE TRIP";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
        //Create trip table.
        String createTableTrip= "CREATE TABLE TRIP ("
        		+ "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
        		+ "TITLE VARCHAR(100), "
        		+ "CAPACITY INT, "
        		+ "OCCUPATION INT"
        		+ ")";
        PreparedStatement ps = conn.prepareStatement(createTableTrip);
        ps.executeUpdate();
        
        
        
        //Drop the booking table in case it exists.
        rs = dbmd.getTables(null, null, "BOOKING", null);
        if (rs.next()) {
            sql = "DROP TABLE BOOKING";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        //Create booking table.
      //Create trip table.
        String createBookingTrip= "CREATE TABLE BOOKING ("
        		+ "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
        		+ "TRIPID INT, "
        		+ "PERSONNAME VARCHAR(100)"
        		+ ")";
        ps = conn.prepareStatement(createBookingTrip);
        ps.executeUpdate();
        
    }
	
	public static void resetFlightBookingTable() throws SQLException{
		createConnection();
        DatabaseMetaData dbmd = conn.getMetaData();
        
      //Drop the flighBookings table in case it exists.
        ResultSet rs = dbmd.getTables(null, null, "FLIGHTBOOKING", null);
        String sql;
        if (rs.next()) {
            sql = "DROP TABLE FLIGHTBOOKING";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
      //Create flightBookings table.
        String createFlightBooking= "CREATE TABLE FLIGHTBOOKING ("
        		+ "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
        		+ "PASSENGERNAME VARCHAR(100), "
        		+ "DEPARTUREDATE VARCHAR(100), "
        		+ "DEPARTUREAIRPORT VARCHAR(100), "
        		+ "ARRIVALDATE VARCHAR(100), "
        		+ "ARRIVALAIRPORT VARCHAR(100)"
        		+ ")";
        PreparedStatement ps = conn.prepareStatement(createFlightBooking);
        ps.executeUpdate();
	}
	
	public static void insertSampleTrips(){
		Trip[] trips = new Trip[3];
		trips[0] = new Trip("Rome", 10, 0);
		trips[1] = new  Trip("Mexico", 20, 0);
		trips[2] = new Trip("Budapest", 30, 0);
		
		insertTrips(trips);
	}
	
	public static void insertSampleFlightBookings(){
		String departureDate = "2016-12-27";
		String arrivalDate = "2016-12-27";
		
		FlightBooking flightBooking = new FlightBooking("Alejandro", departureDate, "France", arrivalDate, "Germany");
		DB.insertFlightBooking(flightBooking);
		
		departureDate = "2017-01-01";
		arrivalDate = "2017-01-01";
		flightBooking = new FlightBooking("John", departureDate, "Hamburg", arrivalDate, "Prague");
		DB.insertFlightBooking(flightBooking);
	}
	
	
	/*INSERTS*/
	public static boolean insertTrip(Trip trip){
		createConnection();
		try{
			String sql = "INSERT INTO TRIP (TITLE, CAPACITY, OCCUPATION) VALUES (?,?,?)";
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, trip.getTitle());
			insert.setInt(2, trip.getCapacity());
			insert.setInt(3, trip.getOccupation());
			int rows = insert.executeUpdate();
			
    		return rows > 0;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
	}

	public static boolean insertTrips(Trip[] trips){
		createConnection();
		try{
			int rows = 0;
			for (int i = 0; i < trips.length; i++){
				String sql = "INSERT INTO TRIP (TITLE, CAPACITY, OCCUPATION) VALUES (?,?,?)";
				PreparedStatement insert = conn.prepareStatement(sql);
				insert.setString(1, trips[i].getTitle());
				insert.setInt(2, trips[i].getCapacity());
				insert.setInt(3, trips[i].getOccupation());
				rows += insert.executeUpdate();
			}
    		
    		return rows == trips.length;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
	}
	
	public static boolean insertFlightBooking(FlightBooking flightBooking){
		createConnection();

		try{
			String sql = "INSERT INTO FLIGHTBOOKING (PASSENGERNAME, DEPARTUREDATE, DEPARTUREAIRPORT, ARRIVALDATE, ARRIVALAIRPORT)" + 
							" VALUES (?,?,?,?,?)";
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, flightBooking.getPassengerName());
			insert.setString(2, flightBooking.getDepartureDate());
			insert.setString(3, flightBooking.getDepartureAirport());
			insert.setString(4, flightBooking.getArrivalDate());
			insert.setString(5, flightBooking.getArrivalAirport());
			int rows = insert.executeUpdate();
			
    		return rows > 0;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
	}
	
	public static boolean insertBooking(TripBooking tripBooking) {
		createConnection();
		try{
			int tripId = tripBooking.getTrip().getId();
			String personName = tripBooking.getPersonName();
    		stmt = conn.createStatement();
    		String query = "INSERT INTO BOOKING (TRIPID, PERSONNAME) VALUES (" + tripId + ",'" + personName + "')" ;
    		int rows = stmt.executeUpdate(query);
    		
    		return rows > 0;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
	}
	
	/*UPDATES*/
	public static boolean updateTrip(Trip trip){
		createConnection();
		try{
    		String sql = "UPDATE TRIP SET TITLE = ?, CAPACITY = ?, OCCUPATION = ? WHERE ID = ?";
    		PreparedStatement update = conn.prepareStatement(sql);
    		update.setString(1, trip.getTitle());
    		update.setInt(2, trip.getCapacity());
    		update.setInt(3, trip.getOccupation());
    		update.setInt(4, trip.getId());
    		
    		int rows = update.executeUpdate();
    		
    		return rows > 0;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
	}
	
	public static boolean updateFlightBooking(FlightBooking flightBooking){
		createConnection();
		try{
    		String sql = "UPDATE FLIGHTBOOKING SET PASSENGERNAME = ?, DEPARTUREDATE = ?, DEPARTUREAIRPORT = ?, " + 
    					" ARRIVALDATE = ?, ARRIVALAIRPORT = ? WHERE ID = ?";
    		PreparedStatement update = conn.prepareStatement(sql);
    		update.setString(1, flightBooking.getPassengerName());
    		update.setString(2, flightBooking.getDepartureDate());
    		update.setString(3, flightBooking.getDepartureAirport());
    		update.setString(4, flightBooking.getArrivalDate());
    		update.setString(5, flightBooking.getArrivalAirport());
    		update.setInt(6, flightBooking.getId());
    		
    		int rows = update.executeUpdate();
    		
    		return rows > 0;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
	}
	
	
	/*SELECTS*/
	public static TripBooking[] selectBookingsFromTrip(Trip trip){
		createConnection();
		try{
			LinkedList<TripBooking> tripBookings = new LinkedList<>();
    		String sql = "SELECT * FROM BOOKING WHERE TRIPID = ?";
    		PreparedStatement query = conn.prepareStatement(sql);
    		query.setInt(1, trip.getId());
    		ResultSet rs = query.executeQuery();
    		
    		while(rs.next()){
    			int id = rs.getInt("ID");
    			int tripId = rs.getInt("TRIPID");
    			String personName = rs.getString("PERSONNAME");
    			tripBookings.add(new TripBooking(id, trip, personName));
    		}
    		
    		return tripBookings.toArray(new TripBooking[tripBookings.size()]);
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		shutdown();
    	}
	}
 
    public static Trip[] selectTrips(){
    	createConnection();
    	try{
    		LinkedList<Trip> trips = new LinkedList<>();
    		stmt = conn.createStatement();
    		String query = "SELECT * FROM " + tableName;
    		ResultSet rs = stmt.executeQuery(query);
    		
    		while (rs.next()) {
                int id = rs.getInt("ID");
                int capacity = rs.getInt("CAPACITY");
                int occupation = rs.getInt("OCCUPATION");
                String title = rs.getString("TITLE");
                trips.add(new Trip(id, title, capacity, occupation));
                
            }
    		return trips.toArray(new Trip[trips.size()]);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		shutdown();
    	}
    }
    
    public static Trip selectTrip(int tripId){
    	createConnection();
    	try{
    		stmt = conn.createStatement();
    		String query = "SELECT * FROM " + tableName + " WHERE ID=" + tripId ;
    		ResultSet rs = stmt.executeQuery(query);
    		
    		Trip trip = null;
    		if (rs.next())
    		{
    			int id = rs.getInt("ID");
                int capacity = rs.getInt("CAPACITY");
                int occupation = rs.getInt("OCCUPATION");
                String title = rs.getString("TITLE");
                trip = new Trip(id, title, capacity, occupation);
    		}
    		
    		return trip;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		shutdown();
    	}
    }
    
    public static FlightBooking[] selectFlightBookings(){
    	createConnection();
    	try{
    		LinkedList<FlightBooking> flightBookings = new LinkedList<>();
    		stmt = conn.createStatement();
    		String query = "SELECT * FROM FLIGHTBOOKING";
    		ResultSet rs = stmt.executeQuery(query);

    		

    		
    		while (rs.next()) {
                int id = rs.getInt("ID");
                String passengerName = rs.getString("PASSENGERNAME");
                String departureDate = rs.getString("DEPARTUREDATE");
                String departureAirport = rs.getString("DEPARTUREAIRPORT");
                String arrivalDate = rs.getString("ARRIVALDATE");
                String arrivalAirport = rs.getString("ARRIVALAIRPORT");
                flightBookings.add(new FlightBooking(id, passengerName, departureDate, departureAirport, arrivalDate, arrivalAirport));
                
            }
    		return flightBookings.toArray(new FlightBooking[flightBookings.size()]);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		shutdown();
    	}
    }
    
    public static FlightBooking selectFlightBooking(int flightBookingId){
    	createConnection();
    	try{
    		stmt = conn.createStatement();
    		String query = "SELECT * FROM FLIGHTBOOKING" + " WHERE ID=" + flightBookingId ;
    		ResultSet rs = stmt.executeQuery(query);
    		
    		FlightBooking flightBooking = null;
    		if (rs.next())
    		{
    			int id = rs.getInt("ID");
                String passengerName = rs.getString("PASSENGERNAME");
                String departureDate = rs.getString("DEPARTUREDATE");
                String departureAirport = rs.getString("DEPARTUREAIRPORT");
                String arrivalDate = rs.getString("ARRIVALDATE");
                String arrivalAirport = rs.getString("ARRIVALAIRPORT");
                flightBooking = new FlightBooking(id, passengerName, departureDate, departureAirport, arrivalDate, arrivalAirport);
    		}
    		
    		return flightBooking;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		shutdown();
    	}
    }

    /*DELETES*/
    public static boolean deleteFlightBooking(FlightBooking flightBooking){
    	createConnection();
    	try{
    		stmt = conn.createStatement();
    		String sql = "DELETE FROM FLIGHTBOOKING WHERE ID = ?";
    		PreparedStatement delete = conn.prepareStatement(sql);
    		delete.setInt(1, flightBooking.getId());
    		
    		int rows = delete.executeUpdate();
    		
    		return rows > 0;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		shutdown();
    	}
    }
    
    /*CONNECTION*/
    private static void createConnection(){
		try
        {
			Class.forName(driver).newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL + ";create=true"); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
	}
    
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
}
