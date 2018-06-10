<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.PedidoLocacaoDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.PedidoLocacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null)
        response.sendRedirect("login.html");
    
    Object pedidoLocacaos = new PedidoLocacaoDAO().consultar(new PedidoLocacao()); 
    request.setAttribute("pedidoLocacaos", new PedidoLocacaoDAO().consultar(new PedidoLocacao()));
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gerenciamento das Locacoes</title>
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
        
        <h4 class="page-head-line">Gerenciamento de PedidoLocacaos</h4>
            <h4>
                <a href="ManutencaoPedidoLocacao.jsp"><button type="button" class="btn btn-info">Incluir</button></a>
            </h4>
        <div class="col-md-12">			
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Cliente</th>
                            <th>Data devolucao</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pedidoLocacao" items="${pedidoLocacaos}">
                            <tr>
                                <td>${pedidoLocacao.cliente.nome}</td>
                                <td>${pedidoLocacao.dataDevolucao}</td>
                                <td>
                                    <a class="label label-success" href="ManutencaoPedidoLocacao.jsp?v=${pedidoLocacao.id}"> Alterar</a>
                                    <a class="label label-danger" href="excluirPedidoLocacao?i=${pedidoLocacao.id}">Excluir</a>
                                </td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>