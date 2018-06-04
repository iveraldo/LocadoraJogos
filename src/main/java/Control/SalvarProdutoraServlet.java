package Control;

import DAO.ProdutoraDAO;
import Model.Produtora;
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

@WebServlet("/salvarProdutora")
public class SalvarProdutoraServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Produtora produtora = new Produtora();
            produtora.setNome(req.getParameter("nome"));
            produtora.setQtdEstudios(Integer.parseInt(req.getParameter("qtdEstudios")));
            produtora.setValorRendaBruta(Double.parseDouble(req.getParameter("valorRendaBruta")));
            produtora.setValorLucros(Double.parseDouble(req.getParameter("valorLucros")));
            produtora.setMascote(req.getParameter("mascote"));
            produtora.setSiteOficial(req.getParameter("siteOficial"));
            produtora.setCidadeSede(req.getParameter("cidadeSede"));
            produtora.setPaisOrigem(req.getParameter("paisOrigem"));
            
            String dataString = req.getParameter("dataFundacao");
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dataAdmissao = new Date(fmt.parse(dataString).getTime());
            produtora.setDataFundacao(dataAdmissao);

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                produtora.setId(Long.parseLong(req.getParameter("id")));
            }
        
            ProdutoraDAO.salvar(produtora);
            resp.sendRedirect("GerenciamentoProdutora.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        } catch (ParseException ex) {
            Logger.getLogger(SalvarVendedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}