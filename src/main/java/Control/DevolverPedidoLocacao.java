package Control;

import DAO.PedidoLocacaoDAO;
import Model.PedidoLocacao;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/devolverPedidoLocacao")
public class DevolverPedidoLocacao extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, UnsupportedEncodingException{
        try {
            PedidoLocacao pedidoLocacao = new PedidoLocacao();

            pedidoLocacao.setId(Long.parseLong(req.getParameter("i")));
            
            pedidoLocacao = PedidoLocacaoDAO.consultar(pedidoLocacao).get(0);
        
            PedidoLocacaoDAO.devolver(pedidoLocacao);
            resp.sendRedirect("GerenciamentoPedidoLocacao.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException | NoSuchAlgorithmException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}