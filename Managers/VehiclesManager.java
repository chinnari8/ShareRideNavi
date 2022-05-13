package Managers;

import BackEnd.DataBase;
import Models.Vehicle;

import java.io.*;
import java.util.*;

public class VehiclesManager
{
    static int vehiclesCount=1;
    
    public static Vehicle addVehicle(int userId, String vehicleModel, String vehicleName)
    {
        Vehicle vehicle = new Vehicle(userId, vehiclesCount,vehicleModel,vehicleName);
        DataBase.getVehiclesTable().add(vehicle.getVehicleId(),vehicle);
        vehiclesCount++;
        return vehicle; 
    }
    
    public static Vehicle getVehicle(int vehicleId)
    {
        return DataBase.getVehiclesTable().get(vehicleId);
    }
    
    public static boolean updateVehicle(Vehicle vehicle)
    {
        return DataBase.getVehiclesTable().update(vehicle.getVehicleId(),vehicle);
    }
}