<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<template:base>

	<jsp:attribute name="titulo">   
		Listar Pedidos Aberto
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
              		<h1 class="title">Pedidos Abertos</h1>
              		<hr />
              	</div>
           </div>
           <h3 class="text-center"> Pedidos </h3>
           <c:if test="${requestScope.pedidos eq null}">
           		<p class="text-center"> Nenhum produto cadastrado ainda. </p>
           </c:if>
           <c:if test="${requestScope.pedidos ne null}">
			<table class="table table-striped table-bordered table-hover"> 
				<thead>
			      <tr>
			      	<th>Prioridade</th>
			        <th>Momento Abertura</th>
			        <th>Previsão Entrega</th>
			        <th>Entregador</th>
					<th>Preço Total </th>
			        <th>Status Atual</th>
			        <th>Listar Produtos</th>
			        <th>Avançar Etapa</th>
			        <th>Cancelar</th>
			      </tr>
			    </thead>
			    <tbody> 
			          <c:forEach items="${requestScope.pedidos}" var="current">
					        <tr>
					          <td><c:out value="${current.prioridade}" /></td>
							  <td><c:out value="${current.dataHoraAberturaFormatada}" /></td>
 							 <td><c:out value="${current.dataHoraEntregaFormatada}" /></td>	
 							 <td><c:out value="${current.entregador.nome}" /></td>
							  <td><c:out value="${current.valorTotal}" /></td>
 							 <td><c:out value="${current.status}" /></td>					  
					          <td align="center">
					       			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal_${current.id}">Abrir Lista</button>
					          </td>
				        	  <td align="center">
				        	  <c:choose>
				        	  	<c:when test="${current.status.codigo eq 1}">
					        	  		<button id="botaoAvancar_${current.id}" type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});"> Preparar </button>
				        	  	</c:when>
				        	     <c:when test="${current.status.codigo eq 2}">
					        	  		<button id="botaoAvancar_${current.id}"  type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});"> Entregar </button>
				        	  	</c:when>
				        	  </c:choose>
				        	  </td>
				        	  <td align="center">
				        	  	<a href="${pageContext.request.contextPath}/remover/produto/?id=${current.id}">
				        	  		<button type="button" class="btn btn-danger"> Cancelar </button>
				        	  	</a>
				        	  </td>
					        </tr>
				        
				      </c:forEach>
			    </tbody>
			</table>
			</c:if>
	    </div> <!-- /container -->
	    
	    
	    
	     <c:forEach items="${requestScope.pedidos}" var="current">
	    <!-- Modal -->
			<div id="modal_${current.id}" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Produtos do Pedido # ${current.id}</h4>
			      </div>
			      <div class="modal-body">
			        <table class="table table-striped table-bordered table-hover"> 
				<thead>
			      <tr>
			        <th>Produto</th>
			        <th>Quantidade</th>
			      </tr>
			    </thead>
			    <tbody> 
			          <c:forEach items="${current.produtosQuantidades}" var="pq">
					        <tr>
					          <td><c:out value="${pq.produto.nome}" /></td>
					          <td><c:out value="${pq.quantidade}" /></td>
					       </tr>
				      </c:forEach>
			    </tbody>
			</table>
			        
	      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
			      </div>
			    </div>
			
			  </div>
			</div>
			</c:forEach>
	    
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
	
		<script>
			function avancarEtapa(pedidoID) {
			    $.ajax({
			        url: "${pageContext.request.contextPath}/avancar/pedido/?id=" + pedidoID,
			        type: 'GET',
			        async: false,
			        cache: false,
			        timeout: 30000,
			        error: function(retorno){
			        	if(retorno = 1) {
			        		$("#botaoAvancar_" + id).text('Entregar');
			        	} else if (retorno = 2) {
			        		$("#botaoAvancar_" + id).text('Finalizar');
			        	}
			        	
			        },
			        success: function(data){ 
						
			        }
			    });
			}
		
		</script>
		
	</jsp:attribute>
	
</template:base>





 





