package Vehicle_management;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RentalServices {
	private Connection con;  

  
    public RentalServices() {
        this.con = MySQLConnection.getConnection();
    }
	 //add your methods.
    public void setAvailabilityCount(Borrower count) {
    	
    	 String updateCartList = "INSERT INTO BORROWER(b_email,cartList,isRented,initial_deposit) VALUES (?,?,?,?);";
    	 try( PreparedStatement updateCartPrep = con.prepareStatement(updateCartList)){
    		
    		
    			 updateCartPrep.setString(1, count.getEmail());
    			 updateCartPrep.setString(2, count.getCartList());
    			 updateCartPrep.setString(3,"car");
    			 updateCartPrep.setDouble(4, 10000);
    			 updateCartPrep.executeUpdate();
    			 System.out.println("Your Vehicle is updated to your cartList");
    		 
    		
    		 
    	 } catch (SQLException e) {
			
			e.printStackTrace();
		}
	    	  
    }
    public void RemovefromCheckout(String cartList) {
    	String removeQuery = "DELETE FROM BORROWER WHERE cartList = ?";
    	try(PreparedStatement prep = con.prepareStatement(removeQuery)){
    		prep.setString(1, cartList);
    		prep.executeUpdate();
    	} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    public int Rent_a_Vehicle(Rented_Report rr) {
    	String rentQuery = "INSERT INTO RENTED_REPORT(renterId,numberPlate,km_used,damagelvl) VALUES(?,?,?,?);";
    	String seviceQuery = "INSERT INTO RENTAL_SERVICE(user_id,numberPlate,date,isRented,isServiced) VALUES(?,?,?,?,?);";
    	String canrent = "SELECT INITIAL_DEPOSIT ,type FROM BORROWER WHERE CARTLIST = ?";
    	try(PreparedStatement prep = con.prepareStatement(rentQuery);
    			PreparedStatement Serprep = con.prepareStatement(seviceQuery);
    			
    			PreparedStatement canprep = con.prepareStatement(canrent)){
    		 canprep.setString(1, rr.getNumberPlate());
    		 ResultSet val = canprep.executeQuery();
    		 int flag =0;
    		 while(val.next()) {
    			 double deposit = val.getDouble("initial_Deposit");
    			 String type = val.getString("type");
    		 
    		 if(type.trim().equalsIgnoreCase("CAR")) {
    			if(deposit>10000)
    				flag = 1;
    			if(rr.getKm_used()>3000) {
    				serviceCar(rr.getNumberPlate());
    			}
    		 }else if(type.trim().equalsIgnoreCase("BIKE")) {
    			 if(deposit>3000)
    				 flag = 1;
    			 if(rr.getKm_used()>1500) {
    				 serviceBike(rr.getNumberPlate());
    			 }
    		 }
    		 else {
    			 flag =0;
    			 System.out.println("Invalid Vehicle type");
    		 } 
    		
    		 }
    	 if(flag == 1 ) {
    		 System.out.println("Your initial Deposit is good to go with the renting");
    		prep.setString(1, rr.getRenterId());
    		prep.setString(2, rr.getNumberPlate());
    		prep.setDouble(3, rr.getKm_used());
    		prep.setInt(4, rr.getDamagelvl());
    		int rs =prep.executeUpdate();
    		
    		if(rs>0)
    			 System.out.println("Your Renting is Successfull..");
    		LocalDateTime currentDateTime = LocalDateTime.now();
    	    String timestamp = String.valueOf(currentDateTime);
    		String numberPlate = rr.getNumberPlate();
    		String user_id = rr.getRenterId();
    		boolean isRented = true;
    		boolean isServiced = true;
    		Serprep.setString(1,user_id);
    		Serprep.setString(2, numberPlate);
    		Serprep.setString(3,timestamp );
    		Serprep.setBoolean(4, isRented);
    		Serprep.setBoolean(5, isServiced);
    		int srs = Serprep.executeUpdate();
    		if(srs>0)
    			 System.out.println("Your Service is Processed");
    		return 1;
    	 }else {
    		 System.out.println("Sorry..! Your initial deposit is not sufficient to proceed the renting");
    		 return 0;
    	 }
    	
    		
    	} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
    }
    
   
 
    private void serviceBike(String numberPlate) {
    	String Query= "UPDATE RENTAL_SERVICE SET ISSERVICED = false WHERE NUMBERPLATE = ?";
		try(PreparedStatement prep = con.prepareStatement(Query)){
			prep.setString(1, numberPlate);
			int res = prep.executeUpdate();
			if(res>0) {
				System.out.println("The bike need to be serviced ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void serviceCar(String numberPlate) {
		String Query= "UPDATE RENTAL_SERVICE SET ISSERVICED = false WHERE NUMBERPLATE = ?";
		try(PreparedStatement prep = con.prepareStatement(Query)){
			prep.setString(1, numberPlate);
			int res = prep.executeUpdate();
			if(res>0) {
				System.out.println("The car need to be serviced ");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	public void Add_to_cart_ifAvailable(String numberPlate, String b_email,String Vehicletype,double deposit) {
        String countQuery = "SELECT availableCount from VEHICLES WHERE numberplate = ?";
        String updateQuery = "UPDATE VEHICLES SET availableCount = ? WHERE numberplate = ?";
        String updateCartList = "INSERT INTO BORROWER(b_email,cartList,type,initial_deposit) VALUES (?,?,?,?);";

        try (PreparedStatement prep = con.prepareStatement(countQuery);
             PreparedStatement updatePrep = con.prepareStatement(updateQuery);
             PreparedStatement updateCartPrep = con.prepareStatement(updateCartList)) {

            prep.setString(1, numberPlate);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
            	System.out.println("Available count - ");
                int countVehicle = rs.getInt("availableCount");
                System.out.println(countVehicle);

                if (countVehicle > 0) {
                    int newCount = countVehicle - 1;

                    updatePrep.setInt(1, newCount);
                    updatePrep.setString(2, numberPlate);

                    int rowsUpdated = updatePrep.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Vehicle is available..");

                        // Get values for Borrower object (replace these with actual values)
                        String email = b_email;
                        String cartList = numberPlate;
                        String type = Vehicletype;
                        double initialDeposit = Borrower.setInitialDeposit(deposit);
						if(initialDeposit == 0) {
							System.out.println("Failed to add to cart cause of lower initial deposit ");
							
							return;
						}
                        updateCartPrep.setString(1, email);
                        updateCartPrep.setString(2, cartList);
                        updateCartPrep.setString(3, type);
                        updateCartPrep.setDouble(4, initialDeposit);

                        int rowsInserted = updateCartPrep.executeUpdate();

                        if (rowsInserted > 0) {
                            System.out.println("Your Vehicle is updated to your cartList");
                        }
                    } else {
                        System.out.println("Failed to update vehicle availability.");
                    }
                } else {
                    System.out.println("Out of Stock...! Please choose another vehicle..");
                }
            } else {
                System.out.println("Vehicle not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void List_Of_prev_rentals() {
		ArrayList<Rented_Report> rr = new ArrayList<>();
		String query = "SELECT * FROM RENTED_REPORT";
		try(PreparedStatement prep = con.prepareStatement(query)){
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				rr.add(MapResultReport(rs));
			}
			if(rr.isEmpty()) {
				System.out.println("Unfortunately..No list of previous rentals available ");
			}else {
				for(Rented_Report rent : rr)
					System.out.println(rent);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	private Rented_Report MapResultReport(ResultSet rs) throws SQLException {
		
		 return new Rented_Report(
				rs.getString("renterId"),
				rs.getString("numberPlate"),
				rs.getDouble("km_used"),
				rs.getInt("damagelvl")
				);
	}
	
}
