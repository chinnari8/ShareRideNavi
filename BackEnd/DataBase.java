package BackEnd;

import Models.Booking;
import Models.Ride;
import Models.User;
import Models.Vehicle;


public class DataBase
{
  private static Table <User> userTable = new Table < User > ();

  private static Table <Vehicle> vehicleTable = new Table < Vehicle > ();

  private static Table <Ride> rideTable = new Table < Ride > ();

  private static Table <Booking>bookingsTable = new Table < Booking >();

  public static Table <User> getUsersTable ()
  {
    return userTable;
  }

  public static Table <Vehicle> getVehiclesTable ()
  {
    return vehicleTable;
  }

  public static Table <Ride> getRidesTable ()
  {
    return rideTable;
  }

  public static Table <Booking>getBookingsTable ()
  {
    return bookingsTable;
  }
}
