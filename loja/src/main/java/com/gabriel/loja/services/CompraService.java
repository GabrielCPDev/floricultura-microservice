package com.gabriel.loja.services;

import com.gabriel.loja.LojaApplication;
import com.gabriel.loja.client.FornecedorClient;
import com.gabriel.loja.entities.dtos.CompraDTO;
import com.gabriel.loja.entities.dtos.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

    @Autowired
    private FornecedorClient fornecedorClient;

    public void realizaCompra(CompraDTO compraDTO){
        InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
        System.out.println(infoPorEstado.getEndereco());
    }
}
