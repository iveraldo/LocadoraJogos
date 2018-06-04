package Control;

import DAO.PlataformaDAO;
import Model.Plataforma;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirPlataforma")
public class ExcluirPlataformaServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Plataforma plataforma = new Plataforma();

            plataforma.setId(Long.parseLong(req.getParameter("i")));
        
            PlataformaDAO.excluir(plataforma);
            resp.sendRedirect("GerenciamentoPlataforma.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}