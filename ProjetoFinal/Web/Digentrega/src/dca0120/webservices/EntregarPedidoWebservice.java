package dca0120.webservices;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dca0120.dao.EntregadoresDAO;
import dca0120.dao.PedidosDAO;
import dca0120.model.Entregador;
import dca0120.model.Pedido;

public class EntregarPedidoWebservice extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8335497326244304045L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
	   
		String entregadorIdStr = request.getParameter("entregadorId");
		String pedidoIdStr = request.getParameter("pedidoId");	
		
		if(entregadorIdStr == null || pedidoIdStr == null) {
			JsonObject json = Json.createObjectBuilder()
	                .add("erro", "Requisição inválida.")
	                .build();    	
			response.getWriter().write(json.toString()); 
			return;
		}
		
		int entregadorId = Integer.parseInt(entregadorIdStr);
		int pedidoId = Integer.parseInt(pedidoIdStr);
		
		EntregadoresDAO ed = new EntregadoresDAO();
		Entregador e = ed.getEntregadorWithID(entregadorId);
		
		PedidosDAO ped = new PedidosDAO();
		Pedido p = ped.getPedidoWithID(pedidoId);
		
		if(e != null && p != null) {
			
			if(p.getEntregador() != null) { // Verifica se o pedido já possui um entregador associado.
				JsonObject json = Json.createObjectBuilder()
		                .add("erro", "Este pedido já possui um entregador associado.")
		                .build();    	
				response.getWriter().write(json.toString()); 
				return;
				
			}
			
			if(p.getStatus() != Pedido.Status.AGUARDANDO_ENTREGADOR) {
				JsonObject json = Json.createObjectBuilder()
		                .add("erro", "Este pedido está em um status inválido.")
		                .build();    	
				response.getWriter().write(json.toString()); 
				return;
			}
			
			p.setEntregador(e);
			p.avancarStatus();
			ped.alterarPedido(p);
			
			JsonObject json = Json.createObjectBuilder()
	                .add("sucesso", true)
	                .add("status", p.getStatus().getDescricao())
	                .build();   
			response.getWriter().write(json.toString());    
		    return;
			
    		 	
 
		    
		}
		
		JsonObject json = Json.createObjectBuilder()
                .add("erro", "Entregador ou pedido não encontrado.")
                .build();    	
		response.getWriter().write(json.toString());  
	         	
    }


}
