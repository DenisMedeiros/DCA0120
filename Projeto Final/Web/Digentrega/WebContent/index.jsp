<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Página inicial
	</jsp:attribute>
	
	<jsp:attribute name="cabecalhoExtra">   
		<!-- Mais arquivos CSS e Javascript aqui.  -->
	</jsp:attribute>
	
    <jsp:attribute name="conteudoPrincipal">     
    	<%-- Parte principal da página. --%> 
     	<div class="container theme-showcase" role="main">
	      <div class="jumbotron">
	        <h1>DigEntrega</h1>
	        <p> O DigEntrega é uma ferramenta que facilita o processo de logística de entregas de encomendas.</p>
	        <p> Por favor, escolha uma função na barra de menu.
	      </div>
      </div>
    </jsp:attribute> 
           
</template:base>