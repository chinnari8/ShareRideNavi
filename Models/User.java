package Models;
import Enums.RideStatus;
import Managers.RidesManager;
import Managers.VehiclesManager;
import Services.AllotmentService;

import java.util.*;

public class User 
{
    private VehiclesManager vehiclesManager;
    private RidesManager ridesManager;
    
    private int userId;
    private String name;
    private String gender;
    private int age;
    
    public User(int id, String name, String gender, int age)
    {
        this.userId=id;
        this.name=name;
        this.gender=gender;
        this.age=age;
    }
    
    // Getters and Setters
    
    public int getUserId()
    {
        return this.userId;
    }
    
    public String getUserName()
    {
        return this.name;
    }
    
    public String getUserGender()
    {
        return this.gender;
    }
    
    public int getUserAge()
    {
        return this.age;
    }
    
    // User APIs
    
    public Vehicle addVehicle(String vehicleModel, String vehicleName)
    {
        return vehiclesManager.addVehicle(this.getUserId(), vehicleModel, vehicleName);
    }
    
    public Ride offerRide(int vehicleId, String origin, String destination, int availableSeats)
    {

        try
        {
            Vehicle vehicle = vehiclesManager.getVehicle(vehicleId);
            // When the vehicles owner is different from the user passed
            if(vehicle.getUserId()!=this.userId)
            {
                throw (new Exception("Vehicle doesnt belong to the right owner"));
            }
            // If vehicle is already been offered.
            if(!vehicle.getVehicleStatus())
            {
            
                Ride ride = ridesManager.offerRide(this.userId, vehicleId, origin, destination,availableSeats);
        
                // add to the bookings table.
                ridesManager.addBooking(this.userId,ride.getRideId(),true,availableSeats);
                vehicle.setVehicleStatus(true);
                vehiclesManager.updateVehicle(vehicle);  
            
                return ride;
            }
            
            return null;
            
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean selectRide(String origin, String destination, int seats, String strategy)
    {
        ArrayList<Ride> mostDesirableRides = AllotmentService.allotRide(this.userId, origin, destination, seats, strategy);
        if(mostDesirableRides.size()!=0)
        {
            for(int i=0;i<mostDesirableRides.size();i++){
                Ride mostDesirableRide = mostDesirableRides.get(i);
                int updatedAvailableSeats = mostDesirableRide.getAvailableSeats()-seats;
                mostDesirableRide.setAvailableSeats(updatedAvailableSeats);
                mostDesirableRide.setRideStatus(RideStatus.IN_PROGRESS);
                ridesManager.updateRide(mostDesirableRide);

                // add to the bookings table.
                ridesManager.addBooking(userId,mostDesirableRide.getRideId(),false,seats);
            }
            return true;
        }
        return false;
    }
    
    public void printRideHistory()
    {
        ArrayList<Booking> bookings = ridesManager.selectAllBookings(this.userId);
        
        int offeredCount=0;
        int takenCount=0;
        
        for(int i=0;i<bookings.size();i++)
        {
            Booking b = bookings.get(i);
            int rideId = b.getRideId(); 
            Ride ride = ridesManager.getRide(rideId);
            if(ride.getRideStatus()==RideStatus.COMPLETED) 
            {
                if(b.IsDriver()) offeredCount++;
                else takenCount++;
            }
        }
        
        System.out.println(this.name+": Taken "+takenCount+", Offered "+offeredCount);
    }
    
}