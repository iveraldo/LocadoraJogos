package DAO;

import Model.Vendedor;
import Util.FabricaConexao;
import Util.Utilitario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {

    public static void salvar(Vendedor pVendedor) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        int contadorParametros = 1;
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        if(pVendedor.getId() == null){
            comando = "INSERT INTO Usuario (nome, login, senha, ativo) VALUES(?, ?, ?, ?);";
            
            PreparedStatement stmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(contadorParametros++, pVendedor.getNome());
            stmt.setString(contadorParametros++, pVendedor.getLogin());
            stmt.setString(contadorParametros++, Utilitario.gerarHash(pVendedor.getSenha()));
            stmt.setBoolean(contadorParametros++, pVendedor.getIsAtivo());
            
            //insere na tabela usuario
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            //recupera o id gerado na tabela usuario
            
            pVendedor.setId(rs.getLong(1));
                        
            //zera o contador de parametros
            contadorParametros = 1;
            
            comando = "INSERT INTO Vendedor (id_usuario, cpf, salario, percentual_comissao, data_admissao) "
                    + "VALUES (?, ?, ?, ?, ?);";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(contadorParametros++, pVendedor.getId());
            stmt.setString(contadorParametros++, pVendedor.getCpf());
            stmt.setDouble(contadorParametros++, pVendedor.getSalario());
            stmt.setInt(contadorParametros++, pVendedor.getPercentualComissao());
            stmt.setDate(contadorParametros++, pVendedor.getDataAdmissao());
            
            //insere na tabela vendedor
            stmt.executeUpdate();
            
            conexao.close();
            
        } else {
            comando = "UPDATE Usuario"
                    + " INNER JOIN Vendedor ON usuario.id = Vendedor.id_usuario SET"
                    + " Usuario.nome = ?"
                    + ", Usuario.login = ?"
                    + ", Usuario.ativo = ?"
                    + ", Vendedor.cpf = ?"
                    + ", Vendedor.salario = ?"
                    + ", Vendedor.percentual_comissao = ?"
                    + ", Vendedor.data_admissao = ?"
                    + ", Vendedor.data_demissao = ?";
            
            if(!pVendedor.getSenha().equals("")){
                comando += ", Usuario.senha = ?";
            }
            
            comando+= " WHERE Usuario.id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pVendedor.getNome());
            stmt.setString(contadorParametros++, pVendedor.getLogin());
            stmt.setBoolean(contadorParametros++, pVendedor.getIsAtivo());
            stmt.setString(contadorParametros++, pVendedor.getCpf());
            stmt.setDouble(contadorParametros++, pVendedor.getSalario());
            stmt.setInt(contadorParametros++, pVendedor.getPercentualComissao());
            stmt.setDate(contadorParametros++, pVendedor.getDataAdmissao());
            stmt.setDate(contadorParametros++, pVendedor.getDataDemissao());
            
            if(!pVendedor.getSenha().equals("")){
                stmt.setString(contadorParametros++, Utilitario.gerarHash(pVendedor.getSenha()));
            }
            
            stmt.setLong(contadorParametros++, pVendedor.getId());
            
            stmt.executeUpdate();
            
            conexao.close();
        }
    }

    public static List<Vendedor> consultar(Vendedor pVendedor) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException{
        int contadorParametros = 1;
        List<Vendedor> vendedores = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT Usuario.id, Usuario.nome, Usuario.login, Usuario.senha, Usuario.ativo"
                + ", Vendedor.cpf, Vendedor.salario, Vendedor.percentual_comissao"
                + ", Vendedor.data_admissao, Vendedor.data_demissao "
                + "FROM Usuario INNER JOIN Vendedor ON Usuario.id = Vendedor.id_usuario "
                + "WHERE 1 = 1 ";
        
        if(pVendedor.getId() != null){
            comando += "AND Usuario.id = ? ";
        }
            
        if(pVendedor.getLogin() != null){
            comando += "AND Usuario.login = ? ";
        }
        
        if(pVendedor.getSenha() != null){
            comando += "AND Usuario.senha = ? AND Usuario.ativo = 1";
        }
        
        stmt = conexao.prepareStatement(comando);
        
        if(pVendedor.getId() != null){
            stmt.setLong(contadorParametros++, pVendedor.getId());
        }
            
        if(pVendedor.getLogin() != null){
            stmt.setString(contadorParametros++, pVendedor.getLogin());
        }
        
        if(pVendedor.getSenha() != null){
            stmt.setString(contadorParametros++, Utilitario.gerarHash(pVendedor.getSenha()));
        }
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            vendedores.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return vendedores;
    }

    public static void excluir(Vendedor pVendedor) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        comando = "DELETE FROM Vendedor WHERE id_usuario = ?";
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pVendedor.getId());
        
        //Exclui da tabela Vendedor
        stmt.executeUpdate();
        
        comando = "DELETE FROM Usuario WHERE id = ?";
        stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pVendedor.getId());
        
        //Exclui da tabela Usuario
        stmt.executeUpdate();
    }
    
    private static Vendedor montarObjeto(ResultSet rs) throws SQLException{
            Vendedor vendedor = new Vendedor();
            vendedor.setId(rs.getLong("id"));
            vendedor.setNome(rs.getString("nome"));
            vendedor.setLogin(rs.getString("login"));
            vendedor.setSenha(rs.getString("senha"));
            vendedor.setAtivo(rs.getBoolean("ativo"));
            vendedor.setCpf(rs.getString("cpf"));
            vendedor.setSalario(rs.getDouble("salario"));
            vendedor.setPercentualComissao(rs.getInt("percentual_comissao"));
            vendedor.setDataAdmissao(rs.getDate("data_admissao"));
            vendedor.setDataDemissao(rs.getDate("data_demissao"));
            return vendedor;
    }
    
}