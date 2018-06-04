package Control;

import DAO.IdiomaDAO;
import Model.Idioma;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirIdioma")
public class ExcluirIdiomaServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Idioma idioma = new Idioma();

            idioma.setId(Long.parseLong(req.getParameter("i")));
        
            IdiomaDAO.excluir(idioma);
            resp.sendRedirect("GerenciamentoIdioma.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}