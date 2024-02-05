package Vehicle_management;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends User{
   public LoginPage(String name, String email, String password, String role) {
		super(name, email, password, role);
		
	}

public static void login(String email,String password) {

	try {
		Connection con = MySQLConnection.getConnection();
	    String select = "SELECT * FROM USERS WHERE email = ? AND password = ?";
	 try(PreparedStatement prepstmt = con.prepareStatement(select)){
		 prepstmt.setString(1, email);
		 prepstmt.setString(2, password);
		 
		 ResultSet resultSet = prepstmt.executeQuery();
		 if(resultSet.next()) {
			 String role = resultSet.getString("role");
			 if("Admin".equalsIgnoreCase(role)) {
			 System.out.println("Welcome, Back!! "+role);
			
				 Admin obj = new Admin();
				 obj.showAdminMenu();
			 }
			 else if(role.equalsIgnoreCase("Borrower")) {
				 System.out.println("Welcome !!..Lets Get Started...!");
				 Customers cust = new Customers();
				 cust.showBorrowerMenu(email);
			 }
		 }
		 else {
			 System.out.println("Invalid email or password ,Please try again ..");
			 
		 }
	 }
	}
	catch(SQLException e) {
		e.printStackTrace();
		System.out.println("Log-In..Failed.Please try again ");
	}
   }
}
