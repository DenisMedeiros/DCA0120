package dca0120.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressDAO {
	
	private Connection conexao;
	
	 /**
	  * Construtor padrÃ£o. Ele abre a conexÃ£o com o banco de dados.
	  */
	public AddressDAO() {
		try {
			conexao = ConnectionFactory.getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	        String sql = "CREATE TABLE IF NOT EXISTS Addresses (" +
	                 "Street VARCHAR(255), " +
	                 "Number INTEGER, " +
	                 "Complement VARCHAR(255), " +
	                 "District VARCHAR(255), " +
	                 "Zip VARCHAR(255), " +
	                 "City VARCHAR(255), " +
	                 "State VARCHAR(255), " +
	                 "PersonID INTEGER, " +
	                 "FOREIGN KEY (PersonID) REFERENCES Persons(PersonID), " +
	                 "PRIMARY KEY (PersonID), " +
	                 ")";
	        st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insere um endereço no banco de dados.
	 * 
	 * @param ad Objeto do tipo Address a ser inserido no banco de dados.
	 */
	public void inserirAddress(Address ad) {
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO Addresses(Street, Number, "
					+ "Complement, District, Zip, City, State, PersonID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
	        pst.setString(1, ad.getStreet());
	        pst.setInt(2, ad.getNum());
	        pst.setString(3, ad.getComplement());
	        pst.setString(4, ad.getDistrict());
	        pst.setString(5, ad.getZip());
	        pst.setString(6, ad.getCity());
	        pst.setString(7, ad.getState());
	        pst.setInt(8, ad.getPerson().getId());
	        pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	
	 * Retorna o endereço da pessoa especificada.
	 * 
	 * @param p Objeto da pessoa deste endereço.
	 * @return a Endereço da pessoa.
	 */
	public Address getAddress(Person p) {
		Address a = null;
		try {
			String sql = "SELECT * FROM Addresses WHERE PersonID=?;";
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setInt(1, p.getId());
	        ResultSet res = pst.executeQuery();
	        
	        if (res.wasNull()) {
	        	return a;
	        }
	        
	        res.next();
	        
	        a = new Address();	
	        a.setStreet(res.getString("Street"));
	        a.setNum(res.getInt("Number"));
	        a.setComplement(res.getString("Complement"));
	        a.setDistrict(res.getString("District"));
	        a.setZip(res.getString("Zip"));
	        a.setCity(res.getString("City"));
	        a.setState(res.getString("State"));
       
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}

}
