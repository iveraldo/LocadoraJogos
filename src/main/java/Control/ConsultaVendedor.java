package Control;

import DAO.VendedorDAO;
import Model.Vendedor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultaVendedor implements ITarefa{

    @Override
    public String executar(HttpServletRequest req, HttpServletResponse resp){

        try{
            //Se for passado o filtro, consulta o vendedor e retorna a tela de manutencao
            
            if(req.getParameterMap().containsKey("filtro")){
                Long idVendedor = Long.parseLong(req.getParameter("filtro"));
                req.setAttribute("vendedores", new VendedorDAO().consultar(new Vendedor(idVendedor)));
                return "ManutencaoVendedor.jsp";

            } else { //se nao for passado filtro, consulta todos os vendedores e retorna a tela de gerenciamento
                req.setAttribute("vendedores", new VendedorDAO().consultar(new Vendedor()));
                return "GerenciamentoVendedor.jsp";
            } 
        } catch(Exception ex){
            return "falha.html";
        }

    }
    
}