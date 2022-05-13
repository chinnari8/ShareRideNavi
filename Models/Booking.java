package Models;

public class Booking
{
    private int bookingId;
    private int userId;
    private int rideId;
    private boolean isDriver;
    private int bookingSeats;
    
    public Booking(int bookingId,int userId, int rideId, boolean isDriver,int bookingSeats)
    {
        this.bookingId=bookingId;
        this.userId=userId;
        this.rideId=rideId;
        this.isDriver=isDriver;
        this.bookingSeats=bookingSeats;
    }
    
    public int getUserId()
    {
        return this.userId;
    }
    
    public int getRideId()
    {
        return this.rideId;
    }
    
    public boolean IsDriver()
    {
        return this.isDriver;
    }
    
    public int getId()
    {
        return this.bookingId;
    }
    
    public int getAllocatedSeats()
    {
        return this.bookingSeats;
    }
    
}