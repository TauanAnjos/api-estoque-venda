package com.tauan_estoque_venda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario extends BaseEntity {
    @Column(name = "nome")
    private String nome;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "senha")
    private String senha;
    @ManyToOne
    @JoinColumn(name = "permissao_id")
    private Permissao permissao;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, Permissao permissao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
