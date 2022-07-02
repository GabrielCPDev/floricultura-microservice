package com.gabriel.microservice.fornecedor.services;

import com.gabriel.microservice.fornecedor.dto.ItemPedidoDTO;
import com.gabriel.microservice.fornecedor.entities.Pedido;
import com.gabriel.microservice.fornecedor.entities.PedidoItem;
import com.gabriel.microservice.fornecedor.entities.Produto;
import com.gabriel.microservice.fornecedor.repositories.PedidoRepository;
import com.gabriel.microservice.fornecedor.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido realizaPedido(List<ItemPedidoDTO> itens){
        if (itens.isEmpty() || itens.equals(null)){
            return null;
        }
        List<PedidoItem> pedidoItems = toPedidoItem(itens);
        return pedidoRepository.save(new Pedido(pedidoItems));
    }
    public Pedido getPedidoPorId(Long id){
        return pedidoRepository.findById(id).orElse(new Pedido());
    }

    private List<PedidoItem> toPedidoItem(List<ItemPedidoDTO> itens) {
        List<Long> idsProdutos = itens
                .stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());

        List<Produto> produtosDoPedido = produtoRepository.findAllById(idsProdutos);
        var produtoItems =  itens.stream().map( item -> {
            var produto = produtosDoPedido
                    .stream()
                    .filter(p -> p.getId().equals(item.getId()))
                    .findFirst().get();
            var pedidoItem = PedidoItem.builder().produto(produto).quantidade(item.getQuantidade());
            return pedidoItem;
        }).collect(Collectors.toList());
        return Collections.singletonList((PedidoItem) produtoItems);
    }
}
