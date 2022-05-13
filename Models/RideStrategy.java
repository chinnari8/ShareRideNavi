package Models;

public class RideStrategy
    {
        private String strategy;
        private boolean isMostVacant=false;
        private String preferredVehicle="";
        
        public RideStrategy(String strategy)
        {
            this.strategy=strategy;
            buildStrategy(strategy);
        }
        
        private void buildStrategy(String strategy)
        {
            if(strategy.contains("MostVacant"))
            {
                this.isMostVacant=true;
            }
            if(strategy.contains("PreferredVehicle"))
            {
                this.preferredVehicle=strategy.split("=")[1];
            }
        }
        
        public boolean getIsMostVacant()
        {
            return this.isMostVacant;
        }
        
        public String getPreferredVehicle()
        {
            return this.preferredVehicle;
        }
    }