<%@ page import="java.util.Date, models.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trip Details</title>
</head>
<body>
	<%
	Trip trip = (Trip)request.getAttribute("trip");
	TripBooking[] bookings = (TripBooking[])request.getAttribute("bookings");
	int id = trip.getId();
	String title = trip.getTitle();
	int capacity = trip.getCapacity();
	int occupation = trip.getOccupation(); 
	
	%>
	<h3>Trip: <%=title%> </h3>
	<p>Capacity: <%=capacity%> </p>
	<p>Current occupancy: <%=occupation%></p>
	
	<br><br>
	<h3>Current persons booked in the trip:</h3>
	<% if (bookings.length == 0){ %>
	<p>There are no persons booked in this trip. </p>
	<%} else {%>
	  <ul>
	  <%  for (TripBooking booking: bookings) {  %>
	      <li>
	      <%=booking.getPersonName()%>
	      </li>
	  <%  }  %>
	  </ul>
	  <%} %>
	
	<br><br>
	<%
	if(occupation >= capacity)
	%>
		<p>Sorry, you can't book anymore because the trip is full.</p>
	<%else{ %>
	<p>Book your place in the trip:</p>
	<form action="./bookServlet" method="post">
	  Name: <input type="text" name="personName"><br>
	  <input type="submit" value="Submit">
	  <input type="hidden" name="tripId" value="<%=id%>">
	</form>
	<%} %>

</body>
</html>