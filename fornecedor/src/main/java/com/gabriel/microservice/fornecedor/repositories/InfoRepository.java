package com.gabriel.microservice.fornecedor.repositories;

import com.gabriel.microservice.fornecedor.entities.InfoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<InfoFornecedor, Long> {
    InfoFornecedor findByEstado(String estado);
}
