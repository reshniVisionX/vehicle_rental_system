package Vehicle_management;

public class Vehicles {
	
		private String name;
	    private String numberPlate;
	    private int availableCount;
	    private String type;

	    public Vehicles(String name, String numberPlate, int availableCount, String type) {
			this.name=name;
			this.numberPlate=numberPlate;
			this.availableCount=availableCount;
			this.type=type;
		}
	
	    public String getName() {
	    	return name;
	    }
	    public String getNumberPlate() {
	    	return numberPlate;
	    }
	    public int getAvailableCount() {
	     
	    	return availableCount;
	    }
	    public  String getType() {
	    	return type;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public void setNumberPlate(String numberPlate) {
	    	this.numberPlate = numberPlate;
	    }
	    public void setAvailableCount(int availableCount) {
	    	
	    	this.availableCount = availableCount;
	    }
	    public void setType(double securityDeposit) {
	    	this.type = type;
	    }

	    @Override
	    public String toString() {
	        return "Vehicles{" +
	                "name = '" + name + '\'' +
	                ", numberPlate = '" + numberPlate + '\'' +
	                ", availableCount = " + availableCount +
	                ", Type = " + type +
	                '}';
	    }
}
