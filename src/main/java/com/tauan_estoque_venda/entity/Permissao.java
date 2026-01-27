package com.tauan_estoque_venda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Permissao extends BaseEntity{
    @Column(name = "nome")
    private String nome;

    public Permissao() {
    }

    public Permissao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
