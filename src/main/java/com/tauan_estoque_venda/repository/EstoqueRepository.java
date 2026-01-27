package com.tauan_estoque_venda.repository;

import com.tauan_estoque_venda.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
}
