<%@ page import="java.util.Date, models.*, rmi.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Example JSP</title>
</head>
<body>
  <h1>Trips:</h1>
  <%
  //DB.resetDatabase();
  //DB.insertSampleTrips();
   Trip[] trips = DB.selectTrips();
   Server.main(null);
  %>
  <ul>
  <%  for (Trip trip: trips) {  %>
      <li>
      <%int id = trip.getId(); %>
      <a href="./tripDetails?id=<%=id%> "> <%=trip%> </a>
      </li>
  <%  }  %>
  </ul>
</body>
</html>