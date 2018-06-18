package Model;

import java.util.ArrayList;
import java.util.List;
import Util.Utilitario.EnumClassificacao;
import Util.Utilitario.EnumGeneroJogo;
import java.sql.Date;

public class Jogo {
    private Long id;
    private String titulo;
    private int qtd;
    private int qtdDisponivel;
    private int qtdMaxJogadores;
    private double tamanhoGB;
    private EnumGeneroJogo genero;
    private EnumClassificacao classificacao;
    private List<Idioma> audios;
    private List<Idioma> legendas;
    private Produtora produtora;
    private double valorJogo;
    private double valorLocacao;
    private Date dataLancamento;
    private boolean apenasComEstoque;
    
    public Jogo(){
        audios = new ArrayList<>();
        legendas = new ArrayList<>();
        apenasComEstoque = false;
    }
    
    public Jogo(Long id){
        this();
        this.id = id;
    }
    
    public Jogo(boolean apenasComEstoque){
        this();
        this.apenasComEstoque = apenasComEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public int getQtdMaxJogadores() {
        return qtdMaxJogadores;
    }

    public void setQtdMaxJogadores(int qtdMaxJogadores) {
        this.qtdMaxJogadores = qtdMaxJogadores;
    }

    public double getTamanhoGB() {
        return tamanhoGB;
    }

    public void setTamanhoGB(double tamanhoGB) {
        this.tamanhoGB = tamanhoGB;
    }

    public EnumGeneroJogo getGenero() {
        return genero;
    }

    public void setGenero(EnumGeneroJogo genero) {
        this.genero = genero;
    }

    public EnumClassificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(EnumClassificacao classificacao) {
        this.classificacao = classificacao;
    }

    public List<Idioma> getAudios() {
        return audios;
    }
    
    public List<Long> getIdAudios(){
        List<Long> idsAudios = new ArrayList<>();
        
        for(Idioma audio: audios){
            idsAudios.add(audio.getId());
        }
        
        return idsAudios;
    }

    public void adicionarAudio(Idioma audio) {
        this.audios.add(audio);
    }
    
    public void setAudio(List<Idioma> audios) {
        this.audios = audios;
    }
    
    public void removerAudio(Idioma audio){
        this.audios.remove(audio);
    }
    
    public void limparAudios(){
        this.audios.removeAll(audios);
    }

    public List<Idioma> getLegendas() {
        return legendas;
    }
    
    public List<Long> getIdLegendas(){
        List<Long> idsLegendas = new ArrayList<>();
        
        for(Idioma legenda: legendas){
            idsLegendas.add(legenda.getId());
        }
        
        return idsLegendas;
    }

    public void adicionarLegenda(Idioma legenda) {
        this.legendas.add(legenda);
    }
    
    public void setLegenda(List<Idioma> legendas) {
        this.legendas = legendas;
    }
    
    public void removerLegenda(Idioma legenda){
        this.legendas.remove(legenda);
    }
    
    public void limparLegendas(){
        this.legendas.removeAll(legendas);
    }

    public Produtora getProdutora() {
        return produtora;
    }

    public void setProdutora(Produtora produtora) {
        this.produtora = produtora;
    }

    public double getValorJogo() {
        return valorJogo;
    }

    public void setValorJogo(double valor) {
        this.valorJogo = valor;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public boolean isApenasComEstoque() {
        return apenasComEstoque;
    }

    public void setApenasComEstoque(boolean apenasComEstoque) {
        this.apenasComEstoque = apenasComEstoque;
    }
    
}