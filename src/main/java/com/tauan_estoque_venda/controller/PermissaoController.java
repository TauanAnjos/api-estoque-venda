package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.Permissao;
import com.tauan_estoque_venda.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {
    @Autowired
    private PermissaoRepository permissaoRepository;

    @PostMapping
    public ResponseEntity<Void> cadastrarPermissao(@RequestBody Permissao permissao){
        permissaoRepository.save(permissao);
        return ResponseEntity.ok().build();
    }
}
