package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.Produto;
import com.tauan_estoque_venda.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
