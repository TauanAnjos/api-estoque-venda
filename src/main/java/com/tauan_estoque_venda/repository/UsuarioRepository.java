package com.tauan_estoque_venda.repository;

import com.tauan_estoque_venda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<UserDetails> findByEmailUsuario(String username);
}
