package dca0120.model;

public class Produto {
	
	private int id;
	private String nome;
	private float preco;
	private String foto;
	private float peso;
	private float volume;
	private String descricao;
	private Caixa responsavelCadastro;
	private int quantidadeEstoque;
	
	public Produto(int id, String nome, float preco, String foto, float peso, float volume, String descricao,
			Caixa responsavelCadastro, int quantidadeEstoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.foto = foto;
		this.peso = peso;
		this.volume = volume;
		this.descricao = descricao;
		this.responsavelCadastro = responsavelCadastro;
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Caixa getResponsavelCadastro() {
		return responsavelCadastro;
	}
	public void setResponsavelCadastro(Caixa responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public String getDescricaoFormatada() {
		if(descricao == null) {
			return "Sem descrição.";
		}
		return descricao;
	}
}
