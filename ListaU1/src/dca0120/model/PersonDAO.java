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
import java.util.Calendar;
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
	 */
	public void criarTabela() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS Persons (" +
	                 " PersonID INTEGER AUTO_INCREMENT, " +
	                 " Name VARCHAR(255) NOT NULL, " +
	                 " Login VARCHAR(255) NOT NULL, " +
	                 " Email VARCHAR(255) NOT NULL, " +
	                 " Birthday DATE, " +
	                 " Street VARCHAR(255), " +
	                 " City VARCHAR(255), " +
	                 " Photo BLOB, " +
	                 " Password VARCHAR(255) NOT NULL, " +
	                 " Primary key (PersonID), " +
	                 "FOREIGN KEY (Street,City) REFERENCES Address(Street,City)" +
	                 ")";//password tipo?
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria a tabela Address no banco de dados.
	 * 
	 */
	public void criarTabelaAddress() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS Address (" +
	                 " Street VARCHAR(255), " +
	                 " Number INTEGER, " +
	                 " Complement VARCHAR(255), " +
	                 " District VARCHAR(255), " +
	                 " Zip VARCHAR(255), " +
	                 " City VARCHAR(255), " +
	                 " State VARCHAR(255), " +
	                 " Primary key (Street, Number, City), " +
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
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Persons(Name, Login"
					+ "Email, Birthday, Street, City, Photo, Password, PhoneMobile, PhoneHome) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
	        pst.setString(1, p.getName());
	        pst.setString(2, p.getLogin());
	        pst.setString(3, p.getEmail());
	        pst.setDate(4, (Date) p.getBirthday().getTime());
	        pst.setString(5, p.getStreet());
	        pst.setString(6, p.getCity());
	        imagePath = new FileInputStream(p.getPhoto());
	        pst.setBinaryStream(7, imagePath, (int) p.getPhoto().length());
	        pst.setString(8, p.getPassword());
	        pst.setString(9, p.getPhoneMobile());
	        pst.setString(10, p.getPhoneHome());
	        pst.executeUpdate();
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Insere um endere�o no banco de dados.
	 * 
	 * @param ad Objeto do tipo Address a ser inserido no banco de dados.
	 */
	public void inserirAddress(Address ad) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Address(Street, Number"
					+ "Complement, District, Zip, City, State) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
	        pst.setString(1, ad.getStreet());
	        pst.setInt(2, ad.getNum());
	        pst.setString(3, ad.getComplement());
	        pst.setString(4, ad.getDistrict());
	        pst.setString(5, ad.getZip());
	        pst.setString(6, ad.getCity());
	        pst.setString(7, ad.getState());
	        pst.executeUpdate();
		} catch (SQLException e) {
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
	        	p.setLogin(res.getString("Login"));
	        	Date birth = res.getDate("Birthday");
	        	birth.getTime();
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(birth);
	        	p.setBirthday(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
	        	p.setEmail(res.getString("Email"));
	        	p.setStreet(res.getString("Street"));
	        	p.setCity(res.getString("City"));
	        	p.setPhoto(res.getBlob("Photo").getBinaryStream().toString());
	        	p.setPassword(res.getString("Password"));
	        	p.setPhoneHome(res.getString("PhoneHome"));
	        	p.setPhoneMobile(res.getString("PhoneMobile"));
	        	
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
	
	/**
	 * Retorna a lista de todos os logins das pessoas cadastradas no sistema.
	 * 
	 * @return lista Lista de logins de todas as pessoas cadastradas.
	 */
	public List<String> getLogins() {
		List<String> lista = new ArrayList<String>();
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT Login FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	String Login = res.getString("Login");
	        	lista.add(Login);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * Retorna a lista de todos os logins das pessoas cadastradas no sistema.
	 * 
	 * @return lista Lista de logins de todas as pessoas cadastradas.
	 */
	public List<String> getPasswords() {
		List<String> lista = new ArrayList<String>();
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT Password FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	String Password = res.getString("Password");
	        	lista.add(Password);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}


