package com.tauan_estoque_venda.service;

import com.tauan_estoque_venda.dtos.ItemPedidoDTO;
import com.tauan_estoque_venda.dtos.PedidoVendaDTO;
import com.tauan_estoque_venda.entity.*;
import com.tauan_estoque_venda.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class VendaService {
    private VendaRepository vendaRepository;
    private UsuarioRepository usuarioRepository;
    private EstoqueRepository estoqueRepository;
    private ProdutoRepository produtoRepository;
    private ItemVendaRepository itemVendaRepository;

    public VendaService(VendaRepository vendaRepository, UsuarioRepository usuarioRepository, EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, ItemVendaRepository itemVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.itemVendaRepository = itemVendaRepository;
    }
    @Transactional
    public void realizarVenda(PedidoVendaDTO pedidoVendaDTO){
        try {

            Usuario usuario = usuarioRepository.findById(pedidoVendaDTO.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            Venda venda = new Venda(usuario, BigDecimal.ZERO, null);
            vendaRepository.save(venda);

            for (ItemPedidoDTO item : pedidoVendaDTO.itens()) {
                Estoque estoque = estoqueRepository.findById(item.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
                if (estoque.getQuantidade() < item.quantidade()){
                    throw new RuntimeException("Quantidade insuficiente no estoque.");
                }
                int quantidadeSubtraida = estoque.getQuantidade() - item.quantidade();
                estoque.setQuantidade(quantidadeSubtraida);
                estoqueRepository.save(estoque);

                Produto produto = estoque.getProduto();
                BigDecimal valorTotal = produto.getValorAtual().multiply(BigDecimal.valueOf(item.quantidade()));
                ItemVenda itemVenda = new ItemVenda(venda,produto, item.quantidade(), valorTotal);
                itemVendaRepository.save(itemVenda);
                venda.setValorTotal(venda.getValorTotal().add(valorTotal));
            }
            vendaRepository.save(venda);

        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
