package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.Estoque;
import com.tauan_estoque_venda.repository.EstoqueRepository;
import com.tauan_estoque_venda.repository.projection.EstoqueView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping()
    public ResponseEntity<List<EstoqueView>> listarEstoque(){
        return ResponseEntity.status(HttpStatus.OK).body(estoqueRepository.listarEstoque());
    }
}
