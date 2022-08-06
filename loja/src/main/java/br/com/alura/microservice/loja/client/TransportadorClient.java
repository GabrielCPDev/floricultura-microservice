package br.com.alura.microservice.loja.client;

import br.com.alura.microservice.loja.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("transportador")
public interface TransportadorClient {


	@PostMapping("/entrega")
	VoucherDTO reservaEntrega(InfoEntregaDTO entregaDto);
}
