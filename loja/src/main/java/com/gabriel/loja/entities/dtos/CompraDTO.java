package com.gabriel.loja.entities.dtos;

import com.gabriel.loja.entities.dtos.ItemCompraDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {

    private Long id;
    private List<ItemCompraDTO> itens = new ArrayList<>();
    private EnderecoDTO endereco;

}
