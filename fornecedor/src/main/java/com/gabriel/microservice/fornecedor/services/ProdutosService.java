package com.gabriel.microservice.fornecedor.services;

import com.gabriel.microservice.fornecedor.entities.Produto;
import com.gabriel.microservice.fornecedor.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getProdutosPorEstado(String estado) {
        return produtoRepository.findByEstado(estado);
    }
}
