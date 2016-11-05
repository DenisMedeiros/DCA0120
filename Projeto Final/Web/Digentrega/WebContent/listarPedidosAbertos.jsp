<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<template:base>

	<jsp:attribute name="titulo">   
		Listar Pedidos Abertos
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/formulario.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/modal-picture.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/dataTables.bootstrap.min.css">
		
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
           <c:if test="${requestScope.pedidos eq null}">
           		<p class="text-center"> Nenhum pedido cadastrado ainda. </p>
           </c:if>
           <c:if test="${requestScope.pedidos ne null}">
			<table id="tabelaPedidosAbertos" class="table table-striped table-bordered table-hover"> 
				<thead>
			      <tr>
			      	<th>Prioridade</th>
			        <th>Momento Abertura</th>
			        <th>Previsão Entrega</th>
			        <th>Entregador</th>
			        <th>Status Atual</th>
			        <th>Local de Entrega </th>
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
 							 <td><c:out value="${current.entregadorFormatado}" /></td>
 							 <td align="center"> <span id="status_${current.id}"> <c:out value="${current.status}" /> </span></td>	
							 <td align="center">
								 <button class="btn btn-info" onclick="qrCodeModal(${current.id},${current.enderecoEntrega.latitude},${current.enderecoEntrega.longitude});">
								 	QR Code
								 </button>
							 </td>
 					  
					         <td align="center">
					       			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal_${current.id}">Abrir Lista</button>
					          </td>
				        	  <td align="center">
				        	  <c:choose>
				        	  	<c:when test="${current.status.codigo eq 1}">
					        	  		<button id="botaoAvancar_${current.id}" type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});"> Preparar </button>
				        	  	</c:when>
				        	     <c:when test="${current.status.codigo eq 2}">
					        	  		<button id="botaoAvancar_${current.id}"  type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});"> Escolher Entregador </button>
				        	  	</c:when>
				        	  	<c:when test="${current.status.codigo eq 3}">
					        	  		<button id="botaoAvancar_${current.id}"  type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});"> Entregar </button>
				        	  	</c:when>
				        	  	<c:when test="${current.status.codigo eq 4}">
					        	  		<button id="botaoAvancar_${current.id}"  type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});" disabled> Aguardando Entrega </button>
				        	  	</c:when>
				        	  	<c:when test="${current.status.codigo eq 6}">
					        	  		<button id="botaoAvancar_${current.id}"  type="button" class="btn btn-primary" onclick="avancarEtapa(${current.id});" disabled> Cancelado </button>
				        	  	</c:when>
				        	  </c:choose>
				        	  </td>
				        	  <td align="center">
<%-- 				        	  	<a href="${pageContext.request.contextPath}/cancelar/pedido/?id=${current.id}"> --%>
				        	  		<c:choose>
				        	  		<c:when test="${current.status.codigo eq 6}">
				        	  			<button id="botaoCancelar_${current.id}" type="button" class="btn btn-danger" onclick="cancelarPedido(${current.id});" disabled> Cancelar </button>
				        	  		</c:when>
				        	  		<c:otherwise>
				        	  			<button id="botaoCancelar_${current.id}" type="button" class="btn btn-danger" onclick="cancelarPedido(${current.id});"> Cancelar </button>
				        	  		</c:otherwise>
				        	  		</c:choose>
<!-- 				        	  	</a> -->
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
			
			<div id="modals"></div>
	    
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js "></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qrcode.min.js"></script>
		
		<script>
			function avancarEtapa(pedidoID) {
												
			    $.ajax({
			        url: "${pageContext.request.contextPath}/avancar/pedido/?id=" + pedidoID,
			        type: 'POST',
			        async: false,
			        cache: false,
			        timeout: 30000,
			        success: function(retorno){
  			        	
			        	if(retorno.statusCodigo == 2) {
			        		$("#botaoAvancar_" + pedidoID).text('Escolher Entregador');
			        		$("#status_" + pedidoID).text(retorno.statusDescricao);
			        	} else if (retorno.statusCodigo == 3) {
			        		$("#botaoAvancar_" + pedidoID).text('Entregar');
			        		$("#status_" + pedidoID).text(retorno.statusDescricao);
			        	} else if (retorno.statusCodigo == 4){
			        		$("#botaoAvancar_" + pedidoID).text('Aguardando Entrega');
			        		$("#status_" + pedidoID).text(retorno.statusDescricao);
			        		$("#botaoAvancar_" + pedidoID).prop("disabled", true);
			        	} else if (retorno.statusCodigo == 6){
			        		$("#botaoAvancar_" + pedidoID).text('Cancelado');
			        		$("#status_" + pedidoID).text(retorno.statusDescricao);
			        		$("#botaoAvancar_" + pedidoID).prop("disabled", true);
			        	} else {
			        		console.log("Falhou!");
			        	}
			        	
			        },
			        error: function(retorno){
			        	console.log("Erro");
			        }
			 
			    });
			}
		
		</script>
		
		<script>
			function cancelarPedido(pedidoID) {

			    var resposta = confirm("Você realmente deseja cancelar este pedido?");
			   
			    if (resposta == true) {
			    	
				    $.ajax({
				        url: "${pageContext.request.contextPath}/cancelar/pedido/?id=" + pedidoID,
				        type: 'POST',
				        async: false,
				        cache: false,
				        timeout: 30000,
				        success: function(retorno){
	  			        	
				        	if(retorno.statusCodigo == 6) {
				        		$("#status_" + pedidoID).text(retorno.statusDescricao);
				        		$("#botaoCancelar_" + pedidoID).prop("disabled", true);
				        	} else {
				        		console.log("Falhou!");
				        	}
				        	
				        },
				        error: function(retorno){
				        	console.log("Erro");
				        }
				 
				    });

			    } 
												

			}
		
		</script>
		<script>
			$("#tabelaPedidosAbertos").DataTable( {
				"language": {
		            "url": "${pageContext.request.contextPath}/static/js/datatable/pt-br.json",
		        }
			});
		</script>
		
		
		
		<script>
		
		function qrCodeModal(id, latitude, longitude)
		{
			
			if($("#qdCodeModal_" + id).length != 0) {
				 $("#qdCodeModal_"+ id).modal('show');
			} else {
			
				var html = '';
				html += '<div id="qdCodeModal_'+ id +'" class="modal fade" role="dialog">';
				html += '<div class="modal-dialog">';
				html += '<div class="modal-content">';
				html += '<div class="modal-header">';
				html += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
				html += '<h4 class="modal-title">QR Code do Pedido # '+ id +'</h4>';
				html += '</div>';
				html += '<div class="modal-body">';
				html += '<div class="qrcode" id="qrcode_'+ id +'"></div>';
				html += '<div class="modal-footer">';
				html += '<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>';
				html += '</div>';
				html += '</div>';
				html += '</div>';
				html += '</div>';
				
						    
			    $("#modals").append(html);
			    
			    var qrcode = new QRCode("qrcode_"+ id, {
			        text: "" + id + "," + latitude + "," + longitude,
			        width: 256,
			        height: 256,
			        colorDark : "#000000",
			        colorLight : "#ffffff",
			        correctLevel : QRCode.CorrectLevel.H,
			    });
			    
			    $("#qdCodeModal_"+ id).modal('show');
			} 
		}
		</script>
		
		<script>
			$(".remover").on("click", function(e) {
				var link = this;

			});
		</script>
		
		
	</jsp:attribute>
	
</template:base>





 





