package com.gabriel.microservice.fornecedor.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoDTO {

    private long id;
    private int quantidade;
}
