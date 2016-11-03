package dca0120.views;

import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.EntregadoresDAO;
import dca0120.dao.PedidosDAO;
import dca0120.dao.ProdutosDAO;
import dca0120.model.Caixa;
import dca0120.model.Endereco;
import dca0120.model.Entregador;
import dca0120.model.Pedido;
import dca0120.model.Produto;

public class CadastrarPedidoServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Você precisa entrar no sistema para acessar esta função.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		// Verifica se o usuário que quer acessar esta função é o administrador.
		Integer administrador = (Integer) session.getAttribute("administrador");
		if(administrador == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		ProdutosDAO pd = new ProdutosDAO();
		EntregadoresDAO ed = new EntregadoresDAO();
		List<Entregador> entregadores = ed.getTodosEntregadores();
		
		if(entregadores.size() > 0) {
			request.setAttribute("entregadores", entregadores);
		} else {
			request.setAttribute("entregadores", null);
		}
			
		List<Produto> produtos = pd.getTodosProdutos();
		
		if(produtos.size() > 0) {
			request.setAttribute("produtos", produtos);
		} else {
			request.setAttribute("produtos", null);
		}
		
        request.getRequestDispatcher("/cadastrarPedido.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Você precisa entrar no sistema para acessar esta função.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		ProdutosDAO prodDAO = new ProdutosDAO();
		PedidosDAO pedDAO = new PedidosDAO();
		EntregadoresDAO ed = new EntregadoresDAO();
		
		// Verifica se o usuário que quer acessar esta função é o caixa.
		Integer caixaID = (Integer) session.getAttribute("caixa");
		if(caixaID == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
        String descricaoPedido = request.getParameter("descricaoPedido");
        String latitudeStr = request.getParameter("latitude");
        String longitudeStr = request.getParameter("latitude");
        String descricaoEndereco = request.getParameter("descricaoEndereco");
        String entregadorIdStr = request.getParameter("entregador");
        int entregadorId = Integer.parseInt(entregadorIdStr);
        Entregador entregador = ed.getEntregadorWithID(entregadorId);
   
        float latitude = Float.parseFloat(latitudeStr);
        float longitude = Float.parseFloat(longitudeStr);

        Endereco endereco = new Endereco(latitude, longitude, descricaoEndereco);

        
        // Prepara a lista de produtos.
        Calendar momentoAbertura = Calendar.getInstance();
        Calendar momentoEntrega =  Calendar.getInstance();
        momentoEntrega.add(Calendar.MINUTE, 30);
        
        Pedido pedido = new Pedido(0, Pedido.Status.ABERTO, descricaoPedido, entregador, momentoEntrega, momentoAbertura, endereco);
       
        Enumeration<String> parametros = request.getParameterNames();
        while(parametros.hasMoreElements()) {
        	String nomeParametro = parametros.nextElement();
        	if(nomeParametro.contains("produto_")) {
        		String idStr = nomeParametro.replace("produto_", "");
        		String quantidadeStr = request.getParameter(nomeParametro);
        		int quantidade = Integer.parseInt(quantidadeStr);
        		int idProduto = Integer.parseInt(idStr);
        		Produto prod = prodDAO.getProduto(idProduto);
        		pedido.addProduto(prod, quantidade);
        	}
        }
       
        // Insere-o no BD.
        pedDAO.inserirPedido(pedido, caixaID);
        
        session.setAttribute("mensagem", "Pedido cadastrado com sucesso!");
        response.sendRedirect(request.getContextPath());
 
    }

} 