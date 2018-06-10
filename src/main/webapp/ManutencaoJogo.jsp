<%@page import="DAO.ProdutoraDAO"%>
<%@page import="Model.Produtora"%>
<%@page import="Model.Idioma"%>
<%@page import="Util.Utilitario.EnumClassificacao"%>
<%@page import="Util.Utilitario.EnumGeneroJogo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.JogoDAO"%>
<%@page import="DAO.IdiomaDAO"%>
<%@page import="Model.Jogo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("jogo", JogoDAO.consultar(new Jogo(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de Jogos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        
        <form action="salvarJogo" method="post" >
            <c:choose>
                <c:when test="${jogo == null}">
                    <h2>Cadastro de jogo</h2>
                </c:when>
                <c:otherwise>
                    <h2>Alteracao de jogo</h2>
                </c:otherwise>
            </c:choose>

            <input type="hidden" name="id" value="${jogo.id}" />

            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="titulo" value="${jogo.titulo}" class="form-control" required="required" placeholder="Digite o titulo" />
                </div>

                <div class="form-group">
                    <label for="qtd">Quantidade</label>
                    <input type="text" name="qtd" value="${jogo.qtd}"  class="form-control" required="required" placeholder="Digite a quantidade"/>
                </div>

                <div class="form-group">
                    <label for="qtdDisponivel">Quantidade disponivel</label>
                    <input type="number" name="qtdDisponivel" value="${jogo.qtdDisponivel}" class="form-control" required="required" placeholder="Digite a quantidade disponivel" />
                </div>
                
                <div class="form-group">
                    <label for="qtdMaxJogadores">Quantidade maxima de jogadores</label>
                    <input type="text" name="qtdMaxJogadores" value="${jogo.qtdMaxJogadores}" class="form-control" required="required" placeholder="Digite a quantidade maxima de jogadores" />
                </div>

                 <div class="form-group">
                    <label for="tamanhoGB">Tamanho em GB</label>
                    <input type="text" name="tamanhoGB" value="${jogo.tamanhoGB}" required="required" class="form-control" placeholder="Digite o tamanho em GB" />
                </div>

                <c:set var="generos" value="<%=EnumGeneroJogo.values()%>"/>
                <div class="form-group">
                    <label for="genero">Genero</label>
                    <select name="genero" class="form-control">
                        <c:forEach var="genero" items="${generos}">
                            <c:choose>
                                <c:when test="${jogo.genero.equals(genero)}">
                                    <option value="${genero}" selected>${genero}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${genero}">${genero}</option>
                                </c:otherwise>
                            </c:choose> 
                        </c:forEach>
                    </select>
                </div>  
                
                <c:set var="classificacoes" value="<%=EnumClassificacao.values()%>"/>
                <div class="form-group">
                    <label for="classificacao">Classificacao</label>
                    <select name="classificacao" class="form-control">
                        <c:forEach var="classificacao" items="${classificacoes}">
                            <c:choose>
                                <c:when test="${jogo.classificacao.equals(classificacao)}">
                                    <option value="${classificacao}" selected>${classificacao}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${classificacao}">${classificacao}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>  
                                
                <c:set var="audios" value="<%= IdiomaDAO.consultar(new Idioma()) %>"/>
                <div class="form-group">
                    <label for="audios">Audios</label><br>
                    <c:forEach var="audio" items="${audios}">
                        <c:choose>
                            <c:when test="${jogo.idAudios.contains(audio.id)}">
                                <input type="checkbox" name="audios" checked value="${audio.id}"> ${audio.nome}<br>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="audios" value="${audio.id}"> ${audio.nome}<br>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                
                <c:set var="legendas" value="<%= IdiomaDAO.consultar(new Idioma()) %>"/>
                <div class="form-group">
                    <label for="legendas">Legendas</label><br>
                    <c:forEach var="legenda" items="${legendas}">
                        <c:choose>
                            <c:when test="${jogo.idLegendas.contains(legenda.id)}">
                                <input type="checkbox" name="legendas" checked value="${legenda.id}"> ${legenda.nome}<br>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="legendas" value="${legenda.id}"> ${legenda.nome}<br>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                
                <c:set var="produtoras" value="<%= ProdutoraDAO.consultar(new Produtora()) %>"/>
                <div class="form-group">
                    <label for="produtora">Produtora</label>
                    <select name="produtora" class="form-control">
                        <c:forEach var="produtora" items="${produtoras}">
                            <option value="${produtora.id}">${produtora.nome}</option>
                        </c:forEach>
                    </select>
                </div>  
                
                <div class="form-group">
                    <label for="valorJogo">Valor do jogo</label>
                    <input type="text" name="valorJogo" value="${jogo.valorJogo}" class="form-control" required="required" placeholder="Digite o valor do jogo" />
                </div>
                
                <div class="form-group">
                    <label for="valorLocacao">Valor da locacao</label>
                    <input type="text" name="valorLocacao" value="${jogo.valorLocacao}" class="form-control" required="required" placeholder="Digite o valor da locacao" />
                </div>
                
                <div class="form-group">
                    <label for="dataLancamento">Data de lancamento</label>
                    <input type="date" name="dataLancamento" value="${jogo.dataLancamento}" class="form-control" required="required" placeholder="Digite a data de lancamento" />
                </div>

                <br>
                <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                <a href="GerenciamentoJogo.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>