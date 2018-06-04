<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.PlataformaDAO"%>
<%@page import="Model.Plataforma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("plataforma", new PlataformaDAO().consultar(new Plataforma(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de Plataformas</title>
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
        
        <form action="salvarPlataforma" method="post" >
            <c:choose>
                <c:when test="${plataforma == null}">
                    <h2>Cadastro de plataforma</h2>
                </c:when>
                <c:otherwise>
                    <h2>Alteracao de plataforma</h2>
                </c:otherwise>
            </c:choose>

            <input type="hidden" name="id" value="${plataforma.id}" />

            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="descricao">Nome</label>
                    <input type="text" name="descricao" value="${plataforma.descricao}" class="form-control" required="required" placeholder="Digite o nome" />
                </div>

                <div class="form-group">
                    <label for="dataCriacao">Data de criacao</label>
                    <input type="date" name="dataCriacao" value="${plataforma.dataCriacao}"  class="form-control" required="required" placeholder="Digite a data de criacao"/>
                </div>

                <div class="form-group">
                    <label for="fabricante">Fabricante</label>
                    <input type="text" name="fabricante" value="${plataforma.fabricante}" class="form-control" required="required" placeholder="Digite a fabricante" />
                </div>
                
                <div class="form-group">
                    <label for="unidadesVendidas">Unidades vendidas</label>
                    <input type="number" name="unidadesVendidas" value="${plataforma.unidadesVendidas}" class="form-control" required="required" placeholder="Digite a quantidade de unidades vendidas" />
                </div>

                 <div class="form-group">
                    <label for="numeroGeracao">Numero da geracao</label>
                    <input type="text" name="numeroGeracao" value="${plataforma.numeroGeracao}" required="required" class="form-control" placeholder="Digite o numero da geracao" />
                </div>

                <div class="form-group">
                    <label for="midia">Midia</label>
                    <input type="text" name="midia" value="${plataforma.midia}" required="required" class="form-control" placeholder="Digite a midia" />
                </div>  
                
                <div class="form-group">
                    <label for="siteOficial">Site oficial</label>
                    <input type="text" name="siteOficial" value="${plataforma.siteOficial}" class="form-control" required="required" placeholder="Digite o site oficial" />
                </div>
                
                <div class="form-group">
                    <label for="precoLancamento">Preco de lancamento</label>
                    <input type="number" name="precoLancamento" value="${plataforma.precoLancamento}" class="form-control" required="required" placeholder="Digite o preco de lancamento" />
                </div>
                
                <div class="form-group">
                    <label for="sistemaOperacional">Sistema operacional</label>
                    <input type="text" name="sistemaOperacional" value="${plataforma.sistemaOperacional}" class="form-control" required="required" placeholder="Digite o sistema operacional" />
                </div>

                <br>
                <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                <a href="GerenciamentoPlataforma.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>