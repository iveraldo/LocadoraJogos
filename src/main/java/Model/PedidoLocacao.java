package Model;

import Util.Utilitario.EnumFormaPagamento;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PedidoLocacao {
    private Long id;
    private List<Jogo> jogos;
    private Date dataPedido;
    private double valorLocacao;
    private EnumFormaPagamento formaPagamento;
    private Cliente cliente;
    private Vendedor vendedor;
    private Date dataDevolucao;
    private String obs;
    private String cupom;
    private boolean devolvido;
    
    public PedidoLocacao(){
        this.jogos = new ArrayList<>();
    }
    
    public PedidoLocacao(Long id){
        this();
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void adicionarJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }
    
    public void removerAudio(Jogo jogo){
        this.jogos.remove(jogo);
    }
    
    public void limparJogos(){
        this.jogos.removeAll(jogos);
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public EnumFormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(EnumFormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean isDevolvido) {
        this.devolvido = isDevolvido;
    }
    
}