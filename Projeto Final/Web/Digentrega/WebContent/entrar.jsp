<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Autenticação
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
              		<h1 class="title">Entrar no Sistema</h1>
              		<hr />
              	</div>
           </div>
	      <form method="post" class="form-signup">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">CPF</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="cpf" id="cpf"  placeholder="Apenas números"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">Senha</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="password" class="form-control" name="senha" id="senha"  placeholder="Senha"/>
						</div>
					</div>
				</div>


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




 





