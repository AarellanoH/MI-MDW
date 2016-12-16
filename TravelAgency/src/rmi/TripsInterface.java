package rmi;

import models.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TripsInterface extends Remote{
	public String hello() throws RemoteException;
	public Trip[] getAllTrips() throws RemoteException;
	public void addNewTrip(Trip trip) throws RemoteException;

}
