package com.tauan_estoque_venda.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ItemVenda extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(name = "preco_venda", nullable = false)
    private BigDecimal precoVenda;

    public ItemVenda() {
    }

    public ItemVenda(Venda venda, Produto produto, Integer quantidade, BigDecimal precoVenda) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }
}
