<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gerenciamento de vendedor</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Recursos/geral.css">
        <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <!--Inicio do menu-->
        <div id="nav-placeholder"></div>
        <script> $(function(){ $("#nav-placeholder").load("menu.html"); }); </script>
        <!--Fim do menu-->
        
        <div class="container">
            <div class="row">
                <div class="col-md-3 celula">Nome</div>
                <div class="col-md-3 celula">Login</div>
            </div>
            <div class="row">
                <div class="col-md-3 celula">Nuno</div>
                <div class="col-md-3 celula">Guilherme</div>
            </div>
            <div class="row">
                <div class="col-md-3 celula">nunoleao</div>
                <div class="col-md-3 celula">guilhermenascimento</div>
            </div>
        </div>
    </body>
</html>