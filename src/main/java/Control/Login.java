package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login implements ITarefa {

    @Override
    public String executar(HttpServletRequest req, HttpServletResponse resp) {
        VendedorDAO vendedorDAO = new VendedorDAO();
        Vendedor vendedor = new Vendedor();
        
        //monta o objeto do vendedor
        vendedor.setLogin(req.getParameter("NomeUsuario"));
        vendedor.setSenha(req.getParameter("SenhaUsuario"));
        try{
            if(vendedorDAO.isLoginCorreto(vendedor)){
                HttpSession sessao = req.getSession();
                sessao.setAttribute("usuarioLogado", true);
                return "index.jsp";
            } else{
                return "falhaAutenticacao.html";
            }
        } catch(Exception ex) {
            System.out.print(ex);
            return "falha.html";
        }
        
    }
    
}