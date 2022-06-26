package com.gabriel.microservice.fornecedor.controllers;

import com.gabriel.microservice.fornecedor.entities.InfoFornecedor;
import com.gabriel.microservice.fornecedor.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/info/{estado}")
    public ResponseEntity<InfoFornecedor> getInfoPorEstado(@PathVariable String estado){
        var infoPorEstado = infoService.getInfoPorEstado(estado);
        return infoPorEstado.isPresent() ?
                ResponseEntity.ok().body(infoPorEstado.get()) : ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<InfoFornecedor> salvaInfoFornecedor(@RequestBody InfoFornecedor fornecedor){
        InfoFornecedor infoFornecedorSalvo = infoService.salvaFornecedor(fornecedor);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(infoFornecedorSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(infoFornecedorSalvo);
    }
}
