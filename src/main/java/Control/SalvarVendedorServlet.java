package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/salvarVendedor")
public class SalvarVendedorServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Vendedor vendedor = new Vendedor();
            vendedor.setNome(req.getParameter("nome"));
            vendedor.setLogin(req.getParameter("login"));
            vendedor.setSenha(req.getParameter("senha"));
            vendedor.setCpf(req.getParameter("cpf"));
            vendedor.setSalario(Double.parseDouble(req.getParameter("salario")));
            vendedor.setPercentualComissao(Integer.parseInt(req.getParameter("percentualComissao")));

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                vendedor.setId(Long.parseLong(req.getParameter("id")));
                
                vendedor.setIsAtivo(enumStatus.valueOf(req.getParameter("status")) == enumStatus.Ativo);
                
                String dataString = req.getParameter("dataAdmissao");
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Date dataAdmissao = new Date(fmt.parse(dataString).getTime());
        
                vendedor.setDataAdmissao(dataAdmissao);
                
                if(vendedor.getIsAtivo()) {
                    vendedor.setDataDemissao(null);
                } else {
                    vendedor.setDataDemissao(new Date(System.currentTimeMillis()));
                }
                
            } else { //CADASTRO
                vendedor.setIsAtivo(true);
                vendedor.setDataAdmissao(new Date(System.currentTimeMillis()));
            }
        
            VendedorDAO.salvar(vendedor);
            resp.sendRedirect("GerenciamentoVendedor.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        } catch (ParseException ex) {
            Logger.getLogger(SalvarVendedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private enum enumStatus{
        Ativo
        ,Inativo
    }
}