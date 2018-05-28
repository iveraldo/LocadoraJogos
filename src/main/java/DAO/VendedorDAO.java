package DAO;

import Model.Vendedor;
import Util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Object> consultar(Object object) throws SQLException, ClassNotFoundException{
        List<Object> vendedores = new ArrayList<Object>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt;
        
        String comando = "select usuario.id, usuario.nome, usuario.login, usuario.senha, vendedor.cpf, vendedor.salario"
                + " from usuario inner join vendedor on usuario.id = vendedor.id_usuario";
        
        Vendedor vendedor = (Vendedor)object;
        
        if(vendedor.getId() != null){
            comando += " where id = ?";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(1, vendedor.getId());
        } else {
            stmt = conexao.prepareStatement(comando);
        }
                 
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            vendedor = new Vendedor();
            vendedor.setId(rs.getLong("id"));
            vendedor.setNome(rs.getString("nome"));
            vendedor.setLogin(rs.getString("login"));
            vendedor.setSenha(rs.getString("senha"));
            vendedor.setCpf(rs.getString("cpf"));
            vendedor.setSalario(rs.getDouble("salario"));
            vendedores.add(vendedor);
        }
        
        //fecha a conexao
        conexao.close();
        
        return vendedores;
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
        boolean retorno = stmt.executeQuery().next();
        
        //fecha a conexao
        conexao.close();
        
        return retorno;
    }
    
}