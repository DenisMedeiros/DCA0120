package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neypi
 *
 */
public class Telefones {
	
	private Connection conexao;

	/**
	 * Construtor padrão da classe Telefones que abre conexão com o banco de dados
	 */
	public Telefones() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
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
	 * @param funcionarioID
	 * @param telefone
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
	 * @param id
	 * @return
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
