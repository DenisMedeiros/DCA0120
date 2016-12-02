package dca0120.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author denis
 * @author ney
 * 
 * Classe responsável por abrir a conexão com o banco de dados.
 *
 */
public class ConnectionFactory {
	
	//private final static String caminhoCompleto = "

		
	/** Abre a conexão com o banco de dados e retorna-a.
	 *
	 * @return conexao Conexão com o banco de dados.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		
		String driver;
		String url;
		String usuario;
		String senha;
		
		InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream("bd.properties");
		
		Properties prop = new Properties();
	    try {
			prop.load(is);
		    
		    driver = prop.getProperty("driver");
		    url = prop.getProperty("url");
		    usuario = prop.getProperty("usuario");
		    senha = prop.getProperty("senha");
		    
			Class.forName(driver);
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			return conexao;
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return null;

		
	}
}
