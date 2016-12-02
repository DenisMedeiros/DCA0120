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
	
	public static Connection conexao = null;

		
	/** Abre a conexão com o banco de dados e retorna-a.
	 *
	 * @return conexao Conexão com o banco de dados.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		
		if(conexao != null) {
			if(!conexao.isClosed()) {
				return conexao;
			}
		}
		
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
			conexao = DriverManager.getConnection(url, usuario, senha);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return conexao;

		
	}
}
