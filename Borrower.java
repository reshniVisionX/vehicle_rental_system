package Vehicle_management;
import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private String email;
    private String cartList;
    private String vehicle_type;
    private static double initialDeposit = 30000;
    Admin ad = new Admin();
    double val = ad.getValue();

    public Borrower(String email, String cartList,String vehicle_type,double initialDeposit) {
        this.email = email;
        this.cartList = cartList;
        this.vehicle_type=vehicle_type;
        this.initialDeposit = val;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
    	this.email=email;
    }
    public String getCartList() {
        return cartList;
    }
    public void setCartList(String cartList) {
    	this.cartList = cartList;
    }
    public String getVehicle_type() {
    	return vehicle_type;
    }
    public void setVehicle_type(String vehicle_type) {
    	this.vehicle_type = vehicle_type;
    }
	public static  double getInitialDeposit(double val) {
		if(initialDeposit<=val) {
			
		 return val;}
		else {
			System.out.println("Your initial deposit must be more than "+initialDeposit);
           return 0;
		}
		
	}

	public static  double setInitialDeposit(double initialDeposit) {
		double res = getInitialDeposit(initialDeposit);
		if(res !=0 ) {
		 
		return res;
		}
		return 0;
	}
}