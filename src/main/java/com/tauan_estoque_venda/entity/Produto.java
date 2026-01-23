package com.tauan_estoque_venda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
@Entity
public class Produto extends BaseEntity{
    @Column(name = "nome")
    private String nome;
    @Column(name = "valor_atual")
    private BigDecimal valorAtual;

    public Produto() {
    }

    public Produto(String nome, BigDecimal valorAtual) {
        this.nome = nome;
        this.valorAtual = valorAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }
}
