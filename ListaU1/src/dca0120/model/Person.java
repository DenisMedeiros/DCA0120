package dca0120.model;

import java.io.*;
import java.util.Calendar;


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
	private String email;
	private String login;
	private String password;//tipo?
	private Address address;
	private Calendar birthday;//tipo?
	private File photo;
	private String phoneMobile;
	private String phoneHome;
	
	
	public Person() {
		
	}
	
	public Person(int id, String Name,  String email, String login, String password, int day, int month, int year, Address address, String phoneMobile, String phoneHome, String photoPath) {
		super();
		this.id = id;
		this.name = Name;
		this.address = address;
		this.setPassword(password);
		this.setEmail(email);
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;
		this.setBirthday(day, month, year);
		this.setPhoto(photoPath);
	}
	
	public void setPhoto(String photoPath) {
		this.photo = new File(photoPath);
	}
	
	public File getPhoto() {
		return photo;
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
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getStreet() {
		return address.getStreet();
	}
	public void setStreet(String street) {
		this.address.setStreet(street);
	}
		
	public String getCity() {
		return address.getCity();
	}
	public void setCity(String city) {
		this.address.setCity(city);
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Address getAddress() {
		return address;
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

	public Calendar getBirthday() {
		return birthday;
	}
	
	
	public void setBirthday(int day, int month, int year) {
		this.birthday.set(year, month, day);
	}
	

}
