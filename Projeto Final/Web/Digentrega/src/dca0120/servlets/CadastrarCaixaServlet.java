package dca0120.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastrarCaixaServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571493L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		// Verifica se o usuário que quer acessar esta função é o administrador.
		Integer administrador = (Integer) session.getAttribute("administrador");
		if(administrador == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
        request.getRequestDispatcher("/cadastrarCaixa.jsp").forward(request, response);
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
        request.getRequestDispatcher("/cadastrar.jsp").forward(request, response);
    }

} 