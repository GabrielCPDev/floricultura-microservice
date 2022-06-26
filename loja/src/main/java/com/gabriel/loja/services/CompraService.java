package com.gabriel.loja.services;

import com.gabriel.loja.LojaApplication;
import com.gabriel.loja.entities.dtos.CompraDTO;
import com.gabriel.loja.entities.dtos.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {
    @Autowired
    private RestTemplate restTemplate;
    public void realizaCompra(CompraDTO compraDto) {

//        RestTemplate client = new RestTemplate();
        ResponseEntity<InfoFornecedorDTO> exchange = restTemplate.exchange("http://fornecedor/fornecedores/info/" + compraDto.getEndereco().getEstado()
                , HttpMethod.GET
                , null
                , InfoFornecedorDTO.class);

        System.out.println(exchange.getBody().getEndereco());
    }
}
