package com.gabriel.loja.entities.dtos;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoFornecedorDTO {

    private Long id;
    private String nome;
    private String estado;
    private String endereco;
}
