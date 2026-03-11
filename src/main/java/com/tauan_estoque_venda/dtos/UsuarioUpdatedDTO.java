package com.tauan_estoque_venda.dtos;

public record UsuarioUpdatedDTO(String nome, String senhaAntiga, String novaSenha, Integer permissao_id) {
}
