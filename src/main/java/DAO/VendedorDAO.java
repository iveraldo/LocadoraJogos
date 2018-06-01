package DAO;

import Model.Vendedor;
import Util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {

    public static void salvar(Vendedor pVendedor) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        if(pVendedor.getId() == null){
            comando = "INSERT INTO Usuario (nome, login, senha) VALUES(?, ?, ?);";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, pVendedor.getNome());
            stmt.setString(2, pVendedor.getLogin());
            stmt.setString(3, pVendedor.getSenha());
            
            //insere na tabela usuario
            stmt.execute(comando);
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            //recupera o id gerado na tabela usuario
            
            pVendedor.setId(rs.getLong(1));
                        
            comando = "INSERT INTO Vendedor (id_usuario, cpf, salario) VALUES (?, ?, ?);";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(1, pVendedor.getId());
            stmt.setString(2, pVendedor.getCpf());
            stmt.setDouble(3, pVendedor.getSalario());
            
            //insere na tabela vendedor
            stmt.executeUpdate(comando);
            
            conexao.close();
            
        } else {
            comando = "UPDATE Usuario" +
                    "INNER JOIN Vendedor ON usuario.id = Vendedor.id_usuario" +
                    "SET Usuario.nome = ?, Usuario.login = ?, " +
                    "Usuario.senha = ?, Vendedor.cpf = ?, Vendedor.salario = ?" +
                    "WHERE Usuario.id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt = conexao.prepareStatement(comando);
            stmt.setString(1, pVendedor.getNome());
            stmt.setString(2, pVendedor.getLogin());
            stmt.setString(3, pVendedor.getSenha());
            stmt.setString(4, pVendedor.getCpf());
            stmt.setDouble(5, pVendedor.getSalario());
            stmt.setLong(6, pVendedor.getId());
            
            stmt.executeUpdate();
            
            conexao.close();
        }
    }

    public static List<Vendedor> consultar(Vendedor pVendedor) throws SQLException, ClassNotFoundException{
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt;
        
        String comando = "select usuario.id, usuario.nome, usuario.login, usuario.senha, vendedor.cpf, vendedor.salario"
                + " from usuario inner join vendedor on usuario.id = vendedor.id_usuario";
        
        if(pVendedor.getId() != null){
            comando += " where usuario.id = ?";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(1, pVendedor.getId());
        } else {
            stmt = conexao.prepareStatement(comando);
        }
                 
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            vendedores.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return vendedores;
    }

    public static void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Vendedor validarLogin(Vendedor vendedor) throws SQLException, ClassNotFoundException{
        Connection conexao = FabricaConexao.getConnection();
        String comando = "select usuario.id, usuario.nome, usuario.login, usuario.senha, vendedor.cpf, vendedor.salario"
                + " from usuario inner join vendedor on usuario.id = vendedor.id_usuario"
                + " where login = ? and senha = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setString(1, vendedor.getLogin());
        stmt.setString(2, vendedor.getSenha());

        ResultSet rs = stmt.executeQuery();
        
        //Se encontrar o vendedor, retorna ele. Caso contrario, retorna null
        if(rs.next()){
            vendedor = montarObjeto(rs);
        } else {
            vendedor = null;
        }
        
        //fecha a conexao
        conexao.close();
        
        return vendedor;
    }
    
    private static Vendedor montarObjeto(ResultSet rs) throws SQLException{
            Vendedor vendedor = new Vendedor();
            vendedor.setId(rs.getLong("id"));
            vendedor.setNome(rs.getString("nome"));
            vendedor.setLogin(rs.getString("login"));
            vendedor.setSenha(rs.getString("senha"));
            vendedor.setCpf(rs.getString("cpf"));
            vendedor.setSalario(rs.getDouble("salario"));
            return vendedor;
    }
    
}