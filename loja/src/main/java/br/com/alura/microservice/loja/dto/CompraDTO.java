package br.com.alura.microservice.loja.dto;

import br.com.alura.microservice.loja.model.CompraState;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CompraDTO {
	@JsonIgnore
	private Long compraId;
	private List<ItemDaCompraDTO> itens;
	private EnderecoDTO endereco;
	private CompraState state;
	public Long getCompraId() {return compraId;}
	public void setCompraId(Long compraId) {this.compraId = compraId;}
	public List<ItemDaCompraDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemDaCompraDTO> itens) {
		this.itens = itens;
	}
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	public CompraState getState() {return state;}
	public void setState(CompraState state) {this.state = state;}
}
