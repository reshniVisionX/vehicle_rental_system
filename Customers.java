package Vehicle_management;

import java.util.List;
import java.util.Scanner;

public class Customers {
    private InventoryManager inventory;
	public RentalServices res ;
	
	public Customers(){
		res = new RentalServices();
		
	}
	
	 public void showBorrowerMenu(String email) {
  	   Scanner sc = new Scanner(System.in);
       int choice;
     do {
       System.out.println();
  	   System.out.println("--------------Borrower Menu List---------------");
  	   System.out.println("1. View Vehicle");
  	   System.out.println("2. Select a Vehicle by numberPlate");
  	   System.out.println("3. Add a Vehicle to Checkout");
  	   System.out.println("4. Remove from Checkout");
  	   System.out.println("5. Rent a Vehicle");
  	   System.out.println("6. List of Previous rentals");
  	   System.out.println("7. Exit");
  	   System.out.println("---------Enter your choice from the above list--------");
  	   choice = sc.nextInt();
  	   
  	 switch(choice) {
	      case 1:{
	    	 System.out.println("All Stocks...By Name ..!!");
	    	 inventory = new InventoryManager();
   	 List<Vehicles> vehiclesByName =inventory.getAllVehiclesSortedByName();
   	 for (Vehicles vehicle : vehiclesByName) {
   	        System.out.println(vehicle); 
   	    }
   	 System.out.println("----------------------------------------");
   	 break;
    
	      }
	      case 2:{
	    	   //view a vehicle by name use joins
	    	  sc.nextLine();
	    	 System.out.println("Enter the vehicle number that you want to look at ");
   	 String number = sc.nextLine();
   	 inventory. searchVehicleByNumberPlate(number);
   	 break;
	      }
	      case 3:{
	    	 //add to checkout
          sc.nextLine();
	    	  System.out.println("Enter the vehicle number :");
	    	  String numberPlate = sc.nextLine();
	    	  System.out.println("Enter the vehicle type :");
	    	  String type = sc.nextLine();
	    	  System.out.println("Enter your initial deposit :");
	    	  double depo =sc.nextDouble();
	    	  res.Add_to_cart_ifAvailable(numberPlate,email,type,depo);
	    	  break;
	    	 
	      }
	      case 4:{
	    	//Remove from checkout
	    	  sc.nextLine();
	    	  System.out.println("Enter the vehicle number :");
	    	  String cartList = sc.nextLine();
	    	 
	    	  res.RemovefromCheckout(cartList);
	    	  System.out.println("Removed "+cartList+" from checkout successfully..");
	    	  break;
	      }
	      case 5:{
	    	  //rent a vehicle
	    	 
	    	  sc.nextLine();
	    	
	    	  System.out.println("Enter the vehicles number :");
	    	  String numberPlate = sc.nextLine();
	    	  System.out.println("Enter the kms used : ");
	    	  Double km_used =sc.nextDouble();
	    	  System.out.println("Enter the Damage either 20%(low) , 50%(medium) or 75%(high)");
	    	  int damagelvl = sc.nextInt();
	    	  Rented_Report rr = new Rented_Report(email,numberPlate,km_used,damagelvl);
	    	  int r = res.Rent_a_Vehicle(rr);
	    	  if(r!=0) {
	    	  System.out.println("Total cost of your renting service is...");
	    	  double total_cost = km_used*damagelvl-1000;
	    	  
	    	  System.out.println("Rs. "+total_cost);
	    	  System.out.println("-------------------------------------------------");
	    	  System.out.println("We Welcome You Again...!!");
	    	  }

               break;
	      }
	      case 6:{
	    	  System.out.println("List of the previous rentals ");
	    	  res.List_Of_prev_rentals();
	    	  break;
	      }
	      case 7:{
	    	  System.out.println("Exit the customer process");
	    	  return;
	      }
	   }
	   }while(choice!=0);
}

}