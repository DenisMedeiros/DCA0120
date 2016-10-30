package dca0120.servlets;

import java.io.IOException;

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

public class PrepararBDServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571193L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
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
		
		
        request.getRequestDispatcher(request.getContextPath()).forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = null;

        if ((username.equals("kiran")) && (password.equals("kiran"))) {
            message = "Welcome "+username+" thanks for login...";
        } else {
            message = "You are not the valid user...";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

} 