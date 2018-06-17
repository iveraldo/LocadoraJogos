package Control;

import DAO.PedidoLocacaoDAO;
import Model.Cliente;
import Model.PedidoLocacao;
import Model.Vendedor;
import Util.Utilitario;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/salvarPedidoLocacao")
public class SalvarPedidoLocacaoServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            PedidoLocacao pedidoLocacao = new PedidoLocacao();
                
            //Monta o objeto pedidoLocacao
            pedidoLocacao.setValorLocacao(Double.parseDouble(req.getParameter("valorPedido")));
            pedidoLocacao.setFormaPagamento(Utilitario.EnumFormaPagamento.valueOf(req.getParameter("formaPagamento")));
            pedidoLocacao.setCliente(new Cliente(Long.parseLong(req.getParameter("cliente"))));
            pedidoLocacao.setVendedor(new Vendedor(Long.parseLong(req.getParameter("idVendedor"))));
            pedidoLocacao.setObs(req.getParameter("obs"));
            pedidoLocacao.setCupom(req.getParameter("cupom"));
            pedidoLocacao.setDevolvido(false);
            
            Date hoje = new java.sql.Date(new java.util.Date().getTime());
                        
            pedidoLocacao.setDataPedido(hoje);

            Date deHojeA8 = new java.sql.Date(hoje.getTime() + ( (7) * 24*60*60*1000) );
            
            pedidoLocacao.setDataDevolucao(deHojeA8);
            
            Object obj = req.getParameterValues("jogosSelecionados");

            //ALTERACAO
            if(!req.getParameter("id").isEmpty()) {
                pedidoLocacao.setId(Long.parseLong(req.getParameter("id")));
            }
            
            PedidoLocacaoDAO.salvar(pedidoLocacao);
            resp.sendRedirect("GerenciamentoPedidoLocacao.jsp");
        } catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            System.out.print(ex);
            resp.sendRedirect("falha.html");
        }
    }
}