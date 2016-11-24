package dca0120.model;

public class Endereco {
	
	
	private double latitude;
	private double longitude;
	private String descricao;
	
	public Endereco(double latitude, double longitude, String descricao) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.descricao = descricao;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
