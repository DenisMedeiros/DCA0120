package dca0120.views;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.CaixasDAO;
import dca0120.dao.ProdutosDAO;
import dca0120.model.Caixa;
import dca0120.model.Produto;
import dca0120.utils.Hashing;
import dca0120.utils.ValidadorCPF;

public class CadastrarPedidoServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571493L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
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
		
		ProdutosDAO pd = new ProdutosDAO();
		
		List<Produto> produtos = pd.getTodosProdutos();
		
		if(produtos.size() > 0) {
			request.setAttribute("produtos", produtos);
		} else {
			request.setAttribute("produtos", null);
		}
		
        request.getRequestDispatcher("/cadastrarPedido.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Você precisa entrar no sistema para acessar esta função.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		CaixasDAO cd = new CaixasDAO();
		

		// Verifica se o usuário que quer acessar esta função é o administrador.
		Integer administradorID = (Integer) session.getAttribute("administrador");
		if(administradorID == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
        String nome = request.getParameter("nome");
        String cpfStr = request.getParameter("cpf");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        String telefone1 = request.getParameter("telefone_1");
        String senha1 = request.getParameter("senha1");
        String senha2 = request.getParameter("senha2");

        // Verifica se algum campo chegou em branco.
        if(nome.trim().isEmpty() || cpfStr.trim().isEmpty() || dataNascimentoStr.trim().isEmpty() || 
        		telefone1.trim().isEmpty() || senha1.trim().isEmpty() || senha2.trim().isEmpty()) {
            session.setAttribute("mensagem", "Algum dos campos foi enviado em branco. Tente novamente!");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        // Transforma o CPF em números apenas.
        String cpf = cpfStr.replace(".", "").replace("-", "");
        
        // Valida o CPF.
        if(!ValidadorCPF.isValidCPF(cpf)) {
        	session.setAttribute("mensagem", "CPF inválido! Tente novamente.");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        // Verifica de o CPF já existe para algum funcionário.
        if(cd.getID(cpf) != -1) {
        	session.setAttribute("mensagem", "Já existe um funcionário com este CPF! Tente novamente.");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        // Verifica se as senhas são iguais.
        if(!senha1.equals(senha2)) {
        	session.setAttribute("mensagem", "As senhas não conferem.");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        // Valida a data de nascimento.
        Calendar dataNascimento = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
			dataNascimento.setTime(sdf.parse(dataNascimentoStr));
		} catch (ParseException e) {
			request.setAttribute("mensagem", "Data de nascimento inválida!");
            response.sendRedirect(request.getHeader("referer"));
            return;
		}
        
        // Organiza o vetor de telefones.
        List<String> telefones = new ArrayList<String>();
        int i = 1;
        while(request.getParameter("telefone_" + i) != null) {
        	telefones.add(request.getParameter("telefone_" + i));
        	i++;
        }
        
        String senhaCriptografada = Hashing.plainToSHA256(senha1, cpf.getBytes());
        
        // Cria o objeto Caixa.
        Caixa caixa = new Caixa(0, cpf, senhaCriptografada, nome, dataNascimento, telefones, false);
       
        // Insere-o no BD.
        cd.inserirCaixa(caixa, administradorID);
        
        session.setAttribute("mensagem", "Caixa cadastrado(a) com sucesso!");
        response.sendRedirect(request.getContextPath());
    }

} 