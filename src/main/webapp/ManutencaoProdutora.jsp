<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.ProdutoraDAO"%>
<%@page import="Model.Produtora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("produtora", new ProdutoraDAO().consultar(new Produtora(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de Produtoras</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="icon" href="https://opengameart.org/sites/default/files/psControllerColored.png">
        <script src="Recursos/script.js" ></script>
    </head>
    <body>
        <!--Inicio do menu-->
        <div id="nav-placeholder"></div>
        <script> $(function(){ $("#nav-placeholder").load("menu.html"); }); </script>
        <!--Fim do menu-->
        
        <form action="salvarProdutora" method="post" >
            <c:choose>
                <c:when test="${produtora == null}">
                    <h2>Cadastro de produtora</h2>
                </c:when>
                <c:otherwise>
                    <h2>Alteracao de produtora</h2>
                </c:otherwise>
            </c:choose>

            <input type="hidden" name="id" value="${produtora.id}" />

            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" value="${produtora.nome}" class="form-control" required="required" placeholder="Digite o nome" />
                </div>

                <div class="form-group">
                    <label for="dataFundacao">Data de fundacao</label>
                    <input type="date" name="dataFundacao" value="${produtora.dataFundacao}"  class="form-control" required="required" placeholder="Digite a data de fundacao"/>
                </div>

                <div class="form-group">
                    <label for="valorRendaBruta">Valor da renda bruta</label>
                    <input type="number" name="valorRendaBruta" value="${produtora.valorRendaBruta}" class="form-control" required="required" placeholder="Digite o valor da renda bruta" />
                </div>
                
                <div class="form-group">
                    <label for="valorLucros">Valor dos lucros</label>
                    <input type="number" name="valorLucros" value="${produtora.valorLucros}" class="form-control" required="required" placeholder="Digite o valor dos lucros" />
                </div>

                 <div class="form-group">
                    <label for="mascote">Mascote</label>
                    <input type="text" name="mascote" value="${produtora.mascote}" required="required" class="form-control" placeholder="Digite o mascote" />
                </div>

                <div class="form-group">
                    <label for="siteOficial">Site oficial</label>
                    <input type="text" name="siteOficial" value="${produtora.siteOficial}" required="required" class="form-control" placeholder="Digite o site oficial" />
                </div>  
                
                <div class="form-group">
                    <label for="cidadeSede">Cidade sede</label>
                    <input type="text" name="cidadeSede" value="${produtora.cidadeSede}" class="form-control" required="required" placeholder="Digite a cidade sede" />
                </div>
                
                <div class="form-group">
                    <label for="paisOrigem">Pais de origem</label>
                    <input type="text" name="paisOrigem" value="${produtora.paisOrigem}" class="form-control" required="required" placeholder="Digite o pais de origem" />
                </div>
                
                <div class="form-group">
                    <label for="qtdEstudios">Quantidade de estudios</label>
                    <input type="number" name="qtdEstudios" value="${produtora.qtdEstudios}" class="form-control" required="required" placeholder="Digite a quantidade de estudios" />
                </div>

                <br>
                <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                <a href="GerenciamentoProdutora.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>