package dca0120.model;

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
	private String password;
	private Calendar birthdate;
	private byte[] photo;
	private String phoneMobile;
	private String phoneHome;
	
	
	public Person() {
		
	}
	
	public Person(int id, String Name,  String email, String login, String password, Calendar birthdate, String phoneMobile, String phoneHome, byte[] photo) {
		super();
		this.id = id;
		this.name = Name;
		this.setPassword(password);
		this.setEmail(email);
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;
		this.photo = photo;
		this.birthdate = birthdate;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public byte[] getPhoto() {
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

	public Calendar getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}
	
	
	

}
