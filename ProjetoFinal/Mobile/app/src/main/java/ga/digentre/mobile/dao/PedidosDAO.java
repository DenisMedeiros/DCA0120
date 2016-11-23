package ga.digentre.mobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ga.digentre.mobile.model.Endereco;
import ga.digentre.mobile.model.Entregador;
import ga.digentre.mobile.model.Pedido;
import ga.digentre.mobile.model.Pedido.Status;
import ga.digentre.mobile.model.Produto;

/**
 * @author ney
 * @author denis
 *         <hr>
 *         Classe respons�vel por interconectar a classe Pedido com a tabela
 *         Pedidos.
 *         </hr>
 */
public class PedidosDAO {

	private Connection conexao;

	/**
	 * Construtor da classe Pedidos que abre conex�o com o banco de dados
	 */
	public PedidosDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria a tabela Pedidos no banco de dados.
	 */
	public void criarTabelaPedidos() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Pedidos ("
					+ "ID INTEGER AUTO_INCREMENT, "
					+ "VolumeTotal FLOAT, "
					+ "PesoTotal FLOAT, "
					+ "ValorTotal FLOAT, "
					+ "Status INTEGER, "
					+ "Descricao VARCHAR(800), "
					+ "EntregadorID INTEGER NULL, "
					+ "DataHoraEntrega TIMESTAMP, "
					+ "PRIMARY KEY (ID), "
					+ "FOREIGN KEY (EntregadorID) REFERENCES Entregadores (FuncionarioID) ON DELETE CASCADE ON UPDATE CASCADE)";
			st.executeUpdate(sql);
			EnderecosEntregaDAO eed = new EnderecosEntregaDAO();
			eed.criarTabelaEnderecosEntrega();
			CaixasGerenciamPedidosDAO cgpe = new CaixasGerenciamPedidosDAO();
			cgpe.criarTabelaCaixasGerenciamPedidos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere o objeto Pedido no banco de dados
	 * 
	 * @param p
	 *            objeto a ser inserido no banco de dados
	 */
	public void inserirPedido(Pedido p, int caixaID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Pedidos(VolumeTotal, PesoTotal, ValorTotal,"
					+ "Status, Descricao, EntregadorID, DataHoraEntrega) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			pst.setFloat(1, p.getVolumeTotal());
			pst.setFloat(2, p.getPesoTotal());
			pst.setFloat(3, p.getValorTotal());
			pst.setInt(4, p.getStatus().getCodigo());
			pst.setString(5, p.getDescricao());
			
			if(p.getEntregador() != null) {
				pst.setInt(6, p.getEntregador().getId());
			} else {
				pst.setNull(6, Types.NULL);
			}

			Calendar calendar = p.getDataHoraEntrega();
			java.sql.Timestamp javaSqlTimestamp = null;
			javaSqlTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

			pst.setTimestamp(7, javaSqlTimestamp);

			pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if(rs.next())
            {
                id = rs.getInt(1);
            }
			
			p.setId(id);
			
			EnderecosEntregaDAO eed = new EnderecosEntregaDAO();
			eed.inserirEnderecosEntrega(p);
			
			CaixasGerenciamPedidosDAO cgpe = new CaixasGerenciamPedidosDAO();
			cgpe.inserirCaixasGerenciamPedidos(p, caixaID);
			
			PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
			
			List<Produto> lista = p.getProdutos();
			for(Produto pr: lista) {
				try {
					pcp.inserirPedidosContemProdutos(pr,p);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muda a data e hora de entrega de um Pedido pelo <b>id</b>
	 * 
	 * @param id
	 *            refer�ncia de tipo inteiro do pedido que ter� alterado a data
	 *            e hora da entega
	 * @param dataHoraEntrega
	 *            objeto do tipo Calendar a ser usado para alterar a data e hora
	 *            de entrega do pedido especificado pelo id
	 */
	public void setDataHoraEntrega(int id, Calendar dataHoraEntrega) {

		try {
			java.sql.Timestamp entrega = null;
			if (dataHoraEntrega != null) {
				entrega = new java.sql.Timestamp(dataHoraEntrega.getTime().getTime());
			}

			PreparedStatement pst = conexao.prepareStatement("UPDATE Pedidos SET DataHoraEntrega = ? WHERE ID=?");

			pst.setTimestamp(1, entrega);
			pst.setInt(2, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove um pedido especifico pelo id
	 * @param id referencia de tipo Int a ser usado para deletar o tal pedido
	 */
	public void removerPedido(int id) {
		
		EnderecosEntregaDAO eed = new EnderecosEntregaDAO();
		eed.removerEndereco(id);
		
		CaixasGerenciamPedidosDAO cgpe = new CaixasGerenciamPedidosDAO();
		cgpe.removerPedidos(id);
		
		PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
		pcp.removerPedido(id);
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Pedidos WHERE ID=?");

			pst.setInt(1, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Altera o Status de um pedido pelo id
	 * @param id tipo inteiro que referencia o pedido
	 * @param status novo Status desejado para o pedido
	 */
	public void alterarStatus(int id, Status status) {

		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Pedidos SET Status=? WHERE ID=?");

			pst.setInt(1, status.getCodigo());
			pst.setInt(2, id);
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna um pedido pelo id
	 * @param id inteiro que referencia um objeto de tipo Pedido
	 * @return objeto de tipo Pedido (ou null caso n�o exista o pedido com o especificado id)
	 */
	public Pedido getPedidoWithID(int id) {
		Pedido p = null;

		try {
			String sql = "SELECT * FROM Pedidos WHERE ID=?";
			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return p;
			}

			if (res.next()) {
				Status status = Status.ABERTO;
				switch (res.getInt("Status")) {
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
				EntregadoresDAO ents = new EntregadoresDAO();
				ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				CaixasGerenciamPedidosDAO cgp = new CaixasGerenciamPedidosDAO();
				dataHoraAbertura = cgp.getDataHoraAbertura(id);
				
				EnderecosEntregaDAO endereco = new EnderecosEntregaDAO();
				Endereco enderecoEntrega = endereco.getEnderecoEntrega(id);
				
				p = new Pedido(id, status, res.getString("Descricao"), ent, dataHoraAbertura, dataHoraEntrega,
						enderecoEntrega);
				PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
				List<Produto> lprod = pcp.getProdutosDoPedido(p);
				for(Produto pr: lprod) {
					p.addProduto(pr, pcp.getQuantidade(pr, p));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Retorna uma lista com todos os pedidos registrados
	 * @return Lista com os objetos de tipo Pedido registrados no banco de dados
	 */
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<Pedido>();

		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Pedidos";
			ResultSet res = st.executeQuery(sql);

			if (res.wasNull()) {
				return lista;
			}

			while (res.next()) {
				Status status = Status.ABERTO;
				switch (res.getInt("Status")) {
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
				EntregadoresDAO ents = new EntregadoresDAO();
				ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				CaixasGerenciamPedidosDAO cgp = new CaixasGerenciamPedidosDAO();
				dataHoraAbertura = cgp.getDataHoraAbertura(res.getInt("ID"));
				
				EnderecosEntregaDAO endereco = new EnderecosEntregaDAO();
				Endereco enderecoEntrega = endereco.getEnderecoEntrega(res.getInt("ID"));
				
				Pedido p = new Pedido(res.getInt("ID"), status, res.getString("Descricao"), ent, dataHoraAbertura,
						dataHoraEntrega, enderecoEntrega);
				
				PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
				List<Produto> lprod = pcp.getProdutosDoPedido(p);
				for(Produto pr: lprod) {
					p.addProduto(pr, pcp.getQuantidade(pr, p));
				}
				
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * Retorna uma lista com todos os pedidos abertos
	 * @return Lista com os objetos de tipo Pedido registrados no banco de dados
	 */
	public List<Pedido> getPedidosAbertos() {
		List<Pedido> lista = new ArrayList<Pedido>();

		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Pedidos WHERE Status=1 OR Status=2 OR Status=3 OR Status=4";
			ResultSet res = st.executeQuery(sql);

			if (res.wasNull()) {
				return lista;
			}

			while (res.next()) {
				Status status = Status.ABERTO;
				switch (res.getInt("Status")) {
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
				EntregadoresDAO ents = new EntregadoresDAO();
				if(res.getInt("EntregadorID") != 0) { 
					ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				}else {
					ent = null;
				}
				
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				CaixasGerenciamPedidosDAO cgp = new CaixasGerenciamPedidosDAO();
				dataHoraAbertura = cgp.getDataHoraAbertura(res.getInt("ID"));
				
				EnderecosEntregaDAO endereco = new EnderecosEntregaDAO();
				Endereco enderecoEntrega = endereco.getEnderecoEntrega(res.getInt("ID"));
				
				Pedido p = new Pedido(res.getInt("ID"), status, res.getString("Descricao"), ent, dataHoraAbertura,
						dataHoraEntrega, enderecoEntrega);
				
				PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
				List<Produto> lprod = pcp.getProdutosDoPedido(p);
				for(Produto pr: lprod) {
					p.addProduto(pr, pcp.getQuantidade(pr, p));
				}
				
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * Retorna uma lista com todos os pedidos fechados
	 * @return Lista com os objetos de tipo Pedido registrados no banco de dados
	 */
	public List<Pedido> getPedidosFechados() {
		List<Pedido> lista = new ArrayList<Pedido>();

		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Pedidos WHERE Status=5 OR Status=6";
			ResultSet res = st.executeQuery(sql);

			if (res.wasNull()) {
				return lista;
			}

			while (res.next()) {
				Status status = Status.ABERTO;
				switch (res.getInt("Status")) {
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
				EntregadoresDAO ents = new EntregadoresDAO();
				if(res.getInt("EntregadorID") != 0) { 
					ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				}else {
					ent = null;
				}
				
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				CaixasGerenciamPedidosDAO cgp = new CaixasGerenciamPedidosDAO();
				dataHoraAbertura = cgp.getDataHoraAbertura(res.getInt("ID"));
				
				EnderecosEntregaDAO endereco = new EnderecosEntregaDAO();
				Endereco enderecoEntrega = endereco.getEnderecoEntrega(res.getInt("ID"));
				
				Pedido p = new Pedido(res.getInt("ID"), status, res.getString("Descricao"), ent, dataHoraAbertura,
						dataHoraEntrega, enderecoEntrega);
				
				PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
				List<Produto> lprod = pcp.getProdutosDoPedido(p);
				for(Produto pr: lprod) {
					p.addProduto(pr, pcp.getQuantidade(pr, p));
				}
				
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	
	/**
	 * Testa se a tabela tem dados inseridos
	 * @return true para tabela vazia, false para tabela com dados inseridos
	 */
	public boolean isEmpty() {
		String sql = "SELECT * FROM Pedidos";

		try {
			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			
			return res.wasNull();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void alterarPedido(Pedido p) {
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Pedidos SET VolumeTotal=?, PesoTotal=?, ValorTotal=?,"
					+ "Status=?, Descricao=?, EntregadorID=?, DataHoraEntrega=? WHERE ID=?");

			pst.setFloat(1, p.getVolumeTotal());
			pst.setFloat(2, p.getPesoTotal());
			pst.setFloat(3, p.getValorTotal());
			pst.setInt(4, p.getStatus().getCodigo());
			pst.setString(5, p.getDescricao());
			
			if(p.getEntregador() != null) {
				pst.setInt(6, p.getEntregador().getId());
			} else {
				pst.setNull(6, Types.NULL);
			}

			Calendar calendar = p.getDataHoraEntrega();
			java.sql.Timestamp javaSqlTimestamp = null;

			if (calendar != null) {
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				javaSqlTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
			} else {
				javaSqlTimestamp = new java.sql.Timestamp(0);
			}
			pst.setTimestamp(7, javaSqlTimestamp);
			pst.setInt(8, p.getId());

			pst.executeUpdate();
			
			EnderecosEntregaDAO eed = new EnderecosEntregaDAO();
			eed.alterarEndereco(p);
			
			CaixasGerenciamPedidosDAO cgpe = new CaixasGerenciamPedidosDAO();
			cgpe.alterarDataHoraAbertura(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Alterar entregador no pedido informado
	 * @param pedidoID identificador do pedido a ter entegador alterado
	 * @param entregadorID identificador do entregador (por 0 para valor null)
	 */
	public void alterarEntregador(int pedidoID, int entregadorID) {
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Pedidos SET EntregadorID=? WHERE ID=?");

			if(entregadorID > 0) {
				pst.setInt(1, entregadorID);
			}else {
				pst.setNull(1, Types.NULL);
			}
			pst.setInt(2, pedidoID);
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Entregador getEntregador(Pedido p) {
		Entregador e = null;
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT EntregadorID FROM Pedidos WHERE ID=?");

			pst.setInt(1, p.getId());
			
			ResultSet res = pst.executeQuery();
			
			EntregadoresDAO ed = new EntregadoresDAO();
			
			if (res.wasNull()) {
				return e;
			}
			if(res.next()) {
				if(res.getInt("EntregadorID") != 0) { 
					e = ed.getEntregadorWithID(res.getInt("EntregadorID"));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
	
	public List<Pedido> getPedidosDoEntregador(int entregadorID) {
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Pedidos WHERE EntregadorID=?");

			pst.setInt(1, entregadorID);
			
			ResultSet res = pst.executeQuery();
			
			if (res.wasNull()) {
				return lista;
			}
			
			while (res.next()) {
				Status status = Status.ABERTO;
				switch (res.getInt("Status")) {
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
				EntregadoresDAO ents = new EntregadoresDAO();
				if(res.getInt("EntregadorID") != 0) { 
					ent = ents.getEntregadorWithID(res.getInt("EntregadorID"));
				}else {
					ent = null;
				}
				
				Calendar dataHoraEntrega = Calendar.getInstance(), dataHoraAbertura;
				dataHoraEntrega.setTimeInMillis(res.getTimestamp("DataHoraEntrega").getTime());
				CaixasGerenciamPedidosDAO cgp = new CaixasGerenciamPedidosDAO();
				dataHoraAbertura = cgp.getDataHoraAbertura(res.getInt("ID"));
				
				EnderecosEntregaDAO endereco = new EnderecosEntregaDAO();
				Endereco enderecoEntrega = endereco.getEnderecoEntrega(res.getInt("ID"));
				
				Pedido p = new Pedido(res.getInt("ID"), status, res.getString("Descricao"), ent, dataHoraAbertura,
						dataHoraEntrega, enderecoEntrega);
				
				PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
				List<Produto> lprod = pcp.getProdutosDoPedido(p);
				for(Produto pr: lprod) {
					p.addProduto(pr, pcp.getQuantidade(pr, p));
				}
				
				lista.add(p);
				
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return lista;
	}
	
}