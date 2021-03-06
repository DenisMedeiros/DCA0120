package dca0120.model;

/**
 * Classe Person.
 * 
 * @author denis
 * @author ney
 *
 */
public class Person {
	
	private int id;
	private String lastName;
	private String firstName;
	private String address;
	private String city;
	
	
	public Person() {
		
	}
	
	public Person(int id, String lastName, String firstName, String address, String city) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	

}
