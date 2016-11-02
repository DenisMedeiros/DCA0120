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
 * @author ney
 * @author denis
 *         <hr>
 *         Classe responsável por interconectar a classe Produto com a tabela
 *         Produtos no banco de dados.
 *         </hr>
 */
public class ProdutosDAO {

	private Connection conexao;

	/**
	 * Construtor da classe Produtos que abre conexão com o banco de dados
	 */
	public ProdutosDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria a tabela Produtos no banco de dados.
	 */
	public void criarTabelaProdutos() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Produtos (ID INTEGER AUTO_INCREMENT, "
					+ "Nome VARCHAR(80) NOT NULL, Preco FLOAT NOT NULL, Foto VARCHAR(200), Peso FLOAT, "
					+ "Volume FLOAT, Descricao VARCHAR(800), CaixaID INTEGER NOT NULL, "
					+ "QuantidadeEmEstoque INTEGER NOT NULL, "
					+ "CONSTRAINT chk_Estoque CHECK (QuantidadeEmEstoque>=0), PRIMARY KEY (ID), "
					+ "FOREIGN KEY (CaixaID) REFERENCES Caixas(FuncionarioID) ON UPDATE CASCADE);";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere um especificado objeto de Produto no banco de dados
	 * 
	 * @param p
	 *            objeto de Produto a ser adicionado no banco de dados
	 */
	public void inserirProduto(Produto p) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Produtos(Nome, Preco, Foto, Peso, Volume,"
					+ "CaixaID, QuantidadeEmEstoque, Descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			pst.setString(1, p.getNome());
			pst.setFloat(2, p.getPreco());
			pst.setString(3, p.getFoto());
			pst.setFloat(4, p.getPeso());
			pst.setFloat(5, p.getVolume());
			pst.setInt(6, p.getResponsavelCadastro().getId());
			pst.setInt(7, p.getQuantidadeEstoque());
			pst.setString(8, p.getDescricao());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca um produto especifico no banco de dados pelo seu id
	 * 
	 * @param id
	 *            referencia do Produto a ser buscado
	 * @return objeto do tipo Produto (ou null, caso nao exista tal Produto com
	 *         id especificado)
	 */
	public Produto getProduto(int id) {
		Produto p = null;

		try {
			String sql = "SELECT * FROM Produtos WHERE ID=?";

			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return p;
			}

			CaixasDAO caixas = new CaixasDAO();
			Caixa c = null;
			if (res.next()) {
				c = caixas.getCaixaWithID(res.getInt("CaixaID"));
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
	 * Altera a quantidade de um Produto no banco de dados
	 * 
	 * @param p
	 *            objeto do tipo Produto a ter o estoque alterado no banco de
	 *            dados
	 */
	public void alterarEstoque(Produto p) {

		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Produtos SET QuantidadeEmEstoque=? WHERE ID=?");
			
			pst.setInt(1, p.getQuantidadeEstoque());
			pst.setInt(2, p.getId());
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Altera o preço do Produto no banco de dados
	 * 
	 * @param id
	 *            Int de referencia do Produto a ter alterdo o seu preco
	 * @param novoPreco
	 *            tipo float com o novo valor do Produto
	 */
	public void alterarPreco(int id, float novoPreco) {
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Produtos SET Preco=? WHERE ID=?");

			pst.setFloat(1, novoPreco);
			pst.setInt(2, id);
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pega todos os Produtos registrados no banco de dados
	 * 
	 * @return Lista objetos de Produto com todos os produtos do banco de dados
	 */
	public List<Produto> getTodosProdutos() {
		List<Produto> lista = new ArrayList<Produto>();

		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Produtos";
			ResultSet res = st.executeQuery(sql);

			if (res.wasNull()) {
				return lista;
			}

			CaixasDAO caixas = new CaixasDAO();
			Caixa c = null;
			while (res.next()) {
				c = caixas.getCaixaWithID(res.getInt("CaixaID"));
				Produto p = new Produto(res.getInt("ID"), res.getString("Nome"), res.getFloat("Preco"),
						res.getString("Foto"), res.getFloat("Peso"), res.getFloat("Volume"), res.getString("Descricao"),
						c, res.getInt("QuantidadeEmEstoque"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Remove o Produto especificado pelo id no banco de dados
	 * 
	 * @param id
	 *            referencia do Produto a ser procurado no banco de dados
	 */
	public void removerProduto(int id) {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Produtos WHERE ID=?");

			pst.setInt(1, id);
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		String sql = "SELECT * FROM Produtos;";

		try {
			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			
			return res.wasNull();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
