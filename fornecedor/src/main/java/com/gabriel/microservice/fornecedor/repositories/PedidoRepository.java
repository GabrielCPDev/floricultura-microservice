package com.gabriel.microservice.fornecedor.repositories;

import com.gabriel.microservice.fornecedor.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
