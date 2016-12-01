package dca0120.webservices;

import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dca0120.dao.EntregadoresDAO;
import dca0120.dao.PedidosDAO;
import dca0120.model.Entregador;
import dca0120.model.Pedido;

public class ListarPedidosEntregadorWebservice extends HttpServlet {

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
		
		if(entregadorIdStr == null) {
			JsonObject json = Json.createObjectBuilder()
	                .add("erro", "Requisição inválida.")
	                .build();    	
			response.getWriter().write(json.toString()); 
			return;
		}
		
		int entregadorId = Integer.parseInt(entregadorIdStr);
		
		EntregadoresDAO ed = new EntregadoresDAO();
		Entregador e = ed.getEntregadorWithID(entregadorId);
		
		if(e != null) {
			
			PedidosDAO ped = new PedidosDAO();
			List<Pedido> pedidos = ped.getPedidosDoEntregador(entregadorId);
			
			if(pedidos.size() < 1) { // Verifica se existe algum pedido associado a este entregador.
				JsonObject json = Json.createObjectBuilder()
		                .add("erro", "Nenhum pedido associado a este entregador.")
		                .build();    	
				response.getWriter().write(json.toString()); 
				return;
				
			}
				
			JsonArrayBuilder jarr = Json.createArrayBuilder();
			
			// Retorna somente os pedidos em trânsito.
			for (Pedido p: pedidos) {
				if(p.getStatus() == Pedido.Status.EM_TRANSITO) {
				    jarr.add(
				    		Json.createObjectBuilder()
					        .add("id", p.getId())
					        .add("latitude", p.getEnderecoEntrega().getLatitude())
						    .add("longitude", p.getEnderecoEntrega().getLongitude())
						    .add("peso", p.getPesoTotal())
						    .add("volume", p.getVolumeTotal())
						    .build()
					);
				}
			}
			
			JsonArray pedidosJSON = jarr.build();
			
			JsonObject json = Json.createObjectBuilder()
	                .add("sucesso", true)
	                .add("pedidos", pedidosJSON)
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
