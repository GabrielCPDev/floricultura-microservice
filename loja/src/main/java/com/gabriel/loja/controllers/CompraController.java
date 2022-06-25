package com.gabriel.loja.controllers;

import com.gabriel.loja.entities.dtos.CompraDTO;
import com.gabriel.loja.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;
    @PostMapping
    public void realizaCompra(@RequestBody CompraDTO compraDto){
        compraService.realizaCompra(compraDto);
    }
}
