<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de vendedor</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Recursos/login.css">
        <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="icon" href="https://opengameart.org/sites/default/files/psControllerColored.png">
    </head>
    <body>
        <!--Inicio do menu-->
        <div id="nav-placeholder"></div>
        <script> $(function(){ $("#nav-placeholder").load("menu.html"); }); </script>
        <!--Fim do menu-->
        
        <form action="login" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-offset-5 col-md-3">
                        <div class="form-login">
                            <h4>Jogo</h4>
                            <input type="text" id="TituloJogo" name="TituloJogo" class="form-control input-sm chat-input" placeholder="Titulo" />
                            </br>
                            <input type="text" id="QtdJogo" name="QtdJogo" class="form-control input-sm chat-input" placeholder="Quantidade" />
                            </br>
                            <input type="text" id="QtdJogo" name="QtdJogo" class="form-control input-sm chat-input" placeholder="Quantidade" />
                            </br>
                            <input type="text" id="QtdJogo" name="QtdJogo" class="form-control input-sm chat-input" placeholder="Quantidade" />
                            </br>
                            <input type="text" id="QtdJogo" name="QtdJogo" class="form-control input-sm chat-input" placeholder="Quantidade" />
                            </br>
                              
                            <button type="submit" class="btn btn-primary btn-md">Entrar <i class="glyphicon glyphicon-log-in"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
