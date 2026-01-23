package com.tauan_estoque_venda.repository;

import com.tauan_estoque_venda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
