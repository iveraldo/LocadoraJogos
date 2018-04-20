package DAO;

import Model.Vendedor;
import Util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VendedorDAO implements InterfaceDAO{

    @Override
    public Long inserir(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> consultarTodos(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isLoginCorreto(Vendedor vendedor) throws SQLException, ClassNotFoundException{
        Connection conexao = FabricaConexao.getConnection();
        String comando = "select id from usuario where login = ? and senha = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setString(1, vendedor.getLogin());
        stmt.setString(2, vendedor.getSenha());

        //se encontrar algum usuario, retorna true, senao, retorna falso
        return (stmt.executeQuery().next());
    }
    
}