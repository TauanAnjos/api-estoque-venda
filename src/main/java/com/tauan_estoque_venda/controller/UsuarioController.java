package com.tauan_estoque_venda.controller;


import com.tauan_estoque_venda.dtos.UsuarioRequest;
import com.tauan_estoque_venda.dtos.UsuarioResponse;
import com.tauan_estoque_venda.dtos.UsuarioUpdatedDTO;
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
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable("id")Integer userId){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioId(userId));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodosUsuariosAtivos(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuariosAtivos());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id")Integer userId){
        usuarioService.deletarUsuario(userId);
        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuarioPorId(@PathVariable("id")Integer userId,@RequestBody UsuarioUpdatedDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(userId, request));
    }
}
