package dca0120.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

/**
 * 
 * @author denis
 * @author ney
 * 
 * Classe responsável por interconectar a classe Person com a tabela Person.
 *
 */
public class PersonDAO {
	
	private Connection conexao;
	
	 /**
	  * Construtor padrão. Ele abre a conexão com o banco de dados.
	  */
	public PersonDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria a tabela Person no banco de dados.
	 * 
	 */
	public void criarTabela() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS Persons (" +
	                 " PersonID INTEGER AUTO_INCREMENT, " +
	                 " Name VARCHAR(255) NOT NULL, " +
	                 " Login VARCHAR(255) NOT NULL, " +
	                 " Email VARCHAR(255) NOT NULL, " +
	                 " Birthday DATE" +
	                 " Address VARCHAR(255), " +
	                 " City VARCHAR(255), " +
	                 " Photo BLOB, " +
	                 " Primary key (PersonID), " +
	                 ")";
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Insere uma pessoa no banco de dados.
	 * 
	 * @param p Objeto do tipo Pessoa a ser inserido no banco de dados.
	 */
	public void inserirPessoa(Person p) {
		FileInputStream imagePath;
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Persons(PersonID, Name, Login"
					+ "Email, Birthday, Address, City, Photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
	        pst.setInt(1, p.getId());
	        pst.setString(2, p.getName());
	        pst.setString(3, p.getLogin());
	        pst.setString(4, p.getEmail());
	        pst.setDate(5, (Date) p.getBirthday().getTime());
	        pst.setString(6, p.getRua());
	        pst.setString(7, p.getCity());
	        imagePath = new FileInputStream(p.getPhoto());
	        pst.setBinaryStream(8, imagePath, (int) p.getPhoto().length());
	        pst.executeUpdate();
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	
	 * Retorna a lista de todas as pessoas cadastradas
	 * 
	 * @return lista Lista de pessoas cadastradas.
	 */
	public List<Person> getTodasPessoas() {
		List<Person> lista = new ArrayList<Person>();
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT * FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	Person p = new Person();
	        	p.setId(res.getInt("PersonID"));
	        	p.setName(res.getString("Name"));
	        	p.setRua(res.getString("Address"));
	        	p.setCity(res.getString("City"));
	        	lista.add(p);
	        	
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * Retorna a lista de todos os nomes das pessoas cadastradas no sistema.
	 * 
	 * @return lista Lista de nomes de todas as pessoas cadastradas.
	 */
	public List<String> getNames() {
		List<String> lista = new ArrayList<String>();
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT Name FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	String Name = res.getString("Name");
	        	lista.add(Name);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
