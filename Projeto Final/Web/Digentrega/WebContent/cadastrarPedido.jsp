<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:base>

	<jsp:attribute name="titulo">   
		Cadastrar Pedido
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-datepicker3.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/parsley.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/googleMaps.css"/>
	</jsp:attribute>


    <jsp:attribute name="conteudoPrincipal">   
    
    	<%-- Parte principal da página. --%>
		<div class="container theme-showcase" role="main">
		   	<div class="panel-heading">
              <div class="panel-title text-center">
              		<h1 class="title">Cadastrar Pedido</h1>
              		<hr />
              	</div>
           </div>
           
    
            <c:if test="${requestScope.produtos eq null}">
           		<p class="text-center"> Nenhum produto cadastrado ainda. </p>
           	</c:if>
           <c:if test="${requestScope.produtos ne null}">
			<form id="formulario" class="form-signup" method="post">
			
			<div class="form-group">
					<label for="descricao" class="cols-sm-2 control-label">Descrição do Pedido</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<textarea class="form-control" rows="3" id="descricaoPedido" name="descricaoPedido" placeholder="Descrição do pedido"></textarea>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
			
			
			
			    <label class="cols-sm-2 control-label">Lista de produos</label>
				<div class="input-group">	
					<span class="input-group-addon" title="* Produto" id="priceLabel">Produto</span>
				    <select id="listaProdutos" name="listaProdutos" class="form-control">
						<c:forEach items="${requestScope.produtos}" var="current">
							<option value="${current.id}">${current.nome} (R$ ${current.preco})</option>
						</c:forEach>
				    </select>
				    <span class="input-group-addon" title="* Price" id="priceLabel">Quantidade</span>
				    <input type="number" id="quantidade" name="quantidade" required="required" class="form-control" value="1">
				    <!-- insert this line -->
				    <span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
					<div class="input-group-btn ">
						<button type="button" class="btn btn-default glyphicon glyphicon-plus" style="margin-top:-1px;" id="adicionarProduto"></button>                     
					</div>
				</div>
				
				<br />

				<label class="cols-sm-2 control-label">Lista de produtos adicionados ao pedido</label>
				<table id="tabelaProdutos" class="table table-striped table-bordered table-hover"> 
				<thead>
			      <tr>
			      	<th>ID</th>
			        <th>Nome</th>
			        <th>Preço (und)</th>
			        <th>Peso (und)</th>
			        <th>Volume (und)</th>
			        <th>Quantidade (und)</th>
			        <th>Remover </th>
			      </tr>
			    </thead>
			    <tbody> 
			    </tbody>
			</table>
			
			<div class="form-group row">
			          <div class="col-sm-4">
			          	<div class="input-group">
			           	<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"> Preco Total </i></span>
			               <input type="text" class="form-control" id="precoTotal" name=""precoTotal"" value="0" readonly>
			           </div>
			       </div>
			          <div class="col-sm-4">
			          	<div class="input-group">
			           	<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"> Peso Total</i></span>
			               <input type="text" class="form-control" id="pesoTotal" name="pesoTotal" value="0" readonly>
			              </div>
			          </div>
			         <div class="col-sm-4">
			          	<div class="input-group">
			           	<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"> Volume Total</i></span>
			               <input type="text" class="form-control" id="volumeTotal" name="volumeTotal" value="0" readonly>
			              </div>
			          </div>
			      </div>
			      
			   <label class="cols-sm-2 control-label">Designar Entregador</label>
				<div class="input-group">	
					<span class="input-group-addon" title="" id="priceLabel"></span>
				    <select id="entregador" name="entregador" class="form-control">
						<c:forEach items="${requestScope.entregadores}" var="current">
							<option value="${current.id}">${current.nome} </option>
						</c:forEach>
				    </select>
				    <!-- insert this line -->
				    <span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
				</div>
			
							
				<br />
				
			
				<div class="form-group">
					<label for="descricao" class="cols-sm-2 control-label">Descrição do Endereço</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<textarea class="form-control" rows="3" id="descricaoEndereco" name="descricaoEndereco" placeholder="Descrição do endereço"></textarea>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				<div id="areaMapa">
					<input id="pac-input" class="controls" type="text" placeholder="Search Box">
	    			<div id="map"></div>
    			</div>
				
				<div class="form-group ">
					<label for="entrega" class="cols-sm-2 control-label">Local de Entrega</label>
					<div class="cols-sm-10">
						<div class="form-group row">
			                <div class="col-sm-6">
			                	<div class="input-group">
				                	<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"> Latitude </i></span>
				                    <input type="text" class="form-control" id="latitude" name="latitude" value="0">
				                </div>
				            </div>
			                <div class="col-sm-6">
			                	<div class="input-group">
				                	<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"> Longitude</i></span>
				                    <input type="text" class="form-control" id="longitude" name="latitude" value="0">
			                    </div>
			                </div>
			            </div>
						<a class="btn btn-primary btn-lg btn-block login-button" id="botaoMapa">Abrir mapa </a>
						<input type="hidden" id="latitude" name="latidude" value="0" />
						<input type="hidden" id="longitude" name="longitude" value="0" />
					</div>
				</div>

				<div class="form-group ">
					<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Cadastrar</button>
				</div>
			</form>
			</c:if>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/parsley.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/i18n/pt-br.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.maskedinput.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/googleMaps.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB9nEMd1ZPizw32y8zG4T4NO3BUdoKAQQU&libraries=places&callback=initAutocomplete"
         async defer></script>
				
		<script>
		$(document).ready(function(){
			
			$('#formulario').parsley({
				errorsContainer: function(pEle) {
			        var $err = pEle.$element.parent().siblings('.mensagem-ajuda');
			        return $err;
			    },
		        errorClass: 'parsley-error',
		        successClass: 'parsley-success',
			 	errorsWrapper: '<ul class="pasley-errors-message"></ul>',
			 	errorTemplate: '<li></li>',
			});
		});
		</script>
		
		<script>
		
		$( document ).ready(function() {
			$("#areaMapa").hide();
		});
		
		$("#botaoMapa").on('click', function(){
			$("#areaMapa").toggle();
			if($('#areaMapa').is(':visible')){
				$("#botaoMapa").text("Fechar Mapa");
			} else {
				$("#botaoMapa").text("Abrir Mapa");
			}
		});
		</script>
		
		<script>
		
		function removerProduto(id) {
			if($("#linha_" + id).length) {
				var quantidade = parseInt($("#quantidade_" + id).text());
				
				console.log(quantidade);
				
				var precoTotal = parseFloat($("#precoTotal").val());
				var pesoTotal = parseFloat($("#pesoTotal").val());
				var volumeTotal = parseFloat($("#volumeTotal").val());
				
				var precoFinal = precoTotal - quantidade *  produtos[id].preco;
				var pesoFinal = pesoTotal - quantidade *  produtos[id].peso;
				var volumeFinal = volumeTotal - quantidade *  produtos[id].volume;
				
				$("#precoTotal").val(precoFinal);
				$("#pesoTotal").val(pesoFinal);
				$("#volumeTotal").val(volumeFinal);
				
				$("#linha_" + id).remove();
			} 
		}
		
		var produtos = {
			<c:forEach items="${requestScope.produtos}" var="current">
				${current.id}: {nome: '${current.nome}', preco: '${current.preco}', peso: '${current.peso}', volume: '${current.volume}', quantidadeInicial: '${current.quantidadeEstoque}'},
			</c:forEach>
		};
			
		$("#adicionarProduto").on('click', function(){
			var id = $("#listaProdutos option:selected").val();
			var quantidade = parseInt($("#quantidade").val());
			
			if(!$("#linha_" + id).length) {
				$('#tabelaProdutos tr:last').after('' +
				  '<tr id="linha_'+ id +'" class="linha" >'+
				  '<td class="idProduto">'+ id +'</td>'+
		          '<td>'+ produtos[id].nome +'</td>'+
		          '<td>'+ produtos[id].preco +'</td>'+
		          '<td>'+ produtos[id].peso +'</td>'+
		          '<td>'+ produtos[id].volume +'</td>'+
		          '<td id="quantidade_'+ id +'">'+ quantidade +'</td>'+
	      	  	  '<td align="center">'+
		      	  	'<a href="#">'+
		      	  		'<button type="button" class="btn btn-danger" id="remover_'+ id +'" onclick="removerProduto('+ id +');"> Remover </button>'+
		      	  	'</a>'+
		      	  '</td>'+
			       '</tr>'
				);
				
			} else {
				var quantidadeCarrinho = parseInt($("#quantidade_" + id).text());
				var quantidadeFinal = quantidadeCarrinho + quantidade;
				if(quantidadeFinal <= produtos[id].quantidadeInicial) {
					$("#quantidade_" + id).text(quantidadeFinal);
				} else {
					alert("Não existe estoque suficiente deste produto!");
					return;
				}
			}
			
			
			var precoTotal = parseFloat($("#precoTotal").val());
			var pesoTotal = parseFloat($("#pesoTotal").val());
			var volumeTotal = parseFloat($("#volumeTotal").val());
			
			var precoFinal = precoTotal + quantidade *  produtos[id].preco;
			var pesoFinal = pesoTotal + quantidade *  produtos[id].peso;
			var volumeFinal = volumeTotal + quantidade *  produtos[id].volume;
			
			$("#precoTotal").val(precoFinal);
			$("#pesoTotal").val(pesoFinal);
			$("#volumeTotal").val(volumeFinal);
			
		});
		</script>
		
		
		<script>
	
		$(function() {
		    $("#formulario").submit(function(e) {
				// Pega todos os dados da tabela e inclui no formulário.
				$("#tabelaProdutos .linha").each(function(i, row) {
					 var $row = $(row);
					 var colId = $row.find('.idProduto');
					 var id = parseInt(colId.text());
					 var quantidade = parseInt($("#quantidade_" + id).text());
					 console.log(quantidade);
					 $('<input>').attr('type', 'hidden').attr('name', 'produto_' + id).attr('value', quantidade).appendTo('#formulario');				 
			    });
				return true;
		    });
		});
		</script>
		
	</jsp:attribute>
	
</template:base>





 





