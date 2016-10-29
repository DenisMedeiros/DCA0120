package dca0120.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import dca0120.model.Caixa;

/**
 * @author ney
 * @author denis
 *
 *         <hr>
 * 		Classe responsável por interconectar a classe Caixa com a tabela
 *         Caixas.
 *         </hr>
 */
public class Caixas extends Funcionarios {

	/**
	 * Construtor padrão da classe Caixas que abre conexão com o banco de dados
	 * via classe mãe
	 */
	public Caixas() {
		super();
	}

	/**
	 * Cria a tabela Caixas no banco de dados.
	 */
	public void criarTabelaCaixas() {
		try {
			Statement st = this.getConexao().createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Caixas (FuncionarioID INTEGER NOT NULL, "
					+ "EAdminstrador BOOLEAN DEFAULT false, PRIMARY KEY (FuncionarioID), "
					+ "FOREIGN KEY (FuncionarioID ) REFERENCES Funcionarios(ID), " + ");";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere um objeto Caixa no banco de dados.
	 * 
	 * @param c
	 *            Objeto do tipo Caixa a ser inserido no banco de dados
	 * @param admID
	 *            ID do Administrador, tipo int da classe herdada Funcionarios,
	 *            que adiciona o Caixa no banco de dados
	 */
	public void inserirCaixa(Caixa c, int admID) {
		try {
			this.inserirFuncionario(c.getNome(), c.getCpf(), c.getSenha(), c.getDataNascimento(), admID);
			PreparedStatement pst = this.getConexao()
					.prepareStatement("INSERT INTO Caixas(FuncionarioID, EAdministrador) VALUES (?, ?);");

			pst.setInt(1, this.getID(c.getCpf()));
			pst.setBoolean(2, c.isAdministrador());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna objeto do tipo Caixa que possui o cpf especificado.
	 * 
	 * @param cpf
	 *            CPF do Caixa a ser procurado no banco de dados
	 * @return c Objeto do tipo Caixa com o cpf especificado (ou null, caso
	 *         não exista).
	 */
	public Caixa getCaixaWithCPF(String cpf) {
		Caixa c = null;

		try {
			String sql = "SELECT * FROM Caixas INNER JOIN Funcionarios ON Funcionarios.ID=Caixas.FuncionarioID "
					+ "WHERE CPF=?;";
			PreparedStatement pst = this.getConexao().prepareStatement(sql);

			pst.setString(1, cpf);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return c;
			}

			if (res.next()) {
				Calendar dataN = Calendar.getInstance();
				dataN.setTime(res.getDate("DataNascimento"));

				Telefones t = new Telefones();
				List<String> telefones = t.getTelefones(res.getInt("FuncionarioID"));

				c = new Caixa(res.getInt("FuncionarioID"), cpf, res.getString("Senha"), res.getString("Nome"), dataN,
						telefones, res.getBoolean("EAdministrador"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	/**
	 * Retorna objeto do tipo Caixa que possui o id especificado.
	 * 
	 * @param id
	 *            ID do Caixa a ser procurado no banco de dados
	 * @return c Objeto do tipo Caixa com o id especificado (ou null, caso nao
	 *         exista).
	 */
	public Caixa getCaixaWithID(int id) {
		Caixa c = null;

		try {
			String sql = "SELECT * FROM Caixas INNER JOIN Funcionarios ON Funcionarios.ID=Caixas.FuncionarioID "
					+ "WHERE Caixas.FuncionarioID=?;";
			PreparedStatement pst = this.getConexao().prepareStatement(sql);

			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();

			if (res.wasNull()) {
				return c;
			}

			if (res.next()) {
				Calendar dataNasc = Calendar.getInstance();
				dataNasc.setTime(res.getDate("DataNascimento"));

				Telefones t = new Telefones();
				List<String> telefones = t.getTelefones(id);

				c = new Caixa(id, res.getString("CPF"), res.getString("Senha"), res.getString("Nome"), dataNasc,
						telefones, res.getBoolean("EAdministrador"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	/**
	 * Retorna lista com todos os caixas registrados
	 * 
	 * @return lista Lista de todos os caixas registrados
	 */
	public List<Caixa> getTodosCaixas() {
		List<Caixa> lista = new ArrayList<Caixa>();
		try {
			Statement st = this.getConexao().createStatement();
			String sql = "SELECT * FROM Caixas INNER JOIN Funcionarios ON Funcionarios.ID=Caixas.FuncionarioID;";
			ResultSet res = st.executeQuery(sql);

			while (res.next()) {
				Calendar dataNasc = Calendar.getInstance();
				dataNasc.setTime(res.getDate("DataNascimento"));

				Telefones t = new Telefones();
				List<String> telefones = t.getTelefones(res.getInt("FuncionarioID"));

				Caixa c = new Caixa(res.getInt("FuncionarioID"), res.getString("CPF"), res.getString("Senha"),
						res.getString("Nome"), dataNasc, telefones, res.getBoolean("EAdministrador"));

				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
