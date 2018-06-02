package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirVendedor")
public class ExcluirVendedorServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Vendedor vendedor = new Vendedor();

            vendedor.setId(Long.parseLong(req.getParameter("i")));
        
            VendedorDAO.excluir(vendedor);
            resp.sendRedirect("GerenciamentoVendedor.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}