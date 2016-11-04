package dca0120.views;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.CaixasDAO;
import dca0120.dao.EntregadoresDAO;
import dca0120.model.Caixa;
import dca0120.model.Entregador;

public class ListarFuncionariosServlet extends HttpServlet {


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
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		CaixasDAO cd = new CaixasDAO();
		EntregadoresDAO ed = new EntregadoresDAO();
		
		List<Caixa> caixas = cd.getTodosCaixas();
		List<Entregador> entregadores = ed.getTodosEntregadores();
		
		if(caixas.size() > 0) {
			request.setAttribute("caixas", caixas);
		} else {
			request.setAttribute("caixas", null);
		}
		
		if(entregadores.size() > 0) {
			request.setAttribute("entregadores", entregadores);
		} else {
			request.setAttribute("entregadores", null);
		}
		
		request.getRequestDispatcher("/listarFuncionarios.jsp").forward(request, response);
	
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
    }

} 