package com.tauan_estoque_venda.repository;

import com.tauan_estoque_venda.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
