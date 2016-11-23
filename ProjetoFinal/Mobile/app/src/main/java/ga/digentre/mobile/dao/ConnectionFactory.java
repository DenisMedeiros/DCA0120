package ga.digentre.mobile.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author denis
 * @author ney
 * 
 * Classe respons�vel por abrir a conex�o com o banco de dados.
 *
 */
public class ConnectionFactory {
	
	//private final static String caminhoCompleto = "jdbc:h2:~/dados";jdbc:h2:tcp://localhost/~/banco
	private final static String caminhoCompleto = "jdbc:h2:tcp://localhost/~/banco4";
	private final static String usuario = "usuario";
	private final static String senha = "123";
	
	
	/** Abre a conex�o com o banco de dados e retorna-a.
	 *
	 * @return conexao Conex�o com o banco de dados.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conexao = DriverManager.getConnection(caminhoCompleto, usuario, senha);
		return conexao;
		
	}
}
