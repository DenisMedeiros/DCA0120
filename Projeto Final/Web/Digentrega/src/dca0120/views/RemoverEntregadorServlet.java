package dca0120.views;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.EntregadoresDAO;

public class RemoverEntregadorServlet extends HttpServlet {

private static final long serialVersionUID = -7552121270167541493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Voc� precisa entrar no sistema para acessar esta fun��o.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		// Verifica se o usu�rio que quer acessar esta fun��o � o administrador.
		Integer administrador = (Integer) session.getAttribute("administrador");
		if(administrador == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode remover funcion�rios.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		EntregadoresDAO ed = new EntregadoresDAO();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(ed.getEntregadorWithID(id) != null) {
				ed.removerEntregador(id);
			}  else {
				response.sendRedirect(request.getContextPath());
				return;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/removerEntregador.jsp").forward(request, response);
	
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
    }
}
