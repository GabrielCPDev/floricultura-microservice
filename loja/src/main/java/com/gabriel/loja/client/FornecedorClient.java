package com.gabriel.loja.client;

import com.gabriel.loja.entities.dtos.InfoFornecedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/fornecedores/info/{estado}")
 InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);
}
