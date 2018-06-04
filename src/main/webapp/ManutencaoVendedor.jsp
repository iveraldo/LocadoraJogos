<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.VendedorDAO"%>
<%@page import="Model.Vendedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("vendedor", new VendedorDAO().consultar(new Vendedor(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de vendedor</title>
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
        
        <form action="salvarVendedor" method="post" >
            <c:choose>
                <c:when test="${vendedor == null}">
                    <h2>Cadastro de vendedor</h2>
                </c:when>
                <c:otherwise>
                    <h2>Alteracao de vendedor</h2>
                </c:otherwise>
            </c:choose>

            <input type="hidden" name="id" value="${vendedor.id}" />

            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" value="${vendedor.nome}" class="form-control" required="required" placeholder="Digite o nome" />
                </div>

                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" name="login" value="${vendedor.login}"  class="form-control" required="required" placeholder="Digite o login"/>
                </div>

                <div class="form-group">
                    <label for="salario">Salario</label>
                    <input type="number" name="salario" value="${vendedor.salario}" class="form-control" required="required" placeholder="Digite o salario" />
                </div>

                 <div class="form-group">
                    <label for="cpf">CPF</label>
                    <input TYPE="text" name="cpf" value="${vendedor.cpf}" required="required" class="form-control" placeholder="Digite o CPF" />
                </div>

                <c:if test="${vendedor != null}">
                    <div class="form-group">  
                    <c:choose>
                        <c:when test="${vendedor.isAtivo}">
                            <input id="ativo" name="status" type="radio" value="Ativo" checked /> Ativo
                            &nbsp; &nbsp;
                            <input id="inativo" name="status" type="radio" value="Inativo" /> Inativo
                        </c:when>
                        <c:otherwise>
                            <input id="ativo" name="status" type="radio" value="Ativo" /> Ativo
                            &nbsp; &nbsp;
                            <input id="inativo" name="status" type="radio" value="Inativo" checked /> Inativo
                        </c:otherwise>
                    </c:choose>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="percentualComissao">Percentual da comissao</label>
                    <input type="number" name="percentualComissao" value="${vendedor.percentualComissao}" required="required" class="form-control" placeholder="Digite o percentual da comissao" />
                </div>
                
                <c:if test="${vendedor != null}">
                    <div class="form-group">
                        <label for="dataAdmissao">Data de admissao</label>
                        <input type="date" name="dataAdmissao" value="${vendedor.dataAdmissao}" class="form-control" />
                    </div>
                </c:if>
                
                <c:if test="${vendedor != null && !vendedor.isAtivo}">
                    <div class="form-group">
                        <label for="dataDemissao">Data de demissao</label>
                        <input type="date" name="dataDemissao" value="${vendedor.dataDemissao}" class="form-control" />
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="senha">Senha</label>       
                    <input type="password" name="senha" value="${vendedor.senha}" class="form-control" />
                </div>

                 <div class="form-group">
                    <label for="confirmacaoSenha">Confirmar Senha</label>                                    
                    <input type="password" name="confirmacaoSenha" value="${vendedor.senha}" class="form-control" />
                </div>                                

                 <br>
                 <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                 <a href="GerenciamentoVendedor.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>