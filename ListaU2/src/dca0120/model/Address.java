package dca0120.model;

/**
 * Classe Address.
 * 
 * @author denis
 * @author ney
 *
 */

public class Address {
	
	private String street;
	private int num;
	private String complement;
	private String district;
	private String zip;
	private String city;
	private String state;
	private Person person;
	
	public Address() {
		
	}

	public Address(String street, int num, String complement, String district, String zip, String city, String state, Person person) {
		super();
		this.street = street;
		this.num = num;
		this.complement = complement;
		this.district = district;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.person = person;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
}
