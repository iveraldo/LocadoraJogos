package DAO;

import Model.Idioma;
import Model.Jogo;
import Util.FabricaConexao;
import Util.Utilitario.EnumTipoIdioma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdiomaDAO {
    public static void salvar(Idioma pIdioma) throws SQLException, ClassNotFoundException {
        int contadorParametros = 1;
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        if(pIdioma.getId() == null){
            comando = "INSERT INTO Idioma (nome, nome_original, qtd_aprox_falantes_nativos, "
                    + "qtd_aprox_falantes_estrangeiros, familia_linguistica, alfabeto, "
                    + "qtd_paises_lingua_oficial, pais_origem, data_origem) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pIdioma.getNome());
            stmt.setString(contadorParametros++, pIdioma.getNomeOriginal());
            stmt.setLong(contadorParametros++, pIdioma.getQtdAproxFalantesNativos());
            stmt.setLong(contadorParametros++, pIdioma.getQtdAproxFalantesEstrangeiros());
            stmt.setString(contadorParametros++, pIdioma.getFamiliaLinguistica());
            stmt.setString(contadorParametros++, pIdioma.getAlfabeto());
            stmt.setInt(contadorParametros++, pIdioma.getQtdPaisesLinguaOficial());
            stmt.setString(contadorParametros++, pIdioma.getPaisOrigem());
            stmt.setDate(contadorParametros++, pIdioma.getDataOrigem());
            
            //insere na tabela Idioma
            stmt.executeUpdate();

            conexao.close();
            
        } else {
            comando = "UPDATE Idioma SET "
                    + " nome = ?"
                    + ", nome_original = ?"
                    + ", qtd_aprox_falantes_nativos = ?"
                    + ", qtd_aprox_falantes_estrangeiros = ?"
                    + ", familia_linguistica = ?"
                    + ", alfabeto = ?"
                    + ", qtd_paises_lingua_oficial = ?"
                    + ", pais_origem = ?"
                    + ", data_origem = ?"
                    + " WHERE id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pIdioma.getNome());
            stmt.setString(contadorParametros++, pIdioma.getNomeOriginal());
            stmt.setLong(contadorParametros++, pIdioma.getQtdAproxFalantesNativos());
            stmt.setLong(contadorParametros++, pIdioma.getQtdAproxFalantesEstrangeiros());
            stmt.setString(contadorParametros++, pIdioma.getFamiliaLinguistica());
            stmt.setString(contadorParametros++, pIdioma.getAlfabeto());
            stmt.setInt(contadorParametros++, pIdioma.getQtdPaisesLinguaOficial());
            stmt.setString(contadorParametros++, pIdioma.getPaisOrigem());
            stmt.setDate(contadorParametros++, pIdioma.getDataOrigem());
            stmt.setLong(contadorParametros++, pIdioma.getId());
            
            stmt.executeUpdate();
            
            conexao.close();
        }
    }

    public static List<Idioma> consultar(Idioma pIdioma) throws SQLException, ClassNotFoundException{
        int contadorParametros = 1;
        List<Idioma> idiomas = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT id, nome, nome_original, qtd_aprox_falantes_nativos, "
                    + "qtd_aprox_falantes_estrangeiros, familia_linguistica, alfabeto, "
                    + "qtd_paises_lingua_oficial, pais_origem, data_origem FROM Idioma "
                    + "WHERE 1 = 1 ";
        
        if(pIdioma.getId() != null){
            comando += "AND id = ? ";
        }
        
        stmt = conexao.prepareStatement(comando);
        
        if(pIdioma.getId() != null){
            stmt.setLong(contadorParametros++, pIdioma.getId());
        }
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            idiomas.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return idiomas;
    }
    
    public static List<Idioma> consultarAudiosLegendas(Jogo pJogo, EnumTipoIdioma pTipoIdioma) throws SQLException, ClassNotFoundException{
        List<Idioma> idiomas = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT Idioma.id, nome, nome_original, qtd_aprox_falantes_nativos, "
                    + "qtd_aprox_falantes_estrangeiros, familia_linguistica, alfabeto, "
                    + "qtd_paises_lingua_oficial, pais_origem, data_origem "
                    + "FROM Idioma INNER JOIN " + pTipoIdioma.toString() + " ON Idioma.id = " + pTipoIdioma.toString() + ".id_idioma "
                    + "WHERE id_jogo = ?";
        
        
        stmt = conexao.prepareStatement(comando);

        stmt.setLong(1, pJogo.getId());
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            idiomas.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return idiomas;
    }

    public static void excluir(Idioma pIdioma) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        comando = "DELETE FROM Idioma WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pIdioma.getId());
        
        //Exclui da tabela Idioma
        stmt.executeUpdate();
    }
    
    private static Idioma montarObjeto(ResultSet rs) throws SQLException{
            Idioma idioma = new Idioma();
            idioma.setId(rs.getLong("id"));
            idioma.setNome(rs.getString("nome"));
            idioma.setNomeOriginal(rs.getString("nome_original"));
            idioma.setQtdAproxFalantesNativos(rs.getLong("qtd_aprox_falantes_nativos"));
            idioma.setQtdAproxFalantesEstrangeiros(rs.getLong("qtd_aprox_falantes_estrangeiros"));
            idioma.setFamiliaLinguistica(rs.getString("familia_linguistica"));
            idioma.setAlfabeto(rs.getString("alfabeto"));
            idioma.setQtdPaisesLinguaOficial(rs.getInt("qtd_paises_lingua_oficial"));
            idioma.setPaisOrigem(rs.getString("pais_origem"));
            idioma.setDataOrigem(rs.getDate("data_origem"));
            return idioma;
    }
}