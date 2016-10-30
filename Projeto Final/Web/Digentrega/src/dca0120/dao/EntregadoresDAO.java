/**
 * 
 */
package dca0120.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import dca0120.model.Entregador;

/**
 * @author ney
 * @author denis
 *         <hr>
 *         Classe respons�vel por interconectar a classe Entregador com a tabela
 *         Entregadores.
 *         </hr>
 */
public class EntregadoresDAO extends FuncionariosDAO {

	/**
	 * Construtor padr�o da classe Entregadores que abre conex�o com o banco de
	 * dados via classe m�e
	 */
	public EntregadoresDAO() {
		super();
	}

	/**
	 * Cria a tabela Entregadores no banco de dados.
	 */
	public void criarTabelaEntregadores() {
		try {
			Statement st = conexao.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Entregadores (FuncionarioID INTEGER NOT NULL, "
					+ "CNH VARCHAR(20) NOT NULL, Placa VARCHAR(7) NOT NULL, PRIMARY KEY (FuncionarioID), "
					+ "FOREIGN KEY (FuncionarioID ) REFERENCES Funcionarios(ID));";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere um Entregador no banco de dados.
	 * 
	 * @param e
	 *            Objeto do tipo Entregador a ser inserido no banco de dados
	 * @param admID
	 *            ID do Administrador, tipo int da classe herdada Funcionarios,
	 *            que adiciona o Entregador no banco de dados
	 */
	public void inserirEntregador(Entregador e, int admID) {
		try {
			this.inserirFuncionario(e.getNome(), e.getCpf(), e.getSenha(), e.getDataNascimento(), admID);
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO Entregador(FuncionarioID, CNH, Placa) VALUES (?, ?, ?);");

			pst.setInt(1, this.getID(e.getCpf()));
			pst.setString(2, e.getCnh());
			pst.setNString(3, e.getPlacaVeiculo());

			pst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Retorna objeto do tipo Entregador que possui o id especificado.
	 * 
	 * @param id
	 *            referencia do Entregador a ser procurado no banco de dados
	 * @return Objeto do tipo Entregador com o ID especificado (ou null, caso
	 *         n�o exista).
	 */
	public Entregador getEntregadorWithID(int id) {
		Entregador ent = null;

		try {
			String sql = "SELECT * FROM Entregadores INNER JOIN Funcionarios "
					+ "ON Funcionarios.ID=Entregadores.FuncionarioID WHERE Funcionarios.ID=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return ent;
			}

			if (res.next()) {
				Calendar dataNasc = Calendar.getInstance();
				dataNasc.setTime(res.getDate("DataNascimento"));

				TelefonesDAO t = new TelefonesDAO();
				List<String> telefones = t.getTelefones(id);

				ent = new Entregador(id, res.getString("CPF"), res.getString("Senha"), res.getString("Nome"), dataNasc,
						telefones, res.getString("CNH"), res.getString("Placa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ent;
	}

	/**
	 * Retorna objeto do tipo Entregador que possui o cpf especificado.
	 * 
	 * @param cpf
	 *            dado do Entregador a ser procurado no banco de dados
	 * @return Objeto do tipo Entregador com o cpf especificado (ou null, caso
	 *         n�o exista).
	 */
	public Entregador getEntregadorWithCpf(String cpf) {
		Entregador ent = null;

		try {
			String sql = "SELECT * FROM Entregadores INNER JOIN Funcionarios "
					+ "ON Funcionarios.ID=Entregadores.FuncionarioID WHERE CPF=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setString(1, cpf);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return ent;
			}

			if (res.next()) {
				Calendar dataNasc = Calendar.getInstance();
				dataNasc.setTime(res.getDate("DataNascimento"));

				TelefonesDAO t = new TelefonesDAO();
				List<String> telefones = t.getTelefones(res.getInt("FuncionarioID"));

				ent = new Entregador(res.getInt("FuncionarioID"), cpf, res.getString("Senha"), res.getString("Nome"),
						dataNasc, telefones, res.getString("CNH"), res.getString("Placa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ent;
	}

	/**
	 * Retorna lista com todos os entregadores registrados no banco de dados
	 * 
	 * @return lista com todos os entregadores registrados
	 */
	public List<Entregador> getTodosEntregadores() {
		List<Entregador> lista = new ArrayList<Entregador>();
		try {
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM Entregadores INNER JOIN Funcionarios "
					+ "ON Funcionarios.ID=Entregadores.FuncionarioID;";
			ResultSet res = st.executeQuery(sql);

			while (res.next()) {
				Calendar dataNasc = Calendar.getInstance();
				dataNasc.setTime(res.getDate("DataNascimento"));

				TelefonesDAO t = new TelefonesDAO();
				List<String> telefones = t.getTelefones(res.getInt("FuncionarioID"));

				Entregador ent = new Entregador(res.getInt("FuncionarioID"), res.getString("CPF"),
						res.getString("Senha"), res.getString("Nome"), dataNasc, telefones, res.getString("CNH"),
						res.getString("Placa"));

				lista.add(ent);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}