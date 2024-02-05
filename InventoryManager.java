package Vehicle_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {
	
	private Connection con;  

    public InventoryManager() {
        this.con = MySQLConnection.getConnection();
    }
	    
	    public void addVehicle(Vehicles stock) {
	        // Add vehicle to the inventory
	    	
	    	String addQuery = "INSERT INTO VEHICLES(name, numberPlate, availableCount,type) VALUES (?, ?, ?, ?);";
	    	try(PreparedStatement prep = con.prepareStatement(addQuery)) {
	    		prep.setString(1, stock.getName());
	    		prep.setString(2, stock.getNumberPlate());
	    		prep.setInt(3, stock.getAvailableCount());
	    		prep.setString(4, stock.getType());
	    		int rs = prep.executeUpdate();
	    	    if(rs>0) {
	    	    	 System.out.println("New Vehicle add to the inventory Successfully...");
	    	    }
	    	} catch (SQLException e) {
				System.out.println("Error in inserting ");
				e.printStackTrace();
			}
	    }

	    public void modifyVehicle(Vehicles vehicle) {
	    	
		    String updateQuery = "UPDATE vehicles SET name = ?, availableCount = ?, type = ?, WHERE numberPlate = ?;";
		    try(PreparedStatement prep = con.prepareStatement(updateQuery)){
		    	  prep.setString(1, vehicle.getName());
		          prep.setInt(2, vehicle.getAvailableCount());
		          prep.setString(3, vehicle.getType());
		          prep.setString(4, vehicle.getNumberPlate());
	    		 int rs = prep.executeUpdate();
		    	  if(rs>0)
		    		  System.out.println("Vehicle Updated to the inventory Successfully...");
	    	}catch(SQLException e) {
				System.out.println("Error in inserting ");
				e.printStackTrace();
	    	}
	    	
	    }

	    public void deleteVehicle(String numberPlate) {
	    	 String deleteQuery = "DELETE FROM vehicles WHERE numberPlate = ?";
	    	 try(PreparedStatement prep = con.prepareStatement(deleteQuery)){
	    		 prep.setString(1, numberPlate);
	    		int rs =  prep.executeUpdate();
	    		if(rs>0) {
	    			 System.out.println("Vechicle "+numberPlate+" has been removed successfully...");
	    		}
	    	 }catch(SQLException e) {
					System.out.println("Error in inserting ");
					e.printStackTrace();
		    	}
	    }

	    public List<Vehicles> getAllVehiclesSortedByName() {
	    	List<Vehicles> vehicles = new ArrayList<>();
	    	String selectQuery = "SELECT * FROM VEHICLES WHERE availableCount>0 ORDER BY name ";
	    	try (Statement stmt = con.createStatement();
	    	         ResultSet rs = stmt.executeQuery(selectQuery)) {
	    	        while (rs.next()) {
	    	        	vehicles.add(mapResultSet(rs));
   	         
	    	        }
	    	    } 
	    	 catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
			return vehicles;
			
	       
	    }

	    public List<Vehicles> getAllVehiclesSortedByAvailableCount() {
	    	List<Vehicles> vehicles = new ArrayList<>();
	    	String selectOrdQuery = "SELECT * FROM VEHICLES ORDER BY availableCount DESC";
	    	try (Statement stmt = con.createStatement();
	    	         ResultSet rs = stmt.executeQuery(selectOrdQuery)) {
	    	        while (rs.next()) {
	    	        	vehicles.add(mapResultSet(rs));
   	         
	    	        }
	    	    } 
	    	 catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
			return vehicles;
			
	    }

	    public Vehicles searchVehicleByName(String name) {
	    	List<Vehicles> vehicles = new ArrayList<>();
	    	String selectOrdQuery = "SELECT * FROM VEHICLES WHERE name = ?";
	    	try (PreparedStatement prep = con.prepareStatement(selectOrdQuery)){
	    		prep.setString(1, name);
	    	         ResultSet rs = prep.executeQuery(); 
	    	         
	    	        while (rs.next()) {
	    	        	System.out.println("Vehicle available");
	    	        	vehicles.add(mapResultSet(rs));
   	         
	    	        }
	    	    } 
	    	 catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
			return  vehicles.get(0);
	    }
	    
	    public ArrayList<Vehicles> searchVehicleByNumberPlate(String numberPlate) {
	        // Search for a vehicle by number plate
	    	ArrayList<Vehicles> vehicle = new ArrayList<>();
	    	String searchVehNo = "SELECT * FROM VEHICLES WHERE numberplate = ?;";
	    	try(PreparedStatement prep = con.prepareStatement(searchVehNo)){
	    		prep.setString(1,numberPlate);
	    		ResultSet rs = prep.executeQuery();
	    		while(rs.next()) {
	    			
	    				vehicle.add(mapResultSet(rs));
	    				System.out.println(vehicle);
	    		}
	    		 if (vehicle.isEmpty()) {
	    	            System.out.println("The vehicle with the particular number plate is not found");
	    	        }	
	    		
	    	} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    	
	        return vehicle; 
	    }


	public void VehiclesbyType() {
		ArrayList<Vehicles> cars = new ArrayList<>();
		ArrayList<Vehicles> bikes = new ArrayList<>();
			String car = "SELECT * FROM VEHICLES WHERE TYPE = 'CAR'";
			String bike = "SELECT * FROM VEHICLES WHERE TYPE = 'BIKE'";
			try(PreparedStatement prepcar = con.prepareStatement(car);
					PreparedStatement prepbike =con.prepareStatement(bike)){
				ResultSet carp = prepcar.executeQuery();
				ResultSet bikep = prepbike.executeQuery();
				while(carp.next()) {
					cars.add(mapResultSet(carp));
				}
				while(bikep.next()) {
					bikes.add(mapResultSet(bikep));
				}
				if(!bikes.isEmpty()) {
				System.out.println("List Of Bikes ...");
				for(Vehicles bk : bikes)
				 System.out.println(bk);
				}else
					System.out.println("No Bikes Available");
				if(!cars.isEmpty()) {
				System.out.println("List Of Cars ...!");
				for(Vehicles cr : cars)
					System.out.println(cr);
				}else
					System.out.println("No Cars Available");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	
	public void List_of_rented_vehicles() {
		ArrayList<Rental_Service> rented = new ArrayList<>();
		String query = "SELECT * FROM RENTAL_SERVICE WHERE ISRENTED = true";
		try(PreparedStatement prep = con.prepareStatement(query)){
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				rented.add(mapResultRental(rs));
			}
			if(rented.isEmpty()) {
				System.out.println("No Vehicles Available in Rented List");
			}else {
				for(Rental_Service rent : rented)
			    	System.out.println(rent);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void Not_Rented_Vehicles() {
		ArrayList<Vehicles> al = new ArrayList<>();
		String Query = "SELECT * FROM VEHICLES WHERE NUMBERPLATE NOT IN(SELECT NUMBERPLATE FROM RENTAL_SERVICE);";
		try(PreparedStatement prep = con.prepareStatement(Query)){
			ResultSet res  = prep.executeQuery();
			while(res.next()) {
				al.add(mapResultSet(res));
			}
			if(al.isEmpty()) {
				System.out.println("No Vehicles is in not rented list");
			}else {
				for(Vehicles nonrent : al)
					System.out.println(nonrent);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	    
	    private Rental_Service mapResultRental(ResultSet rs) throws SQLException{
	    	
		return new Rental_Service(
				rs.getString("user_id"),
				rs.getString("numberPlate"),
				rs.getString("date"),
				rs.getBoolean("isRented"),
				rs.getBoolean("isServiced")
				);
				
	}

		private Vehicles mapResultSet(ResultSet rs) throws SQLException {

	        return new Vehicles(
	                rs.getString("name"),
	                rs.getString("numberPlate"),
	                rs.getInt("availableCount"),
	                rs.getString("type")
	        );
	    }

		public void vehicle_due_sevice() {
			ArrayList<Rental_Service> rented = new ArrayList<>();
			String query = "SELECT * FROM RENTAL_SERVICE WHERE ISRENTED = false";
			try(PreparedStatement prep = con.prepareStatement(query)){
				ResultSet rs = prep.executeQuery();
				while(rs.next()) {
					rented.add(mapResultRental(rs));
				}
				if(rented.isEmpty()) {
					System.out.println("No Vehicles Available in Service List");
				}else {
					System.out.println("The vehicles in service list are");
					for(Rental_Service rent : rented)
				    	System.out.println(rent);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}

}
