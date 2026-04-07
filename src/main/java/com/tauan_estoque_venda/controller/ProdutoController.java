package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.Produto;
import com.tauan_estoque_venda.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Void> cadastrarProduto(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarPordutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }
}
