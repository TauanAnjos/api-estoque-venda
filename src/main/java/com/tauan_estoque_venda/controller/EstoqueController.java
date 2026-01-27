package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.Estoque;
import com.tauan_estoque_venda.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping
    public ResponseEntity<Void> cadastrarProdutoEstoque(@RequestBody Estoque estoque){
        estoqueRepository.save(estoque);
        return ResponseEntity.ok().build();
    }
}
