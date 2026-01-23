package com.tauan_estoque_venda.controller;


import com.tauan_estoque_venda.entity.Usuario;
import com.tauan_estoque_venda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
