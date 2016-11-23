package dca0120.views;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.CaixasDAO;

public class RemoverCaixaServlet extends HttpServlet{

private static final long serialVersionUID = -7552121270167541493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
	     	session.setAttribute("mensagem", "Apenas o administrador pode remover funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		CaixasDAO cd = new CaixasDAO();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			if(cd.getCaixaWithID(id) != null) {
				cd.removerCaixa(id,administrador);
			}  else {
				response.sendRedirect(request.getContextPath());
				return;
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/removerCaixa.jsp").forward(request, response);
	
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    }
}
