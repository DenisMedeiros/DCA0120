package dca0120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dca0120.model.Endereco;
import dca0120.model.Entregador;
import dca0120.model.Pedido;
import dca0120.model.Pedido.Status;

/**
 * @author neypi
 *
 */
public class Pedidos {
	
	private Connection conexao;
	
	/**
	 * Cria a tabela Pedidos no banco de dados.
	 */
	public Pedidos() {
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
	public void CriaTabelaPedidos() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Pedidos (" +
						"ID  INTEGER AUTO_INCREMENT, " +
						"VolumeTotal FLOAT, " +
						"PesoTotal FLOAT, " +
						"ValorTotal FLOAT, " +
						"Status INTEGER, " +
						"Descricao VARCHAR(800), " +
						"EntregadorID INTEGER NOT NULL, " +
						"DataHoraEntrega TIMESTAMP, " +
						"PRIMARY KEY (ID), " +
						"FOREIGN KEY ( EntregadorID ) REFERENCES Entregadores (FuncionarioID), " +
						");";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param p
	 * @param entregadorID
	 */
	public void inserirPedido(Pedido p, int entregadorID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Pedidos(VolumeTotal, PesoTotal, ValorTotal"
					+ "Status, Descricao, EntregadorID, DataHoraEntrega) VALUES ();");
			
	        pst.setFloat(1, p.getVolumeTotal());
	        pst.setFloat(2, p.getPesoTotal());
	        pst.setFloat(3, p.getValorTotal());
	        pst.setInt(4, p.getStatus().getCodigo());
	        pst.setString(5, p.getDescricao());
	        pst.setInt(6, entregadorID);
	        	        	        
	        Calendar calendar = p.getDataHoraEntrega();
	        java.sql.Timestamp javaSqlTimestamp = null;
	        
	        if(calendar != null) {
		        javaSqlTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());  
	        }
	        pst.setTimestamp(7, javaSqlTimestamp);
	        
	        pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * @param id
	 * @param dataHoraEntrega
	 */
	public void setDataHoraEntrega(int id, Calendar dataHoraEntrega) {
		
		try {
			java.sql.Timestamp entrega = null;
			if(dataHoraEntrega != null) {
				entrega = new java.sql.Timestamp(dataHoraEntrega.getTime().getTime());  
	        }
	        	        
			PreparedStatement pst = conexao.prepareStatement("UPDATE Pedidos SET DataHoraEntrega = ? WHERE ID=?;");
			
			pst.setTimestamp(1, entrega);
	        pst.setInt(2, id);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id
	 * @param status
	 */
	public void alterarStatus(int id, Status status) {
		
		try {       	        
			PreparedStatement pst = conexao.prepareStatement("UPDATE Pedidos SET Status=? WHERE ID=?;");
			
			pst.setInt(1, status.getCodigo());
	        pst.setInt(2, id);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Pedido getPedidoWithID(int id) {
		Pedido p = null;
		
		try {
			String sql = "SELECT * FROM Pedidos WHERE ID=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			
			if(res.wasNull()) {
				return p;
			}
			
			if(res.next()) {
				Status status = Status.ABERTO;
				switch(res.getInt("Status")) {
				case 1:
					status = Status.ABERTO;
					break;
				case 2:
					status = Status.EM_PREPARO;
					break;
				case 3:
					status = Status.AGUARDANDO_ENTREGADOR;
					break;
				case 4:
					status = Status.EM_TRANSITO;
					break;
				case 5:
					status = Status.ENTREGUE;
					break;
				case 6:
					status = Status.CANCELADO;
					break;
				}
				Entregador ent;
				Entregadores ents = new Entregadores();
				ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				dataHoraAbertura = null;//a definir acesso
				Endereco enderecoEntrega = null;//a definir acesso
				p = new Pedido(id, status, res.getString("Descricao"), ent, dataHoraAbertura,
						dataHoraEntrega, enderecoEntrega);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		
		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Pedidos;";
			ResultSet res = st.executeQuery(sql);
			
			if(res.wasNull()) {
				return lista;
			}
			
			while (res.next()) {
				Status status = Status.ABERTO;
				switch(res.getInt("Status")) {
				case 1:
					status = Status.ABERTO;
					break;
				case 2:
					status = Status.EM_PREPARO;
					break;
				case 3:
					status = Status.AGUARDANDO_ENTREGADOR;
					break;
				case 4:
					status = Status.EM_TRANSITO;
					break;
				case 5:
					status = Status.ENTREGUE;
					break;
				case 6:
					status = Status.CANCELADO;
					break;
				}
				Entregador ent;
				Entregadores ents = new Entregadores();
				ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				dataHoraAbertura = null;//a definir acesso
				Endereco enderecoEntrega = null;//a definir acesso
				Pedido p = new Pedido(res.getInt("ID"), status, res.getString("Descricao"), ent, dataHoraAbertura,
						dataHoraEntrega, enderecoEntrega);
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
