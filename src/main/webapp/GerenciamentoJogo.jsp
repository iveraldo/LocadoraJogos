<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Recursos/login.css">
        <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="icon" href="https://opengameart.org/sites/default/files/psControllerColored.png">
    </head>
    </head>
    <body>
        <!--Inicio do menu-->
        <div id="nav-placeholder"></div>
        <script> $(function(){ $("#nav-placeholder").load("menu.html"); }); </script>
        <!--Fim do menu-->
        
        <li><a href="ManutencaoJogo.html"><span class="glyphicon glyphicon-plus"></span> Cadastrar Jogo</a></li>
        <form action="login" method="post">
            <<table>
                <tr>
                    <th>Titulo</th>
                    <th>Quantidade</th>
                </tr>
                <tr>
                    <td>God of War</td>
                    <td>The Last of Us</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>6</td>
                </tr>

            </table>
        </form>
    </body>
</html>
