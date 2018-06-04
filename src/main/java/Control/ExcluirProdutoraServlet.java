package Control;

import DAO.ProdutoraDAO;
import Model.Produtora;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirProdutora")
public class ExcluirProdutoraServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Produtora produtora = new Produtora();

            produtora.setId(Long.parseLong(req.getParameter("i")));
        
            ProdutoraDAO.excluir(produtora);
            resp.sendRedirect("GerenciamentoProdutora.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}