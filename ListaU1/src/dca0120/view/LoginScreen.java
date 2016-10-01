package dca0120.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dca0120.model.Person;
import dca0120.model.PersonDAO;
import dca0120.utils.Password;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Cursor;

import java.awt.FlowLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class LoginScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -125543653643154060L;
	private JPanel contentPane;
	private JPasswordField textFieldPassword;
	private JFormattedTextField textFieldLogin;
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public LoginScreen() throws ParseException {
		setResizable(false);
		setTitle("Lista U2 - Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel labelTelaLogin = new JLabel("Tela de Login");
		labelTelaLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelTelaLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(labelTelaLogin);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel2_1 = new JPanel();
		panel2.add(panel2_1);
		panel2_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelLogin = new JLabel("Login ");
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel2_1.add(labelLogin);
		
				
		textFieldLogin = new JFormattedTextField();
		textFieldLogin.setColumns(20);
		
		panel2_1.add(textFieldLogin);
		
		JPanel panel2_2 = new JPanel();
		panel2.add(panel2_2);
		panel2_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setHorizontalAlignment(SwingConstants.CENTER);
		panel2_2.add(labelSenha);
		
		textFieldPassword = new JPasswordField();	
		
		panel2_2.add(textFieldPassword);
		textFieldPassword.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel3_1 = new JPanel();
		panel_3.add(panel3_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		panel3_1.add(btnCancelar);
		
		JButton btnFazerLogin = new JButton("Fazer Login");
		btnFazerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PersonDAO pd = new PersonDAO();
				Person p = pd.getPerson(textFieldLogin.getText());
				
				if (p != null) {
					String passwordSHA256 = Password.plainToSHA256(new String(textFieldPassword.getPassword()), p.getEmail().getBytes());
					if (p.getPassword().equals(passwordSHA256)){
						dispose();
						UserInformationScreen uis = new UserInformationScreen();
						uis.setVisible(true);
						return;			
					}
				}
				
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorretos!");
	
			}
		});
		panel3_1.add(btnFazerLogin);
		
		JPanel panel3_2 = new JPanel();
		panel_3.add(panel3_2);
		FlowLayout fl_panel3_2 = new FlowLayout(FlowLayout.CENTER, 5, 10);
		panel3_2.setLayout(fl_panel3_2);
		
		JLabel lblNoPossuiCadastro = new JLabel("N\u00E3o possui cadastro ainda?");
		lblNoPossuiCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		panel3_2.add(lblNoPossuiCadastro);
		
		JLabel lblCadastro = new JLabel("Clique aqui");
		lblCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				RegistrationScreen rs = null;
				try {
					rs = new RegistrationScreen();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs.setVisible(true);

			
			}
		});
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setForeground(Color.BLUE);
		panel3_2.add(lblCadastro);
		
	}
}
