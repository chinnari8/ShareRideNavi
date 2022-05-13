package Models;

import java.io.*;
import java.util.*;
import Managers.RidesManager;
import Enums.RideStatus;
public class Ride 
{
    private int rideId;
    private int driverId;
    private int vehicleId;
    private int availableSeats;
    private String origin;
    private String destination;
    private RideStatus rideStatus;
    
    public Ride(int rideId, int userId, int vehicleId, String origin, String destination, int availableSeats)
    {
        this.rideId=rideId;
        this.driverId=userId;
        this.vehicleId=vehicleId;
        this.origin=origin;
        this.destination=destination;
        this.availableSeats=availableSeats;
        this.rideStatus = RideStatus.NOT_STARTED;
    }
    
    // Getters and setters
    
    public int getRideId()
    {
        return this.rideId;
    }
    
    public int getVehicleId()
    {
        return this.vehicleId;
    }
    
    public String getOrigin()
    {
        return this.origin;
    }
    
    public String getDestination()
    {
        return this.destination;
    }
    
    public int getAvailableSeats()
    {
        return this.availableSeats;
    }
    
    public void setAvailableSeats(int seats)
    {
        this.availableSeats=seats;
    }
    
    public int getDriverId()
    {
        return this.driverId;
    }
    
    public RideStatus getRideStatus()
    {
        return this.rideStatus;
    }
    
    public void setRideStatus(RideStatus status)
    {
        this.rideStatus=status;
    }
    
    public boolean endRide()
    {
        this.setRideStatus(RideStatus.COMPLETED);
        RidesManager.updateRide(this);
        return true;
    }
    
}