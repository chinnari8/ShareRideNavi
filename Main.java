import Managers.UsersManager;
import Models.User;
import Models.Vehicle;
import Models.Ride;

public class Main
{
	public static void main(String[] args) {
	    
	    try {
			User rohan = UsersManager.addUser("Rohan", "M", 36);
			User shashank = UsersManager.addUser("Shashank", "M", 29);
			User nandini = UsersManager.addUser("Nandini", "F", 29);
			User shipra = UsersManager.addUser("Shipra", "F", 27);
			User gaurav = UsersManager.addUser("Gaurav", "M", 29);
			User rahul = UsersManager.addUser("Rahul", "M", 35);

			Vehicle swift = rohan.addVehicle("Swift", "KA-01-12345");
			Vehicle baleno = shashank.addVehicle("Baleno", "TS-05-62395");
			Vehicle polo = shipra.addVehicle("Polo", "KA-05-41491");
			Vehicle activa = shipra.addVehicle("Activa", "KA-12-12332");
			Vehicle xuv = rahul.addVehicle("XUV", "KA-05-1234");

			Ride ride1 = rohan.offerRide(swift.getVehicleId(), "Hyderabad", "Bangalore", 1);
			Ride ride2 = shipra.offerRide(activa.getVehicleId(), "Bangalore", "Mysore", 1);
			Ride ride3 = shipra.offerRide(polo.getVehicleId(), "Bangalore", "Mysore", 2);
			Ride ride4 = shashank.offerRide(baleno.getVehicleId(), "Hyderabad", "Bangalore", 2);
			Ride ride5 = rahul.offerRide(xuv.getVehicleId(), "Hyderabad", "Bangalore", 5);
			Ride ride6 = rohan.offerRide(swift.getVehicleId(), "Bangalore", "Pune", 1);

			nandini.selectRide("Bangalore", "Mysore", 1, "MostVacant");
			gaurav.selectRide("Bangalore", "Mysore", 1, "PreferredVehicle=Activa");
			shashank.selectRide("Mumbai", "Bangalore", 1, "MostVacant");
			rohan.selectRide("Hyderabad", "Bangalore", 1, "PreferredVehicle=Baleno");
			shashank.selectRide("Hyderabad", "Bangalore", 1, "PreferredVehicle=Polo");

			ride1.endRide();
			ride2.endRide();
			ride3.endRide();
			ride4.endRide();

			System.out.println();

			nandini.printRideHistory();
			rohan.printRideHistory();
			shashank.printRideHistory();
			gaurav.printRideHistory();
			shipra.printRideHistory();
			rahul.printRideHistory();

			// Bonus question test cases
			// Right now the graph logic will work for only one hop i.e,
			// when the maximum number of hops possible between
			// origin to destination rides is 1.
			// For hops more than 1, the allotment service can be improvised further.
			User lakshmi = UsersManager.addUser("Lakshmi", "F", 29);
			User siva = UsersManager.addUser("Siva","M",30);
			Vehicle sivaKia = siva.addVehicle("Kia", "TS-05-123456");
			Vehicle rohanKia = rohan.addVehicle("Kia","TS-01-123");
			Ride ride7 = rohan.offerRide(rohanKia.getVehicleId(),"Bangalore","Goa",4);
			Ride ride8=siva.offerRide(sivaKia.getVehicleId(),"Goa","Mumbai",4);
			lakshmi.selectRide("Bangalore","Mumbai",3,"PreferredVehicle=Kia");
			ride7.endRide();
			ride8.endRide();

			siva.printRideHistory();
			rohan.printRideHistory();
			lakshmi.printRideHistory();


		}
		catch (NullPointerException e){
			System.out.println((e.getMessage()));
		}
		
	}
}
