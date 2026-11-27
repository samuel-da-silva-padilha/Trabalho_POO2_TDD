package br.ufpr.poo2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Compra {

    private static final BigDecimal VALOR_MINIMO = new BigDecimal("100.00");
    private static final BigDecimal PERCENTUAL_DESCONTO = new BigDecimal("0.10");
    private static final BigDecimal SEM_DESCONTO = new BigDecimal("0.00");

    /**
     * Calcula o desconto de uma compra.
     * Compras a partir de R$ 100,00 recebem 10% de desconto;
     * compras abaixo desse valor não recebem desconto.
     *
     * @param valor valor total da compra
     * @return valor do desconto, com duas casas decimais
     * @throws IllegalArgumentException se o valor for nulo ou negativo
     */
    public BigDecimal calcularDesconto(BigDecimal valor) {
        validarValor(valor);
        if (temDireitoAoDesconto(valor)) {
            return arredondar(valor.multiply(PERCENTUAL_DESCONTO));
        }
        return SEM_DESCONTO;
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor da compra é obrigatório");
        }
        if (valor.signum() < 0) {
            throw new IllegalArgumentException(
                    "O valor da compra não pode ser negativo: " + valor);
        }
    }

    private boolean temDireitoAoDesconto(BigDecimal valor) {
        return valor.compareTo(VALOR_MINIMO) >= 0;
    }

    private BigDecimal arredondar(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_EVEN);
    }
}
