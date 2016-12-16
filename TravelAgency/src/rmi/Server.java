package rmi;
 
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import models.DB;
import models.Trip;
 
public class Server extends UnicastRemoteObject implements TripsInterface {
 
    private static final long serialVersionUID = 1L;
 
    protected Server() throws RemoteException {
        super();
    }
    
    
    
 
    @Override
	public String hello() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello";
	}




	@Override
	public Trip[] getAllTrips() throws RemoteException {
		// TODO Auto-generated method stub
		return DB.selectTrips();
	}



	@Override
	public void addNewTrip(Trip trip) throws RemoteException {
		DB.insertTrip(trip);
		
	}



	public static void main(String[] args) {
        try {
            /*
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            */
//            LocateRegistry.createRegistry(2001);
 
            Server server = new Server();
            Naming.rebind("//0.0.0.0/TripsRMI", server);
 
            System.out.println("Server started...");
 
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
 
    }
 
}