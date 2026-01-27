package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.Venda;
import com.tauan_estoque_venda.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    public ResponseEntity<Void> cadastrarVenda(@RequestBody Venda venda){
        vendaRepository.save(venda);
        return ResponseEntity.ok().build();
    }
}
