package Model;

import java.sql.Date;

public class Plataforma extends Marca{
    private String descricao;
    private Date dataCriacao;
    private String fabricante;
    private long unidadesVendidas;
    private int numeroGeracao;
    private String midia;
    private double precoLancamento;
    private String sistemaOperacional;
    
    public Plataforma(){};
    
    public Plataforma(Long id){
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public long getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(long unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public int getNumeroGeracao() {
        return numeroGeracao;
    }

    public void setNumeroGeracao(int numeroGeracao) {
        this.numeroGeracao = numeroGeracao;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    public double getPrecoLancamento() {
        return precoLancamento;
    }

    public void setPrecoLancamento(double precoLancamento) {
        this.precoLancamento = precoLancamento;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
    
}