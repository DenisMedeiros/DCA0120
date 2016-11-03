<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<template:base>

	<jsp:attribute name="titulo">   
		Remover Produto
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/formulario.css">
	</jsp:attribute>


    <jsp:attribute name="conteudoPrincipal">      
    	<%-- Parte principal da página. --%>
		<div class="container theme-showcase" role="main">
		   	<div class="panel-heading">
              <div class="panel-title text-center">
              		<h1 class="title">Produto removido!</h1>
              		<hr />
              		<a href="${pageContext.request.contextPath}/listar/produtos/">
              			<button class="btn btn-primary btn-lg"> Voltar </button>
              		</a>
              	</div>
           </div>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		
	</jsp:attribute>
	
</template:base>