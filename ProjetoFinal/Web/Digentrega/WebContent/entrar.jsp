<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Autenticação
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
              		<h1 class="title">Entrar no Sistema</h1>
              		<hr />
              	</div>
           </div>
	      <form id="formulario" method="post" class="form-signup">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">CPF</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="cpf" id="cpf"  placeholder="Apenas números" required data-parsley-cpf />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">Senha</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="password" class="form-control" name="senha" id="senha" required  placeholder="Senha"/>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>


	        <div class="checkbox">
	          <label>
	            <input type="checkbox" value="remember-me" id="lembrar" name="lembrar"> Lembrar?
	          </label>
	        </div>	        
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	      </form>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
    <jsp:attribute name="rodapeExtra">  
    	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/parsley.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/i18n/pt-br.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.maskedinput.min.js"></script>
    
    		<script>
			$(document).ready(function(){	
				$("#cpf").mask("999.999.999-99");
			});
			
			</script>
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
			
			
			window.Parsley.addValidator('cpf', {
				  validateString: function(value) {	  
					cpf = value.replace(/[^\d]+/g,''); 
				    if(cpf == '') return false; 
				    
				    // CPF do administrador.
				    if(cpf = "00000000000") {
				    	return true;
				    }
				    
				    // Elimina CPFs invalidos conhecidos  
				    if (cpf.length != 11 || 
				        cpf == "00000000000" || 
				        cpf == "11111111111" || 
				        cpf == "22222222222" || 
				        cpf == "33333333333" || 
				        cpf == "44444444444" || 
				        cpf == "55555555555" || 
				        cpf == "66666666666" || 
				        cpf == "77777777777" || 
				        cpf == "88888888888" || 
				        cpf == "99999999999")
				            return false;       
				    // Valida 1o digito 
				    add = 0;    
				    for (i=0; i < 9; i ++)       
				        add += parseInt(cpf.charAt(i)) * (10 - i);  
				        rev = 11 - (add % 11);  
				        if (rev == 10 || rev == 11)     
				            rev = 0;    
				        if (rev != parseInt(cpf.charAt(9)))     
				            return false;       
				    // Valida 2o digito 
				    add = 0;    
				    for (i = 0; i < 10; i ++)        
				        add += parseInt(cpf.charAt(i)) * (11 - i);  
				    rev = 11 - (add % 11);  
				    if (rev == 10 || rev == 11) 
				        rev = 0;    
				    if (rev != parseInt(cpf.charAt(10)))
				        return false;       
				    return true;   
				  },
				}).addMessage('pt-br', 'cpf', 'CPF inválido.');	
			
		</script>
	</jsp:attribute>
    
</template:base>




 





