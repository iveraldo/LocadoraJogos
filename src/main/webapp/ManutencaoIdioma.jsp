<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.IdiomaDAO"%>
<%@page import="Model.Idioma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("idioma", new IdiomaDAO().consultar(new Idioma(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de Idiomas</title>
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
        
        <form action="salvarIdioma" method="post" >
            <c:choose>
                <c:when test="${idioma == null}">
                    <h2>Cadastro de idioma</h2>
                </c:when>
                <c:otherwise>
                    <h2>Alteracao de idioma</h2>
                </c:otherwise>
            </c:choose>

            <input type="hidden" name="id" value="${idioma.id}" />

            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" value="${idioma.nome}" class="form-control" required="required" placeholder="Digite o nome" />
                </div>

                <div class="form-group">
                    <label for="nomeOriginal">Nome original</label>
                    <input type="text" name="nomeOriginal" value="${idioma.nomeOriginal}"  class="form-control" required="required" placeholder="Digite o nome original"/>
                </div>

                <div class="form-group">
                    <label for="qtdAproxFalantesNativos">Quantidade aprox. de falantes nativos</label>
                    <input type="number" name="qtdAproxFalantesNativos" value="${idioma.qtdAproxFalantesNativos}" class="form-control" required="required" placeholder="Digite a quantidade aprox. de falantes nativos" />
                </div>
                
                <div class="form-group">
                    <label for="qtdAproxFalantesEstrangeiros">Quantidade aprox. de falantes estrangeiros</label>
                    <input type="number" name="qtdAproxFalantesEstrangeiros" value="${idioma.qtdAproxFalantesEstrangeiros}" class="form-control" required="required" placeholder="Digite a quantidade aprox. de falantes estrangeiros" />
                </div>

                 <div class="form-group">
                    <label for="cpf">Familia linguistica</label>
                    <input type="text" name="familiaLinguistica" value="${idioma.familiaLinguistica}" required="required" class="form-control" placeholder="Digite a familia linguistica" />
                </div>

                <div class="form-group">
                    <label for="alfabeto">Alfabeto</label>
                    <input type="text" name="alfabeto" value="${idioma.alfabeto}" required="required" class="form-control" placeholder="Digite o alfabeto" />
                </div>  
                
                <div class="form-group">
                    <label for="qtdPaisesLinguaOficial">Quatidade de paises como lingua oficial</label>
                    <input type="number" name="qtdPaisesLinguaOficial" value="${idioma.qtdPaisesLinguaOficial}" class="form-control" required="required" placeholder="Digite a quantidade de paises como lingua oficial" />
                </div>
                
                <div class="form-group">
                    <label for="paisOrigem">Pais de origem</label>
                    <input type="text" name="paisOrigem" value="${idioma.paisOrigem}" class="form-control" required="required" placeholder="Digite o pais de origem" />
                </div>
                
                <div class="form-group">
                    <label for="dataOrigem">Data de origem</label>
                    <input type="date" name="dataOrigem" value="${idioma.dataOrigem}" class="form-control" required="required" placeholder="Digite a data de origem" />
                </div>

                 <br>
                 <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                 <a href="GerenciamentoIdioma.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>