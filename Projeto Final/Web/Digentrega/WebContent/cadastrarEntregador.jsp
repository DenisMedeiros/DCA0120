<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Autenticação
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/signin.css">
	</jsp:attribute>


    <jsp:attribute name="conteudoPrincipal">      
    	<%-- Parte principal da página. --%>
	    
		<div class="container theme-showcase" role="main">
	      <form method="post" class="form-signin">
	        <h2 class="form-signin-heading"> Entrar no sistema</h2>
	        <label for="cpf" class="sr-only">CPF</label>
	        <input type="text" name="cpf" id="cpf" class="form-control" placeholder="CPF (Somente números)" required autofocus>
	        <label for="senha" class="sr-only">Senha</label>
	        <input type="password" name="senha" id="senha" class="form-control" placeholder="Senha" required>
	        <div class="checkbox">
	          <label>
	            <input type="checkbox" value="remember-me"> Lembrar?
	          </label>
	        </div>	        
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	      </form>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
    	<jsp:attribute name="rodapeExtra">  
	</jsp:attribute>
    
</template:base>




 





