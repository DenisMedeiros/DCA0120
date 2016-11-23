package ga.digentre.mobile.model;

import java.util.Calendar;
import java.util.List;

public class Caixa extends Funcionario {
	
	private boolean  administrador;

	public Caixa(int id, String cpf, String senha, String nome, Calendar dataNascimento, List<String> telefones, 
			boolean administrador) {
		super(id, cpf, senha, nome, dataNascimento, telefones);
		this.administrador = administrador;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

}
