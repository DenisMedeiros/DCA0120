package dca0120.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import dca0120.model.Address;
import dca0120.model.AddressDAO;
import dca0120.model.Person;
import dca0120.model.PersonDAO;
import dca0120.utils.Password;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import java.util.Calendar;
import java.util.regex.Matcher;

import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RegistrationScreen extends JFrame {
	
	File photo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2744639044460401052L;
	private JPanel contentPane;
	private JPasswordField textFieldPassword;
	private JFormattedTextField textFieldLogin;
	private JTextField textFieldName;
	private JFormattedTextField textFieldEmail;
	private JFormattedTextField textFieldBirthdate;
	private JFormattedTextField textFieldPhoneHome;
	private JFormattedTextField textFieldPhoneMobile;
	private JFormattedTextField textFieldNumber;
	private JTextField textFieldComplement;
	private JTextField textFieldDistrict;
	private JFormattedTextField textFieldZIP;
	private JFormattedTextField textFieldState;
	private JTextField textFieldCity;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public RegistrationScreen() throws ParseException {
		setTitle("Lista U2 - Tela de Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {660, 0};
		gbl_contentPane.rowHeights = new int[] {40, 500, 40, 30, 0};
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
		textFieldLogin.setColumns(30);
		
		textFieldLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String LOGIN_PATTERN = 
				        "^[a-zA-Z0-9]*$";
			
				Pattern pattern = Pattern.compile(LOGIN_PATTERN);
				Matcher matcher = pattern.matcher(textFieldLogin.getText());
				
				if(!textFieldLogin.getText().isEmpty() && !matcher.matches()) {
					textFieldLogin.setText("");
					JOptionPane.showMessageDialog(null, "Login inválido!");
				}
			}
		});
		
		panel2_1.add(textFieldLogin);
		
		JPanel panel2_2 = new JPanel();
		panel2.add(panel2_2);
		panel2_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setHorizontalAlignment(SwingConstants.CENTER);
		panel2_2.add(labelSenha);
		
		textFieldPassword = new JPasswordField();
		panel2_2.add(textFieldPassword);
		textFieldPassword.setColumns(30);
		
		JPanel panel = new JPanel();
		panel2.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelNome);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(30);
		panel.add(textFieldName);
		
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
		
		JPanel panelPhoto = new JPanel();
		panel2.add(panelPhoto);
		panelPhoto.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		panelPhoto.add(lblPhoto);
		
		JFormattedTextField textFieldPhoto = new JFormattedTextField();
		textFieldPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG, PNG and GIF Images", "jpg", "gif", "png");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	textFieldPhoto.setText(chooser.getSelectedFile().getName());
			    	photo = chooser.getSelectedFile();		    	
			    }
		
			}
		});

		textFieldPhoto.setText("Clique aqui para selecionar sua foto...");
		textFieldPhoto.setColumns(30);
		panelPhoto.add(textFieldPhoto);
		
		JPanel panel_2 = new JPanel();
		panel2.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblDataDeNascimento);
		
		textFieldBirthdate = new JFormattedTextField(new MaskFormatter("##/##/####"));
		textFieldBirthdate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    sdf.setLenient(false);
			    if(sdf.parse(textFieldBirthdate.getText(), new ParsePosition(0)) == null) {
			    	textFieldBirthdate.setText("");
			    	JOptionPane.showMessageDialog(null, "Data de nascimento inválida!");
			    }
			}
		});
		
		
		textFieldBirthdate.setColumns(30);
		
		
		panel_2.add(textFieldBirthdate);
		
		JPanel panel_4 = new JPanel();
		panel2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneResidencial = new JLabel("Telefone Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTelefoneResidencial);
		
		textFieldPhoneHome = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		textFieldPhoneHome.setColumns(30);
		
		
		panel_4.add(textFieldPhoneHome);
		
		JPanel panel_5 = new JPanel();
		panel2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneCelular = new JLabel("Telefone Celular");
		lblTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTelefoneCelular);
		
		textFieldPhoneMobile = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		textFieldPhoneMobile.setColumns(30);
		panel_5.add(textFieldPhoneMobile);
		
		JPanel panel_6 = new JPanel();
		panel2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblRua);
		
		JTextField textFieldStreet = new JTextField();
		textFieldStreet.setColumns(30);
		panel_6.add(textFieldStreet);
		
		JPanel panel_7 = new JPanel();
		panel2.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNumero);
		
		textFieldNumber = new JFormattedTextField(new NumberFormatter());
		textFieldNumber.setColumns(30);
		panel_7.add(textFieldNumber);
		
		JPanel panel_8 = new JPanel();
		panel2.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblComplemento);
		
		textFieldComplement = new JTextField();
		textFieldComplement.setColumns(30);
		panel_8.add(textFieldComplement);
		
		JPanel panel_9 = new JPanel();
		panel2.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblBairro);
		
		textFieldDistrict = new JTextField();
		textFieldDistrict.setColumns(30);
		panel_9.add(textFieldDistrict);
		
		JPanel panel_10 = new JPanel();
		panel2.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblCep);
		
		textFieldZIP = new JFormattedTextField(new MaskFormatter("#####-###"));
		textFieldZIP.setText("");
		textFieldZIP.setColumns(30);
		panel_10.add(textFieldZIP);
		
		
		
		JPanel panel_11 = new JPanel();
		panel2.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblEstado);
		
		textFieldState = new JFormattedTextField(new MaskFormatter("UU"));
		textFieldState.setText("");
		textFieldState.setColumns(30);
		panel_11.add(textFieldState);
		
		JPanel panel_12 = new JPanel();
		panel2.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblCidade);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(30);
		panel_12.add(textFieldCity);
		
		JPanel panel3 = new JPanel();
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.insets = new Insets(0, 0, 5, 0);
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.gridx = 0;
		gbc_panel3.gridy = 2;
		contentPane.add(panel3, gbc_panel3);
		panel3.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel3_1 = new JPanel();
		panel3.add(panel3_1);
		
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
				
				
				// Cria o objeto Person.
				Person p = new Person();
				
				if(textFieldName.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome inválido!");
					return;
				}
				p.setName(textFieldName.getText());
			
				if(textFieldLogin.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Login inválido!");
					return;
				}
				p.setLogin(textFieldLogin.getText());
				
				if(textFieldEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "E-mail inválido!");
					return;
				}
				p.setEmail(textFieldEmail.getText());
				
				if(new String(textFieldPassword.getPassword()).trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Senha inválida!");
					return;
				}
				
				// Protege a senha.
				
				System.out.println(new String(textFieldPassword.getPassword()));
				
				String passwordSHA256 = Password.plainToSHA256(new String(textFieldPassword.getPassword()), p.getEmail().getBytes());	
				p.setPassword(passwordSHA256);
				
				if (photo == null) {
					JOptionPane.showMessageDialog(null, "Foto inválida!");
					return;
				}
				
				try {
					byte[] buffer = Files.readAllBytes(photo.toPath());
					p.setPhoto(buffer);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				if (!textFieldBirthdate.getText().equals("  /  /    ")) {
					try {
						Calendar c = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						c.setTime(sdf.parse(textFieldBirthdate.getText()));
						p.setBirthdate(c);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Data de nascimento inválida!");
					}// all done
				}
				
				if (!textFieldPhoneHome.getText().equals("( )      -     ")) {
					p.setPhoneHome(textFieldPhoneHome.getText());
				}
				
				if (!textFieldPhoneMobile.getText().equals("( )      -     ")) {
					p.setPhoneMobile(textFieldPhoneMobile.getText());
				}

				// Cria o objeto Address.
				
				Address a = new Address();
				
				a.setStreet(textFieldStreet.getText());
				try {
					a.setNum(Integer.parseInt(textFieldNumber.getText()));
				} catch(NumberFormatException ex) {
					a.setNum(0);
				}
				
				a.setComplement(textFieldComplement.getText());
				a.setDistrict(textFieldDistrict.getText());
				a.setZip(textFieldZIP.getText());
				a.setState(textFieldState.getText());
				a.setCity(textFieldCity.getText());
				
				// Insere os dados no BD
				
				PersonDAO pd = new PersonDAO();
				AddressDAO ad = new AddressDAO();
				
				pd.inserirPessoa(p);	
				a.setPerson(pd.getPerson(p.getLogin()));
				
				
				
				ad.inserirAddress(a);
				
				JOptionPane.showMessageDialog(null, "Usuário cadastro com sucesso!");
				dispose();
				
			}
		});
		panel3_1.add(btnCadastrar);
		
	}
}
