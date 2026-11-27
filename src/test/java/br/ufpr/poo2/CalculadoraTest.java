package br.ufpr.poo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    // Tolerância usada nas comparações de valores double
    private static final double DELTA = 1e-9;

    private Calculadora calculadora;

    @BeforeEach
    void criarCalculadora() {
        calculadora = new Calculadora();
    }

    // ---------- somar() ----------

    @Test
    @DisplayName("somar: dois valores positivos")
    void somarValoresPositivos() {
        assertEquals(6.0, calculadora.somar(2.5, 3.5), DELTA);
    }

    @Test
    @DisplayName("somar: dois valores negativos")
    void somarValoresNegativos() {
        assertEquals(-10.0, calculadora.somar(-4.0, -6.0), DELTA);
    }

    @Test
    @DisplayName("somar: valor positivo com valor negativo")
    void somarPositivoComNegativo() {
        assertEquals(7.0, calculadora.somar(10.0, -3.0), DELTA);
    }

    @Test
    @DisplayName("somar: zero é o elemento neutro")
    void somarComZero() {
        assertEquals(8.0, calculadora.somar(8.0, 0.0), DELTA);
        assertEquals(0.0, calculadora.somar(0.0, 0.0), DELTA);
    }

    @Test
    @DisplayName("somar: valores decimais exigem tolerância (0.1 + 0.2)")
    void somarDecimaisComTolerancia() {
        // 0.1 + 0.2 resulta em 0.30000000000000004 em double
        assertEquals(0.3, calculadora.somar(0.1, 0.2), DELTA);
    }

    // ---------- dividir() ----------

    @Test
    @DisplayName("dividir: divisão exata")
    void dividirExata() {
        assertEquals(5.0, calculadora.dividir(10.0, 2.0), DELTA);
    }

    @Test
    @DisplayName("dividir: resultado decimal")
    void dividirResultadoDecimal() {
        assertEquals(2.5, calculadora.dividir(10.0, 4.0), DELTA);
        assertEquals(0.3333333333, calculadora.dividir(1.0, 3.0), DELTA);
    }

    @Test
    @DisplayName("dividir: dividendo negativo")
    void dividirDividendoNegativo() {
        assertEquals(-3.0, calculadora.dividir(-9.0, 3.0), DELTA);
    }

    @Test
    @DisplayName("dividir: zero dividido por um número")
    void dividirZeroPorNumero() {
        assertEquals(0.0, calculadora.dividir(0.0, 5.0), DELTA);
    }

    @Test
    @DisplayName("dividir: divisor zero lança ArithmeticException")
    void dividirPorZeroLancaExcecao() {
        ArithmeticException excecao = assertThrows(ArithmeticException.class,
                () -> calculadora.dividir(5.0, 0.0));
        assertEquals("Divisão por zero não é permitida", excecao.getMessage());
    }

    @Test
    @DisplayName("dividir: zero dividido por zero lança ArithmeticException")
    void dividirZeroPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class,
                () -> calculadora.dividir(0.0, 0.0));
    }
}
