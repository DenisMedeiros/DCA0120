package dca0120.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dca0120.dao.CaixasDAO;
import dca0120.model.Caixa;
import dca0120.utils.ValidadorCPF;

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
		
		
		HttpSession session = request.getSession(false);
		
		// Verifica se o usuário que quer acessar esta função é o administrador.
		Integer administradorID = (Integer) session.getAttribute("administrador");
		if(administradorID == null) {
	     	session.setAttribute("mensagem", "Apenas o administrador pode cadastrar funcionários.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        String telefonesStr = request.getParameter("telefones");
        String senha1 = request.getParameter("senha1");
        String senha2 = request.getParameter("senha2");
        
        System.out.println(nome);
        
        
        if(nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty() || telefonesStr.isEmpty() || senha1.isEmpty()
        		|| senha2.isEmpty() ) {
            session.setAttribute("mensagem", "Algum dos campos está em branco!");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        // Valida o CPF.
        if(!ValidadorCPF.isValidCPF(cpf)) {
        	System.out.println("CPF inválido!");
        	session.setAttribute("mensagem", "CPF inválido!");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        // Verifica de o CPF já existe. TODO
        
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
        String[] telefonesArray = telefonesStr.trim().split(","); // Obtém um array de telefones.
        List<String> telefones = Arrays.asList(telefonesArray);
         
        // Cria o objeto Caixa.
        Caixa caixa = new Caixa(0, cpf, senha1, nome, dataNascimento, telefones, false);
       
        // Insere-o no BD.
        CaixasDAO cd = new CaixasDAO();
        cd.inserirCaixa(caixa, administradorID);

        session.setAttribute("mensagem", "Cadastrado com sucesso!");
        response.sendRedirect(request.getContextPath());
    }

} 