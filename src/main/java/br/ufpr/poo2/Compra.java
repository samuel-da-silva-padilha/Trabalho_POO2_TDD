package br.ufpr.poo2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Compra {

    public BigDecimal calcularDesconto(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor negativo");
        }
        if (valor.compareTo(new BigDecimal("100.00")) >= 0) {
            BigDecimal desconto = valor.multiply(new BigDecimal("0.10"));
            return desconto.setScale(2, RoundingMode.HALF_EVEN);
        }
        return new BigDecimal("0.00");
    }
}
