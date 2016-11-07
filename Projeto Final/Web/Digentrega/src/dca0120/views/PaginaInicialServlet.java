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

public class PaginaInicialServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571193L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);	
		
		// Verifica se o usuário é um entregador.
		if(session != null) {
			Integer entregador = (Integer) session.getAttribute("entregador");
			if(entregador != null) {
				PedidosDAO pd = new PedidosDAO();	
				List<Pedido> pedidos = pd.getPedidosAguardandoEntrega();
				if(pedidos.size() > 0) {
					request.setAttribute("pedidos", pedidos);
				} else {
					request.setAttribute("pedidos", null);
				}
			}
		}
		
		
        request.getRequestDispatcher("/inicio.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
    }

} 