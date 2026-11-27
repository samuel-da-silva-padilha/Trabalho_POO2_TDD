package br.ufpr.poo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PedidoTest {

    private static final String CEP = "85950-000";
    private static final BigDecimal PESO = new BigDecimal("2.0");

    // ---------- Stub: apenas devolve uma resposta pronta ----------

    @Test
    @DisplayName("stub: total = produtos + frete devolvido pelo serviço")
    void totalComFreteDoStub() {
        ServicoFrete stub = (cep, peso) -> new BigDecimal("25.00");
        Pedido pedido = new Pedido(CEP, new BigDecimal("100.00"), PESO, stub);

        assertEquals(new BigDecimal("125.00"), pedido.calcularTotal());
    }

    // ---------- Mock: registra as chamadas para verificar a interação ----------

    /** Mock escrito à mão: devolve um valor fixo e guarda como foi chamado. */
    static class ServicoFreteMock implements ServicoFrete {
        int chamadas = 0;
        String cepRecebido;
        BigDecimal pesoRecebido;

        @Override
        public BigDecimal calcularFrete(String cep, BigDecimal pesoKg) {
            chamadas++;
            cepRecebido = cep;
            pesoRecebido = pesoKg;
            return new BigDecimal("25.00");
        }
    }

    @Test
    @DisplayName("mock: consulta o serviço uma vez, com o CEP e o peso do pedido")
    void consultaServicoComDadosDoPedido() {
        ServicoFreteMock mock = new ServicoFreteMock();
        Pedido pedido = new Pedido(CEP, new BigDecimal("100.00"), PESO, mock);

        pedido.calcularTotal();

        assertEquals(1, mock.chamadas);
        assertEquals(CEP, mock.cepRecebido);
        assertEquals(PESO, mock.pesoRecebido);
    }

    @Test
    @DisplayName("mock: pedido com frete grátis não consulta o serviço")
    void freteGratisNaoConsultaServico() {
        ServicoFreteMock mock = new ServicoFreteMock();
        Pedido pedido = new Pedido(CEP, new BigDecimal("350.00"), PESO, mock);

        assertEquals(new BigDecimal("350.00"), pedido.calcularTotal());
        assertEquals(0, mock.chamadas);
    }

    // ---------- Valores-limite da regra de frete grátis ----------

    @Test
    @DisplayName("limite: pedido de exatamente R$ 300,00 tem frete grátis")
    void pedidoDe300TemFreteGratis() {
        ServicoFreteMock mock = new ServicoFreteMock();
        Pedido pedido = new Pedido(CEP, new BigDecimal("300.00"), PESO, mock);

        assertEquals(new BigDecimal("300.00"), pedido.calcularTotal());
        assertEquals(0, mock.chamadas);
    }

    @Test
    @DisplayName("limite: pedido de R$ 299,99 paga o frete do serviço")
    void pedidoDe299e99PagaFrete() {
        ServicoFreteMock mock = new ServicoFreteMock();
        Pedido pedido = new Pedido(CEP, new BigDecimal("299.99"), PESO, mock);

        assertEquals(new BigDecimal("324.99"), pedido.calcularTotal());
        assertEquals(1, mock.chamadas);
    }
}
