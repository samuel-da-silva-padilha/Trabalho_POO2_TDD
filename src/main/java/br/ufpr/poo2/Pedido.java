package br.ufpr.poo2;

import java.math.BigDecimal;

public class Pedido {

    private static final BigDecimal VALOR_FRETE_GRATIS = new BigDecimal("300.00");

    private final String cep;
    private final BigDecimal valorProdutos;
    private final BigDecimal pesoKg;
    private final ServicoFrete servicoFrete;

    /**
     * O serviço de frete é recebido pelo construtor (injeção de dependência).
     * Assim, nos testes, ele pode ser trocado por um stub ou por um mock.
     */
    public Pedido(String cep, BigDecimal valorProdutos, BigDecimal pesoKg,
                  ServicoFrete servicoFrete) {
        this.cep = cep;
        this.valorProdutos = valorProdutos;
        this.pesoKg = pesoKg;
        this.servicoFrete = servicoFrete;
    }

    // Pedidos a partir de R$ 300,00 têm frete grátis e não consultam o serviço
    public BigDecimal calcularFrete() {
        if (valorProdutos.compareTo(VALOR_FRETE_GRATIS) >= 0) {
            return BigDecimal.ZERO;
        }
        return servicoFrete.calcularFrete(cep, pesoKg);
    }

    public BigDecimal calcularTotal() {
        return valorProdutos.add(calcularFrete());
    }
}
