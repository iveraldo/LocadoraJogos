<%@page import="Util.Utilitario.EnumSexo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.ClienteDAO"%>
<%@page import="Model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.html");
    }
    if(request.getParameter("v") != null){
        Long id = Long.parseLong(request.getParameter("v").toString());
        request.setAttribute("cliente", new ClienteDAO().consultar(new Cliente(id)).get(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manutencao de Clientes</title>
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
        
        <form action="salvarCliente" method="post" >
            <c:choose>
                <c:when test="${cliente == null}">
                    <h2>Cadastro de cliente</h2>
                </c:when>
                <c:otherwise>
                    <h2>Alteracao de cliente</h2>
                </c:otherwise>
            </c:choose>

            <input type="hidden" name="id" value="${cliente.id}" />
            <input type="hidden" name="idEndereco" value="${cliente.endereco.id}" />

            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" value="${cliente.nome}" class="form-control" required="required" placeholder="Digite o nome" />
                </div>

                <div class="form-group">
                    <label for="cpf">CPF</label>
                    <input type="text" name="cpf" value="${cliente.cpf}"  class="form-control" required="required" placeholder="Digite o CPF"/>
                </div>

                <div class="form-group">
                    <label for="idade">Idade</label>
                    <input type="number" name="idade" value="${cliente.idade}" class="form-control" required="required" placeholder="Digite a idade" />
                </div>
                
                <div class="form-group">
                    <label for="celular">Celular</label>
                    <input type="text" name="celular" value="${cliente.celular}" class="form-control" required="required" placeholder="Digite o celular" />
                </div>

                 <div class="form-group">
                    <label for="telefone">Telefone</label>
                    <input type="text" name="telefone" value="${cliente.telefone}" required="required" class="form-control" placeholder="Digite o telefone" />
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" name="email" value="${cliente.email}" required="required" class="form-control" placeholder="Digite o email" />
                </div>  
                
                <c:set var="sexos" value="<%=EnumSexo.values()%>"/>
                <div class="form-group">
                    <label for="sexo">Sexo</label>
                    <select name="sexo" class="form-control">
                        <c:forEach var="sexo" items="${sexos}">
                            <c:choose>
                                <c:when test="${cliente.sexo.equals(sexo)}">
                                    <option value="${sexo}" selected>${sexo}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${sexo}">${sexo}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div> 
                
                <div class="form-group">
                    <label for="logradouro">Logradouro</label>
                    <input type="text" name="logradouro" value="${cliente.endereco.logradouro}" class="form-control" required="required" placeholder="Digite o logradouro" />
                </div>
                
                <div class="form-group">
                    <label for="numero">Numero</label>
                    <input type="number" name="numero" value="${cliente.endereco.numero}" class="form-control" required="required" placeholder="Digite o numero" />
                </div>
                
                <div class="form-group">
                    <label for="complemento">Complemento</label>
                    <input type="text" name="complemento" value="${cliente.endereco.complemento}" class="form-control" required="required" placeholder="Digite o complemento" />
                </div>
                
                <div class="form-group">
                    <label for="bairro">Bairro</label>
                    <input type="text" name="bairro" value="${cliente.endereco.bairro}" class="form-control" required="required" placeholder="Digite o bairro" />
                </div>
                
                <div class="form-group">
                    <label for="cidade">Cidade</label>
                    <input type="text" name="cidade" value="${cliente.endereco.cidade}" class="form-control" required="required" placeholder="Digite o cidade" />
                </div>
                
                <div class="form-group">
                    <label for="estado">Estado</label>
                    <input type="text" name="estado" value="${cliente.endereco.estado}" class="form-control" required="required" placeholder="Digite o estado  " />
                </div>
                
                <div class="form-group">
                    <label for="cep">CEP</label>
                    <input type="text" name="cep" value="${cliente.endereco.cep}" class="form-control" required="required" placeholder="Digite o CEP" />
                </div>
                
                <div class="form-group">
                    <label for="obs">Observacao</label>
                    <input type="text" name="obs" value="${cliente.endereco.obs}" class="form-control" required="required" placeholder="Digite o observacao" />
                </div>

                <br>
                <button type="submit" value="submit" class="btn btn-success">Confirmar</button>
                <a href="GerenciamentoCliente.jsp"><button type="button" class="btn btn-warning">Voltar</button></a>
            </div>
        </form>
    </body>
</html>