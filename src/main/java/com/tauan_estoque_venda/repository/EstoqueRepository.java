package com.tauan_estoque_venda.repository;

import com.tauan_estoque_venda.entity.Estoque;
import com.tauan_estoque_venda.repository.projection.EstoqueView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    Optional<Estoque> findByProdutoId(Integer produtoId);

    @Query(value="""
            SELECT e.id as id, p.nome as nome, e.quantidade as quantidade
            FROM estoque e
            JOIN produto p ON p.id = e.produto_id
            """, nativeQuery = true)
    List<EstoqueView> listarEstoque();
}
