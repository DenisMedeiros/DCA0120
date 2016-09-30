package dca0120.model;

/**
 * Classe Address.
 * 
 * @author denis
 * @author ney
 *
 */

public class Address {
	
	private String rua;
	private int num;
	private String complement;
	private String bairro;
	private String zip;
	private String city;
	private String state;
	
	
	
	public Address() {
		
	}

	public Address(String rua, int num, String complement, String bairro, String zip, String city, String state) {
		super();
		this.rua = rua;
		this.num = num;
		this.complement = complement;
		this.bairro = bairro;
		this.zip = zip;
		this.city = city;
		this.state = state;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
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
}
