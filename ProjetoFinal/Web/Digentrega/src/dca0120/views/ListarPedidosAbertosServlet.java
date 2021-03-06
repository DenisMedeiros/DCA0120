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
import dca0120.utils.TratadorURI;

public class ListarPedidosAbertosServlet extends HttpServlet {


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
		
		PedidosDAO pd = new PedidosDAO();	
		List<Pedido> pedidos = pd.getPedidosAbertos();
		
		if(pedidos.size() > 0) {
			request.setAttribute("pedidos", pedidos);
		} else {
			request.setAttribute("pedidos", pedidos);
		}
		
		request.getRequestDispatcher("/listarPedidosAbertos.jsp").forward(request, response);
	
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
    }

} 