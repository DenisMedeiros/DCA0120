package dca0120.main;

import java.awt.EventQueue;

import dca0120.view.TelaPrincipal;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
