package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.repositories.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;

@Service
public class CompraService {
	
	@Autowired
	private FornecedorClient fornecedorClient;
	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "getByIdThredPool")
	public Compra realizaCompra(CompraDTO compra) {

		final String estado = compra.getEndereco().getEstado();

		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);

		InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());

		Compra compraParaSalvar = new Compra();
		compraParaSalvar.setPedidoId(infoPedido.getId());
		compraParaSalvar.setTempoDePreparo(infoPedido.getTempoDePreparo());
		compraParaSalvar.setEnderecoDestino(info.getEndereco());

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
