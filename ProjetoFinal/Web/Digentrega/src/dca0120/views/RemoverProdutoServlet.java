package dca0120.views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.ProdutosDAO;

public class RemoverProdutoServlet extends HttpServlet {
	
private static final long serialVersionUID = -7552121270167541493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Voc� precisa entrar no sistema para acessar esta fun��o.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
			
		ProdutosDAO pd = new ProdutosDAO();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			if(pd.getProduto(id) != null) {
				pd.removerProduto(id);
			} else {
				response.sendRedirect(request.getContextPath());
				return;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/removerProduto.jsp").forward(request, response);
	
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    }

}
