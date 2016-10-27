
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./favicon.ico">
 
    <title> DigEntrega </title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <!-- Bootstrap theme -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="${pageContext.request.contextPath}/static/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/navbar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/digentrega.css" rel="stylesheet">  

  </head>

  <body>
  
  <div class="container">

     <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">DigEntrega</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav"> 
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Pedidos <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="/pedido/novo/">Novo Pedido</a></li>
                  <li><a href="/pedido/abertos/">Ver Pedidos Abertos</a></li>
                  <li><a href="/pedido/finalizados/">Ver Pedidos Finalizados</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Funcionários <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Cadastrar Caixa</a></li>
                  <li><a href="#">Cadastrar Entregador</a></li>
                  <li><a href="#">Listar Funcionários</a></li>
                </ul>
              </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="#">Sair</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
    </div>

    <div class="container theme-showcase" role="main">
    
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>DigEntrega</h1>
        <p> O DigEntrega é uma ferramenta que facilita o processo de logística de entregas de encomendas.</p>
        <p> Por favor, escolha uma função na barra de menu.
      </div>


    </div> <!-- /container -->
    
    <footer class="footer">
      <div class="container">
        <p class="text-muted text-center"> DigEntrega 0.1. b2016.10.26. </p>
      </div>
    </footer>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/static/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/static/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
