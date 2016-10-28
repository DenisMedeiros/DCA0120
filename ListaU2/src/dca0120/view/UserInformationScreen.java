package dca0120.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dca0120.model.Address;
import dca0120.model.AddressDAO;
import dca0120.model.Person;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class UserInformationScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2744639044460401052L;
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldBirthdate;
	private JTextField textFieldPhoneHome;
	private JTextField textFieldPhoneMobile;
	private JTextField textFieldStreet;
	private JTextField textFieldNumber;
	private JTextField textFieldComplement;
	private JTextField textFieldDistrict;
	private JTextField textFieldZip;
	private JTextField textFieldState;
	private JTextField textFieldCity;
	private JTextField textFieldPhoto;

	/**
	 * Create the frame.
	 */
	public UserInformationScreen(Person p) {
		
		AddressDAO ad = new AddressDAO();
		Address a = ad.getAddress(p);
		
		setTitle("Lista U2 - Dados do Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {466, 0};
		gbl_contentPane.rowHeights = new int[] {40, 500, 40, 30};
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
		
		textFieldLogin = new JTextField(p.getLogin());
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
		
		textFieldName = new JTextField(p.getName());
		textFieldName.setEditable(false);
		textFieldName.setColumns(30);
		panel.add(textFieldName);
		
		JPanel panel_1 = new JPanel();
		panel2.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblEmail);
		
		textFieldEmail = new JTextField(p.getEmail());
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(30);
		panel_1.add(textFieldEmail);
		
		JPanel panel_3 = new JPanel();
		panel2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblFoto = new JLabel("Foto");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblFoto);
		
		textFieldPhoto = new JTextField();
		textFieldPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PictureScreen ps = new PictureScreen(p);
				ps.setVisible(true);	
			}
		});
		
		textFieldPhoto.setText("Clique aqui para ver sua foto...");
		textFieldPhoto.setEditable(false);
		textFieldPhoto.setColumns(30);
		panel_3.add(textFieldPhoto);
		
		JPanel panel_2 = new JPanel();
		panel2.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblDataDeNascimento);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formatted = sdf.format(p.getBirthdate().getTime());
		
		textFieldBirthdate = new JTextField(formatted);
		textFieldBirthdate.setEditable(false);
		textFieldBirthdate.setColumns(30);
		panel_2.add(textFieldBirthdate);
		
		JPanel panel_4 = new JPanel();
		panel2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneResidencial = new JLabel("Telefone Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTelefoneResidencial);
		
		textFieldPhoneHome = new JTextField(p.getPhoneHome());
		textFieldPhoneHome.setEditable(false);
		textFieldPhoneHome.setColumns(30);
		panel_4.add(textFieldPhoneHome);
		
		JPanel panel_5 = new JPanel();
		panel2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTelefoneCelular = new JLabel("Telefone Celular");
		lblTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTelefoneCelular);
		
		textFieldPhoneMobile = new JTextField(p.getPhoneMobile());
		textFieldPhoneMobile.setEditable(false);
		textFieldPhoneMobile.setColumns(30);
		panel_5.add(textFieldPhoneMobile);
		

		
		JPanel panel_6 = new JPanel();
		panel2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblRua);
		
		textFieldStreet = new JTextField(a.getStreet());
		textFieldStreet.setEditable(false);
		textFieldStreet.setColumns(30);
		panel_6.add(textFieldStreet);
		
		JPanel panel_7 = new JPanel();
		panel2.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNumero);
		
		textFieldNumber = new JTextField(String.valueOf(a.getNum()));
		textFieldNumber.setEditable(false);
		textFieldNumber.setColumns(30);
		panel_7.add(textFieldNumber);
		
		JPanel panel_8 = new JPanel();
		panel2.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblComplemento);
		
		textFieldComplement = new JTextField(a.getComplement());
		textFieldComplement.setEditable(false);
		textFieldComplement.setColumns(30);
		panel_8.add(textFieldComplement);
		
		JPanel panel_9 = new JPanel();
		panel2.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblBairro);
		
		textFieldDistrict = new JTextField(a.getDistrict());
		textFieldDistrict.setEditable(false);
		textFieldDistrict.setColumns(30);
		panel_9.add(textFieldDistrict);
		
		JPanel panel_10 = new JPanel();
		panel2.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblCep);
		
		textFieldZip = new JTextField(a.getZip());
		textFieldZip.setEditable(false);
		textFieldZip.setColumns(30);
		panel_10.add(textFieldZip);
		
		JPanel panel_11 = new JPanel();
		panel2.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblEstado);
		
		textFieldState = new JTextField(a.getState());
		textFieldState.setEditable(false);
		textFieldState.setColumns(30);
		panel_11.add(textFieldState);
		
		JPanel panel_12 = new JPanel();
		panel2.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblCidade);
		
		textFieldCity = new JTextField(a.getCity());
		textFieldCity.setEditable(false);
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
		
		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		panel3_1.add(btnExit);
		
	}
}
