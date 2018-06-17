package Control;

import DAO.PedidoLocacaoDAO;
import Model.PedidoLocacao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirPedidoLocacao")
public class ExcluirPedidoLocacaoServlet extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            PedidoLocacao pedidoLocacao = new PedidoLocacao();

            pedidoLocacao.setId(Long.parseLong(req.getParameter("i")));
        
            PedidoLocacaoDAO.excluir(pedidoLocacao);
            resp.sendRedirect("GerenciamentoPedidoLocacao.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}