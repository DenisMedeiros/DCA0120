package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dca0120.model.Pedido;
import dca0120.model.Produto;

/**
 * @author neypi
 * @author denis
 *         <hr>
 *         Classe respons�vel por ligar as classes Pedidos e Produtos com tabela
 *         PedidosContemProdutos.
 *         </hr>
 */
public final class PedidosContemProdutos {

	private Connection conexao;

	/**
	 * Construtor padr�o da classe PedidosContemProdutos que abre conex�o com o
	 * banco de dados
	 */
	public PedidosContemProdutos() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria a tabela PedidosContemProdutos no banco de dados.
	 */
	public void criarTabelaPedidosContemProdutos() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS PedidosContemProdutos (ProdutoID  INTEGER NOT NULL, "
					+ "PedidoID INTEGER NOT NULL, Quantidade INTEGER NOT NULL, "
					+ "PRIMARY KEY (ProdutoID,PedidoID), FOREIGN KEY (PedidoID) REFERENCES Pedidos(ID), "
					+ "FOREIGN KEY (ProdutoID) REFERENCES Produtos(ID))";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere a quantidade de um Produto contido num Pedido no banco de dados.
	 * 
	 * @param prod
	 *            Objeto de referencia do tipo Produto a ser inserido no banco
	 *            de dados
	 * @param ped
	 *            Objeto de referencia do tipo Pedido a ser inserido no banco de
	 *            dados
	 * @param qtd
	 *            tipo Inteiro que representa a Quantidade do Produto contido no
	 *            Pedido
	 */
	public void inserirPedidosContemProdutos(Produto prod, Pedido ped, int qtd) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO PedidosContemProdutos(ProdutoID, PedidoID, Quantidade) VALUES (?, ?, ?);");

			pst.setInt(1, prod.getId());
			pst.setInt(2, ped.getId());
			pst.setInt(3, qtd);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna a quantidade de um produto num pedido
	 * 
	 * @param prod
	 *            Objeto do tipo Produto
	 * @param ped
	 *            Objeto do tipo Pedido
	 * @return Quantidade de Produto 'prod' em um Pedido 'ped' (retorna -1 caso
	 *         n�o exista o produto ou o pedido)
	 */
	public int getQuantidade(Produto prod, Pedido ped) {
		int qtd = -1;

		try {
			String sql = "SELECT Quantidade FROM PedidosContemProdutos WHERE PedidoID=? AND ProdutoID=?;";

			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, ped.getId());
			pst.setInt(2, prod.getId());
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return qtd;
			}

			if (res.next()) {
				qtd = res.getInt("Quantidade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return qtd;
	}

	/**
	 * Altera a quantidade de um produto em um pedido no banco de dados
	 * 
	 * @param prod
	 *            objeto de Produto a ter quantidade alterada
	 * @param ped
	 *            objeto de Pedido a ter quantidade de produto alterada
	 * @param quantidade
	 *            valor a ser usado para alterar a quantidade de produto em um
	 *            pedido
	 */
	public void alterarQuantidade(Produto prod, Pedido ped, int quantidade) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE PedidosContemProdutos SET Quantidade=? WHERE PedidoID=? AND ProdutoID=?;");

			pst.setInt(1, quantidade);
			pst.setInt(2, ped.getId());
			pst.setInt(3, prod.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
