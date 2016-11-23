package dca0120.views;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dca0120.dao.CaixasDAO;
import dca0120.dao.ProdutosDAO;
import dca0120.model.Caixa;
import dca0120.model.Produto;
import dca0120.utils.Hashing;

@MultipartConfig
public class CadastrarProdutoServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123280167571493L;
	
	
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
		
        request.getRequestDispatcher("/cadastrarProduto.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);	
		if(session == null) {
			session = request.getSession(true);	
			session.setAttribute("mensagem", "Você precisa entrar no sistema para acessar esta função.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		// Verifica se o usuário que quer acessar esta função é um caxa.
		Integer caixa = (Integer) session.getAttribute("caixa");
		if(caixa == null) {
	     	session.setAttribute("mensagem", "Apenas o caixa pode cadastrar produtos.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		
		ProdutosDAO pd = new ProdutosDAO();
		CaixasDAO cd = new CaixasDAO();
		
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");
        String pesoStr = request.getParameter("peso");
        String volumeStr = request.getParameter("volume");
        String quantidadeStr = request.getParameter("quantidade");
        String descricao = request.getParameter("descricao");
        
        String precoFormatado = precoStr.replace("R$", "").replace(".", "").replace(",", ".");
        
        Part filePart = request.getPart("foto"); 
               
        String fileName = null;
        InputStream fileContent = null;
        if(filePart != null) {
	        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        fileContent = filePart.getInputStream();
		}

        // Verifica se algum campo chegou em branco.
        if(nome.trim().isEmpty() || precoStr.trim().isEmpty() || pesoStr.trim().isEmpty() || 
        		volumeStr.trim().isEmpty() || quantidadeStr.trim().isEmpty()) {
            session.setAttribute("mensagem", "Algum dos campos foi enviado em branco. Tente novamente!");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        
        String caminhoFoto = null;
        
        if(fileContent != null) {
        	
            String appPath = request.getServletContext().getRealPath("media");
            String savePath = appPath + File.separator + "produtos";
            
            // Cria o diretório caso ele não exista ainda.
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            
			String md5 = "MD5";
			try {
				md5 = Hashing.getMD5Checksum(fileContent);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
			String aleatorio = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999 + 1));
			String extension = fileName.substring(fileName.indexOf("."));

            caminhoFoto = "media" + "/" + "produtos" + "/" + md5 + aleatorio + extension;
            filePart.write(savePath + File.separator +  md5 + aleatorio + extension);
        }
        	
        
        float preco = Float.parseFloat(precoFormatado);
        float peso = Float.parseFloat(pesoStr);
        float volume = Float.parseFloat(volumeStr);
        int quantidade = Integer.parseInt(quantidadeStr);
		Caixa responsavel = cd.getCaixaWithID(caixa);        
       
        Produto produto = new Produto(-1, nome, preco, caminhoFoto, peso, volume, descricao, responsavel, quantidade);
       
        // Insere-o no BD.
        pd.inserirProduto(produto);
        
        session.setAttribute("mensagem", "Produto cadastrado com sucesso!");
        response.sendRedirect(request.getContextPath());
    }
	

} 