package br.ufpr.poo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompraTest {

    private Compra compra;

    @BeforeEach
    void criarCompra() {
        compra = new Compra();
    }

    @Test
    @DisplayName("compra de R$ 80,00 não recebe desconto")
    void compraAbaixoDe100NaoTemDesconto() {
        BigDecimal desconto = compra.calcularDesconto(new BigDecimal("80.00"));
        assertEquals(new BigDecimal("0.00"), desconto);
    }

    @Test
    @DisplayName("compra de R$ 100,00 recebe 10% de desconto (limite)")
    void compraDe100TemDescontoDe10PorCento() {
        BigDecimal desconto = compra.calcularDesconto(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("10.00"), desconto);
    }

    @Test
    @DisplayName("compra de R$ 150,00 recebe 10% de desconto")
    void compraDe150TemDescontoDe10PorCento() {
        BigDecimal desconto = compra.calcularDesconto(new BigDecimal("150.00"));
        assertEquals(new BigDecimal("15.00"), desconto);
    }

    @Test
    @DisplayName("valor negativo é rejeitado com IllegalArgumentException")
    void valorNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> compra.calcularDesconto(new BigDecimal("-10.00")));
    }
}
