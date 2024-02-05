package Vehicle_management;
import java.util.*;
  public class Admin {
	  private InventoryManager inventory;

	    public Admin() {	       
	        inventory = new InventoryManager();
	    }
	 
       public void showAdminMenu() {
    	   Scanner sc = new Scanner(System.in);
       int choice;
       do {
    	   System.out.println();
    	   System.out.println("---------Admin Menu List---------");
    	   System.out.println("1. Add a Vehicle");
    	   System.out.println("2. Update a Vehicle");
    	   System.out.println("3. Remove a Vehicle");
    	   System.out.println("4. List of all vehicles by sorted names");
    	   System.out.println("5. List of all vehicles by available count");
    	   System.out.println("6. Search a vehicle by name");
    	   System.out.println("7. Search a vehicle by number plate");
    	   System.out.println("8. Change Security Deposit");
    	   System.out.println("9. Check car and bike details separately");
    	   System.out.println("10. List of vehicles rented out");
    	   System.out.println("11. List of vehicles that have not been rented");
    	   System.out.println("12. Vehicles Due for service");
    	   System.out.println("13. Exit Admin Panel");
    	   System.out.println("-----Enter your choice from the above list-----");
    	   choice = sc.nextInt();
    	   sc.nextLine();
    	   switch(choice) {
    	     case 1:{
    	    	 System.out.println("Enter the details below ..");
    	    	 System.out.println("Enter vehicle name : ");
    	    	 
    	    	  String name = sc.nextLine(); 
    	    	 System.out.println("Enter the number plate : ");
    	    	  String numberPlate = sc.nextLine();
    	    	 System.out.println("Enter the available count : ");
    	    	  int availableCount = sc.nextInt();
    	    	  sc.nextLine();
    	    	 System.out.println("Enter the vehicle type :");
    	    	  String type = sc.nextLine();
    	    	 Vehicles newVehicle = new Vehicles(name,numberPlate,availableCount,type);
    	    	 
    	    	 inventory.addVehicle(newVehicle);
    	    	
    	    	 break;
    	     }
    	     case 2:{
    	    	System.out.println("Enter the number plate for which you need to update..");
    	    	
    	    	 String numberPlate = sc.nextLine();
    	    	System.out.println("Enter the name :");
    	    	 String name = sc.nextLine();
    	    	System.out.println("Enter the updated count :");
    	    	 int availableCount = sc.nextInt();
    	    	 sc.nextLine();
    	    	System.out.println("Enter the vehicle type : ");
    	    	 String type = sc.nextLine();
    	    	Vehicles newVehicle = new Vehicles(name,numberPlate,availableCount,type);
    	    
    	    	 inventory.modifyVehicle(newVehicle);
    	    	 
    	    	break;
    	     }
    	     case 3:{
    	    	 System.out.println("Enter the number plate that you want to remove ..");
    	    	
    	    	 String numberPlate = sc.nextLine();
    	    	 inventory.deleteVehicle(numberPlate);
    	    	
    	    	 break;
    	     }
    	     case 4:{
    	    	 System.out.println("All Stocks...By Name .");
    	    	 List<Vehicles> vehiclesByName =inventory.getAllVehiclesSortedByName();
    	    	 for (Vehicles vehicle : vehiclesByName) {
    	    	        System.out.println(vehicle); 
    	    	    }
    	    	 System.out.println("------------------------------");
    	    	 break;
    	     }
    	     case 5:{
    	    	 System.out.println("All Stocks...By Available Count..");
    	    	 List<Vehicles> vehiclesByCount =inventory.getAllVehiclesSortedByAvailableCount();
    	    	 for (Vehicles vehicle : vehiclesByCount) {
    	    	        System.out.println(vehicle); 
    	    	    }
    	    	 System.out.println("------------------------------");
    	    	 break;
    	     }
    	     case 6:{
    	    	 System.out.println("Enter the vehicle name that you want to search:");
    	    	 String name = sc.nextLine();
    	    	 Vehicles found = inventory.searchVehicleByName(name);
    	    	 System.out.println(found);
    	    	 System.out.println("---------------------------------");
    	    	 break;
    	     }
    	     case 7:{
    	    	 //search a vehicle by number plate
    	    	 System.out.println("Enter the vehicle number that you want to look at ");
    	    	 String number = sc.nextLine();
    	    	 inventory. searchVehicleByNumberPlate(number);
    	    	 break;
    	     }
    	     case 8:{
    	    	 //change Security deposit
    	    	 System.out.println("Enter the security deposit amount that you want to update ");
    	    	 double val = sc.nextDouble();
    	    	 Admin admin = new Admin();
    	    	 admin.setValue(val);
    	    	break;
    	     }
    	     case 9:{
    	    	 //check car bike separately
    	    	 inventory.VehiclesbyType();
    	    	 break;
    	     }
    	     case 10:{
    	    	 //list of vehi rented out
    	    	 inventory.List_of_rented_vehicles();
    	    	 break;
    	     }
    	     case 11:{
    	    	 inventory.Not_Rented_Vehicles();
    	    	 break;
    	     }
    	     case 12:{
    	    	 inventory.vehicle_due_sevice();
    	    	 break;
    	     }
    	     case 13:{
    	    	 System.out.println("Exiting Admin panel");
    	    	 return;
    	     }
    	     default:
    	    	 System.out.println("Invalid Entry !!");
    	   }
       }while(choice!=0);
       
      }
       private static double res = 30001;
       private void setValue(double val) {
		 res = val;
		 System.out.println("Security Deposit is updated to "+val);
	   }
	
	public double getValue() {
		System.out.println("Initial Amount to be deposited is "+res);
		return res;
	}
	  
    
}
