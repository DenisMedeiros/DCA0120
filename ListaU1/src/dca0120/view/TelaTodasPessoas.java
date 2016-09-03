package dca0120.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dca0120.model.Person;
import dca0120.model.PersonDAO;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TelaTodasPessoas extends JFrame {

	private static final long serialVersionUID = -5969381227186491278L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;


	/**
	 * Create the frame.
	 */
	public TelaTodasPessoas() {
		initialize();
		
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("ID"); 
		model.addColumn("LastName"); 
		model.addColumn("FirstName");
		model.addColumn("Address");
		model.addColumn("City");
		
		PersonDAO pd = new PersonDAO();
		List<Person> lista = pd.getTodasPessoas();
		
		for(Person p: lista) {
			model.addRow(new Object[]{p.getId(), p.getLastName(), p.getFirstName(), p.getAddress(), p.getCity()});
		}
		
		table.setModel(model);	
		
		
	}
	private void initialize() {
		setTitle("Dados obtidos do banco de dados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
