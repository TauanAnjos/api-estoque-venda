package com.tauan_estoque_venda.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Venda extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    public Venda() {
    }

    public Venda(Usuario usuario, BigDecimal valorTotal, LocalDateTime dataVenda) {
        this.usuario = usuario;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }
    @PrePersist
    public void prePersist() {
        this.dataVenda = LocalDateTime.now();
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}
