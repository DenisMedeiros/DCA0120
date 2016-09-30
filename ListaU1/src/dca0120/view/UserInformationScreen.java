package dca0120.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInformationScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2744639044460401052L;
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldDataNascimento;
	private JTextField textFieldTelefone;
	private JTextField textFieldCelular;
	private JTextField textFieldRua;
	private JTextField textFieldNumero;
	private JTextField textFieldComplemento;
	private JTextField textFieldBairro;
	private JTextField textFieldCEP;
	private JTextField textFieldEstado;
	private JTextField textFieldCidade;

	/**
	 * Create the frame.
	 */
	public UserInformationScreen() {
		setTitle("Lista U2 - Dados do Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{466, 0};
		gbl_contentPane.rowHeights = new int[] {40, 400, 40, 30};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new 
				double[]{0.0, 0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel labelTelaLogin = new JLabel("Dados do Usu\u00E1rio");
		labelTelaLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelTelaLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_labelTelaLogin = new GridBagConstraints();
		gbc_labelTelaLogin.fill = GridBagConstraints.BOTH;
		gbc_labelTelaLogin.insets = new Insets(0, 0, 5, 0);
		gbc_labelTelaLogin.gridx = 0;
		gbc_labelTelaLogin.gridy = 0;
		contentPane.add(labelTelaLogin, gbc_labelTelaLogin);
		
		JPanel panel2 = new JPanel();
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		contentPane.add(panel2, gbc_panel2);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel2_1 = new JPanel();
		panel2.add(panel2_1);
		panel2_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel2_1.add(labelLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setEditable(false);
		textFieldLogin.setToolTipText("");
		textFieldLogin.setColumns(30);
		panel2_1.add(textFieldLogin);
		
		JPanel panel = new JPanel();
		panel2.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setEditable(false);
		textFieldNome.setColumns(30);
		panel.add(textFieldNome);
		
		JPanel panel_1 = new JPanel();
		panel2.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(30);
		panel_1.add(textFieldEmail);
		
		JPanel panel_2 = new JPanel();
		panel2.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblDataDeNascimento);
		
		textFieldDataNascimento = new JTextField();
		textFieldDataNascimento.setEditable(false);
		textFieldDataNascimento.setColumns(30);
		panel_2.add(textFieldDataNascimento);
		
		JPanel panel_4 = new JPanel();
		panel2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneResidencial = new JLabel("Telefone Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTelefoneResidencial);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setEditable(false);
		textFieldTelefone.setColumns(30);
		panel_4.add(textFieldTelefone);
		
		JPanel panel_5 = new JPanel();
		panel2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneCelular = new JLabel("Telefone Celular");
		lblTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTelefoneCelular);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setEditable(false);
		textFieldCelular.setColumns(30);
		panel_5.add(textFieldCelular);
		
		JPanel panel_6 = new JPanel();
		panel2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblRua);
		
		textFieldRua = new JTextField();
		textFieldRua.setEditable(false);
		textFieldRua.setColumns(30);
		panel_6.add(textFieldRua);
		
		JPanel panel_7 = new JPanel();
		panel2.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNumero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setEditable(false);
		textFieldNumero.setColumns(30);
		panel_7.add(textFieldNumero);
		
		JPanel panel_8 = new JPanel();
		panel2.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblComplemento);
		
		textFieldComplemento = new JTextField();
		textFieldComplemento.setEditable(false);
		textFieldComplemento.setColumns(30);
		panel_8.add(textFieldComplemento);
		
		JPanel panel_9 = new JPanel();
		panel2.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblBairro);
		
		textFieldBairro = new JTextField();
		textFieldBairro.setEditable(false);
		textFieldBairro.setColumns(30);
		panel_9.add(textFieldBairro);
		
		JPanel panel_10 = new JPanel();
		panel2.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblCep);
		
		textFieldCEP = new JTextField();
		textFieldCEP.setEditable(false);
		textFieldCEP.setColumns(30);
		panel_10.add(textFieldCEP);
		
		JPanel panel_11 = new JPanel();
		panel2.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblEstado);
		
		textFieldEstado = new JTextField();
		textFieldEstado.setEditable(false);
		textFieldEstado.setColumns(30);
		panel_11.add(textFieldEstado);
		
		JPanel panel_12 = new JPanel();
		panel2.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblCidade);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setEditable(false);
		textFieldCidade.setColumns(30);
		panel_12.add(textFieldCidade);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		contentPane.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel3_1 = new JPanel();
		panel_3.add(panel3_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		panel3_1.add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		panel3_1.add(btnCadastrar);
		
	}
}
