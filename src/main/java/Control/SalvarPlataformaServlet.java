package Control;

import DAO.PlataformaDAO;
import Model.Plataforma;
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

@WebServlet("/salvarPlataforma")
public class SalvarPlataformaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Plataforma plataforma = new Plataforma();
            plataforma.setDescricao(req.getParameter("descricao"));
            plataforma.setSistemaOperacional(req.getParameter("sistemaOperacional"));
            plataforma.setFabricante(req.getParameter("fabricante"));
            plataforma.setUnidadesVendidas(Long.parseLong(req.getParameter("unidadesVendidas")));
            plataforma.setNumeroGeracao(Integer.parseInt(req.getParameter("numeroGeracao")));
            plataforma.setMidia(req.getParameter("midia"));
            plataforma.setSiteOficial(req.getParameter("siteOficial"));
            plataforma.setPrecoLancamento(Double.parseDouble(req.getParameter("precoLancamento")));
            
            String dataString = req.getParameter("dataCriacao");
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dataAdmissao = new Date(fmt.parse(dataString).getTime());
            plataforma.setDataCriacao(dataAdmissao);

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                plataforma.setId(Long.parseLong(req.getParameter("id")));
            }
        
            PlataformaDAO.salvar(plataforma);
            resp.sendRedirect("GerenciamentoPlataforma.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        } catch (ParseException ex) {
            Logger.getLogger(SalvarVendedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}