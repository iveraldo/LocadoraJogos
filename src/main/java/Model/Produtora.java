package Model;

import java.sql.Date;

class Produtora {
    private Long id;
    private String nome;
    private Date dataFundacao;
    private double valorRendaBruta;
    private double valorLucros;
    private String mascote;
    private String siteOficial;
    private String cidadeSede;
    private String paisOrigem;
    private int qtdEstudios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public double getValorRendaBruta() {
        return valorRendaBruta;
    }

    public void setValorRendaBruta(double valorRendaBruta) {
        this.valorRendaBruta = valorRendaBruta;
    }

    public double getValorLucros() {
        return valorLucros;
    }

    public void setValorLucros(double valorLucros) {
        this.valorLucros = valorLucros;
    }

    public String getMascote() {
        return mascote;
    }

    public void setMascote(String mascote) {
        this.mascote = mascote;
    }

    public String getSiteOficial() {
        return siteOficial;
    }

    public void setSiteOficial(String siteOficial) {
        this.siteOficial = siteOficial;
    }

    public String getCidadeSede() {
        return cidadeSede;
    }

    public void setCidadeSede(String cidadeSede) {
        this.cidadeSede = cidadeSede;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public int getQtdEstudios() {
        return qtdEstudios;
    }

    public void setQtdEstudios(int qtdEstudios) {
        this.qtdEstudios = qtdEstudios;
    }
    
}