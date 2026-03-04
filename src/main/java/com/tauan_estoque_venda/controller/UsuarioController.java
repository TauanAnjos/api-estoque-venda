package com.tauan_estoque_venda.controller;


import com.tauan_estoque_venda.dtos.UsuarioRequest;
import com.tauan_estoque_venda.dtos.UsuarioResponse;
import com.tauan_estoque_venda.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(request));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }
}
