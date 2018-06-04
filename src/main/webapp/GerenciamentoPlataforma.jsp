<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.PlataformaDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Plataforma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null)
        response.sendRedirect("login.html");
    
    Object plataformas = new PlataformaDAO().consultar(new Plataforma()); 
    request.setAttribute("plataformas", new PlataformaDAO().consultar(new Plataforma()));
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gerenciamento de Plataformas</title>
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
        
        <h4 class="page-head-line">Gerenciamento de Plataformas</h4>
            <h4>
                <a href="ManutencaoPlataforma.jsp"><button type="button" class="btn btn-info">Incluir</button></a>
            </h4>
        <div class="col-md-12">			
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Fabricante</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="plataforma" items="${plataformas}">
                            <tr>
                                <td>${plataforma.descricao}</td>
                                <td>${plataforma.fabricante}</td>
                                <td>
                                    <a class="label label-success" href="ManutencaoPlataforma.jsp?v=${plataforma.id}"> Alterar</a>
                                    <a class="label label-danger" href="excluirPlataforma?i=${plataforma.id}">Excluir</a>
                                </td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>