package dca0120.model;

import java.util.Calendar;
import java.util.List;

public class Entregador extends Funcionario {
	
	private String cnh;
	private String placaVeiculo;
	
	
	public Entregador(int id, String cpf, String nome, Calendar dataNascimento, List<String> telefones,String cnh, 
			String placaVeiculo) {
		super(id, cpf, nome, dataNascimento, telefones);
		this.cnh = cnh;
		this.placaVeiculo = placaVeiculo;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

}
