package dca0120.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dca0120.model.Person;
import dca0120.model.PersonDAO;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = -8287323256759666912L;
	
	private TelaTodasPessoas telaTodasPessoas;
	private TelaTodosNomes telaTodosNomes;
	private TelaTodosSobrenomes telaTodosSobrenomes;
	
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel3;
	private JLabel labelAcessoBD;
	private JLabel labelCriandoTabela;
	private JLabel labelInserindoDados;
	private JLabel labelConcluido;
	private JButton buttonTodosNomes;
	private JButton buttonTodasPessoas;
	private Component verticalGlue;
	private JButton buttonTodosSobrenomes;

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public TelaPrincipal() throws ClassNotFoundException, SQLException {
		
		initialize();
		
		labelAcessoBD.setVisible(true);
		PersonDAO pd = new PersonDAO();
		
		labelCriandoTabela.setVisible(true);
		pd.criarTabela();
		
		labelInserindoDados.setVisible(true);
		Person p1 = new Person(1, "Fernandes", "Carla", "Rua 1", "Natal");
		Person p2 = new Person(1, "Curvelo", "Danilo", "Rua 2", "Mossoró");
		Person p3 = new Person(1, "Fernandes", "Flavia", "Rua 3", "Natal");
		
		// Insere somente se não foram inseridos ainda.
		if(pd.getTodasPessoas().size() == 0) {
			pd.inserirPessoa(p1);
			pd.inserirPessoa(p2);
			pd.inserirPessoa(p3);
		}
		
		labelConcluido.setVisible(true);
		
	}
	
	private void initialize() {
		setTitle("Lista Unidade 1");
		
		telaTodasPessoas = null;
		telaTodosNomes = null;
		telaTodosSobrenomes = null;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		setContentPane(contentPane);
		
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(0, 10, 0, 0));
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(4, 1, 10, 0));
		
		labelAcessoBD = new JLabel("[1] Conectando-se ao banco de dados...");
		labelAcessoBD.setVisible(false);
		panel1.add(labelAcessoBD);
		
		labelCriandoTabela = new JLabel("[2] Criando tabelas...");
		labelCriandoTabela.setVisible(false);
		panel1.add(labelCriandoTabela);
		
		labelInserindoDados = new JLabel("[3] Inserindo dados padrões...");
		labelInserindoDados.setVisible(false);
		panel1.add(labelInserindoDados);
		
		labelConcluido = new JLabel("[4] Preparação inicial concluída!");
		labelConcluido.setVisible(false);
		panel1.add(labelConcluido);
		
		verticalGlue = Box.createVerticalGlue();
		contentPane.add(verticalGlue);
		
		panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panel3);
		panel3.setLayout(new GridLayout(0, 3, 0, 0));
		
		buttonTodasPessoas = new JButton("Todas as pessoas");
		panel3.add(buttonTodasPessoas);
		
		
		buttonTodosNomes = new JButton("Todos os primeiros nomes");
		panel3.add(buttonTodosNomes);
		
		buttonTodosSobrenomes = new JButton("Todos os sobrenomes");
		panel3.add(buttonTodosSobrenomes);
		
		// Adicionando eventos.
		
		buttonTodasPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(telaTodasPessoas != null) {
					telaTodasPessoas.dispose();
				}
				telaTodasPessoas = new TelaTodasPessoas();
				telaTodasPessoas.setVisible(true);
			}
		});
		
		buttonTodosNomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(telaTodosNomes != null) {
					telaTodosNomes.dispose();
				}
				telaTodosNomes = new TelaTodosNomes();
				telaTodosNomes.setVisible(true);
			}
		});
		
		buttonTodosSobrenomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(telaTodosSobrenomes != null) {
					telaTodosSobrenomes.dispose();
				}
				telaTodosSobrenomes = new TelaTodosSobrenomes();
				telaTodosSobrenomes.setVisible(true);
			}
		});

		
	}

}
