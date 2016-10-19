package dca0120.model;

import java.util.Calendar;
import java.util.List;

public class Caixa extends Funcionario {
	
	private boolean  administrador;

	public Caixa(int id, String cpf, String nome, Calendar dataNascimento, List<String> telefones, 
			boolean administrador) {
		super(id, cpf, nome, dataNascimento, telefones);
		this.administrador = administrador;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

}
