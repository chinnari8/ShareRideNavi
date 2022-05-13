package Services;

import Managers.RidesManager;
import Managers.VehiclesManager;
import Models.Ride;
import Models.RideStrategy;
import Models.Vehicle;
import QueryHandlers.RideQuery;

import java.io.*;
import java.util.*;

public class AllotmentService 
{
    public static ArrayList<Ride> allotRide(int userId, String origin, String destination, int seats, String strategy)
    {
        RideQuery rideQuery = new RideQuery(origin,destination,seats,strategy);
        ArrayList<Ride> sameOriginRides = RidesManager.selectAllRides(userId,origin,"",seats,strategy);
        ArrayList<Ride> sameDestinationRides = RidesManager.selectAllRides(userId,"",destination,seats,strategy);

        if(sameDestinationRides.size()==0 || sameOriginRides.size()==0) return new ArrayList<>();
        else {
            ArrayList<Ride> rides = RidesManager.selectAllRides(userId,origin,destination,seats,strategy);
            ArrayList<ArrayList<Ride>> groupedRides = groupRides(rides,origin,destination,rideQuery.getRideStrategy());

            ArrayList<Ride> mostDesirableRide = mostDesirableRide(groupedRides, rideQuery.getRideStrategy());
            return mostDesirableRide;
        }
    }

    private static ArrayList<ArrayList<Ride>> groupRides(ArrayList<Ride> rides, String origin, String destination, RideStrategy rideStrategy)
    {
        ArrayList<ArrayList<Ride>> groupedRides = new ArrayList<ArrayList<Ride>>();

        HashMap<String,ArrayList<Ride>> routeGraphs = new HashMap<>();

        for(int i=0;i<rides.size();i++)
        {
            String currentOrigin = rides.get(i).getOrigin();
            if(routeGraphs.containsKey(currentOrigin)){
                ArrayList<Ride> allRides = routeGraphs.get(currentOrigin);
                allRides.add(rides.get(i));
                routeGraphs.put(currentOrigin,allRides);
            }
            else {
                ArrayList<Ride> allRides = new ArrayList<>();
                allRides.add(rides.get(i));
                routeGraphs.put(currentOrigin,allRides);
            }
        }

        HashMap<String,Boolean> visited= new HashMap<String, Boolean>();
        for(int i=0;i< rides.size();i++){
            visited.put(rides.get(i).getOrigin(),false);
            visited.put(rides.get(i).getDestination(),false);
        }

        ArrayList<Ride> ridesWithSameOrigin = routeGraphs.get(origin);

        for(int i=0;i<ridesWithSameOrigin.size();i++)
        {
            ArrayList<Ride> destinationRide=new ArrayList<>();
            destinationRide.add(ridesWithSameOrigin.get(i));
            populateRidePaths(origin,destination,ridesWithSameOrigin.get(i),
                    routeGraphs, visited, destinationRide,groupedRides);
        }
        return groupedRides;
    }

    private static void populateRidePaths(String origin, String destination, Ride currentRide,
                                          HashMap<String,ArrayList<Ride>> groupedRides,
                                          HashMap<String,Boolean> visited,
                                          ArrayList<Ride> destinationRides,
                                          ArrayList<ArrayList<Ride>> finalRide)
    {
        if(currentRide.getDestination().equals(destination))
        {
            finalRide.add(destinationRides);
            return;
        }
        else if(!groupedRides.containsKey(currentRide.getDestination()))
        {
            return;
        }
        else {
            String newOrigin = currentRide.getDestination();
            visited.put(newOrigin,true);
            ArrayList<Ride> currentRides = groupedRides.get(newOrigin);

            for (int i = 0; i < currentRides.size(); i++) {
                Ride ride = currentRides.get(i);
                if(!visited.get(ride.getDestination()))
                {
                    destinationRides.add(ride);
                    populateRidePaths(newOrigin,destination,ride,groupedRides,visited,destinationRides,finalRide);
                    destinationRides.remove(ride);
                }
            }
            visited.put(newOrigin,false);
        }
        return ;
    }

    private static ArrayList<Ride> mostDesirableRide(ArrayList<ArrayList<Ride>> rides, RideStrategy rideStrategy)
    {
        if(rideStrategy.getIsMostVacant()) return mostVacantRide(rides);
        else return preferredVehicleRide(rides,rideStrategy.getPreferredVehicle());
    }

    private static ArrayList<Ride> mostVacantRide(ArrayList<ArrayList<Ride>> allRides)
    {
        int maxSeats=-1,overAllMaxSeats = -1;
        ArrayList<Ride> mostVacantRide = new ArrayList<>();
        for(int i=0;i<allRides.size();i++)
        {
            ArrayList<Ride> rides = allRides.get(i);

            for(int j=0;j<rides.size();j++)
            {
                maxSeats=Math.max(maxSeats,rides.get(j).getAvailableSeats());
            }
            overAllMaxSeats=Math.max(maxSeats,overAllMaxSeats);
            mostVacantRide = (overAllMaxSeats==maxSeats)?rides:mostVacantRide;
        }
        return mostVacantRide;
    }
    
    private static ArrayList<Ride> preferredVehicleRide(ArrayList<ArrayList<Ride>> allRides, String preferredVehicle)
    {
        try {
            ArrayList<Ride> mostDesirableRide = new ArrayList<Ride>();
            for (int i = 0; i < allRides.size(); i++) {
                ArrayList<Ride> rides = allRides.get(i);
                boolean found = true;
                for (int j = 0; j < rides.size(); j++) {
                    int vehicleId = rides.get(j).getVehicleId();
                    Vehicle vehicle = VehiclesManager.getVehicle(vehicleId);
                    if (!vehicle.getVehicleModel().equals(preferredVehicle)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return rides;
                }
            }
            return mostDesirableRide;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}