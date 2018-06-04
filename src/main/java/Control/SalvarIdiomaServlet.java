package Control;

import DAO.IdiomaDAO;
import Model.Idioma;
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

@WebServlet("/salvarIdioma")
public class SalvarIdiomaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Idioma idioma = new Idioma();
            idioma.setNome(req.getParameter("nome"));
            idioma.setNomeOriginal(req.getParameter("nomeOriginal"));
            idioma.setQtdAproxFalantesNativos(Long.parseLong(req.getParameter("qtdAproxFalantesNativos")));
            idioma.setQtdAproxFalantesEstrangeiros(Long.parseLong(req.getParameter("qtdAproxFalantesEstrangeiros")));
            idioma.setFamiliaLinguistica(req.getParameter("familiaLinguistica"));
            idioma.setAlfabeto(req.getParameter("alfabeto"));
            idioma.setQtdPaisesLinguaOficial(Integer.parseInt(req.getParameter("qtdPaisesLinguaOficial")));
            idioma.setPaisOrigem(req.getParameter("paisOrigem"));
            
            String dataString = req.getParameter("dataOrigem");
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dataAdmissao = new Date(fmt.parse(dataString).getTime());
            idioma.setDataOrigem(dataAdmissao);

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                idioma.setId(Long.parseLong(req.getParameter("id")));
            }
        
            IdiomaDAO.salvar(idioma);
            resp.sendRedirect("GerenciamentoIdioma.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        } catch (ParseException ex) {
            Logger.getLogger(SalvarVendedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}