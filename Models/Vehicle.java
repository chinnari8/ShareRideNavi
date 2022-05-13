package Models;

import java.io.*;
import java.util.*;

public class Vehicle
{
    private int vehicelId;
    private int userId;
    private String vehicleModel;
    private String vehicleName;
    private boolean vehicleStatus;
    
    public Vehicle(int userId, int vehicleId, String vehicleModel, String vehicleName)
    {
        this.vehicelId=vehicleId;
        this.userId=userId;
        this.vehicleModel=vehicleModel;
        this.vehicleName=vehicleName;
        this.vehicleStatus=false;
    }
    
    // Getters and setters 
    
    public int getVehicleId()
    {
        return this.vehicelId;
    }

    public int getUserId() { return this.userId;}
    
    public String getVehicleName()
    {
        return this.vehicleName;
    } 
    
    public String getVehicleModel()
    {
        return this.vehicleModel;
    }
    
    public boolean getVehicleStatus()
    {
        return this.vehicleStatus;
    }
    
    public void setVehicleStatus(boolean status)
    {
        this.vehicleStatus=status;
    }
    
}