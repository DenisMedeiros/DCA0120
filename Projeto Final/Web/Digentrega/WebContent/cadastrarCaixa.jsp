<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Autenticação
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/formulario.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-datepicker3.css"/>
	</jsp:attribute>


    <jsp:attribute name="conteudoPrincipal">      
    	<%-- Parte principal da página. --%>
		<div class="container theme-showcase" role="main">
		   	<div class="panel-heading">
              <div class="panel-title text-center">
              		<h1 class="title">Cadastrar Caixa</h1>
              		<hr />
              	</div>
           </div>
			<form class="form-signup" method="post" action="#">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">Nome completo</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="nome" id="nome"  placeholder="Digite seu nome"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">CPF</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="cpf" id="cpf"  placeholder="Apenas números"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="username" class="cols-sm-2 control-label">Data de Nascimento</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="dataNascimento" id="dataNascimento"  placeholder="dd/mm/aaaa"/>
						</div>
					</div>
				</div>
				
			<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">Telefones</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="cpf" id="cpf"  placeholder="Apenas números"/>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="password" class="cols-sm-2 control-label">Password</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<input type="password" class="form-control" name="password" id="password"  placeholder="Enter your Password"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<input type="password" class="form-control" name="confirm" id="confirm"  placeholder="Confirm your Password"/>
						</div>
					</div>
				</div>

				<div class="form-group ">
					<button type="button" class="btn btn-primary btn-lg btn-block login-button">Register</button>
				</div>
			</form>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.pt-BR.min.js"></script>
		<script>
		    $(document).ready(function(){
		      var date_input=$('#dataNascimento');
		      var options={
		        format: 'dd/mm/yyyy',
		        todayHighlight: true,
		        autoclose: true,
		        language: "pt-BR",
		      };
		      date_input.datepicker(options);
		    })
		</script>
	</jsp:attribute>
	
</template:base>





 





