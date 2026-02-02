package com.tauan_estoque_venda.service;

import com.tauan_estoque_venda.entity.Estoque;
import com.tauan_estoque_venda.entity.Produto;
import com.tauan_estoque_venda.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueService {
    private EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }
    @Transactional
    public Produto atualizarEstoque(Integer produtoId, int quantidade){
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId).orElseThrow(() -> new RuntimeException("Produto de ID: "+ produtoId + " não encontrado."));
        if(estoque.getQuantidade() < quantidade){
            throw new RuntimeException("Quantidade insuficiente no estoque.");
        }
        int subtrairEstoque = estoque.getQuantidade() - quantidade;
        estoque.setQuantidade(subtrairEstoque);
        estoqueRepository.save(estoque);
        return estoque.getProduto();
    }
}
