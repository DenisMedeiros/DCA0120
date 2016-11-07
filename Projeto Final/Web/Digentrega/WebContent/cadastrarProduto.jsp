<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Cadastrar Produto
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/formulario.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/parsley.css"/>
	</jsp:attribute>


    <jsp:attribute name="conteudoPrincipal">      
    	<%-- Parte principal da página. --%>
		<div class="container theme-showcase" role="main">
		   	<div class="panel-heading">
              <div class="panel-title text-center">
              		<h1 class="title">Cadastrar Produto</h1>
              		<hr />
              	</div>
           </div>
			<form id="formulario" class="form-signup" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="nome" class="cols-sm-2 control-label">Nome</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="nome" id="nome" maxlength="255"  placeholder="Digite seu nome" data-parsley-required />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="preco" class="cols-sm-2 control-label">Preço</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="preco" id="preco"  placeholder="Apenas números (em R$)" data-parsley-required data-affixes-stay="true" data-prefix="R$ " data-thousands="." data-decimal=","  />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="peso" class="cols-sm-2 control-label"> Peso </label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="peso" id="peso"  placeholder="Apenas números (em g)" data-parsley-required />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="volume" class="cols-sm-2 control-label"> Volume</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="volume" id="volume"  placeholder="Apenas números (em ml)" data-parsley-required />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="quantidade" class="cols-sm-2 control-label"> Quantidade em Estoque </label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="quantidade" id="quantidade"  placeholder="Apenas números" data-parsley-required  />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>


				<div class="form-group">
					<label for="descricao" class="cols-sm-2 control-label">Descrição</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<textarea class="form-control" rows="5" id="descricao" name="descricao" placeholder="Descrição aqui"></textarea>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				
				<div class="form-group">
					<label for="foto" class="control-label">Foto do produto</label>
					<div class="cols-sm-10">
						<input type="file" id="foto" name="foto" data-parsley-required>
					</div>
					<span class="mensagem-ajuda"></span>
				</div>
				
				
				<div class="form-group ">
					<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Cadastrar</button>
				</div>
			</form>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/parsley.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/i18n/pt-br.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.maskedinput.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.maskMoney.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-filestyle.min.js"></script>
		

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

		 $('#preco').maskMoney();
		 $("#peso").mask("9?9999");
		 $("#volume").mask("9?9999");
		 $("#quantidade").mask("9?9999");
		 
		 $("#foto").filestyle({buttonText: "Foto", buttonBefore: true, placeholder: "Nenhuma arquivo", size: "lg"});
		 
		</script>
		
				
	</jsp:attribute>
	
</template:base>





 





