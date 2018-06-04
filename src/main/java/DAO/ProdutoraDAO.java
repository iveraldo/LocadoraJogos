package DAO;

import Model.Produtora;
import Util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoraDAO {
    public static void salvar(Produtora pProdutora) throws SQLException, ClassNotFoundException {
        int contadorParametros = 1;
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        if(pProdutora.getId() == null){
            comando = "INSERT INTO Produtora (nome, data_fundacao, valor_renda_bruta, "
                    + "valor_lucros, mascote, site_oficial, "
                    + "cidade_sede, pais_origem, qtd_estudios) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pProdutora.getNome());
            stmt.setDate(contadorParametros++, pProdutora.getDataFundacao());
            stmt.setDouble(contadorParametros++, pProdutora.getValorRendaBruta());
            stmt.setDouble(contadorParametros++, pProdutora.getValorLucros());
            stmt.setString(contadorParametros++, pProdutora.getMascote());
            stmt.setString(contadorParametros++, pProdutora.getSiteOficial());
            stmt.setString(contadorParametros++, pProdutora.getCidadeSede());
            stmt.setString(contadorParametros++, pProdutora.getPaisOrigem());
            stmt.setInt(contadorParametros++, pProdutora.getQtdEstudios());
            
            //insere na tabela Produtora
            stmt.executeUpdate();

            conexao.close();
            
        } else {
            comando = "UPDATE Produtora SET "
                    + " nome = ?"
                    + ", data_fundacao = ?"
                    + ", valor_renda_bruta = ?"
                    + ", valor_lucros = ?"
                    + ", mascote = ?"
                    + ", site_oficial = ?"
                    + ", cidade_sede = ?"
                    + ", pais_origem = ?"
                    + ", qtd_estudios = ?"
                    + " WHERE id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pProdutora.getNome());
            stmt.setDate(contadorParametros++, pProdutora.getDataFundacao());
            stmt.setDouble(contadorParametros++, pProdutora.getValorRendaBruta());
            stmt.setDouble(contadorParametros++, pProdutora.getValorLucros());
            stmt.setString(contadorParametros++, pProdutora.getMascote());
            stmt.setString(contadorParametros++, pProdutora.getSiteOficial());
            stmt.setString(contadorParametros++, pProdutora.getCidadeSede());
            stmt.setString(contadorParametros++, pProdutora.getPaisOrigem());
            stmt.setInt(contadorParametros++, pProdutora.getQtdEstudios());
            stmt.setLong(contadorParametros++, pProdutora.getId());
            
            stmt.executeUpdate();
            
            conexao.close();
        }
    }

    public static List<Produtora> consultar(Produtora pProdutora) throws SQLException, ClassNotFoundException{
        int contadorParametros = 1;
        List<Produtora> produtoras = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT id, nome, data_fundacao, valor_renda_bruta, "
                    + "valor_lucros, mascote, site_oficial, "
                    + "cidade_sede, pais_origem, qtd_estudios FROM Produtora "
                    + "WHERE 1 = 1 ";
        
        if(pProdutora.getId() != null){
            comando += "AND id = ? ";
        }
        
        stmt = conexao.prepareStatement(comando);
        
        if(pProdutora.getId() != null){
            stmt.setLong(contadorParametros++, pProdutora.getId());
        }
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            produtoras.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return produtoras;
    }

    public static void excluir(Produtora pProdutora) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        comando = "DELETE FROM Produtora WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pProdutora.getId());
        
        //Exclui da tabela Produtora
        stmt.executeUpdate();
    }
    
    private static Produtora montarObjeto(ResultSet rs) throws SQLException{
            Produtora produtora = new Produtora();
            produtora.setId(rs.getLong("id"));
            produtora.setNome(rs.getString("nome"));
            produtora.setDataFundacao(rs.getDate("data_fundacao"));
            produtora.setValorRendaBruta(rs.getDouble("valor_renda_bruta"));
            produtora.setValorLucros(rs.getDouble("valor_lucros"));
            produtora.setMascote(rs.getString("mascote"));
            produtora.setSiteOficial(rs.getString("site_oficial"));
            produtora.setCidadeSede(rs.getString("cidade_sede"));
            produtora.setPaisOrigem(rs.getString("pais_origem"));
            produtora.setQtdEstudios(rs.getInt("qtd_estudios"));
            return produtora;
    }
}