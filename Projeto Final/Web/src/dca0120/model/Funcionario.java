package dca0120.model;

import java.util.Calendar;
import java.util.List;

public abstract class Funcionario {
	
	private int id;
	private String cpf;
	private String nome;
	private Calendar dataNascimento;
	private List<String> telefones;
	
	public Funcionario(int id, String cpf, String nome, Calendar dataNascimento, List<String> telefones) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefones = telefones;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<String> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
}
