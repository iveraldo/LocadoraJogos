<%@page import="Model.Jogo"%>
<%@page import="DAO.JogoDAO"%>
<%@page import="DAO.ClienteDAO"%>
<%@page import="Model.Cliente"%>
<%@page import="DAO.ProdutoraDAO"%>
<%@page import="Model.Produtora"%>
<%@page import="Model.Idioma"%>
<%@page import="Util.Utilitario.EnumFormaPagamento"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.PedidoLocacaoDAO"%>
<%@page import="DAO.IdiomaDAO"%>
<%@page import="Model.PedidoLocacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("pedidoLocacao", PedidoLocacaoDAO.consultar(new PedidoLocacao(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de Pedidos de Locacao</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="icon" href="https://opengameart.org/sites/default/files/psControllerColored.png">
        <script type="text/javascript" src="Recursos/script.js"></script>
    </head>
    <body>
        <!--Inicio do menu-->
        <div id="nav-placeholder"></div>
        <script> $(function(){ $("#nav-placeholder").load("menu.html"); }); </script>
        <!--Fim do menu-->
                
        <form action="salvarPedidoLocacao" method="post" >
                
            <div class="panel-body" style="width: 50%;">
                
                <input type="hidden" name="id" value="${pedidoLocacao.id}" readonly/>
                <input type="hidden" name="idVendedor" value="${usuarioLogado.id}" readonly/>
                                
                <c:choose>
                    <c:when test="${pedidoLocacao == null}">
                        <h2>Pedido de locacao</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>Alteracao do pedido de locacao</h2>
                    </c:otherwise>
                </c:choose>
                        
                <div class="form-group">
                    <label for="nomeVendedor">Vendedor</label>
                    <input type="text" name="nomeVendedor" value="${usuarioLogado.nome}" class="form-control" required="required" readonly />
                </div>

                <c:set var="clientes" value="<%= ClienteDAO.consultar(new Cliente()) %>"/>
                <div class="form-group">
                    <label for="cliente">Cliente</label>
                    <select name="cliente" class="form-control">
                        <c:forEach var="cliente" items="${clientes}">
                            <c:choose>
                                <c:when test="${pedidoLocacao.cliente.id.equals(cliente.id)}">
                                    <option value="${cliente.id}" selected>${cliente.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cliente.id}">${cliente.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>  
                
                <div class="table-responsive">
                    <label for="">Jogos disponiveis</label>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Selecione</th>
                                <th style="display: none;">Id</th>
                                <th>Jogo</th>
                                <th>Valor da locacao</th>
                                <th>Quantidade disponivel</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="jogos" value="<%=JogoDAO.consultar(new Jogo(true)) %>"/>
                            <c:forEach var="jogo" items="${jogos}">
                                <c:if test="${!pedidoLocacao.idJogos.contains(jogo.id)}">
                                    <tr>
                                        <td><input type="checkbox" name="selecione" /></td>
                                        <td class="d-none" style="display: none;">${jogo.id}</td>
                                        <td>${jogo.titulo}</td>
                                        <td>${jogo.valorLocacao}</td>
                                        <td>${jogo.qtdDisponivel}</td>
                                    </tr>   
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <button type="button" class="btn btn-success">Adicionar jogo(s)</button>
                
                <br><br><br>
                
                <label for="jogosSelecionados">Jogos selecionados</label>
                <select name="jogosSelecionados" id="jogosSelecionados" multiple class="form-control">
                    <c:forEach var="jogo" items="${pedidoLocacao.jogos}">
                        <option value="${jogo.id}">${jogo.titulo}</option>
                    </c:forEach>
                </select>
                
                <br>
                
                <c:set var="formasPagamento" value="<%=EnumFormaPagamento.values()%>"/>
                <div class="form-group">
                    <label for="formaPagamento">Forma de pagamento</label>
                    <select name="formaPagamento" class="form-control">
                        <c:forEach var="formaPagamento" items="${formasPagamento}">
                            <c:choose>
                                <c:when test="${pedidoLocacao.formaPagamento.equals(formaPagamento)}">
                                    <option value="${formaPagamento}" selected>${formaPagamento}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${formaPagamento}">${formaPagamento}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div> 
                
                <div class="form-group">
                    <label for="valorPedido">Valor do pedido</label>
                    <c:choose>
                        <c:when test="${pedidoLocacao == null}">
                            <input type="text" id="valorPedido" name="valorPedido" value="0" class="form-control" required="required" readonly />
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="valorPedido" name="valorPedido" value="${pedidoLocacao.valorLocacao}" class="form-control" required="required" readonly />
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                
                <div class="form-group">
                    <label for="obs">Observacao</label>
                    <textarea name="obs" value="${pedidoLocacao.obs}" class="form-control" ></textarea>
                </div>
                
                <div class="form-group">
                    <label for="cupom">Cupom</label>
                    <input type="text" name="cupom" value="${pedidoLocacao.cupom}" class="form-control" />
                </div>

                <br>
                <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                <a href="GerenciamentoPedidoLocacao.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>