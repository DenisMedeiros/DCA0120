package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dca0120.model.Caixa;
import dca0120.model.Produto;

/**
 * @author neypi
 *
 * Classe responsável por interconectar a classe Produto com a tabela Produtos no banco de dados.
 */
public class Produtos {

	public Connection conexao;
	
	/**
	 * 
	 */
	public Produtos () {
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
	public void criarTabelaProdutos() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS Produtos (" +
			        "ID INTEGER AUTO_INCREMENT, " +
		            "Nome VARCHAR(80) NOT NULL, " +
		            "Preco FLOAT NOT NULL, " +
		            "Foto VARCHAR(200), " +
		            "Peso FLOAT, " +
		            "Volume FLOAT, " +
		            "Descricao VARCHAR(800), " +
		            "CaixaID INTEGER NOT NULL, " +
		            "QuantidadeEmEstoque INTEGER NOT NULL, " +
		            "CONSTRAINT chk_Estoque CHECK (QuantidadeEmEstoque>=0), " +
		            "PRIMARY KEY (ID), " +
		            "FOREIGN KEY (CaixaID) REFERENCES Caixas(FuncionarioID), " +
		            ")";
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param p
	 */
	public void inserirProduto(Produto p) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Produtos(Nome, Preco, Foto, Peso, Volume,"
					+ "CaixaID, QuantidadeEmEstoque) VALUES (?, ?, ?, ?, ?, ?, ?);");

			pst.setString(1, p.getNome());
			pst.setFloat(2, p.getPreco());
			pst.setString(3, p.getFoto());
			pst.setFloat(4, p.getPeso());
			pst.setFloat(5, p.getVolume());
			pst.setInt(6, p.getResponsavelCadastro().getId());
			pst.setInt(7, p.getQuantidadeEstoque());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Produto getProduto(int id) {
		Produto p = null;
		
		try {
			String sql = "SELECT * FROM Produtos WHERE ID=?;";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			
			if(res.wasNull()) {
				return p;
			}
			
			if(res.next()) {
				Caixas caixas = new Caixas();
				Caixa c = caixas.getCaixaWithID(res.getInt("CaixaID"));
				p = new Produto(id, res.getString("Nome"), res.getFloat("Preco"), res.getString("Foto"), 
						res.getFloat("Peso"), res.getFloat("Volume"), res.getString("Descricao"), c, 
						res.getInt("QuantidadeEmEstoque"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * @param id
	 * @param novaQuantidade
	 */
	public void alterarEstoque(int id, int novaQuantidade) {
		
		try {       	        
			PreparedStatement pst = conexao.prepareStatement("UPDATE Produtos SET QuantidadeEmEstoque=? WHERE ID=?;");
			
			pst.setInt(1, novaQuantidade);
	        pst.setInt(2, id);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id
	 * @param novoPreco
	 */
	public void alterarPreco(int id, float novoPreco) {
		try {       	        
			PreparedStatement pst = conexao.prepareStatement("UPDATE Produtos SET Preco=? WHERE ID=?;");
			
			pst.setFloat(1, novoPreco);
	        pst.setInt(2, id);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public List<Produto> getTodosProdutos() {
		List<Produto> lista = new ArrayList<Produto>();
		
		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Produtos;";
			ResultSet res = st.executeQuery(sql);
			
			if(res.wasNull()) {
				return lista;
			}
			
			while (res.next()) {
				Caixas caixas = new Caixas();
				Caixa c = caixas.getCaixaWithID(res.getInt("CaixaID"));
				Produto p = new Produto(res.getInt("ID"), res.getString("Nome"), res.getFloat("Preco"), 
						res.getString("Foto"), res.getFloat("Peso"), res.getFloat("Volume"), 
						res.getString("Descricao"), c, res.getInt("QuantidadeEmEstoque"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * @param id
	 */
	public void removerProduto(int id) {
		try {       	        
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Produtos WHERE ID=?;");
			
	        pst.setInt(1, id);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
