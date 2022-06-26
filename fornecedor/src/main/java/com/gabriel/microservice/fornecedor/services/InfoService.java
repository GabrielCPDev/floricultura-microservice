package com.gabriel.microservice.fornecedor.services;

import com.gabriel.microservice.fornecedor.entities.InfoFornecedor;
import com.gabriel.microservice.fornecedor.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoService {
    @Autowired
    private InfoRepository infoRepository;
    public Optional<InfoFornecedor> getInfoPorEstado(String estado) {
        var infoFornecedor = infoRepository.findByEstado(estado);
        return Optional.ofNullable(infoFornecedor);
    }

    public InfoFornecedor salvaFornecedor(InfoFornecedor fornecedor) {
        var fornecedorSalvo = infoRepository.save(fornecedor);
        return fornecedorSalvo;
    }
}
