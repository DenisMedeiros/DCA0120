package ga.digentre.mobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import ga.digentre.mobile.model.Produto;

/**
 * @author ney
 * @author denis
 *         <hr>
 *         Classe respons�vel por interconectar a classe Caixa e a classe
 *         Produtos com a tabela CaixasGerenciamProdutos.
 *         </hr>
 */
public final class CaixasGerenciamProdutosDAO {
	private Connection conexao;

	/**
	 * Construtor padr�o da classe CaixasGerenciamProdutos que abre conex�o com
	 * o banco de dados
	 */
	public CaixasGerenciamProdutosDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria a tabela CaixasGerenciamProdutos no banco de dados.
	 */
	public void criarTabelaCaixasGerenciamProdutos() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CaixasGerenciamProdutos (ProdutoID INTEGER NOT NULL, "
					+ "CaixaID INTEGER NOT NULL, PRIMARY KEY (ProdutoID,CaixaID), "
					+ "FOREIGN KEY (CaixaID) REFERENCES Caixas(FuncionarioID) ON DELETE CASCADE ON UPDATE CASCADE, "
					+ "FOREIGN KEY (ProdutoID) REFERENCES Produtos(ID) ON DELETE CASCADE ON UPDATE CASCADE);";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere o objeto Caixa que abriu o objeto Produto no banco de dados.
	 * 
	 * @param p
	 *            Objeto do tipo Produto que tem o caixa responsavel por seu
	 *            cadastro como atributo
	 */
	public void inserirCaixasGerenciamProdutos(Produto p) {
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO CaixasGerenciamProdutos(ProdutoID, CaixaID) VALUES (?, ?)");
		
			pst.setInt(1, p.getId());
			pst.setInt(2, p.getResponsavelCadastro().getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mapa que referencia cada um dos produtos com o seu caixa responsavel pelo
	 * cadastro
	 * 
	 * @return Collection do tipo {@link HashMap} com os parametros
	 *         {@code HashMap<Produto, Caixa>}.
	 */
	public HashMap<Integer, Integer> getCaixasGerenciamProdutos() {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();

		try {
			String sql = "SELECT * FROM CaixasGerenciamProdutos";

			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return m;
			}

			while (res.next()) {
				m.put(res.getInt("ProdutoID"), res.getInt("CaixaID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public int getCaixaIDwithProduto(int produtoID) {
		int caixaId = -1;
		try {
			String sql = "SELECT CaixaID FROM CaixasGerenciamProdutos WHERE ProdutoID=?";

			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, produtoID);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return caixaId;
			}

			while (res.next()) {
				caixaId = res.getInt("CaixaID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return caixaId;
	}
	
	public boolean isEmpty() {
		String sql = "SELECT * FROM CaixasGerenciamProdutos";

		try {
			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			
			return res.wasNull();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void removerProdutos(int produtoID) {
		try {
			String sql = "DELETE FROM CaixasGerenciamProdutos WHERE ProdutoID=?";

			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, produtoID);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerCaixas(int caixaID) {
		try {
			String sql = "DELETE FROM CaixasGerenciamProdutos WHERE CaixaID=?";

			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, caixaID);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerCaixasGerenciamProdutos(int caixaID, int produtoID) {
		try {
			String sql = "DELETE FROM CaixasGerenciamProdutos WHERE ProdutoID=? AND CaixaID=?";

			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, produtoID);
			pst.setInt(2, caixaID);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarCaixaDoProduto(int caixaID, int produtoID) {
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE CaixasGerenciamProdutos SET CaixaID=? WHERE ProdutoID=?");
		
			pst.setInt(1, caixaID);
			pst.setInt(2, produtoID);


			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarCaixa(int caixaIdOld, int caixaIdNew) {
		try {
			PreparedStatement pst = conexao
					.prepareStatement("UPDATE CaixasGerenciamProdutos SET CaixaID=? WHERE CaixaID=?");
		
			pst.setInt(1, caixaIdNew);
			pst.setInt(2, caixaIdOld);


			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
