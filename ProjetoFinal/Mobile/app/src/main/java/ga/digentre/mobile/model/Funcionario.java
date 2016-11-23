package ga.digentre.mobile.model;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public abstract class Funcionario {
	
	private int id;
	private String cpf;
	private String nome;
	private Calendar dataNascimento;
	private List<String> telefones;
	private String senha;
	
	public Funcionario(int id, String cpf, String senha, String nome, Calendar dataNascimento, List<String> telefones) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefones = telefones;
		this.setSenha(senha);
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getDataNascimentoFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String saida = sdf.format(this.dataNascimento.getTime());
		return saida;
	}

	public String getTelefonesFormatados() {
		return TextUtils.join(", ", this.telefones);
	}

}
