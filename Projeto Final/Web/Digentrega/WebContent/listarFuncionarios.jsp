<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<template:base>

	<jsp:attribute name="titulo">   
		Listar Funcionários
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
              		<h1 class="title">Listar Funcionários</h1>
              		<hr />
              	</div>
           </div>
           <h3 class="text-center"> Caixas </h3>
           <c:if test="${requestScope.caixas eq null}">
           		<p class="text-center"> Nenhum caixa cadastrado ainda. </p>
           </c:if>
           <c:if test="${requestScope.caixas ne null}">
			<table class="table table-striped table-bordered table-hover"> 
				<thead>
			      <tr>
			        <th>Nome</th>
			        <th>CPF</th>
			        <th>Data de Nascimento</th>
			        <th>Telefones</th>
			        <th>Editar</th>
			        <th>Remover</th>
			      </tr>
			    </thead>
			    <tbody> 
			          <c:forEach items="${requestScope.caixas}" var="current">
					        <tr>
					          <td><c:out value="${current.nome}" /></td>
					          <td><c:out value="${current.cpf}" /></td>
					          <td><c:out value="${current.dataNascimentoFormatada}" /></td>
					          <td><c:out value="${current.telefonesFormatados}" /></td>
					          <td align="center">
					          	<a href="${pageContext.request.contextPath}/editar/caixa/?id=${current.id}">
					       			<button type="button" class="btn btn-default" <c:if test="${current.administrador}"> disabled </c:if>>Editar</button>
					          	</a>
					          </td>
				        	  <td align="center">
				        	  	<a href="${pageContext.request.contextPath}/remover/caixa/?id=${current.id}">
				        	  		<button type="button" class="btn btn-danger" <c:if test="${current.administrador}"> disabled </c:if>>Remover</button>
				        	  	</a>
				        	  </td>
					        </tr>
				        
				      </c:forEach>
			    </tbody>
			</table>
			</c:if>
			<br />
			<h3 class="text-center"> Entregadores </h3>
			<c:if test="${requestScope.entregadores eq null}">
           		<p class="text-center"> Nenhum entregador cadastrado ainda. </p>
           </c:if>
			<c:if test="${requestScope.entregadores ne null}">
				<table class="table table-striped table-bordered table-hover"> 
					<thead>
				      <tr>
				        <th>Nome</th>
				        <th>CPF</th>
				        <th>Data de Nascimento</th>
				        <th>Telefones</th>
				        <th>Editar</th>
				        <th>Remover</th>
				      </tr>
				    </thead>
				    <tbody> 
				          <c:forEach items="${requestScope.entregadores}" var="current">
					        <tr>
					          <td><c:out value="${current.nome}" /></td>
					          <td><c:out value="${current.cpf}" /></td>
					          <td><c:out value="${current.dataNascimentoFormatada}" /></td>
					          <td><c:out value="${current.telefonesFormatados}" /></td>
					          <td align="center">
					          	<a href="${pageContext.request.contextPath}/editar/entregador/?id=${current.id}">
					          		<button type="button" class="btn btn-default">Editar</button>
					          	</a>
					          </td>
				        	  <td align="center">
				        	  	<a href="${pageContext.request.contextPath}/remover/entregador/?id=${current.id}">
				        	  		<button type="button" class="btn btn-danger">Remover</button>
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





 





