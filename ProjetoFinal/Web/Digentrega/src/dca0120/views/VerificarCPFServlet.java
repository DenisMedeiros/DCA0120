package dca0120.views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.CaixasDAO;
import dca0120.utils.TratadorURI;

public class VerificarCPFServlet extends HttpServlet {


	private static final long serialVersionUID = -7552121270167541493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Voc� precisa entrar no sistema para acessar esta fun��o.");
        	response.sendRedirect(TratadorURI.getRaizURL(request));
        	return;
		}
		
		// Verifica se o usu�rio que quer acessar esta fun��o � o administrador.
		Integer administrador = (Integer) session.getAttribute("administrador");
		
		if(administrador == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcion�rios.");
        	response.sendRedirect(TratadorURI.getRaizURL(request));
        	return;
		}
		
		CaixasDAO cd = new CaixasDAO();
		String cpf = request.getParameter("cpf");
		int id = Integer.parseInt(request.getParameter("id"));
		
	    response.setContentType("text/text"); 
	    response.setCharacterEncoding("UTF-8");
		if (cd.getID(cpf) != -1 && cd.getID(cpf) != id) {
		    response.getWriter().write('1');
		} else {
			response.getWriter().write('0');
		}
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    }

} 