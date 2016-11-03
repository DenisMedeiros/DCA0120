<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<template:base>

	<jsp:attribute name="titulo">   
		Listar Produtos
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
              		<h1 class="title">Listar Produtos</h1>
              		<hr />
              	</div>
           </div>
           <h3 class="text-center"> Produtos </h3>
           <c:if test="${requestScope.produtos eq null}">
           		<p class="text-center"> Nenhum produto cadastrado ainda. </p>
           </c:if>
           <c:if test="${requestScope.produtos ne null}">
			<table class="table table-striped table-bordered table-hover"> 
				<thead>
			      <tr>
			        <th>Nome</th>
			        <th>Preço</th>
			        <th>Peso</th>
			        <th>Volume</th>
			        <th>Quantidade em Estoque</th>
			        <th>Foto</th>
			        <th>Descricao</th>
			        <th>Editar</th>
			        <th>Remover</th>
			      </tr>
			    </thead>
			    <tbody> 
			          <c:forEach items="${requestScope.produtos}" var="current">
					        <tr>
					          <td><c:out value="${current.nome}" /></td>
					          <td><c:out value="${current.preco}" /></td>
					          <td><c:out value="${current.peso}" /></td>
					          <td><c:out value="${current.volume}" /></td>
					          <td><c:out value="${current.quantidadeEstoque}" /></td>
					          <td>
						          <a href="${pageContext.request.contextPath}/${current.foto}" target="_blank">
						          	Ver foto
						          </a>
					          </td>
					          <td><c:out value="${current.descricaoFormatada}" /></td>
					          <td align="center">
					          	<a href="${pageContext.request.contextPath}/editar/produto/?id=${current.id}">
					       			<button type="button" class="btn btn-default">Editar</button>
					          	</a>
					          </td>
				        	  <td align="center">
				        	  	<a href="${pageContext.request.contextPath}/remover/produto/?id=${current.id}">
				        	  		<button type="button" class="btn btn-danger"> Remover </button>
				        	  	</a>
				        	  </td>
					        </tr>
				        
				      </c:forEach>
			    </tbody>
			</table>
			</c:if>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		
	</jsp:attribute>
	
</template:base>





 





