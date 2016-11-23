package dca0120.views;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.PedidosDAO;
import dca0120.model.Pedido;

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
			session.setAttribute("mensagem", "Você precisa entrar no sistema para acessar esta função.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		// Verifica se o usuário que quer acessar esta função é o administrador.
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
		

		
		JsonObject json = Json.createObjectBuilder()
	                .add("statusCodigo", ped.getStatus().getCodigo())
	                .add("statusDescricao", ped.getStatus().toString())
	                .build();
	         
		
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    System.out.println(json.toString());
		response.getWriter().write(json.toString());
		
    }

}
