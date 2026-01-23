package com.tauan_estoque_venda.repository;

import com.tauan_estoque_venda.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
