package QueryHandlers;

import Models.Ride;
import Models.RideStrategy;

public class RideQuery implements QueryHandler<Ride>
{
    private String origin;
    private String destination;
    private int numSeats;
    private RideStrategy strategy;
    
    public RideQuery(String origin, String destination, int numSeats, String strategy)
    {
        this.origin=origin;
        this.destination=destination;
        this.numSeats=numSeats;
        this.strategy=new RideStrategy(strategy);
    }
    
    public String getOrigin()
    {
        return this.origin;
    }
    
    public String getDestination()
    {
        return this.destination;   
    }
    
    public int getNumSeats()
    {
        return this.numSeats;
    }
    
    public RideStrategy getRideStrategy()
    {
        return this.strategy;
    }
    
    public boolean IsQueryMatch(Ride ride)
    {
        if(((ride.getOrigin().equals(this.origin) || ride.getDestination().equals(this.destination))) &&
            ride.getAvailableSeats()>=this.numSeats)
        {
            return true;
        }
        return false;
    }
}