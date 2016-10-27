package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dca0120.model.Endereco;

/**
 * @author neypi
 *
 */
public class EnderecosEntrega {
	
	private Connection conexao;
	
	public EnderecosEntrega() {
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
	public void criarTabelaEnderecosEntrega() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS EnderecosEntrega (" +
					"PedidoID  INTEGER NOT NULL, " +
					"Latitude FLOAT NOT NULL, " +
					"Longitude FLOAT NOT NULL, " +
					"Descricao VARCHAR(800), " +
					"PRIMARY KEY (PedidoID), " +
					"FOREIGN KEY ( PedidoID ) REFERENCES Pedidos (ID), " +
				");";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param endereco
	 * @param pedidoID
	 */
	public void inserirEnderecosEntrega(Endereco endereco, int pedidoID) {
		
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO EnderecosEntrega(PedidoID, Latitude, Longitude, "
					+ "Descricao) VALUES (?, ?, ?, ?);");
			
			pst.setInt(1, pedidoID);
			pst.setFloat(2, endereco.getLatitude());
			pst.setFloat(3, endereco.getLongitude());
			pst.setString(4, endereco.getDescricao());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Endereco getEnderecoEntrega(int pedidoID) {
		Endereco endereco = null;
		
		try {
			String sql = "SELECT * FROM EnderecosEntrega WHERE ID=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, pedidoID);
			ResultSet res = pst.executeQuery();
			
			if(res.wasNull()) {
				return endereco;
			}
			
			if(res.next()) {
				endereco = new Endereco(res.getFloat("Longitude"), res.getFloat("Latitude"), 
						res.getString("Descricao"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endereco;
	}
}
