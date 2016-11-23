package dca0120.views;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.PedidosDAO;
import dca0120.model.Pedido;

public class ListarPedidosFechadosServlet extends HttpServlet {


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
		
		PedidosDAO pd = new PedidosDAO();	
		List<Pedido> pedidos = pd.getPedidosFechados();
		
		if(pedidos.size() > 0) {
			request.setAttribute("pedidos", pedidos);
		} else {
			request.setAttribute("pedidos", pedidos);
		}
		
		request.getRequestDispatcher("/listarPedidosFechados.jsp").forward(request, response);
	
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    }

} 