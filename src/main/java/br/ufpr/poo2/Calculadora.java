package br.ufpr.poo2;

public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    /**
     * Divide a por b.
     *
     * Em Java, a divisão de um double por zero não lança exceção:
     * retorna Infinity, -Infinity ou NaN. Para a calculadora, esses valores
     * esconderiam um erro de uso, por isso a divisão por zero é rejeitada
     * explicitamente com ArithmeticException.
     *
     * @throws ArithmeticException se b for zero (0.0 ou -0.0)
     */
    public double dividir(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return a / b;
    }
}
