<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.JogoDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Jogo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null)
        response.sendRedirect("login.html");
    
    Object jogos = new JogoDAO().consultar(new Jogo()); 
    request.setAttribute("jogos", new JogoDAO().consultar(new Jogo()));
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gerenciamento de Jogos</title>
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
        
        <h4 class="page-head-line">Gerenciamento de Jogos</h4>
            <h4>
                <a href="ManutencaoJogo.jsp"><button type="button" class="btn btn-info">Incluir</button></a>
            </h4>
        <div class="col-md-12">			
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Genero</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="jogo" items="${jogos}">
                            <tr>
                                <td>${jogo.titulo}</td>
                                <td>${jogo.genero}</td>
                                <td>
                                    <a class="label label-success" href="ManutencaoJogo.jsp?v=${jogo.id}"> Alterar</a>
                                    <a class="label label-danger" href="excluirJogo?i=${jogo.id}">Excluir</a>
                                </td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>