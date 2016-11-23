package ga.digentre.mobile.model;

public class Endereco {
	
	private float longitude;
	private float latitude;
	private String descricao;
	
	public Endereco(float longitude, float latitude, String descricao) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.descricao = descricao;
	}
	
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
