package project.model;

public class User {

	private int id;
	private int isadmin;
	private String name;
	private String email;
	private String country;
	
	
	public User(int id, String name, String email, String country,int isadmin) {
		super();
		this.id = id;
		this.isadmin=isadmin;
		this.name = name;
		this.email = email;
		this.country = country;
	}
	 
	
	
	public User(String name, String email, String country,int isadmin) {
		super();
		this.name = name;
		this.isadmin = isadmin;
		this.email = email;
		this.country = country;
	}



	public int getIsadmin() {
		return isadmin;
	}



	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
