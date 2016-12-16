package rmi;
 
import java.rmi.Naming;
import java.util.Arrays;
import models.*;
 
public class Client {
 
    public static void main(String[] args) throws Exception{
        HelloInterface client = (HelloInterface)Naming.lookup("//localhost/TripsRMI");
        System.out.println(Arrays.toString(client.getAllTrips()));
    }
}