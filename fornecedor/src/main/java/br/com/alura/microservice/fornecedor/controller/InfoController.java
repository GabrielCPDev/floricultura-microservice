package br.com.alura.microservice.fornecedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.service.InfoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/info")
public class InfoController {
	
	@Autowired
	private InfoService infoService;

	@PostMapping
	public ResponseEntity<InfoFornecedor> salvaInfoFornecedor(@RequestBody InfoFornecedor fornecedor){
		InfoFornecedor infoFornecedorSalvo = infoService.salvaFornecedor(fornecedor);
		var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}").buildAndExpand(infoFornecedorSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(infoFornecedorSalvo);
	}
	
	@RequestMapping("/{estado}")
	public InfoFornecedor getInfoPorEstado(@PathVariable String estado) {
		return infoService.getInfoPorEstado(estado);
	}
}
