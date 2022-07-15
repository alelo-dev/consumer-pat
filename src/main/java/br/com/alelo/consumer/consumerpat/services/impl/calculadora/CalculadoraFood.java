package br.com.alelo.consumer.consumerpat.services.impl.calculadora;

import java.math.BigDecimal;

public class CalculadoraFood implements Calculadora {

  @Override
  public BigDecimal calcular(BigDecimal valor) {
    return valor.subtract(valor.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(10)));
  }
}
