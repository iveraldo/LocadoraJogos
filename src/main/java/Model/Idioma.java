package Model;

import java.sql.Date;

public class Idioma {
    private Long id;
    private String nome;
    private String nomeOriginal;
    private long qtdAproxFalantesNativos;
    private long qtdAproxFalantesEstrangeiros;
    private String familiaLinguistica;
    private String alfabeto;
    private int qtdPaisesLinguaOficial;
    private String paisOrigem;
    private Date dataOrigem;
    
    public Idioma(){}
    
    public Idioma(Long id){
        this.setId(id);
    }

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

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public long getQtdAproxFalantesNativos() {
        return qtdAproxFalantesNativos;
    }

    public void setQtdAproxFalantesNativos(long qtdAproxFalantesNativos) {
        this.qtdAproxFalantesNativos = qtdAproxFalantesNativos;
    }

    public long getQtdAproxFalantesEstrangeiros() {
        return qtdAproxFalantesEstrangeiros;
    }

    public void setQtdAproxFalantesEstrangeiros(long qtdAproxFalantesEstrangeiros) {
        this.qtdAproxFalantesEstrangeiros = qtdAproxFalantesEstrangeiros;
    }

    public String getFamiliaLinguistica() {
        return familiaLinguistica;
    }

    public void setFamiliaLinguistica(String familiaLinguistica) {
        this.familiaLinguistica = familiaLinguistica;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public int getQtdPaisesLinguaOficial() {
        return qtdPaisesLinguaOficial;
    }

    public void setQtdPaisesLinguaOficial(int qtdPaisesLinguaOficial) {
        this.qtdPaisesLinguaOficial = qtdPaisesLinguaOficial;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public Date getDataOrigem() {
        return dataOrigem;
    }

    public void setDataOrigem(Date dataOrigem) {
        this.dataOrigem = dataOrigem;
    }
    
}