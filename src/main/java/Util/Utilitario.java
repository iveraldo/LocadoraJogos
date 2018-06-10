package Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilitario {
    
    public enum EnumGeneroJogo {
        Acao
        ,Aventura
        ,Estrategia
        ,Rpg
        ,Esporte
        ,Corrida
        ,Simulacao
        ,Plataforma
        ,Terror
    }
    
    public enum EnumClassificacao{
        Livre
        ,Doze
        ,Catorze
        ,Dezesseis
        ,Dezoito
    }
    
    public enum EnumEstado{
        AC
        ,AL
        ,AP
        ,AM
        ,BA
        ,CE
        ,DF
        ,ES
        ,GO
        ,MA
        ,MT
        ,MS
        ,MG
        ,PA
        ,PB
        ,PR
        ,PE
        ,PI
        ,RJ
        ,RN
        ,RS
        ,RO
        ,RR
        ,SC
        ,SP
        ,SE
        ,TO
    }
    
    public enum EnumFormaPagamento{
        Dinheiro
        ,CartaoCredito
        ,CartaoDebito
        ,Cheque
    }
    
    public enum EnumSexo{
        Masculino
        ,Feminino
    }
    
    public enum EnumAcao{
        Cadastro
        ,Alteracao
        ,Consulta
        ,Exclusao
    }
    
    public enum EnumTipoIdioma{
        Jogo_Audio
        ,Jogo_Legenda
    }
    
    public static String gerarHash(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //byte[] bytesPalavra = palavra.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(senha.getBytes());
        byte[] bytesPalavraCriptograda = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytesPalavraCriptograda.length; i++) {
            sb.append(Integer.toString((bytesPalavraCriptograda[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String retorno = sb.toString();
        
        return retorno;
    }
}