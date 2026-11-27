package br.ufpr.poo2;

import java.math.BigDecimal;

/**
 * Serviço externo que calcula o frete de uma entrega
 * (na aplicação real, uma API de transportadora acessada pela rede).
 */
public interface ServicoFrete {

    BigDecimal calcularFrete(String cep, BigDecimal pesoKg);
}
