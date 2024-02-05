package Vehicle_management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class SignUpPage extends User{
    public SignUpPage(String name, String email, String password, String role) {
		super(name, email, password, role);
		
	}

	public void signup(String name,String email,String password,String role) {

    	try {
    		Connection con = MySQLConnection.getConnection();
    		String insert = "INSERT INTO USERS(name,email,password,role) VALUES(?,?,?,?)";
    		try (PreparedStatement prepstmt = con.prepareStatement(insert)){
    			prepstmt.setString(1, name);
    			prepstmt.setString(2, email);
    			prepstmt.setString(3, password);
    			prepstmt.setString(4, role);
    			int rs = prepstmt.executeUpdate();
    		
    		if(rs>0)
    		System.out.println("Sign-Up Successful ..!!!");
    		if("Admin".equals(role)) {
   			 System.out.println("Welcome, Back!! "+role);
   			
   				 Admin obj = new Admin();
   				 obj.showAdminMenu();
   			 }
   			 else if(role.equals("Borrower")) {
   				 System.out.println("Welcome !!..Lets Get Started...!");
   				 Customers cust = new Customers();
   				 cust.showBorrowerMenu(email);
   			 }
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("Sign-Up Failed ...Please try again..!");
    		
    	}
    }
	
}
