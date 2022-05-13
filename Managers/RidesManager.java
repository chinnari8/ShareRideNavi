package Managers;

import BackEnd.DataBase;
import Models.Booking;
import Models.Ride;
import QueryHandlers.BookingQuery;
import QueryHandlers.RideQuery;

import java.util.ArrayList;

public class RidesManager
{
    private static int rideCount=1;
    private static int bookingCount=1;
    
    public static Ride getRide(int rideId)
    {
        return DataBase.getRidesTable().get(rideId);
    }
    
    public static boolean updateRide(Ride ride)
    {
        return DataBase.getRidesTable().update(ride.getRideId(),ride);
    }
    
    public static Ride offerRide(int userId, int vehicleId, String origin, String destination, int availableSeats)
    {
        Ride ride = new Ride(rideCount,userId,vehicleId,origin,destination,availableSeats);
        DataBase.getRidesTable().add(ride.getRideId(),ride);
        rideCount++;
        return ride;
    }
    
    public static ArrayList<Ride> selectAllRides(int userId, String origin, String destination, int seats, String strategy)
    {
        RideQuery rideQuery = new RideQuery(origin,destination,seats,strategy);
        ArrayList<Ride> rides = DataBase.getRidesTable().getByQuery(rideQuery);
        return rides;
    }
    
    public static Booking addBooking(int userId, int rideId, boolean isDriver, int seats)
    {
        Booking booking = new Booking(bookingCount,userId,rideId,isDriver,seats);
        DataBase.getBookingsTable().add(bookingCount,booking);
        bookingCount++;
        return booking;
    }
    
    public static ArrayList<Booking> selectAllBookings(int userId)
    {
        BookingQuery query = new BookingQuery(userId);
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings=DataBase.getBookingsTable().getByQuery(query);
        return bookings;
    }
    
}