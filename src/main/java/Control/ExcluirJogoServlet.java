package Control;

import DAO.JogoDAO;
import Model.Jogo;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirJogo")
public class ExcluirJogoServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Jogo jogo = new Jogo();

            jogo.setId(Long.parseLong(req.getParameter("i")));
        
            JogoDAO.excluir(jogo);
            resp.sendRedirect("GerenciamentoJogo.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}