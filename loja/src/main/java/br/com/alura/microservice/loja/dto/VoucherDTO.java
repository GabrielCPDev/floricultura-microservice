package br.com.alura.microservice.loja.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class VoucherDTO implements Serializable {
    private Long numero;
    private LocalDate previsaoParaEntrega;
    public VoucherDTO(){}

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getPrevisaoParaEntrega() {
        return previsaoParaEntrega;
    }

    public void setPrevisaoParaEntrega(LocalDate previsaoParaEntrega) {
        this.previsaoParaEntrega = previsaoParaEntrega;
    }
}
