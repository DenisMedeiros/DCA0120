package dca0120.views;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Enumeration;
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
public class EditarProdutoServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123280167571493L;
	
	
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
		
		
		ProdutosDAO pd = new ProdutosDAO();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Produto produto = pd.getProduto(id);
			if(produto != null) {
				request.setAttribute("produto", produto);
				request.getRequestDispatcher("/editarProduto.jsp").forward(request, response);
				return;
			} else {
				response.sendRedirect(request.getContextPath());
				return;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
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
		
		// Verifica se o usuário que quer acessar esta função é um caxa.
		Integer caixa = (Integer) session.getAttribute("caixa");
		if(caixa == null) {
	     	session.setAttribute("mensagem", "Apenas o caixa pode cadastrar produtos.");
        	response.sendRedirect(request.getContextPath());
        	return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		ProdutosDAO pd = new ProdutosDAO();
		CaixasDAO cd = new CaixasDAO();
		Produto original = pd.getProduto(id);
	
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");
        String pesoStr = request.getParameter("peso");
        String volumeStr = request.getParameter("volume");
        String quantidadeStr = request.getParameter("quantidade");
        String descricao = request.getParameter("descricao");
        
        if(!nome.trim().isEmpty()) {
        	original.setNome(nome);
        }
        
        if(!precoStr.trim().isEmpty()) {
        	float preco = Float.parseFloat(precoStr);
        	original.setPreco(preco);
        }
        
        if(pesoStr.trim().isEmpty()) {
        	float peso = Float.parseFloat(pesoStr);
        	original.setPreco(peso);
        }
        
        if(volumeStr.trim().isEmpty()) {
        	float volume = Float.parseFloat(volumeStr);
        	original.setPreco(volume);
        }
        
        if(quantidadeStr.trim().isEmpty()) {
            int quantidade = Integer.parseInt(quantidadeStr);
        	original.setQuantidadeEstoque(quantidade);
        }       
       
        
        Part filePart = request.getPart("foto"); 
        
        String fileName = null;
        InputStream fileContent = null;
        
        if(filePart != null && filePart.getSize() > 0) {
	        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        fileContent = filePart.getInputStream();
		}

        String caminhoFoto = null;
        
        if(filePart != null && filePart.getSize() > 0) {
        	
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
            original.setFoto(caminhoFoto);
            filePart.write(savePath + File.separator +  md5 + aleatorio + extension);
        }
        	
        // Insere-o no BD.
        pd.alterarProduto(original);
        
        session.setAttribute("mensagem", "Produto editado com sucesso!");
        response.sendRedirect(request.getContextPath());
    }
	

} 