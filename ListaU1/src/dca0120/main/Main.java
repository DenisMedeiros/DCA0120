package dca0120.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import dca0120.model.AddressDAO;
import dca0120.model.PersonDAO;
import dca0120.utils.Password;
import dca0120.view.LoginScreen;

/**
 * 
 * @author denis 
 * @author ney
 * 
 * Classe Main.
 *
 */
public class Main {
	
	
	public static void main(String[] args) {
		
		// Altera o Look And Feel para o atual do sistema operacional.
		try {			
		    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e){

		}
		
				
		
		// Cria o banco de dados e as tabelas, caso elas não existam.
		PersonDAO pd = new PersonDAO();
		AddressDAO ad = new AddressDAO();
		
		pd.criarTabelaPerson();
		ad.criarTabelaAddress();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
