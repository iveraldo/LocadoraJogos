package DAO;

import Model.Jogo;
import Model.Idioma;
import Model.Produtora;
import Util.FabricaConexao;
import Util.Utilitario;
import Util.Utilitario.EnumClassificacao;
import Util.Utilitario.EnumGeneroJogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {

    public static void salvar(Jogo pJogo) throws SQLException, ClassNotFoundException {
        int contadorParametros = 1;
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        if(pJogo.getId() == null){
            comando = "INSERT INTO Jogo (titulo, qtd, qtd_disponivel, qtd_max_jogadores, tamanho_GB, genero"
                    + ", classificao, id_produtora, valor_jogo, valor_locacao, data_lancamento) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            stmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
            
        } else {
            comando = "UPDATE Jogo SET"
                    + " titulo = ?"
                    + ", qtd = ?"
                    + ", qtd_disponivel = ?"
                    + ", qtd_max_jogadores = ?"
                    + ", tamanho_GB = ?"
                    + ", genero = ?"
                    + ", classificao = ?"
                    + ", id_produtora = ?"
                    + ", valor_jogo = ?"
                    + ", valor_locacao = ?"
                    + ", data_lancamento = ?"
                    + " WHERE id = ?";
            stmt = conexao.prepareStatement(comando);
        }

        stmt.setString(contadorParametros++, pJogo.getTitulo());
        stmt.setInt(contadorParametros++, pJogo.getQtd());
        stmt.setInt(contadorParametros++, pJogo.getQtdDisponivel());
        stmt.setInt(contadorParametros++, pJogo.getQtdMaxJogadores());
        stmt.setDouble(contadorParametros++, pJogo.getTamanhoGB());
        stmt.setString(contadorParametros++, pJogo.getGenero().toString());
        stmt.setString(contadorParametros++, pJogo.getClassificacao().toString());
        stmt.setLong(contadorParametros++, pJogo.getProdutora().getId());
        stmt.setDouble(contadorParametros++, pJogo.getValorJogo());
        stmt.setDouble(contadorParametros++, pJogo.getValorLocacao());
        stmt.setDate(contadorParametros++, pJogo.getDataLancamento());

        //passa o id para ALTERACAO
        if(pJogo.getId() != null) {
            stmt.setLong(contadorParametros++, pJogo.getId());
        }
        
        //salva na tabela jogo
        stmt.executeUpdate();

        //recupera o id gerado na tabela jogo para INCLUSAO
        if(pJogo.getId() == null){
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            pJogo.setId(rs.getLong(1));
            
        } else {
            //Exclui os audios vinculados ao jogo
            contadorParametros = 1;
            comando = "DELETE FROM Jogo_Audio WHERE id_jogo = ?";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(contadorParametros++, pJogo.getId());
            stmt.executeUpdate();
            
            //Exclui as legendas vinculadas ao jogo
            contadorParametros = 1;
            comando = "DELETE FROM Jogo_Legenda WHERE id_jogo = ?";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(contadorParametros++, pJogo.getId());
            stmt.executeUpdate();
        }

        for (Idioma audio : pJogo.getAudios()) {
            //zera o contador de parametros
            contadorParametros = 1;
            
            comando = "INSERT INTO Jogo_Audio (id_jogo, id_idioma) "
            + "VALUES (?, ?);";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(contadorParametros++, pJogo.getId());
            stmt.setLong(contadorParametros++, audio.getId());

            //insere o audio na tabela Idioma
            stmt.executeUpdate();
        }

        for (Idioma legenda : pJogo.getLegendas()) {
            //zera o contador de parametros
            contadorParametros = 1;
        
            comando = "INSERT INTO Jogo_Legenda (id_jogo, id_idioma) "
            + "VALUES (?, ?);";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(contadorParametros++, pJogo.getId());
            stmt.setLong(contadorParametros++, legenda.getId());

            //insere a legenda na tabela Idioma
            stmt.executeUpdate();
        }

        conexao.close();
            
    }

    public static List<Jogo> consultar(Jogo pJogo) throws SQLException, ClassNotFoundException{
        int contadorParametros = 1;
        List<Jogo> jogos = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT id, titulo, qtd, qtd_disponivel, qtd_max_jogadores, tamanho_GB, genero"
                        + ", classificao, id_produtora, valor_jogo, valor_locacao, data_lancamento"
                        + " FROM Jogo WHERE 1 = 1 ";
        
        if(pJogo.getId() != null){
            comando += "AND Jogo.id = ? ";
        }
        
        stmt = conexao.prepareStatement(comando);
        
        if(pJogo.getId() != null){
            stmt.setLong(contadorParametros++, pJogo.getId());
        }
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            jogos.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return jogos;
    }

    public static void excluir(Jogo pJogo) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        //Exclui os audios vinculados ao jogo
        comando = "DELETE FROM Jogo_Audio WHERE id_jogo = ?";
        stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pJogo.getId());
        stmt.executeUpdate();

        //Exclui as legendas vinculadas ao jogo
        comando = "DELETE FROM Jogo_Legenda WHERE id_jogo = ?";
        stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pJogo.getId());
        stmt.executeUpdate();
        
        //Exclui da tabela Jogo
        comando = "DELETE FROM Jogo WHERE id = ?";
        stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pJogo.getId());
        stmt.executeUpdate();
    }
    
    private static Jogo montarObjeto(ResultSet rs) throws SQLException, ClassNotFoundException{
            Jogo jogo = new Jogo();
                
            //Monta o objeto jogo
            jogo.setId(rs.getLong("id"));
            jogo.setTitulo(rs.getString("titulo"));
            jogo.setQtd(rs.getInt("qtd"));
            jogo.setQtdDisponivel(rs.getInt("qtd_disponivel"));
            jogo.setQtdMaxJogadores(rs.getInt("qtd_max_jogadores"));
            jogo.setTamanhoGB(rs.getDouble("tamanho_GB"));
            jogo.setGenero(EnumGeneroJogo.valueOf(rs.getString("genero")));
            jogo.setClassificacao(EnumClassificacao.valueOf(rs.getString("classificao")));
            jogo.setProdutora(new Produtora(rs.getLong("id_produtora")));
            jogo.setValorJogo(rs.getDouble("valor_jogo"));
            jogo.setValorLocacao(rs.getDouble("valor_locacao"));
            jogo.setDataLancamento(rs.getDate("data_lancamento"));
            
            jogo.setAudio(IdiomaDAO.consultarAudiosLegendas(jogo, Utilitario.EnumTipoIdioma.Jogo_Audio));
            jogo.setLegenda(IdiomaDAO.consultarAudiosLegendas(jogo, Utilitario.EnumTipoIdioma.Jogo_Legenda));
            
            return jogo;
    }
}