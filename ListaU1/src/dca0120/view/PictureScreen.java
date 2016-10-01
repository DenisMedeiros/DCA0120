package dca0120.view;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dca0120.model.Person;

import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PictureScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6903032987198552440L;
	private JPanel contentPane;
	private JLabel lblFoto;

	/**
	 * Create the frame.
	 */
	public PictureScreen(Person p) {
		setTitle("Foto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		ImageIcon ic = new ImageIcon(p.getPhoto());
		
		Image img = ic.getImage();
		Image newimg = img.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon nic = new ImageIcon(newimg);
		
		lblFoto = new JLabel(nic);
		lblFoto.setSize(new Dimension(400, 400));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(lblFoto);
	}

}
