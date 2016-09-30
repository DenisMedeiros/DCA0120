package dca0120.model;

import java.util.Date;
/**
 * Classe Person.
 * 
 * @author denis
 * @author ney
 *
 */
public class Person {
	
	private int id;
	private String name;
	private String phoneMobile;
	private String phoneHome;
	private String email;
	private String login;
	private String password;
	private Address address;
	private Date birthday;

	
	
	public Person() {
		
	}
	
	public Person(int id, String Name, String login, Address address, String password, String email, String phoneMobile, String phoneHome) {
		this.id = id;
		this.name = Name;
		this.setLogin(login);
		this.address = address;
		this.setPassword(password);
		this.setEmail(email);
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRua() {
		return address.getRua();
	}
	
	public String getCity() {
		return address.getCity();
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setRua(String rua) {
		this.address.setRua(rua);
	}
		
	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(int day, int month, int year) {
		this.birthday.
	}
	

}
