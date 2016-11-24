package dca0120.webservices;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dca0120.dao.EntregadoresDAO;
import dca0120.model.Entregador;
import dca0120.utils.Hashing;

public class LoginEntregadorServlet extends HttpServlet {

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
	   
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");	
		
		EntregadoresDAO ed = new EntregadoresDAO();
		Entregador e = ed.getEntregadorWithCpf(cpf);
		
		if(e != null) {
			String senhaCriptografada = Hashing.plainToSHA256(senha, e.getCpf().getBytes());
	        if(e.getSenha().equals(senhaCriptografada)) { // Login ocorreu com sucesso.
	    		JsonObject json = Json.createObjectBuilder()
		                .add("par1", 2)
		                .add("par2", 3)
		                .build();    	
	        	  
			    response.getWriter().write(json.toString());    
			    return;
	        }
		}
		
		JsonObject json = Json.createObjectBuilder()
                .add("erro", "Falha na autenticação")
                .build();    	
		response.getWriter().write(json.toString());  
	         	
    }


}
