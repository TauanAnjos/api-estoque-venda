package com.tauan_estoque_venda.dtos;

import com.tauan_estoque_venda.entity.Permissao;

public record UsuarioRequest(String nome, String email, String senha, Integer permissao_id) {
}
