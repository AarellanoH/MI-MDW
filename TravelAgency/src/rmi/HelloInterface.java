package rmi;
 
import java.rmi.Remote;
import java.rmi.RemoteException;
import models.*;
 
public interface HelloInterface extends Remote{
    public String hello() throws RemoteException;
    public Trip[] getAllTrips() throws RemoteException;
    public void addNewTrip(Trip trip) throws RemoteException;
}