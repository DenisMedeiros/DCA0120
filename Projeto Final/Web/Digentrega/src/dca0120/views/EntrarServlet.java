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

        request.getRequestDispatcher("/entrar.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
        String cpfStr = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        
        // Transforma o CPF em números apenas.
        String cpf = cpfStr.replace(".", "").replace("-", "");
        
        CaixasDAO cd = new CaixasDAO();
        EntregadoresDAO ed = new EntregadoresDAO();
        
        Caixa c = cd.getCaixaWithCPF(cpf);
        Entregador e = ed.getEntregadorWithCpf(cpf);
        
        if(c == null && e == null) { // Não existe funcionário com este CPF.
        	session.setAttribute("mensagem", "Falha de autenticação: CPF e/ou senha incorretos.");
        	response.sendRedirect(request.getContextPath() + "/entrar/");
        	return;
        }
        
        String senhaCriptografada = Hashing.plainToSHA256(senha, c.getCpf().getBytes());
       
        
        if(c != null) {

	        if(c.getSenha().equals(senhaCriptografada)) { // Verifica se é um Caixa.
	        		        	
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
        	if (e.getSenha().equals(senhaCriptografada)) {  // Verifica se é um Entregador.
        		session.setAttribute("entregador", c.getId());
			    session.setAttribute("mensagem", "Entregador logado com sucesso!");
			    response.sendRedirect(request.getContextPath());
			    return;
        	}
        }
        	
		// Se chegou até aqui, é porque a senha está errada.
        response.sendRedirect(request.getContextPath() + "/entrar");
	}

} 