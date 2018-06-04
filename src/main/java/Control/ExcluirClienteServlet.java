package Control;

import DAO.ClienteDAO;
import Model.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirCliente")
public class ExcluirClienteServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Cliente cliente = new Cliente();

            cliente.setId(Long.parseLong(req.getParameter("i")));
        
            ClienteDAO.excluir(cliente);
            resp.sendRedirect("GerenciamentoCliente.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}