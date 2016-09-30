package dca0120.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

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
		
		
		try {			
			// Altera o Look And Feel para o atual do sistema operacional.
		    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e){

		}
		
		
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
