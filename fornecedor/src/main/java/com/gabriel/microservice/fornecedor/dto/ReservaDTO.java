package com.gabriel.microservice.fornecedor.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDTO {

    public Integer idReserva;
    public Integer tempoDePreparo;
}
