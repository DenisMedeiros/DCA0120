package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import dca0120.model.Caixa;
import dca0120.model.Produto;

/**
 * @author ney
 * @author denis
 *         <hr>
 *         Classe responsável por interconectar a classe Caixa e a classe
 *         Produtos com a tabela CaixasGerenciamProdutos.
 *         </hr>
 */
public final class CaixasGerenciamProdutosDAO {
	private Connection conexao;

	/**
	 * Construtor padrão da classe CaixasGerenciamProdutos que abre conexão com
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
			String sql = "CREATE TABLE IF NOT EXISTS CaixasGerenciamProdutos (ProdutoID  INTEGER NOT NULL, "
					+ "CaixaID INTEGER NOT NULL, PRIMARY KEY (ProdutoID,CaixaID), "
					+ "FOREIGN KEY (CaixaID) REFERENCES Caixas(FuncionarioID), "
					+ "FOREIGN KEY (ProdutoID) REFERENCES Produtos(ID), " + ")";
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
					.prepareStatement("INSERT INTO CaixasGerenciamProdutos(CaixaID, ProdutoID) VALUES (?, ?);");

			pst.setInt(1, p.getResponsavelCadastro().getId());
			pst.setInt(2, p.getId());

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
	public HashMap<Produto, Caixa> getCaixasGerenciamProdutos() {
		HashMap<Produto, Caixa> m = new HashMap<Produto, Caixa>();

		try {
			String sql = "SELECT * FROM CaixasGerenciamProdutos;";

			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return m;
			}

			while (res.next()) {
				ProdutosDAO ps = new ProdutosDAO();
				Produto p = ps.getProduto(res.getInt("ProdutoID"));
				CaixasDAO cs = new CaixasDAO();
				Caixa c = cs.getCaixaWithID(res.getInt("CaixaID"));
				m.put(p, c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

}
