package com.tauan_estoque_venda.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Estoque extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    @Column(name = "tipo_movimentacao")
    private String tipoMovimentacao;

    public Estoque() {
    }

    public Estoque(Produto produto, Integer quantidade, LocalDateTime dataHora, String tipoMovimentacao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.tipoMovimentacao = tipoMovimentacao;
    }
    @PrePersist
    public void prePersiste(){
        this.dataHora = LocalDateTime.now();
    }
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
