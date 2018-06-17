package Control;

import DAO.JogoDAO;
import Model.Idioma;
import Model.Jogo;
import Model.Produtora;
import Util.Utilitario.EnumClassificacao;
import Util.Utilitario.EnumGeneroJogo;
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

@WebServlet("/salvarJogo")
public class SalvarJogoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            Jogo jogo = new Jogo();
            jogo.setTitulo(req.getParameter("titulo"));
            jogo.setQtd(Integer.parseInt(req.getParameter("qtd")));
            jogo.setQtdMaxJogadores(Integer.parseInt(req.getParameter("qtdMaxJogadores")));
            jogo.setTamanhoGB(Double.parseDouble(req.getParameter("tamanhoGB")));
            jogo.setGenero(EnumGeneroJogo.valueOf(req.getParameter("genero")));
            jogo.setClassificacao(EnumClassificacao.valueOf(req.getParameter("classificacao")));
            jogo.setProdutora(new Produtora(Long.parseLong(req.getParameter("produtora"))));
            jogo.setValorJogo(Double.parseDouble(req.getParameter("valorJogo")));
            jogo.setValorLocacao(Double.parseDouble(req.getParameter("valorLocacao")));
            
            String[] audios = req.getParameterValues("audios");
            if(audios != null){
                for(String idAudio : audios){
                    //adiciona os audios
                    jogo.adicionarAudio(new Idioma(Long.parseLong(idAudio)));
                }
            }

            String[] legendas = req.getParameterValues("legendas");
            if(legendas != null){
               for(String idLegenda : legendas){
                    //adiciona as legendas
                    jogo.adicionarLegenda(new Idioma(Long.parseLong(idLegenda)));
                } 
            }
            
            String dataString = req.getParameter("dataLancamento");
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dataLancamento = new Date(fmt.parse(dataString).getTime());
            jogo.setDataLancamento(dataLancamento);

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                jogo.setId(Long.parseLong(req.getParameter("id")));
                jogo.setQtdDisponivel(Integer.parseInt(req.getParameter("qtdDisponivel")));
            } else {
                jogo.setQtdDisponivel(jogo.getQtd());
            }
        
            JogoDAO.salvar(jogo);
            resp.sendRedirect("GerenciamentoJogo.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        } catch (ParseException ex) {
            Logger.getLogger(SalvarVendedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}