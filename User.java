package Vehicle_management;

public class User {
	private String name;
	private String email;
	private String password;
	private String role;
	
	public User(String name,String email,String password,String role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
 
	 public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getRole() {
	    	return role;
	    }

	    // Setter methods
	    public void setName(String name) {
	    	this.name=name;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
}
