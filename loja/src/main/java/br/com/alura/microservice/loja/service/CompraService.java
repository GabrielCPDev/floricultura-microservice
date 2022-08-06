package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.dto.*;
import br.com.alura.microservice.loja.model.CompraState;
import br.com.alura.microservice.loja.repositories.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.model.Compra;

import java.time.LocalDate;
import java.util.Optional;

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

		Compra compraParaSalvar = new Compra();
		InfoEntregaDTO entregaDto = new InfoEntregaDTO();

		compraParaSalvar.setState(CompraState.RECEBIDO);
		entregaDto.setEnderecoDestino(compra.getEndereco().toString());
		compraRepository.save(compraParaSalvar);
		compra.setCompraId(compraParaSalvar.getId());

		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);
		InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());
		compraParaSalvar.setState(CompraState.PEDIDO_REALIZADO);

		compraParaSalvar.setPedidoId(infoPedido.getId());
		compraParaSalvar.setTempoDePreparo(infoPedido.getTempoDePreparo());
		compraRepository.save(compraParaSalvar);

		entregaDto.setPedidoId(infoPedido.getId());
		entregaDto.setDataParaEntrega(LocalDate.now().plusDays(infoPedido.getTempoDePreparo()));
		entregaDto.setEnderecoOrigem(info.getEndereco());

		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);

		compraParaSalvar.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
		compraParaSalvar.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraParaSalvar.setVoucher(voucher.getNumero());
		compraParaSalvar.setEnderecoDestino(info.getEndereco());
		Compra compraSalva = compraRepository.save(compraParaSalvar);

		return compraSalva;
	}
	public Compra reprocessaCompra(Long id){
		return null;
	}
	public Compra realizaCompraFallback(CompraDTO compra) {
		if (compra.getCompraId() != null){
			return compraRepository.findById(compra.getCompraId()).get();
		}
		var compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().getEstado());
		return compraFallback;
	}

	@HystrixCommand(threadPoolKey = "realizaCompraThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}
}
