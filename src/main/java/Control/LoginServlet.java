package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Vendedor vendedor = new Vendedor();
        
            //monta o objeto do vendedor
            vendedor.setLogin(req.getParameter("NomeUsuario"));
            vendedor.setSenha(req.getParameter("SenhaUsuario"));
            
            if(VendedorDAO.validarLogin(vendedor) != null){
                HttpSession sessao = req.getSession();
                sessao.setAttribute("usuarioLogado", true);
                resp.sendRedirect("index.jsp");
            } else{
                resp.sendRedirect("falhaAutenticacao.html");
            }
        } catch(IOException | ClassNotFoundException | SQLException ex) {
            System.out.print("Erro: " + ex);
            resp.sendRedirect("falha.html");
        }
    }
}