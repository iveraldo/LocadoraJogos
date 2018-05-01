package Model;

import java.util.ArrayList;
import java.util.List;
import Util.Utilitario;
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
    private Utilitario.EnumClassificacao enumClassificacao;
    private List<Idioma> audios;
    private List<Idioma> legendas;
    private Produtora produtora;
    private double valorJogo;
    private double valorLocacao;
    private Date dataLancamento;
    
    public Jogo(){
        audios = new ArrayList<>();
        legendas = new ArrayList<>();
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

    public Utilitario.EnumClassificacao getEnumClassificacao() {
        return enumClassificacao;
    }

    public void setEnumClassificacao(Utilitario.EnumClassificacao enumClassificacao) {
        this.enumClassificacao = enumClassificacao;
    }

    public List<Idioma> getAudios() {
        return audios;
    }

    public void adicionarAudio(Idioma audio) {
        this.audios.add(audio);
    }
    
    public void removerAudio(Idioma audio){
        this.audios.remove(audio);
    }

    public List<Idioma> getLegendas() {
        return legendas;
    }

    public void adicionarLegenda(Idioma legenda) {
        this.legendas.add(legenda);
    }
    
    public void removerLegenda(Idioma legenda){
        this.legendas.remove(legenda);
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
    
}