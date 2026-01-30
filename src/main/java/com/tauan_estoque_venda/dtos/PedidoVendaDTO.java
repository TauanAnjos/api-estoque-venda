package com.tauan_estoque_venda.dtos;

import java.util.List;

public record PedidoVendaDTO(Integer usuarioId, List<ItemPedidoDTO> itens) {
}
