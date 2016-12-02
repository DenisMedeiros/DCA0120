package dca0120.views;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dca0120.dao.CaixasDAO;
import dca0120.dao.CaixasGerenciamPedidosDAO;
import dca0120.dao.CaixasGerenciamProdutosDAO;
import dca0120.dao.ConnectionFactory;
import dca0120.dao.EnderecosEntregaDAO;
import dca0120.dao.EntregadoresDAO;
import dca0120.dao.PedidosContemProdutosDAO;
import dca0120.dao.PedidosDAO;
import dca0120.dao.ProdutosDAO;
import dca0120.dao.TelefonesDAO;
import dca0120.model.Caixa;
import dca0120.utils.Hashing;

public class PrepararBDServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571193L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Cria todas as tabelas, caso não existam ainda.
		
		CaixasDAO cd = new CaixasDAO();
		cd.criarTabelaFuncionarios();
		cd.criarTabelaCaixas();
		EntregadoresDAO ed = new EntregadoresDAO();
		ed.criarTabelaEntregadores();
		TelefonesDAO td = new TelefonesDAO();
		td.criarTabelaTelefones();
		PedidosDAO pd = new PedidosDAO();
		pd.criarTabelaPedidos();
		ProdutosDAO pd2 = new ProdutosDAO();
		pd2.criarTabelaProdutos();
		EnderecosEntregaDAO eed = new EnderecosEntregaDAO();
		eed.criarTabelaEnderecosEntrega();
		CaixasGerenciamPedidosDAO cgpd = new CaixasGerenciamPedidosDAO();
		cgpd.criarTabelaCaixasGerenciamPedidos();
		CaixasGerenciamProdutosDAO cgp2 = new CaixasGerenciamProdutosDAO();
		cgp2.criarTabelaCaixasGerenciamProdutos();
		PedidosContemProdutosDAO pcpd = new PedidosContemProdutosDAO();
		pcpd.criarTabelaPedidosContemProdutos();
		
		// Cria o administrador padrão.
		InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream("administrador.properties");
		Properties prop = new Properties();
		
	    try {
			prop.load(is);
		   
		    String nome = prop.getProperty("nome");
		    String cpf = prop.getProperty("cpf");
		    String dataNascimentoStr = prop.getProperty("dataNascimento");
		    String telefonesStr = prop.getProperty("telefones");
		    String senha = prop.getProperty("senha");
		    
		    
	        // Valida a data de nascimento.
	        Calendar dataNascimento = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        try {
				dataNascimento.setTime(sdf.parse(dataNascimentoStr));
			} catch (ParseException e) {
				request.setAttribute("mensagem", "Data de nascimento inválida!");
	            response.sendRedirect(request.getHeader("referer"));
	            return;
			}
	        
	        List<String> telefones =  Arrays.asList(telefonesStr.split(";"));
	        String senhaCriptografada = Hashing.plainToSHA256(senha, cpf.getBytes());
	        
	        Caixa caixa = new Caixa(0, cpf, senhaCriptografada, nome, dataNascimento, telefones, true);
	        
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.append("Tabelas e administrador criados com sucesso!\n");
			out.close();
	        
			if(cd.getCaixaWithCPF("00000000000") == null) {
				cd.inserirCaixa(caixa, -1);
			}
		    
		    
		} catch (IOException e) {
			e.printStackTrace();
		}

     
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    }

} 