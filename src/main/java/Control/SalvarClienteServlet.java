package Control;

import DAO.ClienteDAO;
import Model.Cliente;
import Model.Endereco;
import Util.Utilitario;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/salvarCliente")
public class SalvarClienteServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Cliente cliente = new Cliente();
            Endereco endereco = new Endereco();
                
            //Monta o objeto cliente
            cliente.setNome(req.getParameter("nome"));
            cliente.setCpf(req.getParameter("cpf"));
            cliente.setIdade(Integer.parseInt(req.getParameter("idade")));
            cliente.setCelular(req.getParameter("celular"));
            cliente.setTelefone(req.getParameter("telefone"));
            cliente.setEmail(req.getParameter("email"));
            cliente.setSexo(Utilitario.EnumSexo.valueOf(req.getParameter("sexo")));
            cliente.setDataCadastro(new Date(System.currentTimeMillis()));
            
            //Monta o objeto Endereco
            endereco.setLogradouro(req.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(req.getParameter("numero")));
            endereco.setComplemento(req.getParameter("complemento"));
            endereco.setBairro(req.getParameter("bairro"));
            endereco.setCidade(req.getParameter("cidade"));
            endereco.setEstado(Utilitario.EnumEstado.valueOf(req.getParameter("estado")));
            endereco.setCep(req.getParameter("cep"));
            endereco.setObs(req.getParameter("obs"));
            endereco.setDataCriacao(new Date(System.currentTimeMillis()));

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                cliente.setId(Long.parseLong(req.getParameter("id")));
                endereco.setId(Long.parseLong(req.getParameter("idEndereco")));
            }
            
            cliente.setEndereco(endereco);
        
            ClienteDAO.salvar(cliente);
            resp.sendRedirect("GerenciamentoCliente.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }

}