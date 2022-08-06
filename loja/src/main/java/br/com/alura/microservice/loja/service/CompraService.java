package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.dto.*;
import br.com.alura.microservice.loja.repositories.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.model.Compra;

import java.time.LocalDate;

@Service
public class CompraService {
	
	@Autowired
	private FornecedorClient fornecedorClient;
	@Autowired
	private TransportadorClient transportadorClient;
	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "getByIdThredPool")
	public Compra realizaCompra(CompraDTO compra) {

		final String estado = compra.getEndereco().getEstado();

		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);

		InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());
		InfoEntregaDTO entregaDto = new InfoEntregaDTO();
		entregaDto.setPedidoId(infoPedido.getId());
		entregaDto.setDataParaEntrega(LocalDate.now().plusDays(infoPedido.getTempoDePreparo()));
		entregaDto.setEnderecoOrigem(info.getEndereco());
		entregaDto.setEnderecoDestino(compra.getEndereco().toString());
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);

		Compra compraParaSalvar = new Compra();
		compraParaSalvar.setPedidoId(infoPedido.getId());
		compraParaSalvar.setTempoDePreparo(infoPedido.getTempoDePreparo());
		compraParaSalvar.setEnderecoDestino(info.getEndereco());
		compraParaSalvar.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraParaSalvar.setVoucher(voucher.getNumero());

		Compra compraSalva = compraRepository.save(compraParaSalvar);

		return compraSalva;
	}
	public Compra realizaCompraFallback(CompraDTO compra) {
		var compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().getEstado());
		return compraFallback;
	}

	@HystrixCommand(threadPoolKey = "realizaCompraThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}
}
