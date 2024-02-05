package Vehicle_management;
import java.util.*;
public class Vehicle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
			System.out.println("------------------------------------------------");
			System.out.println("Welcome to Vehicle Rental System ...!!");
			System.out.println("1.SignUp");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			System.out.println("choose what you are looking for from the above option");
			System.out.println("------------------------------------------------");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
	    	SignUpPage obj;
	    
	    	if(choice == 1) {
	    		String emailValid= "^[a-zA-Z0-9][a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]*\\@[a-z]+.[a-zA-Z]{2,3}$";
		    	String roleValid = "admin|borrower";
		    	
		    	System.out.println("Enter your Name : ");
		    	String name = sc.nextLine();
		    	System.out.println("Enter your Email id to SignUp :");
		    	String email = sc.nextLine();
		    	if(!email.matches(emailValid)) {
		    		System.out.println("Email id you entered is invalid");
		    		return;
		    	} 
		    	System.out.println("Enter your password :");
		    	String password = sc.nextLine();
		    	System.out.println("Enter your Role (Admin / Borrower) :");
		    	String role = sc.nextLine().toLowerCase();
		    	if(!role.matches(roleValid)) {
		    		System.out.println("Role should be either 'admin' or 'borrower' ");
		    		return;
		    	}
		    	
	    		obj = new SignUpPage(name,email,password,role);
	    		obj.signup(name,email,password,role);
	    		LoginPage.login(email,password);
	    	}
	    	else if(choice == 2) {
	    		System.out.println("Enter your email id to login :");
	    		String email = sc.nextLine();
	    		System.out.println("Enter your password : ");
	    		String password = sc.nextLine();
	    		LoginPage.login(email,password);
	    	}
	    	else
	    		return;

	}

}
