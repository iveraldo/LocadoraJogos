package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String textoResposta = "";
        VendedorDAO vendedorDAO = new VendedorDAO();
        Vendedor vendedor = new Vendedor();
                    PrintWriter writer = resp.getWriter();
        try {

            writer.println("<html>");
            writer.println("<body>");
            
            //monta o objeto do vendedor
            vendedor.setLogin(req.getParameter("NomeUsuario"));
            vendedor.setSenha(req.getParameter("SenhaUsuario"));

            if(vendedorDAO.isLoginCorreto(vendedor)){
                resp.sendRedirect("index.jsp");
            } else{
                textoResposta = "Usuario ou senha errado";
            }

            writer.println("Resultado da busca: " + textoResposta + " <br/>");
            writer.println("</body>");
            writer.println("</html>");
            
        } catch(Exception ex) {
            writer.println("Resultado da busca: " + ex + " <br/>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}