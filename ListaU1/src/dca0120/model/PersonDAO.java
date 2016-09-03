package dca0120.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class PersonDAO {
	
	Connection conexao;
	
	public PersonDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void criarTabela() {
		try {
			Statement st = conexao.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS Persons (" +
	                 " PersonID INTEGER, " +
	                 " FirstName VARCHAR(255), " +
	                 " LastName VARCHAR(255), " +
	                 " Address VARCHAR(255), " +
	                 " City VARCHAR(255), " +
	                 ")";
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserirPessoa(Person p) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Persons(PersonID, LastName, FirstName, "
					+ "Address, City) VALUES (?, ?, ?, ?, ?)");
			
	        pst.setInt(1, p.getId());
	        pst.setString(2, p.getLastName());
	        pst.setString(3, p.getFirstName());
	        pst.setString(4, p.getAddress());
	        pst.setString(5, p.getCity());
	        pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> getTodasPessoas() {
		List<Person> lista = new ArrayList<Person>();
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT * FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	Person p = new Person();
	        	p.setId(res.getInt("PersonID"));
	        	p.setLastName(res.getString("LastName"));
	        	p.setFirstName(res.getString("FirstName"));
	        	p.setAddress(res.getString("Address"));
	        	p.setCity(res.getString("City"));
	        	lista.add(p);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return lista;
	}
	
	public List<String> getLastNames() {
		List<String> lista = new ArrayList<String>();
		
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT LastName FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	String lastName = res.getString("LastName");
	        	lista.add(lastName);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<String> getFirstNames() {
		List<String> lista = new ArrayList<String>();
		
		try {
			Statement st = conexao.createStatement();
	        String sql = "SELECT FirstName FROM Persons";
	        ResultSet res = st.executeQuery(sql);
	       
	        while (res.next()) {
	        	String firstName = res.getString("FirstName");
	        	lista.add(firstName);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}
