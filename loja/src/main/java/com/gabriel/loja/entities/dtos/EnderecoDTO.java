package com.gabriel.loja.entities.dtos;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String rua;
    private Integer numero;
    private String estado;

}
