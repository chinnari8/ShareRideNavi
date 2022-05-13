package QueryHandlers;

import Models.Booking;

public class BookingQuery implements QueryHandler<Booking>
{
    private int userId;
    
    public BookingQuery(int userId)
    {
        this.userId=userId;
    }
    
    public boolean IsQueryMatch(Booking booking)
    {
        if(booking.getUserId()==this.userId)
        {
            return true;
        }
        return false;
    }
}