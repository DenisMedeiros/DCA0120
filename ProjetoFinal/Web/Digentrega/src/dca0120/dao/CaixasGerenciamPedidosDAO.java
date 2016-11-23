package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import dca0120.model.Pedido;

/**
 * @author ney
 * @author denis
 * 
 *         <hr>
 *         Classe responsável por interconectar a classe Caixa e a classe
 *         Pedidos com a tabela CaixasGerenciamPedidos.
 *         </hr>
 */
public final class CaixasGerenciamPedidosDAO {
	private Connection conexao;

	/**
	 * Construtor padrão da classe CaixasGerenciamPedidos que abre conexão com o
	 * banco de dados
	 */
	public CaixasGerenciamPedidosDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria a tabela CaixasGerenciamPedidos no banco de dados.
	 */
	public void criarTabelaCaixasGerenciamPedidos() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CaixasGerenciamPedidos (CaixaID INTEGER NOT NULL, "
					+ "PedidoID INTEGER NOT NULL, DataHoraAbertura TIMESTAMP NOT NULL, "
					+ "PRIMARY KEY (CaixaID, PedidoID), "
					+ "FOREIGN KEY (CaixaID) REFERENCES Caixas(FuncionarioID) ON DELETE CASCADE ON UPDATE CASCADE, "
					+ "FOREIGN KEY (PedidoID) REFERENCES Pedidos(ID) ON DELETE CASCADE ON UPDATE CASCADE);";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere o objeto Caixa que abriu o objeto Pedido, com a sua respectiva
	 * hora de abertura, no banco de dados.
	 * 
	 * @param p
	 *            Objeto do tipo Pedido a ser inserido no banco de dados
	 * @param caixaID
	 *            Uma referencia de um objeto de tipo Caixa a ser inserida no
	 *            banco de dados
	 */
	public void inserirCaixasGerenciamPedidos(Pedido p, int caixaID) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO CaixasGerenciamPedidos(CaixaID, PedidoID, DataHoraAbertura) VALUES (?, ?, ?)");

			Calendar calendar = p.getDataHoraAbertura();
			java.sql.Timestamp javaSqlTimestamp = null;
			javaSqlTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

			pst.setInt(1, caixaID);
			pst.setInt(2, p.getId());
			pst.setTimestamp(3, javaSqlTimestamp);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna a data e a hora de abertura de um pedido por um caixa
	 * 
	 * @param pedidoID
	 *            Referencia do pedido a ser obtido a hora de abertura
	 * @return objeto do tipo Calendar com os valores da hora de abertura do
	 *         pedido especificado (ou retorna um Calendar com valores da epoch
	 *         caso nao exista tal pedido)
	 */
	public Calendar getDataHoraAbertura(int pedidoID) {
		Calendar dataHoraAbertura = Calendar.getInstance();
		dataHoraAbertura.setTimeInMillis(0);

		try {
			String sql = "SELECT DataHoraAbertura FROM CaixasGerenciamPedidos WHERE PedidoID=?";

			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, pedidoID);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return dataHoraAbertura;
			}

			if (res.next()) {
				dataHoraAbertura.setTimeInMillis(res.getTimestamp("DataHoraAbertura").getTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataHoraAbertura;
	}

	public boolean isEmpty() {
		String sql = "SELECT * FROM CaixasGerenciamPedidos;";

		try {
			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();

			return res.wasNull();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void removerPedidos(int pedidoID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM CaixasGerenciamPedidos WHERE PedidoID=?");

			pst.setInt(1, pedidoID);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerCaixas(int caixaID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM CaixasGerenciamPedidos WHERE CaixaID=?");

			pst.setInt(1, caixaID);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarDataHoraAbertura(Pedido p) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"Update CaixasGerenciamPedidos SET DataHoraAbertura=? WHERE PedidoID=?");

			Calendar calendar = p.getDataHoraAbertura();
			java.sql.Timestamp javaSqlTimestamp = null;
			javaSqlTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

			pst.setTimestamp(1, javaSqlTimestamp);
			pst.setInt(2, p.getId());
			

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarCaixaDoPedido(Pedido p, int caixaID) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"Update CaixasGerenciamPedidos SET CaixaID=? WHERE PedidoID=?");

			pst.setInt(1, caixaID);
			pst.setInt(2, p.getId());
			

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarPedidoDoCaixa(Pedido p, int caixaID) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"Update CaixasGerenciamPedidos SET PedidoID=? WHERE CaixaID=?");

			pst.setInt(1, p.getId());
			pst.setInt(2, caixaID);
			

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarCaixas(int caixaIdOld, int caixaIdNew) {
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"Update CaixasGerenciamPedidos SET CaixaID=? WHERE CaixaID=?");

			pst.setInt(1, caixaIdNew);
			pst.setInt(2, caixaIdOld);
			

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
