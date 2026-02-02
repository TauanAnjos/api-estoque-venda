package com.tauan_estoque_venda.service;

import com.tauan_estoque_venda.dtos.ItemPedidoDTO;
import com.tauan_estoque_venda.dtos.PedidoVendaDTO;
import com.tauan_estoque_venda.entity.*;
import com.tauan_estoque_venda.exception.UserNotFoundException;
import com.tauan_estoque_venda.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class VendaService {
    private VendaRepository vendaRepository;
    private UsuarioRepository usuarioRepository;

    private ItemVendaRepository itemVendaRepository;
    private EstoqueService estoqueService;

    public VendaService(VendaRepository vendaRepository, UsuarioRepository usuarioRepository, ItemVendaRepository itemVendaRepository, EstoqueService estoqueService) {
        this.vendaRepository = vendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.estoqueService = estoqueService;
    }
    @Transactional
    public void realizarVenda(PedidoVendaDTO pedidoVendaDTO){


        Usuario usuario = usuarioRepository.findById(pedidoVendaDTO.usuarioId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Venda venda = new Venda(usuario, BigDecimal.ZERO, null);
        vendaRepository.save(venda);

        for (ItemPedidoDTO item : pedidoVendaDTO.itens()) {

            Produto produto = estoqueService.atualizarEstoque(item.produtoId(), item.quantidade());
            BigDecimal valorTotal = produto.getValorAtual().multiply(BigDecimal.valueOf(item.quantidade()));
            ItemVenda itemVenda = new ItemVenda(venda,produto, item.quantidade(), valorTotal);
            itemVendaRepository.save(itemVenda);
            venda.setValorTotal(venda.getValorTotal().add(valorTotal));
        }
        vendaRepository.save(venda);
    }
}
