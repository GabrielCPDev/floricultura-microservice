package br.com.alura.microservice.loja.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Compra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long pedidoId;
	private Integer tempoDePreparo;
	private String enderecoDestino;
	private LocalDate dataParaEntrega;
	private Long voucher;
	@Enumerated(EnumType.STRING)
	private CompraState state;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Integer getTempoDePreparo() {
		return tempoDePreparo;
	}
	public void setTempoDePreparo(Integer tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}
	public String getEnderecoDestino() {
		return enderecoDestino;
	}

	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}
	public LocalDate getDataParaEntrega() { return dataParaEntrega;}
	public void setDataParaEntrega(LocalDate previsaoParaEntrega) {this.dataParaEntrega = previsaoParaEntrega;}
	public Long getVoucher(){return voucher;}
	public void setVoucher(Long numero) { this.voucher = numero; }
	public CompraState getState() { return state; }
	public void setState(CompraState state) { this.state = state; }
}
