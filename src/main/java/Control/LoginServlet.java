package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Vendedor vendedor = new Vendedor();
            List<Vendedor> vendedores;
        
            //monta o objeto do vendedor
            vendedor.setLogin(req.getParameter("nome"));
            vendedor.setSenha(req.getParameter("senha"));
            
            vendedores = VendedorDAO.consultar(vendedor);
            
            if(vendedores.size() > 0){
                HttpSession sessao = req.getSession();
                sessao.setAttribute("usuarioLogado", vendedores.get(0));
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