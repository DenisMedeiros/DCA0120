package dca0120.views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.CaixasDAO;
import dca0120.dao.EntregadoresDAO;
import dca0120.model.Caixa;
import dca0120.model.Entregador;
import dca0120.utils.Hashing;

public class EntrarServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571493L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/entrar.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		
        String cpfStr = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        
        // Transforma o CPF em n�meros apenas.
        String cpf = cpfStr.replace(".", "").replace("-", "");
        
        CaixasDAO cd = new CaixasDAO();
        EntregadoresDAO ed = new EntregadoresDAO();
        
        Caixa c = cd.getCaixaWithCPF(cpf);
        Entregador e = ed.getEntregadorWithCpf(cpf);
               
        if(c != null) {
        	
        	String senhaCriptografada = Hashing.plainToSHA256(senha, c.getCpf().getBytes());

	        if(c.getSenha().equals(senhaCriptografada)) { // Verifica se � um Caixa.
	        		        	
	        	if(c.isAdministrador()) {
	        		session.setAttribute("administrador", c.getId());
	        		session.setAttribute("mensagem", "Administrador logado com sucesso!");
	        	} else {
	        		session.setAttribute("mensagem", "Caixa logado com sucesso!");
	        	}
				    
	        	session.setAttribute("caixa", c.getId());
			    response.sendRedirect(request.getContextPath());    
			    return;     
	        }
        } 
        
        if (e != null) { // Se e != null
        	
        	String senhaCriptografada = Hashing.plainToSHA256(senha, e.getCpf().getBytes());
        	
        	if (e.getSenha().equals(senhaCriptografada)) {  // Verifica se � um Entregador.
        		session.setAttribute("entregador", e.getId());
			    session.setAttribute("mensagem", "Entregador logado com sucesso!");
			    response.sendRedirect(request.getContextPath());
			    return;
        	}
        }
        
		// Se chegou at� aqui, � porque a senha est� errada ou o funcion�rio n�o existe.
    	session.setAttribute("mensagem", "Falha de autentica��o: CPF e/ou senha incorretos.");
        response.sendRedirect(request.getContextPath() + "/entrar");
	}

} 