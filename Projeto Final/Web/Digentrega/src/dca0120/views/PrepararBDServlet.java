package dca0120.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dca0120.dao.CaixasDAO;
import dca0120.dao.CaixasGerenciamPedidosDAO;
import dca0120.dao.CaixasGerenciamProdutosDAO;
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
		List<String> telefones = new ArrayList<String>();
		telefones.add("123123123");
		
		String senha = Hashing.plainToSHA256("123", "00000000000".getBytes());
		
		Caixa caixa = new Caixa(0, "00000000000", senha, "Administrador", Calendar.getInstance(), telefones, true);
		
		if(cd.getCaixaWithCPF("00000000000") == null) {
			cd.inserirCaixa(caixa, -1);
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.append("Tabelas e administrador criados com sucesso!\n");
		out.close();
     
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    }

} 