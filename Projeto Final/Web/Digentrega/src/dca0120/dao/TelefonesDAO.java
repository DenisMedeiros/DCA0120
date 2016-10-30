package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ney
 * @author denis
 * <hr>
 *  Classe responsavel por ligar um telefone com seu dono, um Funcionario, no banco de dados
 *</hr>
 */
public class TelefonesDAO {
	
	private Connection conexao;

	/**
	 * Construtor padrão da classe Telefones que abre conexão com o banco de dados
	 */
	public TelefonesDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria a tabela Telefones
	 */
	public void criarTabelaTelefones() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Telefones (" +
					"DDDeNumero VARCHAR(11) NOT NULL, " +
					"FuncionarioID INTEGER NOT NULL, " +
					"PRIMARY KEY (DDDeNumero), " +
					"FOREIGN KEY (FuncionarioID ) REFERENCES Funcionarios (ID), " +
					");";
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Insere o telefone de um Funcionario no banco de dados
	 * @param funcionarioID Int que referencia um Funcionario
	 * @param telefone String a ser usada para guardar o telefone no banco de dados
	 */
	public void inserirTelefone(int funcionarioID, String telefone) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Telefones(DDDeNumero, FuncionarioID) "
					+ "VALUES (?, ?);");
			
			pst.setString(1, telefone);
			pst.setInt(2, funcionarioID);
			
	        pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista com todas os telefones de um funcionario
	 * @param id referencia do Funcionario
	 * @return Lista de String com os telefones do Funcionario especificado pelo id
	 */
	public List<String> getTelefones(int id) {
		List<String> telefones = new ArrayList<String>();
		try {
			String sql = "SELECT * FROM Telefones Where FuncionarioID=?;";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, id);
			
			ResultSet res = pst.executeQuery();
			
			if(res.wasNull()) {
				return telefones;
			}
			
			while (res.next()) {
				telefones.add(res.getString("DDDeNumero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telefones;
	}
	
}
