package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import dca0120.model.Pedido;

public class CaixasGerenciamPedidos {
	public Connection conexao;

	/**
	 * 
	 */
	public CaixasGerenciamPedidos() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void criarTabelaCaixasGerenciamPedidos() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS CaixasGerenciamPedidos (" +
					"CaixaID INTEGER NOT NULL, " +
					"PedidoID  INTEGER NOT NULL, " +
					"DataHoraAbertura TIMESTAMP NOT NULL, " + 
					"PRIMARY KEY (CaixaID, PedidoID), " +
					"FOREIGN KEY (CaixaID) REFERENCES Caixas(FuncionarioID), " +
					"FOREIGN KEY (PedidoID) REFERENCES Pedidos(ID), " +
					")";
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param p
	 * @param caixaID
	 */
	public void inserirCaixasGerenciamPedidos(Pedido p, int caixaID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO CaixasGerenciamPedidos(CaixaID, PedidoID, "
					+ "DataHoraAbertura) VALUES (?, ?, ?);");

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
	
	public Calendar getDataHoraAbertura(int pedidoID) {
		Calendar dataHoraAbertura = Calendar.getInstance();
		
		try {
			String sql = "SELECT DataHoraAbertura FROM CaixasGerenciamPedidos WHERE PedidoID=?;";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setInt(1, pedidoID);
			ResultSet res = pst.executeQuery();
			
			if(res.wasNull()) {
				return dataHoraAbertura;
			}
			
			if(res.next()) {
				dataHoraAbertura.setTimeInMillis(res.getTimestamp("DataHoraAbertura").getTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataHoraAbertura;
	}
}
