package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/salvarVendedor")
public class SalvarVendedorServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Vendedor vendedor = new Vendedor();
            vendedor.setNome(req.getParameter("txtNomeVendedor"));
            vendedor.setLogin(req.getParameter("txtLoginVendedor"));
            vendedor.setSenha(req.getParameter("txtSenhaVendedor"));
            vendedor.setCpf(req.getParameter("txtCpfVendedor"));
            vendedor.setSalario(Double.parseDouble(req.getParameter("txtSalarioVendedor")));

            if(!req.getParameter("txtIdVendedor").isEmpty()) {
                vendedor.setId(Long.parseLong(req.getParameter("txtIdVendedor")));
            }
        
            VendedorDAO.salvar(vendedor);
            resp.sendRedirect("GerenciamentoVendedor.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}