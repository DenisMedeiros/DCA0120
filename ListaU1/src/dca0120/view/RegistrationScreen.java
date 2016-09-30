package dca0120.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistrationScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2744639044460401052L;
	private JPanel contentPane;
	private JPasswordField textFieldSenha;
	private JFormattedTextField textFieldLogin;
	private JTextField textFieldNome;
	private JFormattedTextField textFieldEmail;
	private JFormattedTextField textFieldDataNascimento;
	private JFormattedTextField textFieldTelefone;
	private JFormattedTextField textFieldCelular;
	private JTextField textFieldRua;
	private JFormattedTextField textFieldNumero;
	private JTextField textFieldComplemento;
	private JTextField textFieldBairro;
	private JFormattedTextField textFieldCEP;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public RegistrationScreen() throws ParseException {
		setResizable(false);
		setTitle("Lista U2 - Tela de Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 520);
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
		
		JLabel labelTelaLogin = new JLabel("Tela de Cadastro");
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
		
		textFieldLogin = new JFormattedTextField();
		textFieldLogin.setToolTipText("");
		textFieldLogin.setColumns(30);
		panel2_1.add(textFieldLogin);
		
		JPanel panel2_2 = new JPanel();
		panel2.add(panel2_2);
		panel2_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setHorizontalAlignment(SwingConstants.CENTER);
		panel2_2.add(labelSenha);
		
		textFieldSenha = new JPasswordField();
		panel2_2.add(textFieldSenha);
		textFieldSenha.setColumns(30);
		
		JPanel panel = new JPanel();
		panel2.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(30);
		panel.add(textFieldNome);
		
		JPanel panel_1 = new JPanel();
		panel2.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);		
		panel_1.add(lblEmail);
		
		textFieldEmail = new JFormattedTextField();
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String EMAIL_PATTERN = 
				        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(textFieldEmail.getText());
				
				if(!textFieldEmail.getText().isEmpty() && !matcher.matches()) {
					textFieldEmail.setText("");
					JOptionPane.showMessageDialog(null, "E-mail inválido!");
				}
			}
		});
		
		textFieldEmail.setColumns(30);
	
		panel_1.add(textFieldEmail);
		
		JPanel panel_2 = new JPanel();
		panel2.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblDataDeNascimento);
		
		textFieldDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		textFieldDataNascimento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    sdf.setLenient(false);
			    if(sdf.parse(textFieldDataNascimento.getText(), new ParsePosition(0)) == null) {
			    	JOptionPane.showMessageDialog(null, "Data de nascimento inválida!");
			    }
			}
		});
		
		
		textFieldDataNascimento.setColumns(30);
		
		
		panel_2.add(textFieldDataNascimento);
		
		JPanel panel_4 = new JPanel();
		panel2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneResidencial = new JLabel("Telefone Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTelefoneResidencial);
		
		textFieldTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		textFieldTelefone.setColumns(30);
		
		
		panel_4.add(textFieldTelefone);
		
		JPanel panel_5 = new JPanel();
		panel2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneCelular = new JLabel("Telefone Celular");
		lblTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTelefoneCelular);
		
		textFieldCelular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		textFieldCelular.setColumns(30);
		panel_5.add(textFieldCelular);
		
		JPanel panel_6 = new JPanel();
		panel2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblRua);
		
		textFieldRua = new JTextField();
		textFieldRua.setColumns(30);
		panel_6.add(textFieldRua);
		
		JPanel panel_7 = new JPanel();
		panel2.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNumero);
		
		textFieldNumero = new JFormattedTextField(new NumberFormatter());
		textFieldNumero.setColumns(30);
		panel_7.add(textFieldNumero);
		
		JPanel panel_8 = new JPanel();
		panel2.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblComplemento);
		
		textFieldComplemento = new JTextField();
		textFieldComplemento.setColumns(30);
		panel_8.add(textFieldComplemento);
		
		JPanel panel_9 = new JPanel();
		panel2.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblBairro);
		
		textFieldBairro = new JTextField();
		textFieldBairro.setColumns(30);
		panel_9.add(textFieldBairro);
		
		JPanel panel_10 = new JPanel();
		panel2.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblCep);
		
		textFieldCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
		textFieldCEP.setColumns(30);
		panel_10.add(textFieldCEP);
		
		
		
		JPanel panel_11 = new JPanel();
		panel2.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblEstado);
		
		textField_8 = new JTextField();
		textField_8.setColumns(30);
		panel_11.add(textField_8);
		
		JPanel panel_12 = new JPanel();
		panel2.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblCidade);
		
		textField_9 = new JTextField();
		textField_9.setColumns(30);
		panel_12.add(textField_9);
		
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
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Cria o objeto pessoa
				
				
				
			}
		});
		panel3_1.add(btnCadastrar);
		
	}
}
