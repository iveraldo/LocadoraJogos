package DAO;

import Model.Plataforma;
import Util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlataformaDAO {
    public static void salvar(Plataforma pPlataforma) throws SQLException, ClassNotFoundException {
        int contadorParametros = 1;
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        if(pPlataforma.getId() == null){
            comando = "INSERT INTO Plataforma (descricao, data_criacao, fabricante, "
                    + "unidades_vendidas, numero_geracao, midia, "
                    + "site_oficial, preco_lancamento, sistema_operacional) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pPlataforma.getDescricao());
            stmt.setDate(contadorParametros++, pPlataforma.getDataCriacao());
            stmt.setString(contadorParametros++, pPlataforma.getFabricante());
            stmt.setLong(contadorParametros++, pPlataforma.getUnidadesVendidas());
            stmt.setInt(contadorParametros++, pPlataforma.getNumeroGeracao());
            stmt.setString(contadorParametros++, pPlataforma.getMidia());
            stmt.setString(contadorParametros++, pPlataforma.getSiteOficial());
            stmt.setDouble(contadorParametros++, pPlataforma.getPrecoLancamento());
            stmt.setString(contadorParametros++, pPlataforma.getSiteOficial());
            
            //insere na tabela Plataforma
            stmt.executeUpdate();

            conexao.close();
            
        } else {
            comando = "UPDATE Plataforma SET "
                    + " descricao = ?"
                    + ", data_criacao = ?"
                    + ", fabricante = ?"
                    + ", unidades_vendidas = ?"
                    + ", numero_geracao = ?"
                    + ", midia = ?"
                    + ", site_oficial = ?"
                    + ", preco_lancamento = ?"
                    + ", sistema_operacional = ?"
                    + " WHERE id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pPlataforma.getDescricao());
            stmt.setDate(contadorParametros++, pPlataforma.getDataCriacao());
            stmt.setString(contadorParametros++, pPlataforma.getFabricante());
            stmt.setLong(contadorParametros++, pPlataforma.getUnidadesVendidas());
            stmt.setInt(contadorParametros++, pPlataforma.getNumeroGeracao());
            stmt.setString(contadorParametros++, pPlataforma.getMidia());
            stmt.setString(contadorParametros++, pPlataforma.getSiteOficial());
            stmt.setDouble(contadorParametros++, pPlataforma.getPrecoLancamento());
            stmt.setString(contadorParametros++, pPlataforma.getSiteOficial());
            stmt.setLong(contadorParametros++, pPlataforma.getId());
            
            stmt.executeUpdate();
            
            conexao.close();
        }
    }

    public static List<Plataforma> consultar(Plataforma pPlataforma) throws SQLException, ClassNotFoundException{
        int contadorParametros = 1;
        List<Plataforma> plataformas = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT id, descricao, data_criacao, fabricante, "
                    + "unidades_vendidas, numero_geracao, midia, "
                    + "site_oficial, preco_lancamento, sistema_operacional FROM Plataforma "
                    + "WHERE 1 = 1 ";
        
        if(pPlataforma.getId() != null){
            comando += "AND id = ? ";
        }
        
        stmt = conexao.prepareStatement(comando);
        
        if(pPlataforma.getId() != null){
            stmt.setLong(contadorParametros++, pPlataforma.getId());
        }
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            plataformas.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return plataformas;
    }

    public static void excluir(Plataforma pPlataforma) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        comando = "DELETE FROM Plataforma WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pPlataforma.getId());
        
        //Exclui da tabela Plataforma
        stmt.executeUpdate();
    }
    
    private static Plataforma montarObjeto(ResultSet rs) throws SQLException{
            Plataforma plataforma = new Plataforma();
            plataforma.setId(rs.getLong("id"));
            plataforma.setDescricao(rs.getString("descricao"));
            plataforma.setDataCriacao(rs.getDate("data_criacao"));
            plataforma.setFabricante(rs.getString("fabricante"));
            plataforma.setUnidadesVendidas(rs.getLong("unidades_vendidas"));
            plataforma.setNumeroGeracao(rs.getInt("numero_geracao"));
            plataforma.setMidia(rs.getString("midia"));
            plataforma.setSiteOficial(rs.getString("site_oficial"));
            plataforma.setPrecoLancamento(rs.getDouble("preco_lancamento"));
            plataforma.setSistemaOperacional(rs.getString("sistema_operacional"));
            return plataforma;
    }
}