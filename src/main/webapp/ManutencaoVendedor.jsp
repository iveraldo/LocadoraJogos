<%@page import="DAO.VendedorDAO"%>
<%@page import="Model.Vendedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("usuarioLogado") == null)
        response.sendRedirect("login.html");
    
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
    </head>
    <body>
        <!--Inicio do menu-->
        <div id="nav-placeholder"></div>
        <script> $(function(){ $("#nav-placeholder").load("menu.html"); }); </script>
        <!--Fim do menu-->
        
        <form action="salvarVendedor" method="post" >
                        	
            <input type="hidden" name="txtIdVendedor" value="${vendedor.id}" />
                        	
            <div class="panel-body" style="width: 50%;">

                <div class="form-group">
                    <label for="txtNomeVendedor">Nome: </label>
                    <input type="text" id ="txtNomeVendedor" name="txtNomeVendedor" value="${vendedor.nome}" class="form-control" required="required" placeholder="Digite o nome" />
                </div>
                
                <div class="form-group">
                    <label for="txtLoginVendedor">Login: </label>
                    <input type="text" id="txtLoginVendedor" name="txtLoginVendedor" value="${vendedor.login}"  class="form-control" required="required" placeholder="Digite o login"/>
                </div>
                
                <div class="form-group">
                    <label for="txtSalarioVendedor">Salario: </label>
                    <input type="text" name="txtSalarioVendedor" value="${vendedor.salario}" class="form-control" required="required" placeholder="Digite o salario" />
                </div>

                 <div class="form-group">
                    <label for="txtCpfVendedor">CPF: </label>
                    <input type="text" name="txtCpfVendedor" value="${vendedor.cpf}" required="required" class="form-control" placeholder="Digite o CPF" />
                </div>

                 <div class="form-group">
                    <label for="txtSenhaVendedor">Senha: </label>
                    <input type="password" name="txtSenhaVendedor" value="${vendedor.senha}" class="form-control" placeholder="Digite a senha" />
                </div>

                 <div class="form-group">
                    <label for="txtConfirmacaoSenhaVendedor">Confirmar Senha: </label>                                    
                    <input type="password" name="txtConfirmacaoSenhaVendedor" value="${vendedor.senha}" class="form-control" />
                </div>                                

                 <br>
                 <button type="submit" value="submit" class="btn btn-default">Confirmar</button>
            </div>
        </form>
    </body>
</html>
