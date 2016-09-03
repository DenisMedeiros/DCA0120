package dca0120.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private final static String caminhoCompleto = "jdbc:h2:./dados";
	private final static String usuario = "usuario";
	private final static String senha = "123";
	
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conexao = DriverManager.getConnection(caminhoCompleto, usuario, senha);
		return conexao;
		
	}
}
