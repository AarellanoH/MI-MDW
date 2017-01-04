package services;
import models.*;

import javax.jws.*;

@WebService(name="GeneralBookingWS")
public class GeneralBookingWS {
 
    @WebMethod(operationName="makeBooking")
    @WebResult(name="result")
    public Object hwMakeBooking(@WebParam(name="type") String type, @WebParam(name="name") String name, @WebParam(name="destination") int destination){
    	System.out.println("HwWS - General: " + type);
    	return null;
    }
}