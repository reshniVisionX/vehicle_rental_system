package Vehicle_management;

public class Rented_Report {
  
	private String renterId;
	private String numberPlate;
	
	private double km_used;
	private int damagelvl;
	
	public Rented_Report(String renterId,String numberPlate,double km_used,int damagelvl) {
		this.renterId = renterId;
		this.numberPlate = numberPlate;
		
		this.km_used = (double) km_used;
		this.damagelvl = damagelvl;
	}
	 public String getRenterId() {
	        return renterId;
	    }

	    public void setRenterId(String renterId) {
	        this.renterId = renterId;
	    }

	    public String getNumberPlate() {
	        return numberPlate;
	    }

	    public void setNumberPlate(String numberPlate) {
	        this.numberPlate = numberPlate;
	    }

	    public double getKm_used() {
	        return km_used;
	    }

	    public void setKm_used(double km_used) {
	        this.km_used = km_used;
	    }

	    public int getDamagelvl() {
	        return damagelvl;
	    }

	    public void setDamagelvl(int damagelvl) {
	        this.damagelvl = damagelvl;
	    }
	    @Override
	    public String toString() {
			return "Rental Service : { Renter_id : \'"+renterId+
					"\' NumberPlate : \'"+numberPlate+"\' Km_used : \'"+km_used+"\' DamageLevel : \'"+damagelvl+"\' }";
	    	
	    }
	}