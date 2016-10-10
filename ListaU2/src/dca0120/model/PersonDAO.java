package dca0120.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author denis
 * @author ney
 * 
 * Classe responsÃ¡vel por interconectar a classe Person com a tabela Person.
 *
 */
public class PersonDAO {
	
	private Connection conexao;
	
	 /**
	  * Construtor padrÃ£o. Ele abre a conexÃ£o com o banco de dados.
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
	public void criarTabelaPerson() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS Persons (" +
	                 "PersonID INTEGER AUTO_INCREMENT, " +
	                 "Name VARCHAR(255) NOT NULL, " +
	                 "Login VARCHAR(255) NOT NULL UNIQUE, " +
	                 "Password VARCHAR(255) NOT NULL, " +
	                 "Email VARCHAR(255) NOT NULL UNIQUE, " +
	                 "Birthday DATE, " +
	                 "Photo BLOB NOT NULL, " +
	                 "PhoneHome VARCHAR(255), " +
	                 "PhoneMobile VARCHAR(255), " +
	                 "PRIMARY KEY (PersonID), " +
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
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Persons(Name, Login, "
					+ "Password, Email, Birthday, Photo, PhoneHome, PhoneMobile) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
	        pst.setString(1, p.getName());
	        pst.setString(2, p.getLogin());
	        pst.setString(3, p.getPassword());
	        pst.setString(4, p.getEmail());
	        
	        Calendar calendar = p.getBirthdate();
	        java.sql.Date javaSqlDate;
	        
	        if(calendar != null) {
		        calendar.set(Calendar.HOUR_OF_DAY, 0);
		        calendar.set(Calendar.MINUTE, 0);
		        calendar.set(Calendar.SECOND, 0);
		        calendar.set(Calendar.MILLISECOND, 0);
		        javaSqlDate = new java.sql.Date(calendar.getTime().getTime());  
	        } else {
	        	javaSqlDate = new java.sql.Date(0);
	        }
	        
	        pst.setDate(5, javaSqlDate);
	        
	        pst.setBlob(6, new SerialBlob(p.getPhoto()));
	        pst.setString(7, p.getPhoneMobile());
	        pst.setString(8, p.getPhoneHome());
	        pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * 	
	 * Retorna a pessoa que possui o login especificado.
	 * 
	 * @param login Login do usuário.
	 * @return p Pessoa com o login especificado (ou null, caso não exista).
	 */
	public Person getPerson(String login) {
		Person p = null;
		try {
			String sql = "SELECT * FROM Persons WHERE Login=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setString(1, login);
	        ResultSet res = pst.executeQuery();
	       
	        if (res.wasNull()) {
	        	return p;
	        }
	        
	        if(res.next()) {
	        
		        p = new Person();	
	        	p.setId(res.getInt("PersonID"));
	        	p.setName(res.getString("Name"));
	        	p.setLogin(res.getString("Login"));
	        	p.setPassword(res.getString("Password"));
	        	p.setEmail(res.getString("Email"));
	        	
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(res.getDate("Birthday"));
	        	
	        	p.setBirthdate(c);
	        	
	        	Blob blob = res.getBlob("Photo");
	        	byte[] buffer = blob.getBytes(1, (int)blob.length());
	        	
	        	p.setPhoto(buffer);
	        	p.setPhoneHome(res.getString("PhoneHome"));
	        	p.setPhoneMobile(res.getString("PhoneMobile"));
        	
	        }
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
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
	        	p.setPassword(res.getString("Password"));
	        	p.setEmail(res.getString("Email"));
	        	
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(res.getDate("Birthday"));
	        	p.setBirthdate(c);
	        	
	        	Blob blob = res.getBlob("Photo");
	        	byte[] buffer = blob.getBytes(1, (int)blob.length());
	        	p.setPhoto(buffer);
	        	
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
	 * 	
	 * Retorna a pessoa que possui o email especificado.
	 * 
	 * @param email Email do usu⳩o.
	 * @return p Pessoa com o email especificado (ou null, caso n䯠exista).
	 */
	public Person getPersonWithEmail(String email) {
		Person p = null;
		try {
			String sql = "SELECT * FROM Persons WHERE Email=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setString(1, email);
	        ResultSet res = pst.executeQuery();
	       
	        if (res.wasNull()) {
	        	return p;
	        }
	        
	        if(res.next()) {
	        
		        p = new Person();	
	        	p.setId(res.getInt("PersonID"));
	        	p.setName(res.getString("Name"));
	        	p.setLogin(res.getString("Login"));
	        	p.setPassword(res.getString("Password"));
	        	p.setEmail(res.getString("Email"));
	        	
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(res.getDate("Birthday"));
	        	
	        	p.setBirthdate(c);
	        	
	        	Blob blob = res.getBlob("Photo");
	        	byte[] buffer = blob.getBytes(1, (int)blob.length());
	        	
	        	p.setPhoto(buffer);
	        	p.setPhoneHome(res.getString("PhoneHome"));
	        	p.setPhoneMobile(res.getString("PhoneMobile"));
        	
	        }
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	

}


