package ga.digentre.mobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
public final class PedidosContemProdutosDAO {

	private Connection conexao;

	/**
	 * Construtor padr�o da classe PedidosContemProdutos que abre conex�o com o
	 * banco de dados
	 */
	public PedidosContemProdutosDAO() {
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
			String sql = "CREATE TABLE IF NOT EXISTS PedidosContemProdutos (ProdutoID INTEGER NOT NULL, "
					+ "PedidoID INTEGER NOT NULL, Quantidade INTEGER NOT NULL, "
					+ "PRIMARY KEY (ProdutoID,PedidoID), "
					+ "FOREIGN KEY (PedidoID) REFERENCES Pedidos(ID) ON DELETE CASCADE ON UPDATE CASCADE, "
					+ "FOREIGN KEY (ProdutoID) REFERENCES Produtos(ID) ON DELETE CASCADE ON UPDATE CASCADE);";
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
	 * @throws Exception Se nao usar o metodo {@code Pedido.addProduto} para adicionar Produto em Pedido
	 */
	public void inserirPedidosContemProdutos(Produto prod, Pedido ped) throws Exception{
		try {
			if(ped.getQuantidadeProduto(prod) != -1) {
				PreparedStatement pst = conexao.prepareStatement(
						"INSERT INTO PedidosContemProdutos(ProdutoID, PedidoID, Quantidade) VALUES (?, ?, ?);");
	
				pst.setInt(1, prod.getId());
				pst.setInt(2, ped.getId());
				pst.setInt(3, ped.getQuantidadeProduto(prod));
				
				pst.executeUpdate();
			}else {
				throw new Exception("Produto nao adicionado em Pedido");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna a quantidade de um produto num pedido registrado no banco de dados
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
	 *            objeto de Pedido a ter quantidade de produto alterada com o produto e sua quantidade j� inserido
	 */
	public void alterarQuantidade(Produto prod, Pedido ped) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE PedidosContemProdutos SET Quantidade=? WHERE PedidoID=? AND ProdutoID=?;");

			pst.setInt(1, ped.getQuantidadeProduto(prod));
			pst.setInt(2, ped.getId());
			pst.setInt(3, prod.getId());
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna uma lista com os produtos de um pedido registrado no banco de dados
	 * @param ped objeto Pedido do qual se quer os produtos
	 * @return Lista de objetos Produto no Pedido ped 
	 */
	public List<Produto> getProdutosDoPedido(Pedido ped) {
		List<Produto> lista = new ArrayList<Produto>();
		
		try {
			String sql = "SELECT ProdutoID FROM PedidosContemProdutos WHERE PedidoID=?;";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, ped.getId());
			
			ResultSet res = pst.executeQuery();
			
			if(res.wasNull()) {
				return lista;
			}
			
			ProdutosDAO prs = new ProdutosDAO();
			Produto pr = null;
			while(res.next()) {
				pr = prs.getProduto(res.getInt("ProdutoID"));
				if(prs.getProduto(res.getInt("ProdutoID")) != null) {
					lista.add(pr);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public boolean isEmpty() {
		String sql = "SELECT * FROM PedidosContemProdutos;";

		try {
			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			
			return res.wasNull();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void removerProdutoDoPedido(int produtoID, int pedidoID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM PedidosContemProdutos WHERE ProdutoID=? AND PedidoID=?");

			pst.setInt(1, produtoID);
			pst.setInt(2, pedidoID);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove o pedido pelo ID
	 * @param pedidoID
	 */
	public void removerPedido(int pedidoID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM PedidosContemProdutos WHERE PedidoID=?");

			pst.setInt(1, pedidoID);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove o produto de todos os pedidos, identificado pelo ID
	 * @param produtoID
	 */
	public void removerProduto(int produtoID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM PedidosContemProdutos WHERE ProdutoID=?");

			pst.setInt(1, produtoID);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
