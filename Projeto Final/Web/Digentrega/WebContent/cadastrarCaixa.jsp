<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:base>

	<jsp:attribute name="titulo">   
		Cadastrar Caixa
	</jsp:attribute>
		
	<jsp:attribute name="cabecalhoExtra">   
		<%-- Mais arquivos CSS e Javascript aqui. --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/formulario.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-datepicker3.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/parsley.css"/>
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
			<form id="formulario" class="form-signup" method="post">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">Nome completo</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="nome" id="nome"  placeholder="Digite seu nome" data-parsley-required />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
										
	
				<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">CPF</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="cpf" id="cpf"  placeholder="Apenas números" data-parsley-required data-parsley-cpf />
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="username" class="cols-sm-2 control-label">Data de Nascimento</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="dataNascimento" id="dataNascimento"  placeholder="dd/mm/aaaa"/ data-parsley-required>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>
				
				
			<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">Telefones</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<input type="text" class="form-control" name="telefones" id="telefones"  placeholder="Apenas números (mais de um telefone separado por virgula)" data-parsley-required/>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>


				<div class="form-group">
					<label for="password" class="cols-sm-2 control-label">Senha</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<input type="password" class="form-control" name="senha1" id="senha1"  placeholder="Senha" data-parsley-required/>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="confirm" class="cols-sm-2 control-label">Confirmar Senha</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<input type="password" class="form-control" name="senha2" id="senha2"  placeholder="Confirm your Password" data-parsley-required/>
						</div>
						<span class="mensagem-ajuda"></span>
					</div>
				</div>

				<div class="form-group ">
					<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Cadastrar</button>
				</div>
			</form>
	    </div> <!-- /container -->
	    
    </jsp:attribute>
    
        
	<jsp:attribute name="rodapeExtra">  
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.pt-BR.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/parsley.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/i18n/pt-br.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.maskedinput.min.js"></script>
		
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
			
			window.Parsley.addValidator('cpf', {
			  validateString: function(value) {	  
				cpf = value.replace(/[^\d]+/g,''); 
			    if(cpf == '') return false; 
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
			}).addMessage('pt-br', 'cpf', 'CPF inválido.');;
			
			
		});
		</script>
		<script>
			$(document).ready(function(){	
				$("#cpf").mask("999.999.999-99");
			});
		</script>
		
	</jsp:attribute>
	
</template:base>





 





