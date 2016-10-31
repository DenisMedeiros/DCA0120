package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dca0120.model.Endereco;
import dca0120.model.Pedido;

/**
 * @author ney
 * @author denis
 *         <hr>
 *         Classe responsavel por gerar a tabela EnderecosEntrega e conectar um
 *         objeto Pedido com seu respectivo Objeto Endereco
 *         </hr>
 */
public class EnderecosEntregaDAO {

	private Connection conexao;

	/**
	 * Construtor padrão da classe EnderecosEntrega que abre conexão com o banco
	 * de dados
	 */
	public EnderecosEntregaDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria a tabela EnderecosEntrega no banco de dados.
	 */
	public void criarTabelaEnderecosEntrega() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS EnderecosEntrega (PedidoID INTEGER NOT NULL, "
					+ "Latitude FLOAT NOT NULL, Longitude FLOAT NOT NULL, Descricao VARCHAR(800), "
					+ "PRIMARY KEY (PedidoID), FOREIGN KEY (PedidoID) REFERENCES Pedidos (ID));";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere um endereco de entrega de um pedido no banco de dados
	 * 
	 * @param p
	 *            Objeto do tipo Pedido a ter seu endereco de entrega inserido
	 *            no banco de dados
	 */
	public void inserirEnderecosEntrega(Pedido p) {

		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO EnderecosEntrega(PedidoID, Latitude, Longitude, Descricao) VALUES (?, ?, ?, ?)");

			pst.setInt(1, p.getId());
			pst.setFloat(2, p.getEnderecoEntrega().getLatitude());
			pst.setFloat(3, p.getEnderecoEntrega().getLongitude());
			pst.setString(4, p.getEnderecoEntrega().getDescricao());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retorna o endereco de entrega de um pedido com seu ID (ou null caso não
	 * exista tal ID)
	 * 
	 * @param pedidoID
	 *            tipo Inteiro que referencia um objeto Pedido
	 * @return objeto do tipo Endereco (ou null caso nao exista tal pedido)
	 */
	public Endereco getEnderecoEntrega(int pedidoID) {
		Endereco endereco = null;

		try {
			String sql = "SELECT * FROM EnderecosEntrega WHERE PedidoID=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setInt(1, pedidoID);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return endereco;
			}

			if (res.next()) {
				endereco = new Endereco(res.getFloat("Longitude"), res.getFloat("Latitude"),
						res.getString("Descricao"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endereco;
	}
	
	public boolean isEmpty() {
		String sql = "SELECT * FROM EnderecosEntrega;";

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
