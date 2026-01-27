package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.entity.ItemVenda;
import com.tauan_estoque_venda.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemVenda")
public class ItemVendaController {
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @PostMapping
    public ResponseEntity<Void> cadastrarItemVenda(@RequestBody ItemVenda item){
        itemVendaRepository.save(item);
        return ResponseEntity.ok().build();
    }
}
