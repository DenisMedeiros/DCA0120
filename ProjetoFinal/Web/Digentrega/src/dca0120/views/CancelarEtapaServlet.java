package dca0120.views;

import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.PedidosContemProdutosDAO;
import dca0120.dao.PedidosDAO;
import dca0120.dao.ProdutosDAO;
import dca0120.model.Pedido;
import dca0120.model.Produto;

public class CancelarEtapaServlet extends HttpServlet {

private static final long serialVersionUID = -7552121270167541493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Voc� precisa entrar no sistema para acessar esta fun��o.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		// Verifica se o usu�rio que quer acessar esta fun��o � o administrador.
		Integer caixa = (Integer) session.getAttribute("caixa");
		if(caixa == null) {
	     	session.setAttribute("mensagem", "Apenas o caixa pode cancelar pedidos.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		String idStr = request.getParameter("id");
		
		int id = Integer.parseInt(idStr);
		
		PedidosDAO pd = new PedidosDAO();
		Pedido ped = pd.getPedidoWithID(id);
		ped.setStatus(Pedido.Status.CANCELADO);
		pd.alterarPedido(ped);
		PedidosContemProdutosDAO pcp = new PedidosContemProdutosDAO();
		ProdutosDAO prodDAO = new ProdutosDAO();
		List<Produto> lista = pcp.getProdutosDoPedido(ped);
		for(Produto prod: lista) {	
			prod.setQuantidadeEstoque(prod.getQuantidadeEstoque() + ped.getQuantidadeProduto(prod));
			ped.removerProduto(prod, ped.getQuantidadeProduto(prod));
			prodDAO.alterarEstoque(prod);
			pcp.alterarQuantidade(prod, ped);
			
		}
		pd.alterarPedido(ped);
		

		
		JsonObject json = Json.createObjectBuilder()
	                .add("statusCodigo", ped.getStatus().getCodigo())
	                .add("statusDescricao", ped.getStatus().toString())
	                .build();
	         
		
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
		
    }

}
